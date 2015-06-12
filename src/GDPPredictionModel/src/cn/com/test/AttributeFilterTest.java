package cn.com.test;

import java.io.IOException;

import cn.com.forecasting.pojo.ColumnPoJo;
import cn.com.preprocessing.io.ExcelReader;
import cn.com.preprocessing.math.AttributeFilter;
import cn.com.preprocessing.math.MathCalculation;

public class AttributeFilterTest {
	
	public static void main(String[] args)
	{
		
		ExcelReader reader = new ExcelReader("../../data/data.xls");
		AttributeFilter filter = new AttributeFilter(reader);
		MathCalculation ma = new MathCalculation();
		try {
			reader.readExcelFile();
			filter.getAllDataWithCoefficient("上海", 6);
			reader.closeInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
