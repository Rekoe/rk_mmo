package com.rekoe.msg;

public enum MessageExecuteResult {
	FINISHED,
	GOTO_FIRST_STEP_STATE,
	GOTO_LAST_STEP_STATE,
	GOTO_IO_STEP_STATE;
}
