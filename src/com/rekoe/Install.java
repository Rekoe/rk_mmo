package com.rekoe;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.Ioc;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.rekoe.ioc.db.domain.User;
import com.rekoe.ioc.db.service.UserService;
import com.rekoe.ioc.resource.RegisterResource;
import com.rekoe.mvc.GameSetup;
import com.rekoe.mvc.config.GameConfig;

/**
 * @author 科技㊣²º¹³
 * Feb 18, 2013 9:42:00 AM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class Install implements GameSetup {

	private final static Log log = Logs.get();
	@Override
	public void initResource(GameConfig config) {
		install(config.getIoc());
		log.info("load Resource");
		RegisterResource reg = config.getIoc().get(RegisterResource.class);
		reg.load();
	}

	@Override
	public void checkResource(GameConfig config) {
		log.info("check Resource");
		RegisterResource reg = config.getIoc().get(RegisterResource.class);
		reg.check();
	}

	/**
	 * java的Runtime.getRuntime().exec(commandStr)可以调用执行cmd指令。
	 * cmd /c dir 是执行完dir命令后关闭命令窗口。
	 * cmd /k dir 是执行完dir命令后不关闭命令窗口。
	 * cmd /c start dir 会打开一个新窗口后执行dir指令，原窗口会关闭。
	 * cmd /k start dir 会打开一个新窗口后执行dir指令，原窗口不会关闭。
	 */
	private void install(Ioc ioc) {
		//启动cache 缓存
		Runtime run = Runtime.getRuntime();
		try {
			run.exec("cmd /k start D:\\Tools\\redis-2.0.2\\redis-server.exe");
		} catch (Throwable e) {
			Lang.wrapThrow(e);
		}
		// 初始化数据库
		log.debug("初始化数据库...");
		Dao dao = ioc.get(Dao.class, "dao");
		boolean exists = dao.exists(User.class);
		if(!exists)
		{
			dao.create(User.class, true);
			FileSqlManager fm = new FileSqlManager("init_system_h2.sql");
			List<Sql> sqlList = fm.createCombo(fm.keys());
			dao.execute(sqlList.toArray(new Sql[sqlList.size()]));

			// 清空redis
			log.debug("清空redis...");
			JedisPool pool = ioc.get(JedisPool.class, "jedisPool");
			Jedis jedis = pool.getResource();
			jedis.flushDB();
			pool.returnResource(jedis);
		}
		log.info(Json.toJson(ioc.get(UserService.class).view(2)));
	}
}
