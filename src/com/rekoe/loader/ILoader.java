package com.rekoe.loader;

public interface ILoader{
	public void clear();
	public void loader(String ...fileNames);
	public String getResourceType();
	public <T> T getClassObject(String name);
}