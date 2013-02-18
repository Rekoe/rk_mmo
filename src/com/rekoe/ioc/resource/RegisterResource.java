package com.rekoe.ioc.resource;

import java.util.ArrayList;
import java.util.List;

import org.nutz.ioc.loader.annotation.IocBean;

import com.rekoe.ioc.resource.imp.XlsResourceLoader;
import com.rekoe.mvc.RkMvcContext;
import com.rekoe.mvc.config.GameConfig;

/**
 * @author 科技㊣²º¹³
 * Feb 18, 2013 6:13:47 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
@IocBean
public class RegisterResource {

	private List<IResourceLoader> list = new ArrayList<IResourceLoader>();

	private GameConfig config;
	public RegisterResource()
	{
		this.config = RkMvcContext.me().getConfig();
		list.add(new XlsResourceLoader(config));
	}
	
	public void load()
	{
		for(IResourceLoader loader:list)
		{
			loader.load();
		}
	}
	
	public void check()
	{
		for(IResourceLoader loader:list)
		{
			loader.check();
		}
	}
}
