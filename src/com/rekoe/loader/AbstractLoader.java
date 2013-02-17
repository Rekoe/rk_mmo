package com.rekoe.loader;

import java.io.InputStream;
import java.util.List;

import org.nutz.lang.Lang;
import org.nutz.lang.Streams;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.resource.NutResource;
import org.nutz.resource.Scans;

/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public abstract class AbstractLoader implements ILoader {
	private static final Log LOG = Logs.get();
	
	protected abstract String getScanPatten();
	public abstract void add(String name,InputStream ins);
	
	@Override
	public void loader(String... fileNames) {
		try {
			List<NutResource> list = Scans.me().loadResource(getScanPatten(),fileNames);
			for (NutResource nr : list) {
				InputStream ins = nr.getInputStream();
				add(nr.getName(),ins);
				Streams.safeClose(ins);
			}
			if (LOG.isDebugEnabled())
				LOG.debugf("Load complete :\n%s");
		} catch (Throwable e) {
			throw Lang.wrapThrow(e);
		}
	}
}