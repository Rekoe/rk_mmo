package com.rekoe.loader;
/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public interface ILoader{
	public void clear();
	public void loader(String ...fileNames);
	public String getResourceType();
	public <T> T getClassObject(String name);
}