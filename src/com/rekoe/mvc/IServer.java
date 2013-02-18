package com.rekoe.mvc;

import org.jboss.netty.channel.ChannelHandler;

import com.rekoe.mvc.config.GameConfig;
/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public interface IServer extends ChannelHandler{
	public abstract void initMessageExecutor();
	public void connect(GameConfig config);
}