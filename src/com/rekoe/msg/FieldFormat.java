package com.rekoe.msg;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class FieldFormat {

	public static final int TYPE_UNKNOWN = 0;
	public static final int TYPE_BYTE = 1;
	public static final int TYPE_SHORT = 2;
	public static final int TYPE_INT = 3;
	public static final int TYPE_LONG = 4;
	public static final int TYPE_STRING = 5;

	private String name;
	private int type; 
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setType(String type) {
		if (type.equalsIgnoreCase("byte")) {
			this.type = TYPE_BYTE;
		} else if (type.equalsIgnoreCase("short")) {
			this.type = TYPE_SHORT;
		} else if (type.equalsIgnoreCase("int")) {
			this.type = TYPE_INT;
		} else if (type.equalsIgnoreCase("long")) {
			this.type = TYPE_LONG;
		} else if (type.equalsIgnoreCase("string")) {
			this.type = TYPE_STRING;
		} else {
			this.type = TYPE_UNKNOWN;
		}
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}