package com.rekoe.exception;


/**
 * @author 科技㊣²º¹³
 * Feb 18, 2013 7:47:35 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class ResourceLoadException extends RuntimeException {

	private static final long serialVersionUID = 1549031803256248512L;

	public ResourceLoadException() {
		super();
	}

	public ResourceLoadException(String message, Throwable cause,Object ...args) {
		super(String.format(message, args), cause);
	}

	public ResourceLoadException(String message,Object ...args) {
		super(String.format(message, args));
	}

	public ResourceLoadException(Throwable cause) {
		super(cause);
	}
}
