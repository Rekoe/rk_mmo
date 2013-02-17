package com.rekoe.mvc.config;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.nutz.Nutz;
import org.nutz.ioc.Ioc;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Encoding;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.lang.Stopwatch;
import org.nutz.lang.util.Context;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.LoadingException;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.Localization;

import com.rekoe.mvc.IServer;
import com.rekoe.mvc.RkMvcContext;
import com.rekoe.mvc.annotation.GameResourceBy;
import com.rekoe.mvc.annotation.IocGameBy;
import com.rekoe.mvc.annotation.ServerStartBy;

public class GameLoading implements com.rekoe.mvc.Loading{

	private static final Log log = Logs.get();

	public void load(GameConfig config) {
		if (log.isInfoEnabled()) {
			log.infof("Nutz Version : %s ", Nutz.version());
			log.infof("Nutz.Mvc is initializing ...", "");
		}
		if (log.isDebugEnabled()) {
			log.debug("Web Container Information:");
			log.debugf(" - Default Charset : %s", Encoding.defaultEncoding());
			log.debugf(" - Current . path  : %s", new File(".").getAbsolutePath());
			log.debugf(" - Java Version    : %s", System.getProperties().get("java.version"));
			log.debugf(" - File separator  : %s", System.getProperties().get("file.separator"));
			log.debugf(" - Timezone        : %s", System.getProperties().get("user.timezone"));
		}
		/*
		 * 准备计时
		 */
		Stopwatch sw = Stopwatch.begin();
		try {
			/*
			 * 检查主模块，调用本函数前，已经确保过有声明 MainModule 了
			 */
			Class<?> mainModule = config.getMainModule();
			
			/*
			 * 分析本地化字符串
			 */
			evalLocalization(config, mainModule);
			/*
			 * 检查 Ioc 容器并创建和保存它
			 */
			createIoc(config, mainModule);
			/*
			 * 处理环境设置
			 */
			createContext(config);
			/*
			 * 加载资源文件
			 */
			createResourceLoader(config, mainModule);
			evalMainServerMonitor(config, mainModule);
		}
		catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("Error happend during start serivce!", e);
			throw Lang.wrapThrow(e, LoadingException.class);
		}

		// ~ Done ^_^
		sw.stop();
		if (log.isInfoEnabled())
			log.infof("RK_MMO[%s] is up in %sms", "1.0", sw.getDuration());
	}
	
	private void evalMainServerMonitor(GameConfig config, Class<?> mainModule) throws Exception 
	{
		ServerStartBy sb = mainModule.getAnnotation(ServerStartBy.class);
		if (null != sb) {
			if (log.isInfoEnabled())
				log.info("Server Start application...");
			Class<? extends IServer>[] servers = sb.value();
			for(Class<? extends IServer> classType:servers)
			{
				IServer start = evalObj(config, classType, sb.args());
				config.setAttributeIgnoreNull(IServer.class.getName(), start);
				start.initMessageExecutor();
				start.connect(config);
			}
		}
	}
	private void createIoc(GameConfig config, Class<?> mainModule) throws Exception {
		IocGameBy ib = mainModule.getAnnotation(IocGameBy.class);
		if (null != ib) {
			if (log.isDebugEnabled())
				log.debugf("@IocBy(%s)", ib.type().getName());
			Ioc ioc = Mirror.me(ib.type()).born().create(config, ib.args());
			config.getGameContext().setAttribute(RkMvcContext.GAME_IOC_KEY, ioc);
		} else if (log.isInfoEnabled())
			log.info("!!!Your application without @IocBy supporting");
	}
	private void createResourceLoader(GameConfig config, Class<?> mainModule) throws Exception {
		GameResourceBy ib = mainModule.getAnnotation(GameResourceBy.class);
		if (null != ib) {
			if (log.isDebugEnabled())
				log.debugf("@GameResourceBy(%s)", ib.type().getName());
			Mirror.me(ib.type()).born().loader(config, ib.args());
		} else if (log.isInfoEnabled())
			log.info("!!!Your application without @GameResourceBy supporting");
	}
	private void evalLocalization(GameConfig config, Class<?> mainModule) {
		Localization lc = mainModule.getAnnotation(Localization.class);
		if (null != lc) {
			if (log.isDebugEnabled())
				log.debugf("Localization message: '%s'", lc.value());
			Map<String, Map<String, Object>> msgss = Mirror.me(lc.type()).born().load(lc.value());
			config.getGameContext().setAttribute(RkMvcContext.GAME_CONFIG_KEY, msgss.get(Mvcs.DEFAULT_MSGS));
		} else if (log.isDebugEnabled()) {
			log.debug("!!!Can not find localization message resource");
		}
	}
	private static void createContext(GameConfig config) {
		// 构建一个上下文对象，方便子类获取更多的环境信息
		// 同时，所有 Filter 和 Adaptor 都可以用 ${app.root} 来填充自己
		Context context = Lang.context();

		// 载入环境变量
		for (Entry<String, String> entry : System.getenv().entrySet())
			context.set("env." + entry.getKey(), entry.getValue());
		// 载入系统变量
		for (Entry<Object, Object> entry : System.getProperties().entrySet())
			context.set("sys." + entry.getKey(), entry.getValue());

		if (log.isTraceEnabled()) {
			log.tracef(">>\nCONTEXT %s", Json.toJson(context, JsonFormat.nice()));
		}
	}
	public static <T> T evalObj(GameConfig config, Class<T> type, String[] args) {
		return Mirror.me(type).born((Object[]) args);
	}



	public void depose(GameConfig config) {
		if (log.isInfoEnabled())
			log.infof("Nutz.Mvc[%s] is deposing ...", "mmo");
		Stopwatch sw = Stopwatch.begin();
		/*try {
			GameSetUp setup = config.getAttributeAs(GameSetUp.class, GameSetUp.class.getName());
			if (null != setup)
				setup.destroy(config);
		}
		catch (Exception e) {
			throw new LoadingException(e);
		}*/
		// Done, print info
		sw.stop();
		if (log.isInfoEnabled())
			log.infof("Nutz.Mvc[%s] is down in %sms","", sw.getDuration());
	}
}
