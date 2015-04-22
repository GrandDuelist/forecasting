package cn.com.sql.handle;

public interface EconomyBIService {
	/**
	 * 多远线性回归
	 * @return
	 */
	public double[] yearRegression();
	public double yearRegressionPredict();
	public double[] monthRegression();
	
	public double[] realYearValue();
	public double[] realMonthValue();
	
	public double[] yearBPTrain();
	public double[] monthBPTrain();
	
	
}
