package com.comein.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.comein.base.DriverBase;
/** 
 * @ClassName: PageBase
 * @description: 
 * @author: your name
 * @Date: 2019年4月18日 上午10:57:43
 */
//封装页面元素
public class PageBase {
	public DriverBase driverBase;
	public static String configurationUrl;
    public PageBase(DriverBase driverBase) {
	    this.driverBase = driverBase;
        }
	    public static By getByElement(String pe){
			List<String> value = Configuration(pe,">");
			String localType = value.get(0);
			String localvalue = value.get(1);
			if(localType.equals("id")){
				return By.id(localvalue);
			}else if(localType.equals("name")){
				return By.name(localvalue);
			}else if(localType.equals("className")){
				return By.className(localvalue);
			}else if(localType.equals("xpath")){
				return By.xpath(localvalue);
			}else if(localType.equals("linkText")){
				return By.linkText(localvalue);
			}else if(localType.equals("partialLinkText")){
				return By.partialLinkText(localvalue);
			}else if(localType.equals("tagName")){
				return By.tagName(localvalue);
			}else{
				return By.cssSelector(localvalue);
			}
		}
		//封装webelement
		public WebElement getWebElement(By by){
			WebElement element = driverBase.getDriver().findElement(by);
			return element;
		}
		//将类似name>btn这样的元素形式，拆分开来
		public static List<String> Configuration(String pe,String splitType){
			List<String> list=new ArrayList<String>();
			if(splitType.isEmpty()){
				list.add(pe);
				return list;
			}else{
				String localType = pe.split(splitType)[0];
				System.out.println(localType);
				String localvalue = pe.split(splitType)[1];
				System.out.println(localvalue);
				list.add(localType);
				list.add(localvalue);
				return list;
			}
		}
	    //判断元素是否存在于页面
	    public  boolean isElementExist(String key, int timeoutSeconds) {
	    	driverBase.driver.manage().timeouts().implicitlyWait(timeoutSeconds, TimeUnit.SECONDS);
	        try {
	        	getWebElement(getByElement(key));
	            return true;
	        } catch (Exception e) {
	            return false;
	        } finally {
	        	driverBase.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	        }
	    }
	    
	    //根据指定文件获取文件上的信息
	    public String getInfoByUtil(String key){
	    	configurationUrl = "src/test/resources/elements.properties";
	    	List<String> list  = Configuration(key,"");
			return list.get(0);
	    }
	    
		//封装点击事件
		public void click(WebElement element){
			if(!element.equals(null)){
				element.click();
			}else{
				System.out.println("无法点击该元素！");
			}
		}
		//封装添加值的方法
		public void sendKeys(WebElement element,String value){
			if(!element.equals(null)){
				element.sendKeys(value);;
			}else{
				System.out.println(element+":无法添加该元素!");
			}
		}
		//封装isDisplayed()
		public boolean assertElement(WebElement element){
			return element.isDisplayed();
		}
		//封装Action，鼠标操作事件
		public void mouseAction(WebElement xElement,String mouseActionType,WebElement...elements){
			Actions action = new Actions(driverBase.driver);
			if(mouseActionType.equals("left-hander")){
				//左击
				action.click(xElement);
			}else if(mouseActionType.equals("right-hander")){
				//右击
				action.contextClick(xElement);
			}else if(mouseActionType.equals("double-click")){
				//双击
				action.doubleClick(xElement);
			}else if(mouseActionType.equals("drag-drop")){
				action.dragAndDrop(xElement,elements[0]); 
			}else if(mouseActionType.equals("click-hold")){
				action.clickAndHold(xElement);
			}else if(mouseActionType.equals("move-to")){
				//鼠标移到该处
				action.moveToElement(xElement);
			}
			
		}
		//鼠标下拉找到元素就点击
		public void pullDownList(WebElement element){
			Actions action=new Actions(driverBase.driver);
				action.moveToElement(element);
				((JavascriptExecutor) driverBase.getDriver()).executeScript("arguments[0].scrollIntoView();",element);
				element.click();
		}
		//封装Text
		public String getText(WebElement element){
			return element.getText();
		}
		//打Debugger，针对弹出DIV时:setTimeout(function(){debugger;},4000)
		
		//封装获取子级元素
		public WebElement getSubLevelElement(By parentBy,By nodeBy){
			WebElement ele = getWebElement(parentBy);
			ele.findElement(nodeBy);
			return ele.findElement(nodeBy);
		}
}
