package com.rekoe.loader.xls;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.rekoe.loader.AbstractLoader;

/**
 * @author 科技㊣²º¹³
 *  Feb 16, 2013 12:06:35 PM 
 *  http://www.rekoe.com 
 *  QQ:5382211
 */
public class XLSLoader extends AbstractLoader {

	private static final Log LOG = Logs.get();

	protected Map<String, Workbook> xlsMap = new LinkedHashMap<String, Workbook>();

	public XLSLoader(String... fileNames) {
		loader(fileNames);
	}
	@Override
	public String getResourceType() {
		return "xls";
	}
	protected String getScanPatten() {
		return ".+[.]xls";// ".+[.]xls$"
	}

	public Map<String, Workbook> getXlsMap() {
		return this.xlsMap;
	}

	public void clear() {
		this.xlsMap.clear();
	}

	public static void main(String[] args) {
		Mirror.me(XLSLoader.class).born(new Object[] { "e:/conf" });
	}

	@Override
	public void add(String name, InputStream ins) {
		try {
			Workbook wb = WorkbookFactory.create(ins);
			xlsMap.put(name, wb);
			LOG.info(name);
		} catch (Throwable e) {
			throw Lang.wrapThrow(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getClassObject(String name) {
		return (T) xlsMap.get(name);
	}
}
