package cn.com.sql.handle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import cn.com.forecasting.bp.DataMapping;
import cn.com.forecasting.bp.EconomyBP;
import cn.com.forecasting.mullinearregression.MultivariableLinearRegression;
import cn.com.sql.pojo.EconomyPoJo;

public class EconomyBIServiceImp implements EconomyBIService {

	private EconomyHandle handle = new EconomyHandle();
	private MultivariableLinearRegression regression = new MultivariableLinearRegression();
	private EconomyBP bp = new EconomyBP(DataMapping.numberX,
			(int) DataMapping.numberX / 2, DataMapping.numberY);

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
			EconomyPoJo pojo = handle.selectYearEconomy(year - 1); // 找到前一年的经济数据外推
			predictGDP = regression.predictThroughDataBase(pojo, coef);
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
			List<EconomyPoJo> pojos = handle.selectPreviousYearSameMonth(year,
					month);
			coef = regression.regressionThroughDatabase(pojos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handle.close();
		return coef;
	}

	/**
	 * 按月预测 多元线性回归
	 */
	@Override
	public double monthRegressionPredict(int year, int month, double[] coef) {
		// TODO Auto-generated method stub
		double predictGDP = 0;
		try {
			handle.connect();
			EconomyPoJo pojo = handle.selectMonthEconomy(year - 1, month);
			handle.close();
			predictGDP = regression.predictThroughDataBase(pojo, coef);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return predictGDP;
	}

	/**
	 * 真实年份数据
	 */
	@Override
	public double realYearValue(int year) {
		// TODO Auto-generated method stub
		double gdp = 0;
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

	/**
	 * 真实月份数据
	 */
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
	public double yearBPTrain(int year) {
		// TODO Auto-generated method stub
		double result = 0;
		handle.connect();
		try {
			EconomyPoJo pojo = handle.selectYearEconomy(year);
			int numberX = handle.changeGDPAttributeToArray(pojo).length;
			EconomyBP bp = new EconomyBP(numberX, (int) (numberX / 2), 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		handle.close();
		return result;
	}

	@Override
	public double monthBPTrain(int year, int month) {
		// TODO Auto-generated method stub
		double result = 0;
		EconomyBP bp = new EconomyBP(8, 4, 1);
		return result;
	}

	/**
	 * 训练所有数据
	 */
	public void train() {
		try {
			this.trainYear();
			this.trainMonth();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对数据库的所有数据进行查询后计算参数，并以对象的方式存在于文件中
	 * 
	 * @throws SQLException
	 */
	public void trainYear() throws Exception {
		handle.connect();
		List<EconomyPoJo> pojos = handle.selectAllYear();
		handle.close();
		for (int i = 0; i < pojos.size(); i++) {
			EconomyPoJo pojo = pojos.get(i);
			this.trainSpecificYear(pojo.getYear() + 1);
		}
	}

	public void trainMonth() throws Exception {
		handle.connect();
		List<EconomyPoJo> pojos = handle.selectAllMonth();
		handle.close();
		for (int i = 0; i < pojos.size(); i++) {
			EconomyPoJo pojo = pojos.get(i);
			this.trainSpecificMonth(pojo.getYear() + 1, pojo.getMonth());
		}
	}

	/**
	 * 指定预测某年数据，训练改年之前的数据并存于本地对象W中
	 * 
	 * @param year
	 * @throws SQLException
	 * @throws IOException
	 */
	public void trainSpecificYear(int year) throws Exception {
		handle.connect();
		// 拿到改年的所有数据进行训练
		List<EconomyPoJo> previousPoJos = handle.selectPreviousYear(year);
		handle.close();
		if (previousPoJos.size() > DataMapping.minPreviousDataNumber) {
			this.bp.trainOnlyByNormalizedEconomy(previousPoJos,
					DataMapping.bpTrainTime);
			this.bp.outputBaseBpToFile(year); // 存到硬盘上
		}
	}

	public void trainSpecificMonth(int year, int month) throws Exception {
		handle.connect();
		List<EconomyPoJo> previousPoJos = handle.selectPreviousYearSameMonth(
				year, month);
		handle.close();
		if (previousPoJos.size() > DataMapping.minPreviousDataNumber) {
			this.bp.trainOnlyByNormalizedEconomy(previousPoJos,
					DataMapping.bpTrainTime);
			this.bp.outputBaseBpToFile(year, month);
		}
	}
	
	public double bpPredict(int year) throws Exception{
		handle.connect();
		EconomyPoJo pojo = handle.selectYearEconomy(year-1);//前一年的
		handle.close();
		this.bp.readBaseBpFromFile(year); //读取该年的权值数据
		return this.bp.test(pojo)[0];
		
	}
	public double bpPredict(int year, int month) throws Exception{
		handle.connect();
		EconomyPoJo pojo = handle.selectMonthEconomy(year-1, month);
		handle.close();
		this.bp.readBaseBpFromFile(year,month); //读取该年的权值数据
		return this.bp.test(pojo)[0];
	}
}
