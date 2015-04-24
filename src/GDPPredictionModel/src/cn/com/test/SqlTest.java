package cn.com.test;

import cn.com.sql.handle.EconomyHandle;
import cn.com.sql.pojo.EconomyPoJo;

public class SqlTest {
	
	public static void main(String[] args){
		EconomyHandle handle = new EconomyHandle();
		try {
			handle.connect();
			/*List<EconomyPoJo> ecs = handle.selectPreviousYear(1988);*/
			EconomyPoJo pojo = handle.selectMonthEconomy(1988,8);
//			System.out.println(ecs.size());
			
			handle.close();
			
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
