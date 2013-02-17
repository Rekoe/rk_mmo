package com.rekoe;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.w3c.dom.Document;

import com.rekoe.mvc.RkMvcContext;
import com.rekoe.mvc.config.FilterGameConfig;
import com.rekoe.mvc.config.GameActionHandler;

/**
 * @author 科技㊣²º¹³
 * Feb 15, 2013 7:53:04 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class Server {


	private final static Log log = Logs.get();
	/**
	 * @param args
	 *            http://javaxyq.googlecode.com/svn/trunk/
	 *            https://github.com/Rekoe/rk_mmo.git
	 */
	public static void main(String[] args) {
		FilterGameConfig config = new FilterGameConfig();
		new GameActionHandler(config);
		Document wk = RkMvcContext.me().getResourceAttributeAs(Document.class, "webInfo.xml");
		log.info(wk);
	}

}
