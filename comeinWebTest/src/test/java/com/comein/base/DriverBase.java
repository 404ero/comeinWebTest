package com.comein.base;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/** 
 * @ClassName: DriverBase
 * @description: 封装Driver方法类
 * @author: your name
 * @Date: 2019年4月18日 上午10:57:43
 */

public class DriverBase {
	WebDriver driver;
	//封装Driver
	public DriverBase(String browser) {
		SelectDriver sd = new SelectDriver();
		this.driver = sd.driverName(browser);
	}
	
	/**
	 *  打开url
	 */
	public void get(String url){
		driver.manage().window().maximize(); 
		driver.get(url);
	}
	
	/**
	 *  封装stop()
	 */
	public void close(){
		driver.close();
	}
	
	/**
	 *  网页返回
	 */
	public void back(){
		driver.navigate().back();
	}
	
	/**
	 *  返回指定URL
	 */
	public void backToUrl(String url){
		driver.navigate().to(url);
	}
	
	/**
	 *  sleep()封装
	 */
	public void sleep(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *  封装driver
	 */
	public WebDriver getDriver() {
		return driver;
	}
	
	/**
	 *  封装By
	 * @param by 获取By类型的元素
	 */
	public List<WebElement> discoverElements(By by){
		List<WebElement> elements = driver.findElements(by);
		return elements;
	}
	
    /**
	 *  alert框处理
	 */
	public void switchToAlert(){
		driver.switchTo().alert();
	}
	
    /**
	 *  模态框处理
	 */
	public void switchToModel(){
		driver.switchTo().activeElement();
	}
	
	/**
	 *  Cookie操作
	 *  删除全部Cookie
	 */
	public void deleteCookie(){
		driver.manage().deleteAllCookies();
	}
	/**
	 *  Cookie操作
	 *  添加Cookie
	 */
	public void setCookie(Cookie cookie){
		System.out.println(driver);
		driver.manage().addCookie(cookie);
	}
	/**
	 *  Cookie操作
	 *  获取cookie
	 */
	public Set<Cookie> getCookie(){
		Set<Cookie> cookies = driver.manage().getCookies();
		return cookies;
	}
}
