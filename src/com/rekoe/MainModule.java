package com.rekoe;

import org.nutz.mvc.annotation.Localization;
import org.nutz.mvc.annotation.Modules;

import com.rekoe.application.game.GameServer;
import com.rekoe.application.policy.PolicyServer;
import com.rekoe.mvc.SetupBy;
import com.rekoe.mvc.annotation.GameResourceBy;
import com.rekoe.mvc.annotation.IocGameBy;
import com.rekoe.mvc.annotation.ServerStartBy;
import com.rekoe.mvc.ioc.provider.ComboIocGameProvider;
import com.rekoe.mvc.ioc.provider.ComboResourceGameProvider;

/**
 * @author 科技㊣²º¹³ 
 * Feb 13, 2013 2:53:34 PM 
 * http://www.rekoe.com 
 * QQ:5382211
 */
@Modules(scanPackage = true)
@Localization("msg")
@SetupBy(Install.class)
@IocGameBy(type = ComboIocGameProvider.class, args = {
		"*org.nutz.ioc.loader.json.JsonLoader", "ioc/",
		"*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.rekoe.ioc.db" })
@GameResourceBy(type = ComboResourceGameProvider.class, args = {
		"*com.rekoe.loader.js.TaskLoader", "baidu.js",
		"*com.rekoe.loader.xls.XLSLoader", "xls/" ,
		"*com.rekoe.loader.xlm.XMLLoader","xml/"})
@ServerStartBy({PolicyServer.class,GameServer.class})
public class MainModule {

}
