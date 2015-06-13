package cn.com.sql.serviceimp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import cn.com.forecasing.type.DataMapping;
import cn.com.forecasing.type.EconomyType;
import cn.com.forecasting.DAO.EconomyDAO;
import cn.com.forecasting.model.EconomyBP;
import cn.com.forecasting.model.MixedModel;
import cn.com.forecasting.model.MultivariableLinearRegression;
import cn.com.forecasting.pojo.EconomyPoJo;
import cn.com.forecasting.service.GDPBIService;
import cn.com.forecasting.service.TaxBIService;

public class EconomyBIServiceImp implements GDPBIService, TaxBIService {

	private MixedModel mixedModel = new MixedModel();
	private EconomyDAO handle = new EconomyDAO();
	private MultivariableLinearRegression regression = new MultivariableLinearRegression();
	private EconomyBP bp = new EconomyBP(DataMapping.numberX,
			(int) DataMapping.numberX / 2, DataMapping.numberY);
	


	/**
	 * 按年回归 多元线性回归
	 */
	public double[] yearRegression(int year, EconomyType type) {
		// TODO Auto-generated method stub
		double coef[] = null;
		handle.connect();
		try {
			List<EconomyPoJo> pojos = handle.selectPreviousYear(year);
			coef = regression.regressionThroughDatabase(pojos, type);
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
	public double[] monthRegression(int year, int month, EconomyType type) {
		// TODO Auto-generated method stub
		double[] coef = null;
		handle.connect();
		try {
			List<EconomyPoJo> pojos = handle.selectPreviousYearSameMonth(year,
					month);
			coef = regression.regressionThroughDatabase(pojos, type);
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

	@Override
	public double regressionPredictGDP(int year, int month) {
		double[] coef = this.monthRegression(year, month, EconomyType.GDP);
		return this.monthRegressionPredict(year, month, coef);
	}

	@Override
	public double regressionPredictGDP(int year) {
		double[] coef = this.yearRegression(year, EconomyType.GDP);
		return this.yearRegressionPredict(year, coef);
	}

	/**
	 * 真实年份数据
	 */
	@Override
	public double realYearValueGDP(int year) {
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
	public double realMonthValueGDP(int year, int month) {
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

	public double monthBPTrain(int year, int month) {
		// TODO Auto-generated method stub
		double result = 0;
		EconomyBP bp = new EconomyBP(8, 4, 1);
		return result;
	}

	/**
	 * 训练所有数据
	 */
	@Override
	public void trainGDP() {
		try {
			this.trainYear(EconomyType.GDP);
			this.trainMonth(EconomyType.GDP);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对数据库的所有数据进行查询后计算参数，并以对象的方式存在于文件中
	 * 
	 * @throws SQLException
	 */
	public void trainYear(EconomyType type) throws Exception {
		handle.connect();
		List<EconomyPoJo> pojos = handle.selectAllYear();
		handle.close();
		for (int i = 0; i < pojos.size(); i++) {
			EconomyPoJo pojo = pojos.get(i);
			this.trainSpecificYear(pojo.getYear() + 1, type);
		}
	}

	public void trainMonth(EconomyType type) throws Exception {
		handle.connect();
		List<EconomyPoJo> pojos = handle.selectAllMonth();
		handle.close();
		for (int i = 0; i < pojos.size(); i++) {
			EconomyPoJo pojo = pojos.get(i);
			this.trainSpecificMonth(pojo.getYear() + 1, pojo.getMonth(), type);
		}
	}

	/**
	 * 指定预测某年数据，训练改年之前的数据并存于本地对象W中
	 * 
	 * @param year
	 * @throws SQLException
	 * @throws IOException
	 */
	public void trainSpecificYear(int year, EconomyType type) throws Exception {
		handle.connect();
		// 拿到改年的所有数据进行训练
		List<EconomyPoJo> previousPoJos = handle.selectPreviousYear(year);
		handle.close();
		if (previousPoJos.size() > DataMapping.minPreviousDataNumber) {
			this.bp.trainOnlyByNormalizedEconomy(previousPoJos, type,
					DataMapping.bpTrainTime);
			this.bp.outputBaseBpToFile(year, type); // 存到硬盘上
		}
	}

	public void trainSpecificMonth(int year, int month, EconomyType type)
			throws Exception {
		handle.connect();
		List<EconomyPoJo> previousPoJos = handle.selectPreviousYearSameMonth(
				year, month);
		handle.close();
		if (previousPoJos.size() > DataMapping.minPreviousDataNumber) {
			this.bp.trainOnlyByNormalizedEconomy(previousPoJos, type,
					DataMapping.bpTrainTime);
			this.bp.outputBaseBpToFile(year, month, type);
		}
	}

	@Override
	public double bpPredictGDP(int year) throws Exception {
		handle.connect();
		EconomyPoJo pojo = handle.selectYearEconomy(year - 1);// 前一年的
		handle.close();
		this.bp.readBaseBpFromFile(year, EconomyType.GDP); // 读取该年的权值数据
		return this.bp.bp.preprocessing.reverseY(bp.test(pojo)[0]);

	}

	@Override
	public double bpPredictGDP(int year, int month) throws Exception {
		handle.connect();
		EconomyPoJo pojo = handle.selectMonthEconomy(year - 1, month);
		handle.close();
		this.bp.readBaseBpFromFile(year, month, EconomyType.GDP); // 读取该年的权值数据
		return this.bp.bp.preprocessing.reverseY(bp.test(pojo)[0]);
	}

	/**
	 * 计算误差
	 * 
	 * @param realValue
	 * @param predictValue
	 * @return
	 */
	@Override
	public double aberration(double realValue, double predictValue) {
		return (Math.abs(realValue - predictValue) / realValue) * 100;
	}

	// 税务部分实现

	@Override
	public double regressionPredictTax(int year) {
		// TODO Auto-generated method stub
		double[] coef = this.yearRegression(year, EconomyType.TAX);
		return this.yearRegressionPredict(year, coef);
	}

	@Override
	public double regressionPredictTax(int year, int month) {
		// TODO Auto-generated method stub
		double[] coef = this.monthRegression(year, month, EconomyType.TAX);
		return this.monthRegressionPredict(year, month, coef);
	}

	@Override
	public void trainTax() {
		try {
			this.trainYear(EconomyType.TAX);
			this.trainMonth(EconomyType.TAX);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public double bpPredictTax(int year) throws Exception {
		handle.connect();
		EconomyPoJo pojo = handle.selectYearEconomy(year - 1);// 前一年的
		handle.close();
		this.bp.readBaseBpFromFile(year, EconomyType.TAX); // 读取该年的权值数据
		return this.bp.bp.preprocessing.reverseY(bp.test(pojo)[0]);
	}

	@Override
	public double bpPredictTax(int year, int month) throws Exception {
		handle.connect();
		EconomyPoJo pojo = handle.selectMonthEconomy(year - 1, month);
		handle.close();
		this.bp.readBaseBpFromFile(year, month, EconomyType.TAX); // 读取该年的权值数据
		return this.bp.bp.preprocessing.reverseY(bp.test(pojo)[0]);
	}

	@Override
	public double realYearValueTax(int year) {
		double tax = 0;
		try {
			handle.connect();
			EconomyPoJo pojo = handle.selectYearEconomy(year);
			handle.close();
			tax = pojo.getTax();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tax;
	}

	@Override
	public double realMonthValueTax(int year, int month) {
		// TODO Auto-generated method stub
		double tax = 0;
		handle.connect();
		try {
			EconomyPoJo pojo = handle.selectMonthEconomy(year, month);
			tax = pojo.getTax();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handle.close();
		return tax;

	}
	
	@Override
	public double mixedPredictGDP(int year,int month) throws Exception{
		double result = 0;
		mixedModel.bpResultLastYear = new double[12];
		mixedModel.bpErrorLastYear = new double[12];
		mixedModel.bpResult = new double[month];
		mixedModel.bpError = new double[month];

		mixedModel.regResultLastYear = new double[12];
		mixedModel.regErrorLastYear = new double[12];
		mixedModel.regResult = new double[month];
		mixedModel.regError = new double[month];
		this.bpYearMonthsGDP(year-1,mixedModel.bpResultLastYear,mixedModel.bpErrorLastYear,12);
		this.bpYearMonthsGDP(year,mixedModel.bpResult,mixedModel.bpError,month);
		
		this.regYearMonthsGDP(year-1,mixedModel.regResultLastYear,mixedModel.regErrorLastYear,12);
		this.regYearMonthsGDP(year,mixedModel.regResult,mixedModel.regError,month);
		result = mixedModel.getPredictionResult(month);
		return result;
	}
	@Override
	public double mixedPredictTax(int year, int month) throws Exception{
		double result = 0;
		mixedModel.bpResultLastYear = new double[12];
		mixedModel.bpErrorLastYear = new double[12];
		mixedModel.bpResult = new double[month];
		mixedModel.bpError = new double[month];

		mixedModel.regResultLastYear = new double[12];
		mixedModel.regErrorLastYear = new double[12];
		mixedModel.regResult = new double[month];
		mixedModel.regError = new double[month];
		this.bpYearMonthsTax(year-1,mixedModel.bpResultLastYear,mixedModel.bpErrorLastYear,12);
		this.regYearMonthsTax(year-1,mixedModel.regResultLastYear,mixedModel.regErrorLastYear,12);
		this.bpYearMonthsTax(year,mixedModel.bpResult,mixedModel.bpError,month);
		this.regYearMonthsTax(year,mixedModel.regResult,mixedModel.regError,month);
		result = mixedModel.getPredictionResult(month);
		return result;
	}
	
	
	
	public void bpYearMonthsGDP(int year, double[]bpResult,double[] bpError,int monthsNumber) throws Exception{
		
		for(int i=1;i<monthsNumber+1;i++){
			bpResult[i-1] = this.bpPredictGDP(year, i);
			bpError[i-1] = this.aberration(this.realMonthValueGDP(year, i),bpResult[i-1]);
		}
	}
	
	public void regYearMonthsGDP(int year,double[]regResult, double[] regError,int monthsNumber){
		for(int i=1;i<monthsNumber+1;i++){
			regResult[i-1] = this.regressionPredictGDP(year, i);
			regError[i-1] = this.aberration(this.realMonthValueGDP(year, i),regResult[i-1]);
		}
	}
	
	public void mixedYearMonthGDP(int year,double[]mixedResult, double[] mixedError,int monthsNumber){
		for(int i=1;i<monthsNumber+1;i++){
			try {
				mixedResult[i-1] = this.mixedPredictGDP(year, i);
				mixedError[i-1] = this.aberration(this.realMonthValueGDP(year, i),mixedResult[i-1]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void mixedYearMonthTax(int year,double[]mixedResult, double[] mixedError,int monthsNumber){
	
		for(int i=1;i<monthsNumber+1;i++){
			try {
				mixedResult[i-1] = this.mixedPredictTax(year, i);
				mixedError[i-1] = this.aberration(this.realMonthValueTax(year, i),mixedResult[i-1]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void bpYearMonthsTax(int year, double[]bpResult,double[] bpError,int monthsNumber) throws Exception{
		
		
		for(int i=1;i<monthsNumber+1;i++){
			bpResult[i-1] = this.bpPredictTax(year, i);
			bpError[i-1] = this.aberration(this.realMonthValueTax(year, i),bpResult[i-1]);
		}
	}
	
	public void regYearMonthsTax(int year,double[]regResult, double[] regError,int monthsNumber){
		
		for(int i=1;i<monthsNumber+1;i++){
			regResult[i-1] = this.regressionPredictTax(year, i);
			regError[i-1] = this.aberration(this.realMonthValueTax(year, i),regResult[i-1]);
		}
	}
	
}
