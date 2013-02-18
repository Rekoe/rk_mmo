package com.rekoe;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.rekoe.mvc.GameSetup;
import com.rekoe.mvc.config.GameConfig;

/**
 * @author 科技㊣²º¹³
 * Feb 18, 2013 9:42:00 AM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class Install implements GameSetup {

	private final static Log log = Logs.get();
	@Override
	public void initResource(GameConfig config) {
		log.info("load Resource");
	}

	@Override
	public void checkResource(GameConfig config) {
		log.info("check Resource");
	}


}
