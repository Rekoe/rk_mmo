package com.rekoe.msg;

import com.rekoe.model.gameobjects.client.Client;



public interface IMessageRecognizer<K> {
	public IMessage<Client> createMessage(K type);
}
