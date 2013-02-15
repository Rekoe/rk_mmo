package com.rekoe.msg;

public enum MessageExecuteState {

	INITIALIZED,			
	CANCELED,				
	EXECUTED,				
	EXECUTING_FIRST_STEP,	
	EXECUTING_LAST_STEP,	
	EXECUTING_IO_STEP;		
}
