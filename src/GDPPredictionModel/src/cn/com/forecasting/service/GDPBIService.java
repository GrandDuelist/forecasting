package cn.com.forecasting.service;

import java.sql.SQLException;
import java.util.List;

import cn.com.forecasting.pojo.EconomyPoJo;

public interface GDPBIService {

	public double regressionPredictGDP(int year);
	public double regressionPredictGDP(int year, int month);
	/**
	 * bp 训练
	 */
	public void trainGDP(); 
	public double bpPredictGDP(int year) throws Exception;
	public double bpPredictGDP(int year,int month) throws Exception;
	/**
	 * 真实的年份经济数据
	 * @param year
	 * @return
	 */
	public double realYearValueGDP(int year);
	/**
	 * 真实的月份经济数据
	 * @param year 年份
	 * @param month 月份
	 * @return  当月的GPD值
	 */
	public double realMonthValueGDP(int year, int month);
	/**
	 * 计算数据误差
	 * @param realValue
	 * @param predictValue
	 * @return
	 */
	public double aberration(double realValue, double predictValue);
	
	/**
	 * 混合模型
	 */
	 public double mixedModelGDP(int year);
	 public double mixedModelGDP(int year, int month);
}
