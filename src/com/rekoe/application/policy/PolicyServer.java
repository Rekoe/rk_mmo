package com.rekoe.application.policy;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.timeout.ReadTimeoutException;
import org.jboss.netty.handler.timeout.ReadTimeoutHandler;
import org.jboss.netty.util.CharsetUtil;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;
import org.nutz.lang.Files;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.rekoe.codec.FlashPolicyServerDecoder;
import com.rekoe.mvc.GameContext;
import com.rekoe.mvc.IServer;
import com.rekoe.mvc.config.GameConfig;
/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */

public class PolicyServer extends SimpleChannelUpstreamHandler  implements IServer {

	private Log log = Logs.get();
	private final Timer timer = new HashedWheelTimer();
	private static final String FLASH_POLICY_FILE = "sample_flash_policy.xml";
	private final StringBuffer FLASH_POLICY_STR = new StringBuffer();
	private final static int PORT = 843;
	private static final String NEWLINE = "\r\n";
	@Override
	public void connect(GameConfig config) {
		GameContext context = config.getGameContext();
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),Executors.newCachedThreadPool()));
		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("timeout", new ReadTimeoutHandler(timer, 30));
		        pipeline.addLast("decoder", new FlashPolicyServerDecoder());
		        pipeline.addLast("handler", PolicyServer.this);
				return pipeline;
			}
		});
		bootstrap.bind(new InetSocketAddress(PORT));
		context.setAttribute("BOOT_STRAP_POLICY", bootstrap);
		if (log.isInfoEnabled())
			log.infof("Flash Server Start application Monitor at  Port %s" ,PORT);
	}
	@Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        ChannelFuture f = e.getChannel().write(this.getPolicyFileContents());
        f.addListener(ChannelFutureListener.CLOSE);
    }

    private ChannelBuffer getPolicyFileContents() throws Exception {
        return ChannelBuffers.copiedBuffer(FLASH_POLICY_STR.toString()+ NEWLINE,CharsetUtil.UTF_8);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
            throws Exception {
        if (e.getCause() instanceof ReadTimeoutException) {
            log.info("Connection timed out.");
            e.getChannel().close();
        } else {
            e.getCause().printStackTrace();
            e.getChannel().close();
        }
    }
	@Override
	public void initMessageExecutor() {
		FLASH_POLICY_STR.append(Files.read(FLASH_POLICY_FILE));
	}
	@Override
	public void stopServer() throws Exception {
		
	}

}
