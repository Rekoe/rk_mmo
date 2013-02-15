package com.rekoe.msg;


public interface IMessage<T extends IMessageSender> {
	public abstract int getType();
	public T getSender();
	public void setSender(T p);
}