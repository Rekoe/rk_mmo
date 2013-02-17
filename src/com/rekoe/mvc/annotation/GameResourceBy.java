package com.rekoe.mvc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.rekoe.mvc.ResoutceGameProvider;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface GameResourceBy {
	Class<? extends ResoutceGameProvider> type();

	/**
	 * 这个参数将传递给 ILoader 的 构造 方法，作为构造  必要的参数
	 */
	String[] args();
}
