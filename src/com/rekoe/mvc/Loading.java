package com.rekoe.mvc;

import com.rekoe.mvc.config.GameConfig;
/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */

public interface Loading {

	public static final String CONTEXT_NAME = "_RK_MMO_LOADING_CONTEXT_";

	void load(GameConfig config);

	void depose(GameConfig config);

}
