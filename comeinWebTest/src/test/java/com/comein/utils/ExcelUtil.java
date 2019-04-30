package com.comein.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

/** 
 * @ClassName: ExcelUtil
 * @description: Excel读写操作工具类
 * @author: your name
 * @Date: 2019年4月18日 上午10:57:43
 */

public class ExcelUtil {
	public static HSSFSheet excelSheet;
	public static HSSFWorkbook excelBook;
	public static HSSFRow row;
	public static HSSFCell cell;
	
	/**
	 *  加载excel
	 * @param path excel文件路径
	 */
	public static void setExcelFile(String path) {
		FileInputStream excelFile;
		
		try {
			excelFile = new FileInputStream(path);
			excelBook = new HSSFWorkbook(excelFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取excel中对应单元格的值
	 * @param rownum 行（从0开始）
	 * @param cellnum 列（从0开始）
	 * @param sheetName sheet名
	 * @return
	 */
	public static String getCellData(int rownum,int cellnum,int sheetNum) {
		excelSheet = excelBook.getSheetAt(sheetNum);
		cell = excelSheet.getRow(rownum).getCell(cellnum);
		String cellData = getCellValue(cell);
		return cellData;
	}
	//单元格内容类型转换为字符串
	public static String getCellValue(Cell cell) {
        String cellValue = "";
        if(cell == null){
        	return cellValue;
        }else{
        // 以下是判断数据的类型
        switch (cell.getCellTypeEnum()) {
            case NUMERIC: // 数字
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                } else {
                    DataFormatter dataFormatter = new DataFormatter();
                    cellValue = dataFormatter.formatCellValue(cell);
                }
                break;
            case STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN: // Boolean
                cellValue = cell.getBooleanCellValue() + "";
                break;
            case FORMULA: // 公式
                cellValue = cell.getCellFormula() + "";
                break;
            case BLANK: // 空值
                cellValue = "";
                break;
            case ERROR: // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
      }
    }
	
	/**
	 *  将测试结果写入excel
	 * @param result 测试结果
	 * @param rownum 行（从0开始）
	 * @param cellnum 列（从0开始）
	 * @param path excel文件路径
	 * @param sheetName sheet名
	 */
	public static void setCellData(String result,int rownum,int cellnum,String path,int sheetNum) {
		try {
			excelSheet = excelBook.getSheetAt(sheetNum);
			row = excelSheet.getRow(rownum);
			cell = row.getCell(cellnum);
			if (cell == null) {
				cell = row.createCell(cellnum);
				cell.setCellValue(result);
			} else {
				cell.setCellValue(result);
			}
			FileOutputStream fileOut = new FileOutputStream(path);
			excelBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			excelBook = new HSSFWorkbook(new FileInputStream(path));
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	/**
	 * 获取excel的sheet的第一行
	 * @param sheetName
	 * @return
	 */
	public static int getFirstRownum(int sheetNum) {
		int row = 0;
		try {
			excelSheet = excelBook.getSheetAt(sheetNum);
			row = excelSheet.getFirstRowNum();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return row;
	}
	/**
	 * 获取excel的sheet的最后一行
	 * @param sheetName
	 * @return
	 */
	public static int getLastRownum(int sheetNum) {
		int row = 0;
		try {
			excelSheet = excelBook.getSheetAt(sheetNum);
			row = excelSheet.getLastRowNum();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return row;
	}
	
	/**
	 * 获取excel的sheet的最后一行
	 * @param sheetName
	 * @return
	 */
	public static int getPhysicalNumberOfRows(int sheetNum) {
		int row = 0;
		try {
			excelSheet = excelBook.getSheetAt(sheetNum);
			row = excelSheet.getPhysicalNumberOfRows();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return row;
	}
	

}
