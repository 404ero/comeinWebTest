package com.comein.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.log4j.xml.DOMConfigurator;

import com.comein.base.CommonEngine;
import com.comein.base.ExcelContant;
import com.comein.utils.DataStore;
import com.comein.utils.ExcelUtil;

/** 
 * @ClassName: ReadExcel
 * @description: 封装读取Excel行列的值方法类
 * @author: your name
 * @Date: 2019年4月28日 下午2:45:57
 */

public class ReadExcel {
	public static String keyword;
	public static String pe;
	public static String value;
	public static boolean result;
	public static String suiteTestid;
	
	public void ReadExcelValue(String excelFileUrl, Object actionKeywords, Logger logger, Integer sheetNum){
		
		ExcelUtil.setExcelFile(excelFileUrl);// 加载登录Excel文件
		DOMConfigurator.configure(DataStore.logUrl);//加载log4j文件
		result = true;
		int count = 0;
		// 获取当前的sheet表格的第一行第一列的测试用例序列号的值
		String caseTestid = ExcelUtil.getCellData(ExcelUtil.getFirstRownum(sheetNum)+1, ExcelContant.suiteTestIdCol, sheetNum);
		for (int k = 1;k<=ExcelUtil.getLastRownum(ExcelContant.suiteSheet);k++) {
			suiteTestid = ExcelUtil.getCellData(k, ExcelContant.suiteTestIdCol, ExcelContant.suiteSheet);
			if(caseTestid.equals(suiteTestid)){
				count = k;
				break;
			}
		}
		//循环读取suitSheet里面的值，找出运行的场景
		for (int j = count;j<=ExcelUtil.getLastRownum(ExcelContant.suiteSheet);j++) {			
			// 读取suitesheet里面的runmode字段，如果为yes则执行该用例，No则不执行
			String runMode = ExcelUtil.getCellData(j, ExcelContant.suiteRunmodeCol, ExcelContant.suiteSheet);
			System.out.println(caseTestid);
			// 读取suitesheet里面的testsuiteID字段
			suiteTestid = ExcelUtil.getCellData(j, ExcelContant.suiteTestIdCol, ExcelContant.suiteSheet);
			if (runMode.equals("YES")) {
				logger.log(Level.INFO, "开始执行第"+j+"条");
				// 循环遍历loginsheet里面的值，找出运行的步骤
				int num = ExcelUtil.getLastRownum(sheetNum);
				for (int srownum = 1;srownum<=num;srownum++) {
					// 获取loginsheet里面的测试用例序号
					String loginTestid = ExcelUtil.getCellData(srownum, ExcelContant.caseTestIdCol, sheetNum);
					if (loginTestid.trim().equals(suiteTestid)) { // 如果loginsheet里面的测试用例序号和suitesheet里面的用例序号一致
						// 获取loginsheet里面的测试步骤序号（和loginkeyword里面的方法对应）
						keyword = ExcelUtil.getCellData(srownum, ExcelContant.caseKeywordCol, sheetNum);
						
						// 获取loginsheet里面的页面元素（id，xpath路径等）
						pe = ExcelUtil.getCellData(srownum, ExcelContant.casePageElementCol, sheetNum);
						
						// 获取loginsheet里面的值（需要输入或者对比的值）
						value = ExcelUtil.getCellData(srownum, ExcelContant.casePageValueCol, sheetNum);
						CommonEngine.Action(keyword, actionKeywords, pe, value, srownum, result);
						
						if (result == true) {// 将结果写入loginsheet
							ExcelUtil.setCellData(ExcelContant.pass, srownum, ExcelContant.caseResultCol, ExcelContant.TextPath+ExcelContant.fileName, sheetNum);
						} else {// 将结果写入loginsheet
							ExcelUtil.setCellData(ExcelContant.fail, srownum, ExcelContant.caseResultCol, ExcelContant.TextPath+ExcelContant.fileName, sheetNum);
						}
						if (result == false) {// 将结果写入suitesheet
							ExcelUtil.setCellData(ExcelContant.fail, j, ExcelContant.suiteResultCol, ExcelContant.TextPath+ExcelContant.fileName, ExcelContant.suiteSheet);
							logger.log(Level.INFO, "第"+j+"条用例执行完成");
						}
					}
				}
				if (result == true) {// 将结果写入suitesheet
					ExcelUtil.setCellData(ExcelContant.pass, j, ExcelContant.suiteResultCol, ExcelContant.TextPath+ExcelContant.fileName, ExcelContant.suiteSheet);
					logger.log(Level.INFO, "第"+j+"条用例执行完成");
				}
			} else {
				logger.log(Level.INFO, "没有要执行的用例");
				break;
			}
		}
	}

}
