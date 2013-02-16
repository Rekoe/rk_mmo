package com.rekoe.dial;

import org.nutz.lang.Lang;

/**
 * @author 科技㊣²º¹³ 
 * Feb 16, 2013 5:07:21 PM 
 * http://www.rekoe.com 
 * QQ:5382211
 */
public class BaiduBonus {

	private int dialId;
	private int type;
	private int npcID;
	private String dialTitle;
	
	public String getDialTitle() {
		return dialTitle;
	}

	public void setDialTitle(String dialTitle) {
		this.dialTitle = dialTitle;
	}

	public void bindNpc(int npcID) {
		this.npcID = npcID;
	}

	public boolean exec() {
		return true;
	}

	public boolean canShow() {
		return false;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public int getNpcID() {
		return npcID;
	}

	public void set(String key, int value) {
		if (Lang.equals(key, "id")) {
			this.setDialId(value);
		}
	}

	public int getDialId() {
		return dialId;
	}

	public void setDialId(int dialId) {
		this.dialId = dialId;
	}

}
