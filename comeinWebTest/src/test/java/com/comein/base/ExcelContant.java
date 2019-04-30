package com.comein.base;  
 
/** 
 * @ClassName: LoginContant
 * @description: 配置Excel文件中的参数类
 * @author: your name
 * @Date: 2019年4月22日 下午5:15:14
 */

public class ExcelContant {
	     // 读取文件的文件夹路径
	public static final String TextPath = "C:/Users/Administrator/desktop/";
		// 读取的文件名
	public static final String fileName = "pageElements.xls";
		// 读取的文件的底部sheet的值
	public static final Integer suiteSheet = 0;
	public static final Integer loginSheet = 1;
	public static final Integer userInfoSheet = 2;
		// 用例执行的结果定义
	public static final String pass = "PASS";
	public static final String fail = "FAIL";
		
		//suite需要用到的参数（对应值所在的列）
	public static final int suiteTestIdCol = 0;
	public static final int suiteRunmodeCol = 2;
	public static final int suiteResultCol = 3;
		
		// case需要用到的参数（对应值所在的列）
	public static final int caseTestIdCol = 0;
	public static final int caseKeywordCol = 1;
	public static final int casePageElementCol = 3;
	public static final int casePageValueCol = 4;
	public static final int caseResultCol = 5;


}
