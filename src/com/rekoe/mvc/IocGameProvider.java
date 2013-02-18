package com.rekoe.mvc;


import org.nutz.ioc.Ioc;

import com.rekoe.mvc.config.GameConfig;
/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public interface IocGameProvider {
	Ioc create(GameConfig config, String[] args);
}
