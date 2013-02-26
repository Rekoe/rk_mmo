package com.rekoe.application.game;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * @author 科技㊣²º¹³
 * Feb 21, 2013 4:25:59 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class IdleCheckHandler extends IdleStateAwareChannelHandler{
	private final static Log LOG = Logs.get();
	@Override
	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e)
			throws Exception
	{
		if(e.getState() == IdleState.ALL_IDLE){
			LOG.warnf("Channel &s has been idle, it will be disconnected now: ",e.getChannel());
			e.getChannel().close();
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception
	{
		LOG.warnf("Channel %s has thrown exception {}",e.getChannel(),e);
		e.getChannel().close();
		super.exceptionCaught(ctx, e);
	}
}
