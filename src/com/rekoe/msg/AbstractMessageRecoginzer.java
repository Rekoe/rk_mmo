package com.rekoe.msg;

import com.rekoe.model.gameobjects.client.Client;
import com.rekoe.msg.cs.CSLoginMessage;


/**
 *消息注册器
 */
public abstract class AbstractMessageRecoginzer implements IMessageRecognizer<Integer> {

	public BaseIoMessage<Client> createMessage(Integer type) {
		switch(type) {
			case MessageType.CS_LOGIN:{
				return new CSLoginMessage();
			}
		}
		return null;
	}
}
