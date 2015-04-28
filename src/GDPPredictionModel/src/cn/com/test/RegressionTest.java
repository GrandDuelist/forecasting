package cn.com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.com.forecasting.mullinearregression.MultivariableLinearRegression;
import cn.com.preprocessing.AttributeFilter;
import cn.com.preprocessing.excel.ColumnPoJo;
import cn.com.preprocessing.excel.ExcelReader;
import cn.com.preprocessing.math.MathCalculation;
import cn.com.sql.handle.EconomyBIServiceImp;
import cn.com.sql.handle.EconomyHandle;
import cn.com.sql.handle.EconomyType;
import cn.com.sql.handle.GDPBIService;
import cn.com.sql.handle.TaxBIService;
import cn.com.sql.pojo.EconomyPoJo;

public class RegressionTest {
	

	public static void main(String[] args)
	{
		
		ExcelReader reader = new ExcelReader("../../data/data.xls");
		AttributeFilter filter = new AttributeFilter(reader);
		MathCalculation ma = new MathCalculation();
		MultivariableLinearRegression regression = new MultivariableLinearRegression();
		try {
		//	reader.readExcelFile();
			/*List<ColumnPoJo> xColumnPoJo = filter.getAllDataWithCoefficient("上海", 6);
			ColumnPoJo yColumnPoJo = reader.getTargetCol("上海", 6);*/
			EconomyHandle handle = new EconomyHandle();
			handle.connect();
			List<EconomyPoJo> pojos = handle.selectAllYear();
			handle.close();
			
			// 分支1
			/*
			ColumnPoJo yColumnPoJo = new ColumnPoJo();
			List<ColumnPoJo> xColumnPoJo = reader.transFromSqlToColumn(pojos, yColumnPoJo);
			
			int numberX = xColumnPoJo.size();
			double[] judgeData = new double[4];
			double[] v = new double[numberX];
			double[] coef = regression.regressionByColumnPoJo(xColumnPoJo, yColumnPoJo, judgeData, v);
			ColumnPoJo result = new ColumnPoJo();
			regression.evaluation(xColumnPoJo, yColumnPoJo, result, coef);
			reader.closeInputStream();
			*/
			
			//分支2
			/**
			 double[] coef = regression.regressionThroughDatabase(pojos,EconomyType.TAX);
			 double yearGDP=regression.predictThroughDataBase(pojos.get(pojos.size()-3), coef);
			 */
			
			//分支3
			GDPBIService service = new EconomyBIServiceImp();
			double yearGDP = service.regressionPredictGDP(2012);
			 System.out.println(yearGDP);
			 
			TaxBIService tax = new EconomyBIServiceImp();
			double yearTax = tax.regressionPredictTax(2012);
			System.out.println(yearTax);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
