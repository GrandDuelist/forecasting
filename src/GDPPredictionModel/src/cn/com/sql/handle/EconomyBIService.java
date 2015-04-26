package cn.com.sql.handle;

import java.sql.SQLException;
import java.util.List;

import cn.com.sql.pojo.EconomyPoJo;

public interface EconomyBIService {
	/**
	 * 历史数据的多元线性回归， 计算相关系数
	 * @param year 要预测的年份
	 * @return  相关系数
	 */
	public double[] yearRegression(int year);
	public double yearRegressionPredict(int year, double[] coef);
	public double[] monthRegression(int year, int month);
	public double monthRegressionPredict(int year,int month,double[] coef);
	
	/**
	 * 真实的年份经济数据
	 * @param year
	 * @return
	 */
	public double realYearValue(int year);
	
	/**
	 * 真实的月份经济数据
	 * @param year 年份
	 * @param month 月份
	 * @return  当月的GPD值
	 */
	public double realMonthValue(int year, int month);
	/**
	 * 对年份数据进行训练，然后得出阈值和权值
	 * @return
	 */
	public double yearBPTrain(int year);
	
	/**
	 * 对月份数据进行训练
	 * @param year
	 * @param month
	 * @return
	 */
	public double monthBPTrain(int year, int month);
	
	/**
	 * 训练数据库的所有数据
	 * @return
	 * @throws SQLException 
	 */
	public void trainYear() throws Exception;
	public void trainMonth() throws Exception;
	
	
}
