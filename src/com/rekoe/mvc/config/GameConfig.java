package com.rekoe.mvc.config;

import org.apache.poi.ss.usermodel.Workbook;
import org.nutz.ioc.Ioc;

import com.rekoe.mvc.GameContext;
import com.rekoe.mvc.Loading;

/**
 * @author 科技㊣²º¹³
 * Feb 13, 2013 3:00:03 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public interface GameConfig {

	/**
	 * 获取配置的主模块，一般的说是存放在 initParameter 集合下的 "modules" 属性 值为一个 class 的全名
	 * @return 配置的主模块，null - 如果没有定义这个参数
	 */
	public Class<?> getMainModule();
	/**
	 * 根据 MainModule 中的 '@LoadingBy' 得到一个加载逻辑的实现类
	 * @return 加载逻辑
	 */
	public Loading createLoading();
	
	/**
	 * 在上下文环境中设置属性对象，如果值为 null，则忽略
	 * 
	 * @param name
	 *            属性名
	 * @param obj
	 *            属性值
	 */
	public void setAttributeIgnoreNull(String name, Object obj);
	
	/**
	 * @return 当前应用的上下文对象
	 */
	public GameContext getGameContext();
	
	public <T> T getAttributeAs(Class<T> type, String name);
	
	public Ioc getIoc();

	//public Workbook getWorkbook(String name);
}
