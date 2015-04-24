package cn.com.sql.handle;

import java.sql.SQLException;
import java.util.List;

import cn.com.forecasting.mullinearregression.MultivariableLinearRegression;
import cn.com.sql.pojo.EconomyPoJo;

public class EconomyBIServiceImp implements EconomyBIService {

	private EconomyHandle handle = new EconomyHandle();
	private MultivariableLinearRegression regression = new MultivariableLinearRegression();
	/**
	 * 按年回归 多元线性回归
	 */
	@Override
	public double[] yearRegression(int year) {
		// TODO Auto-generated method stub
		double coef[] = null;
		handle.connect();
		try {
			List<EconomyPoJo> pojos = handle.selectPreviousYear(year);
			coef = regression.regressionThroughDatabase(pojos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handle.close();
		return coef;
	}

	/**
	 * 按年预测 多元线性回归
	 */
	@Override
	public double yearRegressionPredict(int year, double[] coef) {
		// TODO Auto-generated method stub
		double predictGDP = 0;
		handle.connect();
		try {
			EconomyPoJo pojo = handle.selectYearEconomy(year-1);  //找到前一年的经济数据外推
			predictGDP= regression.predictThroughDataBase(pojo, coef);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handle.close();
		return predictGDP;
	}

	/**
	 * 按月回归 多元线性回归
	 */
	@Override
	public double[] monthRegression(int year, int month) {
		// TODO Auto-generated method stub
		double[] coef = null;
		handle.connect();
		try {
			List<EconomyPoJo> pojos = handle.selectPreviousYearSameMonth(year, month);
			coef = regression.regressionThroughDatabase(pojos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handle.close();
		return coef;
	}

	/**
	 * 按月预测  多元线性回归
	 */
	@Override
	public double monthRegressionPredict(int year, int month,double[] coef) {
		// TODO Auto-generated method stub
		double predictGDP = 0;
		try {
			handle.connect();
			EconomyPoJo pojo = handle.selectMonthEconomy(year-1, month);
			handle.close();
			predictGDP = regression.predictThroughDataBase(pojo, coef);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return predictGDP;
	}

	@Override
	public double realYearValue(int year) {
		// TODO Auto-generated method stub
		double gdp=0;
		try {
			handle.connect();
			EconomyPoJo pojo = handle.selectYearEconomy(year);
			handle.close();
			gdp = pojo.getCityGDP();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gdp;
	}

	@Override
	public double realMonthValue(int year, int month) {
		// TODO Auto-generated method stub
		double gdp = 0;
		handle.connect();
		try {
			EconomyPoJo pojo = handle.selectMonthEconomy(year, month);
			gdp = pojo.getCityGDP();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handle.close();
		return gdp;
	}

	@Override
	public double[] yearBPTrain() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] monthBPTrain() {
		// TODO Auto-generated method stub
		return null;
	}

}
