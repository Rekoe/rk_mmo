package com.rekoe.msg;

/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public interface IStateMessage<T extends IMessageSender> extends IMessage<T> {
	
	public MessageExecuteState getExecuteState();
	public void setExecuteState(MessageExecuteState value);
}
