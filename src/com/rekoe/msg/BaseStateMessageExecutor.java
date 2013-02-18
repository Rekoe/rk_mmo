package com.rekoe.msg;

/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public abstract class BaseStateMessageExecutor<T extends IMessageSender> extends BaseMessageExecutor<T> {

	public MessageExecuteResult execute(IMessage<T> msg) {
		final IStateMessage<T> stateMsg = (IStateMessage<T>)msg;
		MessageExecuteState state = stateMsg.getExecuteState();
		if (state == MessageExecuteState.INITIALIZED) {
			executeFirstStep(msg);
			stateMsg.setExecuteState(MessageExecuteState.EXECUTING_FIRST_STEP);
			return MessageExecuteResult.GOTO_FIRST_STEP_STATE;
		}
		else if (state == MessageExecuteState.EXECUTING_FIRST_STEP) {
			new Thread() {
				public void run() {
					executeImpl(stateMsg);
					stateMsg.setExecuteState(MessageExecuteState.EXECUTING_LAST_STEP);
				}
			}.start();
			stateMsg.setExecuteState(MessageExecuteState.EXECUTING_IO_STEP);
			return MessageExecuteResult.GOTO_IO_STEP_STATE;
		}
		else if (state == MessageExecuteState.EXECUTING_IO_STEP) {
			return MessageExecuteResult.GOTO_IO_STEP_STATE;
		}
		else if (state == MessageExecuteState.EXECUTING_LAST_STEP) {
			executeLastStep(msg);
			stateMsg.setExecuteState(MessageExecuteState.EXECUTED);
			return MessageExecuteResult.FINISHED;
		}
		else {
			return MessageExecuteResult.FINISHED;
		}

	}
	
	protected abstract void executeFirstStep(IMessage<T> msg);
	
	protected abstract void executeLastStep(IMessage<T> msg);

}
