package com.rekoe.mvc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.rekoe.mvc.IocGameProvider;

/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface IocGameBy {
	/**
	 * 通过 IocProvider，来决定采用何种方式的 Ioc 容器
	 */
	Class<? extends IocGameProvider> type();

	/**
	 * 这个参数将传递给 IocProvider 的 create 方法，作为构造 Ioc 容器必要的参数
	 * <p>
	 * 不同的 IocProvider 对参数数组的具体要求是不一样的，具体请参看各个 IocProvider 的说明
	 */
	String[] args();
}
