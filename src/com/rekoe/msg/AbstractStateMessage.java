package com.rekoe.msg;

import com.rekoe.model.gameobjects.client.Client;

public abstract class AbstractStateMessage extends AbstractMessage implements IStateMessage<Client> {

	private MessageExecuteState executeState = MessageExecuteState.INITIALIZED;
	public MessageExecuteState getExecuteState() {
		return executeState;
	}
	
	public void setExecuteState(MessageExecuteState value) {
		executeState = value;
	}

}
