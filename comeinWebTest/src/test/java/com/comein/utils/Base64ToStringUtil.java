package com.comein.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.JavascriptExecutor;

import com.alibaba.fastjson.JSONObject;
import com.comein.base.DriverBase;

/** 
 * @ClassName: Base64ToStringUtil
 * @description: 
 * @author: your name
 * @Date: 2019年4月30日 上午9:44:16
 */

public class Base64ToStringUtil {
	
	public static void Base64toLogined(DriverBase driverBase) {
		//post请求登陆状态，获得已登录状态的token等信息，通过Base64拼成浏览器Local Storage缓存区的userInfo的已登录状态字段信息
		driverBase.get("https://testnet.comein.cn/");
		String tokenOfLogined = "";
		String postResult = SendRquest.sendPost(DataStore.loginRequestUrl,DataStore.postParam);
		//通过JSONObject解析JSON，得到webtoken的value
		String webtoken = JSONObject.parseObject(JSONObject.parseObject(postResult).getString("data")).getString("webtoken"); 
		System.out.println(webtoken);
		String requestValue = "{\"avatarurl\":\"https://testimage.comein.cn/comein-files/img/2018-08-29/dd5f3ae0-4bc4-447d-b425-e2c47014af47/dd5f3ae0-4bc4-447d-b425-e2c47014af47.jpg\","
				+ "\"logintype\":\"1\",\"phonenumber\":\"13828840324\",\"status\":3,\"uid\":\"1494613\",\"uname\":\"陈勇测试账号VIP最账号我11111只是想试试155554；f?f?f哈哈哈哈哈哈哈和嘟嘟嘟嘟如何\","
				+ "\"userType\":[],\"userinfo\":{\"areacode\":\"+86\",\"avatarurl\":\"https://testimage.comein.cn/comein-files/img/2018-08-29/dd5f3ae0-4bc4-447d-b425-e2c47014af47/dd5f3ae0-4bc4-447d-b425-e2c47014af47.jpg\","
				+ "\"business\":\"财务\",\"company\":\"乐唯科技有限公司222444哈哈哈斤斤计较斤斤计较哈哈哈哈沟沟壑壑不斤斤计较斤斤计较斤斤计较155\",\"completed\":1,\"fanscount\":54,\"haspassword\":1,\"id\":1494613,\"invitecode\":\"8561187\","
				+ "\"ipaddress\":\"119.139.199.197\",\"isactive\":1,\"isdel\":0,\"ivstatus\":-1,\"level\":3,\"location\":\"阿拉善盟\",\"login\":\"JM1494613\",\"loginsalt\":\"37835\",\"loginupdate\":1,\"occupation\":\"开发工程师！\",";
		String jsonVlaue = requestValue
				+ "\"onlinestatus\":1,\"os\":\"后台创建\",\"phonenumber\":\"13828840324\",\"sex\":2,\"sign\":\"开发工程师开发工程师开发工程师开发工程师开发工程师11111俄亥俄给你寄 logo名明明你哦44444反反复复 vv334344444个一一补钙666）更丰富多彩方太吞吞吐吐仍然让人头疼头疼头111\","
				+ "\"status\":3,\"subcount\":37,\"token\":\""+webtoken+"\",\"type\":0,\"uid\":\"1494613\",\"uname\":\"陈勇测试账号VIP最账号我11111只是想试试155554；f?f?f哈哈哈哈哈哈哈和嘟嘟嘟嘟如何\",\"uuid\":\"1494613\","
				+ "\"webtoken\":\""+webtoken+"\"},\"webtoken\":\""+webtoken+"\"}";
		Base64 base64 = new Base64();
		try {
			tokenOfLogined = base64.encodeToString(jsonVlaue.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			Log.info("------------------登陆token信息转Base64失败----------------");
			e.printStackTrace();
		}
		//添加userInfo和usersave的值至Local Storage缓存区
		String userinfo="window.localStorage.setItem('userinfo',"+"'"+tokenOfLogined+"'"+")";
		String usersave="window.localStorage.setItem('usersave','1')";
		JavascriptExecutor jse = (JavascriptExecutor) driverBase.getDriver();
		//调用方法执行JS代码
		jse.executeScript(userinfo);
		jse.executeScript(usersave);
	}
}
