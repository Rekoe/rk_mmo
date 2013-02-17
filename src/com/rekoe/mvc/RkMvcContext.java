package com.rekoe.mvc;

import java.util.List;
import java.util.Map;

import org.nutz.ioc.Ioc;
import org.nutz.lang.util.SimpleContext;
import org.nutz.log.Logs;

import com.rekoe.loader.ILoader;
import com.rekoe.mvc.config.GameConfig;
import com.rekoe.mvc.ioc.provider.ComboResourceGameProvider;

/**
 * @author 科技㊣²º¹³
 * Feb 15, 2013 9:07:15 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class RkMvcContext extends SimpleContext {
	public final static String GAME_IOC_KEY = "IOC";
	public final static String GAME_CONFIG_KEY = "GAME_CONFIG";
	public final static String GAME_RESOURCE_KEY = "GAME_RESOURCE";
	private GameConfig config;

	public static final RkMvcContext me() {
		return RkMvcContextHolder.instance;
	}

	private static class RkMvcContextHolder {
		protected static final RkMvcContext instance = new RkMvcContext();
	}
	
	public void setConfig(GameConfig config) {
		this.config = config;
	}

	public Ioc getIoc()
	{
		return config.getIoc();
	}
	
	public <T> T getAttributeAs(Class<T> type, String name) 
	{
		return config.getAttributeAs(type, name);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getResourceAttributeAs(Class<T> type, String name) 
	{
		ComboResourceGameProvider provider = getAttributeAs(ComboResourceGameProvider.class, GAME_RESOURCE_KEY);
		Map<String, List<ILoader>> resourceList = provider.resourceList();
		String resType = "js";
		Logs.get().info(type.getName());
		if(type.getName().contains("Workbook"))
		{
			resType = "xls";
		}else if(type.getName().contains("Document"))
		{
			resType = "xml";
		}
		List<ILoader> list = resourceList.get(resType);
		if(list == null)
		{
			throw new NullPointerException(String.format("%s is not load", resType));
		}
		for(ILoader loader:list)
		{
			Object obj = loader.getClassObject(name);
			if(obj != null)
			{
				return (T) obj;
			}
		}
		throw new NullPointerException(String.format("%s is not load", resType));
	}
}
