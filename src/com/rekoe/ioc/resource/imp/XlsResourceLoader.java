package com.rekoe.ioc.resource.imp;

import org.apache.poi.ss.usermodel.Workbook;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.rekoe.ioc.resource.IResourceLoader;
import com.rekoe.mvc.RkMvcContext;
import com.rekoe.mvc.config.GameConfig;

/**
 * @author 科技㊣²º¹³
 * Feb 18, 2013 6:09:05 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class XlsResourceLoader implements IResourceLoader {

	private final static Log log = Logs.get();

	private Class<?> classType = Workbook.class;
	public XlsResourceLoader(GameConfig config) {
	}
	@Override
	public void load() {
		Workbook item = getWorkbookByName("item.xls");
		log.infof("xls resource load %s",item);
	}

	@Override
	public void check() {

		//throw new ResourceLoadException("测试一下哈 %s",123);
	}

	@SuppressWarnings("unchecked")
	private <T>T getWorkbookByName(String name)
	{
		return (T)RkMvcContext.me().getResourceAttributeAs(classType, name);
	}
}
