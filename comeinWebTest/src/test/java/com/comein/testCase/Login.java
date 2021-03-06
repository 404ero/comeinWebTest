package com.comein.testCase;

import java.util.logging.Logger;
import org.testng.annotations.Test;

import com.comein.base.DriverBase;
import com.comein.base.ExcelContant;
import com.comein.base.LoginKeyword;
import com.comein.utils.DataStore;
import com.comein.utils.ReadExcel;
 

/** 
 * @ClassName: Login
 * @description: 
 * @author: your name
 * @Date: 2019年4月18日 上午10:57:43
 */

public class Login {
	public static LoginKeyword loginkeyword;
	public static Logger logger = Logger.getLogger(Login.class.getName());
	public static String excelFileUrl = ExcelContant.TextPath +ExcelContant.fileName;
	public Login () {
		loginkeyword = new LoginKeyword(new DriverBase(DataStore.startExplore));
	}
	@Test
	public void test() {
		ReadExcel re = new ReadExcel();
		re.ReadExcelValue(excelFileUrl, loginkeyword , logger, ExcelContant.loginSheet);
	}
}
