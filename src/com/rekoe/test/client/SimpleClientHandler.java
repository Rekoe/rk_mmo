package com.rekoe.test.client;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.rekoe.msg.BaseIoMessage;

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
		//CSLoginMessage loginMessage = new CSLoginMessage("iwan", "?name=rekoe", (byte)1);
		//e.getChannel().write(loginMessage);
		//需要延时 等候10秒
		//Thread.sleep(3000);
		//模拟物品移动
		//CSItemMoveMessage move = new CSItemMoveMessage((short)ItemConstants.ITEM_CONTAINER_TYPE_BAG, (short)0, (short)0, (short)ItemConstants.ITEM_CONTAINER_TYPE_BODY, (short)0, (short)0);
		//e.getChannel().write(move);
	}
	
}
