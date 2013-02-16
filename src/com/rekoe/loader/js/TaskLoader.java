package com.rekoe.loader.js;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.bsf.BSFManager;
import org.nutz.lang.Lang;
import org.nutz.lang.Streams;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.rekoe.loader.AbstractLoader;

/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 3:05:29 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class TaskLoader extends AbstractLoader {

	private final static Log log = Logs.get();
	private Map<String,Object> tasks = new HashMap<String,Object>();
	public TaskLoader(String ...fileNames)
	{
		loader(fileNames);
	}
	@Override
	public void clear() {

	}

	@Override
	protected String getScanPatten() {
		return ".+[.]js$";
	}

	@Override
	public void add(String name, InputStream ins) {
		BSFManager context = new BSFManager();
		try {
		context.declareBean("tasks", this, TaskLoader.class);
		String content = new String(Streams.readBytes(ins));
		log.info(content);
		context.exec("javascript", name, 0, 0, content);
		} catch (Throwable e) {
			throw Lang.wrapThrow(e);
		}
	}
	public static void main(String[] args) {
		TaskLoader loader = new TaskLoader("e:/conf");
		System.out.println(loader.tasks);
	}
}
