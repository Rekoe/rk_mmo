var ioc = {
	dataSource : {
		type : "com.alibaba.druid.pool.DruidDataSource",
		events : {
			depose : 'close'
		},
		fields : {
			// 请修改下面的数据库连接信息
			url :{java : '$conf.get("db_url")'},
			username : {java : '$conf.get("db_user")'},
			password : {java : '$conf.get("db_passwd")'},
			filters : "stat",
			maxActive : 20,
			validationQuery : "SELECT 'x'",
			testWhileIdle : true,
			testOnBorrow : false,
			testOnReturn : false
		}
	},

	dao : {
		type : 'org.nutz.dao.impl.NutDao',
		args : [ {
			refer : 'dataSource'
		} ]
	}
};