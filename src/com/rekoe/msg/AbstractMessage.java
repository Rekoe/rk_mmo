package com.rekoe.msg;

import com.rekoe.model.gameobjects.client.Client;

/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public abstract class AbstractMessage extends BaseIoMessage<Client>{
	protected Client player;
	private String charId;
	
	public String getCharId() {
		return charId;
	}

	public void setCharId(String charId) {
		this.charId = charId;
	}

	@Override
	public Client getSender() {
		return player;
	}

	@Override
	public void setSender(Client p) {
		player = p;
	}
}
