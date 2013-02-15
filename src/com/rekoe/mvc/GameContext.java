package com.rekoe.mvc;

import java.util.HashMap;
import java.util.Map;

public class GameContext {

	private Map<String,Object> map = new HashMap<String,Object>();
	
	public void setAttribute(String name, Object obj){
		map.put(name, obj);
	}
	
	public Object getAttribute(String name){
		return map.get(name);
	}
	
	public Map<String,Object> getObject()
	{
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public String getConfigValue(String name)
	{
		Map<String,String> config = ((Map<String,String>)map.get("config"));
		return config.get(name);
	}
}
