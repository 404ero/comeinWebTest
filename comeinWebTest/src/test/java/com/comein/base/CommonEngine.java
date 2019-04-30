package com.comein.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/** 
 * @ClassName: CommonEngine
 * @description: 根据反射机制获取关键字类中方法的类
 * @author: your name
 * @Date: 2019年4月22日 下午5:20:54
 */

public class CommonEngine {
	/**
	 *  执行关键字中的方法
	 * @param keyword 关键字方法名称
	 * @param actionKeywords 关键字类
	 * @param pe 页面元素
	 * @param value 需要用到的值
	 * @param rownum 哪一行
	 * @param sresult 执行结果
	 */
	public static void Action(String keyword,Object actionKeywords,String pe,String value,int rownum,boolean sresult) {
		// 根据反射机制获取关键字类中的方法  
		Method[] method = actionKeywords.getClass().getMethods();
		for (int i = 0; i < method.length; i++) {// 循环遍历关键字类中的方法
			if (method[i].getName().trim().equals(keyword)) { // 如果关键字类中的方法和excel关键字的方法一致
				try {
					if (pe.equals("") && value.equals("")) {
						method[i].invoke(actionKeywords);
					} else if (!(pe.equals("")) && value.equals("")) {
						method[i].invoke(actionKeywords, pe);
					} else if (pe.equals("") && !(value.equals(""))) {
						method[i].invoke(actionKeywords, value);
					} else {
						method[i].invoke(actionKeywords, pe, value);
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
