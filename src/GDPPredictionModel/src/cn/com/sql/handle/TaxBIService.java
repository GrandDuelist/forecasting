package cn.com.sql.handle;

public interface TaxBIService {
	public double regressionPredictTax(int year);
	public double regressionPredictTax(int year, int month);
	/**
	 * bp 训练
	 */
	public void train(); 
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
	public double aberrationTax(double realValue, double predictValue);
}
