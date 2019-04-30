package com.comein.testCase;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.comein.base.DriverBase;
import com.comein.base.ExcelContant;
import com.comein.base.UserInfoKeyword;
import com.comein.utils.DataStore;
import com.comein.utils.ReadExcel;

/** 
 * @ClassName: UserInfo
 * @description: 
 * @author: your name
 * @Date: 2019年4月28日 下午10:55:49
 */

public class UserInfo {
	public static UserInfoKeyword userInfokeyword;
	public static Logger logger = Logger.getLogger(Login.class.getName());
	public static String excelFileUrl = ExcelContant.TextPath +ExcelContant.fileName;
	public UserInfo () {
		userInfokeyword = new UserInfoKeyword(new DriverBase(DataStore.startExplore));
	}
	@Test
	public void test() {
		ReadExcel re = new ReadExcel();
		re.ReadExcelValue(excelFileUrl, userInfokeyword , logger, ExcelContant.userInfoSheet);
	}

}
