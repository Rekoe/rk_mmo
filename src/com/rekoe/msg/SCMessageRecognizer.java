package com.rekoe.msg;

import com.rekoe.model.gameobjects.client.Client;
import com.rekoe.msg.cs.CSLoginMessage;


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
