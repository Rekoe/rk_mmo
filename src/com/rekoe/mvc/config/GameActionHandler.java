package com.rekoe.mvc.config;



/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class GameActionHandler{

	private com.rekoe.mvc.Loading loading;
	private GameConfig config;
	
	public GameActionHandler(GameConfig config) {
		this.config = config;
		this.loading = config.createLoading();
		loading.load(config);
		initLineServer();
	}
	
	private void initLineServer(){
		
	}
	public boolean handle() {
		return true;
	}

	public void depose() {
		loading.depose(config);
	}
}
