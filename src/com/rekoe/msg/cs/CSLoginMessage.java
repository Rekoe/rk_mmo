package com.rekoe.msg.cs;

import com.rekoe.msg.AbstractStateMessage;
import com.rekoe.msg.MessageType;

/**
 * 登录消息类
 * @author koukou890@qq.com
 */
public class CSLoginMessage extends AbstractStateMessage{

	private String name;
	private String password;
	private short loginType;
	
	public CSLoginMessage(){}
	public CSLoginMessage(String name,String password,short loginType)
	{
		this.name = name;
		this.password = password;
		this.loginType = loginType;
	}
	@Override
	public int getType() {
		return MessageType.CS_LOGIN;
	}

	@Override
	public void readImpl() {
		this.loginType = readByte();
		this.name = readString();
		this.password = readString();
	}

	@Override
	public void writeImpl() {
		writeByte((byte)loginType);
		writeString(name);
		writeString(password);
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public short getLoginType() {
		return loginType;
	}
}