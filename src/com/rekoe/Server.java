package com.rekoe;

import com.rekoe.mvc.config.FilterGameConfig;
import com.rekoe.mvc.config.GameActionHandler;

public class Server {

	/**
	 * @param args
	 *            http://javaxyq.googlecode.com/svn/trunk/
	 */
	public static void main(String[] args) {
		FilterGameConfig config = new FilterGameConfig();
		new GameActionHandler(config);

	}
}
