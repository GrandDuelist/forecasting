package cn.com.test;

import java.sql.SQLException;
import java.util.List;

import cn.com.forecasting.mullinearregression.MultivariableLinearRegression;
import cn.com.preprocessing.AttributeFilter;
import cn.com.preprocessing.excel.ColumnPoJo;
import cn.com.preprocessing.excel.ExcelReader;
import cn.com.preprocessing.math.MathCalculation;
import cn.com.sql.handle.EconomyHandle;

public class SqlTest {
	
	public static void main(String[] args){
		EconomyHandle handle = new EconomyHandle();
		try {
		/*	handle.connect();
			handle.selectAll();
			handle.close();*/
			
			//插入excel数据到sql
		/*
			ExcelReader reader = new ExcelReader("../../data/data.xls");
			AttributeFilter filter = new AttributeFilter(reader);
		
			
			reader.readExcelFile();
			List<ColumnPoJo> xColumnPoJo = filter.getAllDataWithCoefficient("上海", 6);
			ColumnPoJo yColumnPoJo = reader.getTargetCol("上海", 6);
			
			reader.insertDataFromExcel(xColumnPoJo, yColumnPoJo);
			reader.closeInputStream();*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
