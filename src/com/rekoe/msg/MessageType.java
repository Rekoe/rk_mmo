package com.rekoe.msg;

public interface MessageType {

	public static final int CS_LOGIN							=	100;			//正常登陆
	public static final int CS_ITEM_MOVE						= 10200;//物品移动
	public static final int CS_ITEM_CONTAINER_LOAD				= 24;//包裹加载
	public static final int SC_ITEM_CONTAINER_LOAD				= 25;
	public static final int SC_LOGIN_RESULT						= 200;//登录结果
	public static final int SC_ITEM_UPDATE						= 10000;//物品更新
	public static final int SC_ITEM_ADD							= 10001;//新的物品
	public static final int CS_MESSAGE_CHAT						= 1000;//聊天
	public static final int CS_OHTEST	 = 1001;
	public static final int SC_OHTEST	 = 2001;
}