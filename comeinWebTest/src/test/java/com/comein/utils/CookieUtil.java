package com.comein.utils;

import java.util.Set;

import org.openqa.selenium.Cookie;

import com.comein.base.DriverBase;

/** 
 * @ClassName: CookieUtil
 * @description: 
 * @author: your name
 * @Date: 2019年4月18日 上午10:57:43
 */

public class CookieUtil {
	public DriverBase driverBase;
	
	public CookieUtil(DriverBase driverBase) {
		this.driverBase = driverBase;
	}
	public void setCookie(){
		String value = PropertiesFile.read("JSESSIONID");
		Cookie cookie = new Cookie("JSESSIONID",value,"testserver.comein.cn","/ComeinManager",null);
		System.out.println(driverBase.getDriver());
		driverBase.setCookie(cookie);
	}
	//获取cookie
	public void writeCookie(){
		Set<Cookie> cookies = driverBase.getCookie();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("apsid")){
				PropertiesFile.writePro(cookie.getName(), cookie.getValue());
			}
		}
	}

}
