package com.rekoe.mvc.ioc.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;

import com.rekoe.loader.ILoader;
import com.rekoe.loader.js.TaskLoader;
import com.rekoe.loader.xlm.XMLLoader;
import com.rekoe.loader.xls.XLSLoader;
import com.rekoe.mvc.ResoutceGameProvider;
import com.rekoe.mvc.config.GameConfig;

/**
 * @author 科技㊣²º¹³
 *  Feb 17, 2013 10:03:49 AM 
 *  http://www.rekoe.com 
 *  QQ:5382211
 *  资源文件加载器
 */
public class ComboResourceGameProvider implements ResoutceGameProvider {

	/**
	 * 类别名
	 */
	private static Map<String, Class<? extends ILoader>> loaders = new HashMap<String, Class<? extends ILoader>>();
	static {
		loaders.put("js", TaskLoader.class);
		loaders.put("xml", XMLLoader.class);
		loaders.put("xls", XLSLoader.class);
	}

	@Override
	public void loader(GameConfig config, String[] args) {
		try {
			ArrayList<String> argsList = null;
			String currentClassName = null;
			for (String str : args) {
				if (str.length() > 0 && str.charAt(0) == '*') {
					if (argsList != null)
						createIocLoader(currentClassName, argsList);
					currentClassName = str.substring(1);
					argsList = new ArrayList<String>();
				} else
					argsList.add(str);
			}
			if (currentClassName != null)
				createIocLoader(currentClassName, argsList);
		} catch (ClassNotFoundException e) {
			throw Lang.wrapThrow(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void createIocLoader(String className, List<String> args)
			throws ClassNotFoundException {
		Class<? extends ILoader> klass = loaders.get(className);
		if (klass == null){
			klass = (Class<? extends ILoader>) Lang.loadClass(className);
		}
		Mirror.me(klass).born(args.toArray(new Object[args.size()]));
	}
}
