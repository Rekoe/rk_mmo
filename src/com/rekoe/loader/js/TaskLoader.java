package com.rekoe.loader.js;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.rekoe.dial.BaiduBonus;

/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 3:05:29 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class TaskLoader extends AbstractJsLoader {

	private final static Log log = Logs.get();
	
	public TaskLoader(String ...fileNames)
	{
		init();
		loader(fileNames);
	}
	
	public static void main(String[] args) {
		new TaskLoader("e:/conf");
	}

	public void add(BaiduBonus bonus){
		log.info(bonus);
	}
	@Override
	protected void init() {
		super.classType = getClass();
		super.obj = this;
		super.name = "tasks";
	}
}
