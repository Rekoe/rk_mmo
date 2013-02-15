package com.rekoe.test.client.login;import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.rekoe.codec.GameMessageDecoder;
import com.rekoe.codec.GameMessageEncoder;
import com.rekoe.msg.SCMessageRecognizer;
import com.rekoe.test.client.SimpleClientHandler;

/**
 * @author 科技㊣²º¹³
 * Feb 15, 2013 11:44:13 AM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class Login {


	public static void main(String[] args) {
		ClientBootstrap _bootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));		
		_bootstrap.setPipelineFactory(new SimpleClientPipelineFactory());
		ChannelFuture _future = _bootstrap.connect(new InetSocketAddress("localhost",8166));
		_future.getChannel().getCloseFuture().awaitUninterruptibly();
		_bootstrap.releaseExternalResources();
	}
	
	private static class SimpleClientPipelineFactory implements ChannelPipelineFactory {
		/** 解码器 */
		private GameMessageDecoder decoder = new GameMessageDecoder(new SCMessageRecognizer());
		/** 编码器 */
		private GameMessageEncoder encoder = new GameMessageEncoder();
		/** 消息处理器 */
		private SimpleClientHandler handler = new SimpleClientHandler();
		public ChannelPipeline getPipeline() throws Exception {
			ChannelPipeline pipeline = Channels.pipeline();
			pipeline.addLast("decoder", decoder);
			pipeline.addLast("encoder", encoder);
			pipeline.addLast("handler", handler);
			return pipeline;
		}
	}

}
