package cn.com.test;

import java.io.IOException;
import java.util.List;

import cn.com.forecasting.mullinearregression.MultivariableLinearRegression;
import cn.com.preprocessing.AttributeFilter;
import cn.com.preprocessing.excel.ColumnPoJo;
import cn.com.preprocessing.excel.ExcelReader;
import cn.com.preprocessing.math.MathCalculation;

public class RegressionTest {
	

	public static void main(String[] args)
	{
		
		ExcelReader reader = new ExcelReader("../../data/data.xls");
		AttributeFilter filter = new AttributeFilter(reader);
		MathCalculation ma = new MathCalculation();
		MultivariableLinearRegression regression = new MultivariableLinearRegression();
		try {
			reader.readExcelFile();
			List<ColumnPoJo> xColumnPoJo = filter.getAllDataWithCoefficient("上海", 6);
			ColumnPoJo yColumnPoJo = reader.getTargetCol("上海", 6);
			
			int numberX = xColumnPoJo.size();
			double[] coef = new double[numberX+1];
			double[] judgeData = new double[4];
			double[] v = new double[numberX];
			
			regression.regressionByColumnPoJo(xColumnPoJo, yColumnPoJo, coef, judgeData, v);
			reader.closeInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
