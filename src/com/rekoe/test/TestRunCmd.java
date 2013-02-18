package com.rekoe.test;

/**
 * @author 科技㊣²º¹³
 * Feb 18, 2013 8:10:54 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class StreamDrainer implements Runnable {
	private InputStream ins;

	public StreamDrainer(InputStream ins) {
		this.ins = ins;
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					ins));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

public class TestRunCmd {

	public static void main(String[] args) {
		// run.exec("cmd /k start E:\\backback.bat");
		// String[] cmd = new String[] { "cmd.exe", "/C",
		// "wmic process get name" };
		// try {
		// Process process = Runtime.getRuntime().exec(cmd);
		//
		// new Thread(new StreamDrainer(process.getInputStream())).start();
		// new Thread(new StreamDrainer(process.getErrorStream())).start();
		//
		// process.getOutputStream().close();
		//
		// int exitValue = process.waitFor();
		// System.out.println("返回值：" + exitValue);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		Runtime run = Runtime.getRuntime();
		try {
			run.exec("cmd /k start D:\\Tools\\redis-2.0.2\\redis-server.exe");
		} catch (IOException e) {
			System.out.println("备份数据库失败,堆栈信息如下");
			e.printStackTrace();
		}

	}

}
