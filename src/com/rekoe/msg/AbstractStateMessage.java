package com.rekoe.msg;

import com.rekoe.model.gameobjects.client.Client;
/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public abstract class AbstractStateMessage extends AbstractMessage implements IStateMessage<Client> {

	private MessageExecuteState executeState = MessageExecuteState.INITIALIZED;
	public MessageExecuteState getExecuteState() {
		return executeState;
	}
	
	public void setExecuteState(MessageExecuteState value) {
		executeState = value;
	}

}
