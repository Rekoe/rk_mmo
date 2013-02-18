package com.rekoe.msg;

import java.util.Arrays;

import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public abstract class BaseMessageExecutor<T extends IMessageSender> implements IMessageExecutor<T> {
	
	private final static Log log = Logs.get();
	@Override
	public boolean canExecute(T sender) {
		log.infof("sys %s >> %s", Json.toJson(getPreposingStates()),sender.getMessageState());
		return Arrays.binarySearch(getPreposingStates(), sender.getMessageState()) >= 0;
	}
	
	protected abstract Short[] getPreposingStates();

	public MessageExecuteResult execute(IMessage<T> msg) {
		executeImpl(msg);
		return MessageExecuteResult.FINISHED;
	}
	
	protected abstract void executeImpl(IMessage<T> msg);
}
