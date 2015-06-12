package cn.com.test;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;

import cn.com.forecasting.pojo.ColumnPoJo;
import cn.com.preprocessing.io.ExcelReader;
import cn.com.preprocessing.math.MathCalculation;

public class ExcelReaderClolumnTest {
	
	public static void main(String[] args)
	{
		
		ExcelReader reader = new ExcelReader("../../data/data.xls");
		
		MathCalculation ma = new MathCalculation();
		try {
			reader.readExcelFile();
			ColumnPoJo column = reader.getTargetCol("上海", 6);
			ColumnPoJo c2 = reader.getTargetCol("上海", 8);
			System.out.println(ma.calcCoefficient(column.getData(), c2.getData(), c2.getNumber()));
			reader.closeInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
