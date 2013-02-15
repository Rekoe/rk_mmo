package com.rekoe.mvc;

import org.nutz.ioc.Ioc;
import org.nutz.lang.util.SimpleContext;

import com.rekoe.mvc.config.GameConfig;

/**
 * @author 科技㊣²º¹³
 * Feb 15, 2013 9:07:15 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class RkMvcContext extends SimpleContext {
	public final static String GAME_IOC_KEY = "IOC";
	public final static String GAME_CONFIG_KEY = "GAME_CONFIG";
	private GameConfig config;

	public static final RkMvcContext me() {
		return RkMvcContextHolder.instance;
	}

	private static class RkMvcContextHolder {
		protected static final RkMvcContext instance = new RkMvcContext();
	}
	
	public void setConfig(GameConfig config) {
		this.config = config;
	}

	public Ioc getIoc()
	{
		return config.getIoc();
	}
	
}
