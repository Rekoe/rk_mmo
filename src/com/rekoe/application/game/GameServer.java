package com.rekoe.application.game;

import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.rekoe.codec.GameMessageDecoder;
import com.rekoe.codec.GameMessageEncoder;
import com.rekoe.model.gameobjects.client.Client;
import com.rekoe.msg.BaseIoMessage;
import com.rekoe.msg.IMessage;
import com.rekoe.msg.MessageQueueExecutor;
import com.rekoe.msg.MessageType;
import com.rekoe.msg.MessageTypeState;
import com.rekoe.msg.SCMessageRecognizer;
import com.rekoe.msg.executor.LoginMessageExecutor;
import com.rekoe.mvc.GameContext;
import com.rekoe.mvc.IServer;
import com.rekoe.mvc.config.GameConfig;
/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
@SuppressWarnings({"rawtypes"})
public class GameServer extends SimpleChannelUpstreamHandler implements IServer{
	public static final String PLAYER_KEY = "PLAYER";
	private Log log = Logs.get();
	private MessageQueueExecutor queueExecutor;
	public Map<ChannelHandlerContext,ChannelHandlerContext> suspendedContexts = new ConcurrentHashMap<ChannelHandlerContext,ChannelHandlerContext>();
	public GameServer()
	{
		this.queueExecutor = new MessageQueueExecutor();
		startMonitorThread();
		//加载消息处理器
		Thread t = new Thread(queueExecutor, getClass().getName());
		t.start();
	}
	
	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelOpen(ctx, e);
		log.infof("Method %s ","channelOpen");
	}

	@Override
	public void channelBound(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelBound(ctx, e);
		log.infof("Method %s ","channelBound");
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		Client client = new Client(ctx.getChannel());
		client.setMessageState(MessageTypeState.CONNECTED);
		ctx.setAttachment(client);
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent message)
			throws Exception {
		if(message.getMessage() instanceof BaseIoMessage){
			BaseIoMessage<Client> _msg = extracted(message);
			Client client = (Client) ctx.getAttachment();
			extracted(_msg, client); 
			log.infof("Method messageReceived msg %s ",_msg);
			BlockingQueue<IMessage> queue = queueExecutor.getMessageQueue();
			queue.add(_msg);
			if(queueExecutor.isFull())
			{
				ctx.getChannel().setReadable(false);
				suspendedContexts.put(ctx,ctx);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private BaseIoMessage<Client> extracted(MessageEvent message) {
		return (BaseIoMessage<Client>)message.getMessage();
	}

	private void extracted(BaseIoMessage<Client> _msg, Client client) {
		_msg.setSender(client);
	}
	@Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        ctx.sendUpstream(e);
        log.info("Method channelClosed");
        Client client = (Client) ctx.getAttachment();
        client.channelClose();
    }
	@Override
	public void initMessageExecutor() {
		queueExecutor.registerMessageExecutor(MessageType.CS_LOGIN, new LoginMessageExecutor());
	}
	/**
	 * Socket异步发送接收通讯协议
	 */
	public void connect(GameConfig config){
		GameContext context = config.getGameContext();
		int port = Lang.str2number(context.getConfigValue("server.port")).intValue();
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),Executors.newCachedThreadPool()));
		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			//MemoryAwareThreadPoolExecutor executor = new OrderedMemoryAwareThreadPoolExecutor(threadNums, maxChannelMemorySize,
                   // maxTotalMemorySize, keepAliveTime,
                    //TimeUnit.SECONDS);
			//ExecutionHandler executionHandler = new ExecutionHandler(executor); 
			/** 解码器 */
			private GameMessageDecoder decoder = new GameMessageDecoder(new SCMessageRecognizer());
			/** 编码器 */
			private GameMessageEncoder encoder = new GameMessageEncoder();
			//http://www.blogjava.net/hankchen/archive/2012/04/08/373572.aspx
			//ExecutionHandler executionHandler = new ExecutionHandler(new MemoryAwareThreadPoolExecutor(16, 1048576, 1048576));
			public ChannelPipeline getPipeline() throws Exception {
//				ChannelPipeline pipeline = Channels.pipeline();
//				pipeline.addLast("decoder", decoder);
//				pipeline.addLast("encoder", encoder);
//				pipeline.addLast("executionHandler",executionHandler);
//				pipeline.addLast("handler", MainServerStart.this);
//				return pipeline;
				return Channels.pipeline(decoder, encoder/**,executionHandler**/,GameServer.this);
			}
		});
		bootstrap.bind(new InetSocketAddress(port));
		context.setAttribute("BOOT_STRAP", bootstrap);
		if (log.isInfoEnabled())
			log.infof("Game Server Start application Monitor at  Port %s" ,port);
	}
	
	private void startMonitorThread() {
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Iterator<ChannelHandlerContext> _iter = suspendedContexts.keySet().iterator();
				while(_iter.hasNext()) {
					ChannelHandlerContext _context = _iter.next();
					_iter.remove();
					_context.getChannel().setReadable(true);
				}
			}
		}, 0, 2, TimeUnit.SECONDS);
	}
}
