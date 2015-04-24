package cn.com.sql.handle;

public interface TaxBIService {
	/**
	 * 历史数据的多元线性回归， 计算相关系数
	 * @param year 要预测的年份
	 * @return  相关系数
	 */
	public double[] yearRegression(int year);
	public double yearRegressionPredict(int year, double[] coef);
	public double[] monthRegression(int year, int month);
	public double monthRegressionPredict(int year,int month,double[] coef);
}
