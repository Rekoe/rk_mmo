package com.rekoe.mvc;

import org.jboss.netty.channel.ChannelHandler;

import com.rekoe.mvc.config.GameConfig;

public interface IServer extends ChannelHandler{
	public abstract void initMessageExecutor();
	public void connect(GameConfig config);
}