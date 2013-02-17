package com.rekoe.loader.js;

import java.io.InputStream;

import org.apache.bsf.BSFManager;
import org.nutz.lang.Lang;
import org.nutz.lang.Streams;

import com.rekoe.loader.AbstractLoader;

/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 8:02:13 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public abstract class AbstractJsLoader extends AbstractLoader {

	public static String PRE_STR =    "var BaiduBonus 	 	= Packages.com.rekoe.dial.BaiduBonus;";
	protected String name;
	protected Class<?> classType;
	protected Object obj;
	
	@Override
	public String getResourceType() {
		return "js";
	}

	@Override
	public void clear() {

	}

	protected abstract void init();
	
	@Override
	protected String getScanPatten() {
		return ".+[.]js$";
	}

	@Override
	public void add(String name, InputStream ins) {
		BSFManager context = new BSFManager();
		try {
			context.declareBean(this.name, obj, classType);
			String dialContent = new String(Streams.readBytes(ins));
			String content = PRE_STR + dialContent;
			context.exec("javascript", name, 0, 0, content);
		} catch (Throwable e) {
			throw Lang.wrapThrow(e);
		}
	}

}
