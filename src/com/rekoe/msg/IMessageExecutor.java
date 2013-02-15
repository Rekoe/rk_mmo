package com.rekoe.msg;


public interface IMessageExecutor<T extends IMessageSender>{
	
	public MessageExecuteResult execute(IMessage<T> msg);
	public boolean canExecute(T sender);
}
