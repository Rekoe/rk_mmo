package com.rekoe.msg.executor;


import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.rekoe.model.gameobjects.client.Client;
import com.rekoe.msg.BaseStateMessageExecutor;
import com.rekoe.msg.IMessage;
import com.rekoe.msg.MessageTypeState;
import com.rekoe.msg.cs.CSLoginMessage;
/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class LoginMessageExecutor extends BaseStateMessageExecutor<Client> {
	private Log log = Logs.get();
	private final static Short[] preposingStates = new Short[] {MessageTypeState.CONNECTED};
	
	@Override
	protected void executeImpl(IMessage<Client> msg) {
		//log.infof("######## %s ###########",GameMvcs.getMMOContext().getAttribute("rekoe"));
	}
	
	@Override
	protected void executeFirstStep(IMessage<Client> msg) {
		CSLoginMessage login = (CSLoginMessage) msg;
		//Client client = msg.getSender();
		//UserDataService usreService = GameMvcs.getIoc().get(UserDataService.class);
		//log.info(" userService "+usreService);
		//login.getSender().setMessageState(MessageTypeState.LOADING);
		short type = login.getLoginType();
		String name = login.getName();
		String pwd = login.getPassword();
		
		log.infof("user %s password %s type %s", name,pwd,type);
		
	}
	
	@Override
	protected void executeLastStep(IMessage<Client> msg) {
		//用户进入地图
		msg.getSender().setMessageState(MessageTypeState.MAP);
	}
	
	@Override
	protected Short[] getPreposingStates() {
		return preposingStates;
	}

}