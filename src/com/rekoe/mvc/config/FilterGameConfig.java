package com.rekoe.mvc.config;


import org.nutz.ioc.Ioc;

import com.rekoe.mvc.GameContext;


public class FilterGameConfig extends AbstractGameConfig{

	private GameContext gameContext;
	
	public FilterGameConfig()
	{
		this.gameContext = new GameContext();
		//GameMvcs.setMMOContext(mmoContext);
		//GameMvcs.setNutConfig(this);
	}
	@Override
	public GameContext getGameContext() {
		return this.gameContext;
	}
	@Override
	public Ioc getIoc() {
		return null;//GameMvcs.getIoc();
	}

}
