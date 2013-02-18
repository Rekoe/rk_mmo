package com.rekoe.msg;

/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public interface IMessageExecutor<T extends IMessageSender>{
	
	public MessageExecuteResult execute(IMessage<T> msg);
	public boolean canExecute(T sender);
}
