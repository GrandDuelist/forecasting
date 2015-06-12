package cn.com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.com.forecasting.DAO.EconomyDAO;
import cn.com.forecasting.model.MultivariableLinearRegression;
import cn.com.forecasting.pojo.ColumnPoJo;
import cn.com.forecasting.pojo.EconomyPoJo;
import cn.com.forecasting.service.GDPBIService;
import cn.com.forecasting.service.TaxBIService;
import cn.com.preprocessing.io.ExcelReader;
import cn.com.preprocessing.math.AttributeFilter;
import cn.com.preprocessing.math.MathCalculation;
import cn.com.sql.handle.EconomyBIServiceImp;
import cn.com.sql.handle.EconomyType;

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
			EconomyDAO handle = new EconomyDAO();
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
			for(int j=2001;j<2013;j++){
			for(int i=1;i<13;i++){
				double monthGDP = service.regressionPredictGDP(j,i);
				double real = service.realMonthValueGDP(j,i);
				double error =  service.aberration(monthGDP, real);
				System.out.println(j+"年"+i+"月"+" "+monthGDP+" "+real+" "+error);
			 
			}
			}
			/* 
			TaxBIService tax = new EconomyBIServiceImp();
			double yearTax = tax.regressionPredictTax(2012);
			System.out.println(yearTax);
*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
