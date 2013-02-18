package com.rekoe.msg;

import com.rekoe.model.gameobjects.client.Client;

/**
 * 消息处理器，一个消息可能需要根据消息参数的不同而使用不同的处理器来处理
 */
public interface IMessageSender {
	
	public short getMessageState();
	public void setMessageState(short value);
	public void write(IMessage<Client> message);
}
