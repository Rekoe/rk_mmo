package com.rekoe.mvc;


import org.nutz.ioc.Ioc;

import com.rekoe.mvc.config.GameConfig;

public interface IocGameProvider {
	Ioc create(GameConfig config, String[] args);
}
