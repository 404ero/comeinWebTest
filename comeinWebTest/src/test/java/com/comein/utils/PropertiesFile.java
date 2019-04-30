package com.comein.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/** 
 * @ClassName: PropertiesFile
 * @description: 
 * @author: your name
 * @Date: 2019年4月22日 下午4:57:51
 */

public class PropertiesFile {
	private static Properties pps;
	public static String read(String key) {
		pps = new Properties();
		InputStream in = null;
		String configFileUrl = "src/test/resourse/Config.properties";
		try {
			// 读取config文件
			File file = new File(configFileUrl);
			in = new BufferedInputStream(new FileInputStream(file));
			pps.load(in);// 加载属性列表
			if (pps.containsKey(key)) {
				String value = pps.getProperty(key);
				return value; // 返回读取的内容
			} else {
				System.out.println("没有读取到"+key);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	public static void writePro(String key,String value){
		pps = new Properties();
		pps.setProperty(key, value);
	}
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		PropertiesFile pf = new PropertiesFile();
		String ss = pf.read("user");
		System.out.println(ss);
	}

}
