package com.rekoe.mvc.ioc.provider;


import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.impl.ScopeContext;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.lang.Lang;

import com.rekoe.mvc.IocGameProvider;
import com.rekoe.mvc.config.GameConfig;

public class ComboIocGameProvider implements IocGameProvider {

	@Override
	public Ioc create(GameConfig config, String[] args) {
		try {
			return new NutIoc(new ComboIocLoader(args), new ScopeContext("rk_mmo"), "rk_mmo");
		}
		catch (ClassNotFoundException e) {
			throw Lang.wrapThrow(e);
		}
	}
}