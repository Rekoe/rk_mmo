package com.rekoe.loader.xlm;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;

import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.w3c.dom.Document;

import com.rekoe.loader.AbstractLoader;

/**
 * @author 科技㊣²º¹³
 *  Feb 16, 2013 12:06:35 PM 
 *  http://www.rekoe.com 
 *  QQ:5382211
 */
public class XMLLoader extends AbstractLoader {

	private static final Log LOG = Logs.get();

	protected Map<String, Document> xmlMap = new LinkedHashMap<String, Document>();
	private DocumentBuilder builder;
	public XMLLoader(String... fileNames) {
		try {
			this.builder = Lang.xmls();
		} catch (Throwable e) {
			throw Lang.wrapThrow(e);
		}
		loader(fileNames);
	}

	protected String getScanPatten() {
		return ".+[.]xml$";// ".+[.]xls$"
	}

	public Map<String, Document> getXlsMap() {
		return this.xmlMap;
	}

	public void clear() {
		this.xmlMap.clear();
	}

	public static void main(String[] args) {
		Mirror.me(XMLLoader.class).born(new Object[] { "e:/conf" });
	}

	@Override
	public void add(String name, InputStream ins) {
		try {
            Document document = this.builder.parse(ins);
            document.normalizeDocument();
            xmlMap.put(name, document);
			LOG.info(name);
		} catch (Throwable e) {
			throw Lang.wrapThrow(e);
		}
	}
}
