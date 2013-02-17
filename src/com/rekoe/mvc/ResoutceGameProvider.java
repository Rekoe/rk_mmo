package com.rekoe.mvc;

import java.util.List;
import java.util.Map;

import com.rekoe.loader.ILoader;
import com.rekoe.mvc.config.GameConfig;

/**
 * @author 科技㊣²º¹³
 * Feb 17, 2013 10:01:00 AM
 * http://www.rekoe.com
 * QQ:5382211
 */
public interface ResoutceGameProvider {
	
	public ResoutceGameProvider loader(GameConfig config, String[] args);
	
	public Map<String,List<ILoader>> resourceList();
}
