package com.rekoe.mvc;

import com.rekoe.mvc.config.GameConfig;

/**
 * @author 科技㊣²º¹³
 * Feb 18, 2013 5:19:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public interface GameSetup {

	void initResource(GameConfig config);
	
	void checkResource(GameConfig config);
}
