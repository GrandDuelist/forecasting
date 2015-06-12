package cn.com.forecasting.service;

public interface TaxBIService {
	public double regressionPredictTax(int year);
	public double regressionPredictTax(int year, int month);
	/**
	 * bp 训练
	 */
	public void trainTax(); 
	public double bpPredictTax(int year) throws Exception;
	public double bpPredictTax(int year,int month) throws Exception;
	/**
	 * 真实的年份经济数据
	 * @param year
	 * @return
	 */
	public double realYearValueTax(int year);
	/**
	 * 真实的月份经济数据
	 * @param year 年份
	 * @param month 月份
	 * @return  当月的GPD值
	 */
	public double realMonthValueTax(int year, int month);
	/**
	 * 计算数据误差
	 * @param realValue
	 * @param predictValue
	 * @return
	 */
	public double aberration(double realValue, double predictValue);
	
	/**
	 * 混合模型
	 * @param year
	 * @return
	 */
	public double mixedModelTax(int year);
	public double mixedModelTax(int year, int month);
}
