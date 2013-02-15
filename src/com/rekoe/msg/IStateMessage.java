package com.rekoe.msg;



public interface IStateMessage<T extends IMessageSender> extends IMessage<T> {
	
	public MessageExecuteState getExecuteState();
	public void setExecuteState(MessageExecuteState value);
}
