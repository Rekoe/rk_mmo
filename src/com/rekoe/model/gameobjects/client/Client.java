package com.rekoe.model.gameobjects.client;

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

	@Override
	public String getName() {
		return null;
	}

	@Override
	public short getMessageState() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMessageState(short value) {
		
	}

	@Override
	public void write(IMessage<?> message) {
		
	}

}
