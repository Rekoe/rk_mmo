package com.rekoe;

import org.nutz.mvc.annotation.Modules;

import com.rekoe.mvc.annotation.IocGameBy;
import com.rekoe.mvc.ioc.provider.ComboIocGameProvider;

/**
 * @author 科技㊣²º¹³ 
 * Feb 13, 2013 2:53:34 PM 
 * http://www.rekoe.com 
 * QQ:5382211
 */
@Modules(scanPackage = true)
@IocGameBy(type = ComboIocGameProvider.class, args = {
		"*org.nutz.ioc.loader.json.JsonLoader", "ioc/",
		"*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.rekoe.db" })

public class MainModule {

}
