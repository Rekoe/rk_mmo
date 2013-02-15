package com.rekoe.model.gameobjects.client;

import org.jboss.netty.channel.Channel;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.rekoe.model.gameobjects.RkObject;
import com.rekoe.msg.IMessage;
import com.rekoe.msg.IMessageSender;

/**
 * @author 科技㊣²º¹³
 * Feb 15, 2013 11:52:07 AM
 * http://www.rekoe.com
 * QQ:5382211
 * 客户端的连接者
 * 每次连接主动分配一个ID
 */
public class Client extends RkObject implements IMessageSender{

	private final static Log log = Logs.get();
	private Channel channel;
	private short messageState;
	public Client(Channel channel)
	{
		this.channel = channel;
	}
	@Override
	public String getName() {
		return "游客："+getObjectId();
	}

	@Override
	public short getMessageState() {
		return this.messageState;
	}

	@Override
	public void setMessageState(short value) {
		this.messageState = value;
	}

	@Override
	public void write(IMessage<Client> message) {
		if(channel.isConnected())
		{
			channel.write(message);
		}else{
			log.errorf("the Channel %s is disConnected", channel.getRemoteAddress().toString());
		}
	}

	public void channelClose()
	{
		channel.close();
	}
}
