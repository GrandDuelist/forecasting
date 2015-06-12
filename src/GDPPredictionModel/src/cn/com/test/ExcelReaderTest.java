package cn.com.test;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;

import cn.com.preprocessing.io.ExcelReader;

public class ExcelReaderTest {
	
	public static void main(String[] args)
	{
		
		ExcelReader reader = new ExcelReader("../../data/data.xls");
		
		
		try {
			reader.readExcelFile();
			HSSFRow row = reader.getTargetRow("上海", 8);
			reader.printRowData(row);
			reader.closeInputStream();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("test");
	}

}
