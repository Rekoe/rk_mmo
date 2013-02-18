package com.rekoe.msg;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class MessageQueueExecutor implements Runnable  {

	private Log log = Logs.get();
	
	protected BlockingQueue<IMessage> queue;
	protected Map<Integer, IMessageExecutor> executors;
	
	public BlockingQueue<IMessage> getMessageQueue() {
		return queue;
	}
	
	public MessageQueueExecutor() {
		this.queue = new LinkedBlockingQueue<IMessage>();
		this.executors =  new HashMap<Integer, IMessageExecutor>();
	}
	public MessageQueueExecutor(BlockingQueue<IMessage> queue, Map<Integer, IMessageExecutor> executors) {
		this.queue = queue;
		this.executors = executors;
	}

	public void registerMessageExecutor(int type, IMessageExecutor executor)
	{
		executors.put(type, executor);
	}
	@Override
	public void run() {
		while (true) {
			try {
				IMessage<IMessageSender> msg = queue.take();
				int type = msg.getType();
				IMessageExecutor<IMessageSender> executor = executors.get(type);
				if(!Lang.isEmpty(executor))
				{
					boolean canExecute = executor.canExecute(msg.getSender());
					if(canExecute)
					{
						MessageExecuteResult result = executor.execute(msg);
						if (result != MessageExecuteResult.FINISHED) {
							try {
								queue.add(msg);
							}
							catch(IllegalStateException ex) {
								log.errorf("消息队列已满,无法加入消息:\n%d", msg.toString());
							}
						}
					}
				}else{
					log.errorf("消息处理器没有没只:%d",type);
				}
			}
			catch(InterruptedException ex) {
				log.errorf(ex.getMessage());
			}
		}
	}
	public boolean isFull()
	{
		return queue.size() > 1000;
	}
}
