package com.rekoe.test.client;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.rekoe.msg.BaseIoMessage;
import com.rekoe.msg.cs.CSLoginMessage;
/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class SimpleClientHandler extends SimpleChannelUpstreamHandler {

	private final static Log log = Logs.get();
	@Override
	public void messageReceived(ChannelHandlerContext ctx, org.jboss.netty.channel.MessageEvent e)
			throws Exception {
		Object message = e.getMessage();
		if(message == null || !(message instanceof BaseIoMessage)) {
			return;
		}
		//if(e.getMessage() instanceof LoginResult)
		{
			//LoginResult loginReslut = (LoginResult)e.getMessage();
			//log.info(loginReslut.getResult());
		}
		log.info(e.getMessage());
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		//模拟登录
		CSLoginMessage loginMessage = new CSLoginMessage("iwan", "?name=rekoe", (byte)1);
		e.getChannel().write(loginMessage);
	}
	
}
