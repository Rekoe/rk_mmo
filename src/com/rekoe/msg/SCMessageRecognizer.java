package com.rekoe.msg;

import com.rekoe.model.gameobjects.client.Client;
import com.rekoe.msg.cs.CSLoginMessage;

/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class SCMessageRecognizer implements IMessageRecognizer<Integer> {

	@Override
	public BaseIoMessage<Client> createMessage(Integer type) {
		switch(type) {
			case MessageType.CS_LOGIN:{
				return new CSLoginMessage();
			}
		}
		return null;
	}

}
