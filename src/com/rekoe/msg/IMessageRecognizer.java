package com.rekoe.msg;

import com.rekoe.model.gameobjects.client.Client;

/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public interface IMessageRecognizer<K> {
	public IMessage<Client> createMessage(K type);
}
