package com.comein.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/** 
 * @ClassName: proUtil
 * @description: 读取文件工具类
 * @author: yongChen
 * @Date: 2019年4月18日 上午10:57:43
 */
public class ProUtil {
	private Properties prop;
	private String filePath;
	/**
	 * 读取文件工具类（暂没有用到）
	 */
	public ProUtil(String filePath) {
		this.filePath = filePath;
		this.prop = readProperties();
	}
	/**
	 * 读取文件
	 */
	private Properties readProperties(){
		Properties properties = new Properties();
		try {
			InputStream input = new FileInputStream(filePath);
			BufferedInputStream bf = new BufferedInputStream(input);
			properties.load(bf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
		
	}
	
	public String getPro(String key){
		if(prop.containsKey(key)){
			String value = prop.getProperty(key);
			return value;
		}else{
			System.out.println("key不存在");
			return "";
		}
	}
	public void writePro(String key,String value){
		prop.setProperty(key, value);
	}
}
