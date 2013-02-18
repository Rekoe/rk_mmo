package com.rekoe.mvc.config;


import org.nutz.ioc.Ioc;

import com.rekoe.mvc.GameContext;
import com.rekoe.mvc.RkMvcContext;

/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class FilterGameConfig extends AbstractGameConfig{

	private GameContext gameContext;
	
	public FilterGameConfig()
	{
		this.gameContext = new GameContext();
		RkMvcContext.me().setConfig(this);
	}
	@Override
	public GameContext getGameContext() {
		return this.gameContext;
	}
	@Override
	public Ioc getIoc() {
		return (Ioc)getGameContext().getAttribute(RkMvcContext.GAME_IOC_KEY);
	}

}
