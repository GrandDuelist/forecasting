package cn.com.forecasting.service;
/***
 * 关联分析
 * @author zhihan
 *
 */
public interface CorrelationAnalysisService {
	public double getStartYear();
	public void setStartYear(int startYear);
	public void setEndYear(int endYear);
	public double getEndYear();
	/**
	 * 计算关联系数
	 * @return
	 */
	public double yearCorrelation();
	public void setStartEndYear(int startYear, int endYear);
	
}
