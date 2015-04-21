package cn.com.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import cn.com.sql.handle.EconomyHandle;
import cn.com.sql.pojo.EconomyPoJo;

public class ProcessData {
	
	public static void main(String[] args){
		EconomyHandle handle = new EconomyHandle();
		handle.connect();
		
		try {
			List<EconomyPoJo> economies = handle.selectAll();
			
			//将实际数据打乱按月重组
			for(int i=1;i<economies.size();i++){
				EconomyPoJo pre = economies.get(i-1);
				EconomyPoJo current = economies.get(i);
				EconomyPoJo[] pojos= changeYearToMonthEconomy(pre,current);
				insertMonthDataToDatabase(pojos,handle);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		handle.close();
	}
	
	public static void insertMonthDataToDatabase(EconomyPoJo[] pojos,EconomyHandle handle){
		
		for(int i=0;i<pojos.length;i++){
			EconomyPoJo pojo = pojos[i];
			handle.insertToEconomyMonth(pojo);
		}
		
	}
	/**
	 * 将年份数据模拟为月份数据
	 * @param preYearPoJo
	 * @param yearPoJo
	 * @return
	 */
	public static EconomyPoJo[] changeYearToMonthEconomy(EconomyPoJo preYearPoJo,EconomyPoJo yearPoJo){
		EconomyPoJo[] pojos = new EconomyPoJo[12];
		double[] monthGDP =randomGenerateMonthData(yearPoJo.getCityGDP(),yearPoJo.getCityGDP()/12*0.005);
		double[] monthExportTrade = randomGenerateMonthData(yearPoJo.getExportTrade(),yearPoJo.getExportTrade()/12*0.005);
		double[] monthForeignInvestMent = randomGenerateMonthData(yearPoJo.getForeignInvestMent(),yearPoJo.getForeignInvestMent()/12*0.005);
		double[] monthIndustryIncrement = randomGenerateMonthData(yearPoJo.getIndustryIncrement(),yearPoJo.getIndustryIncrement()/12*0.005);
		double[] monthImportExportTrade = randomGenerateMonthData(yearPoJo.getImportExportTrade(),yearPoJo.getImportExportTrade()/12*0.005);
		double[] monthPopulation = gradualChangeGenerateMonthData((yearPoJo.getPopulation()-preYearPoJo.getPopulation())*0.05/12,12,preYearPoJo.getPopulation(),yearPoJo.getPopulation());
		double[] monthRetailSales = randomGenerateMonthData(yearPoJo.getGrossRetailSales(),yearPoJo.getGrossRetailSales()/12*0.005);
		double[] monthEnergyConsumePerGDP = gradualChangeGenerateMonthData((yearPoJo.getEnergyConsumePerGDP()-preYearPoJo.getEnergyConsumePerGDP())/12*0.05,12,preYearPoJo.getEnergyConsumePerGDP(),yearPoJo.getEnergyConsumePerGDP());
		double[] monthTax = randomGenerateMonthData(yearPoJo.getTax(),yearPoJo.getTax()/12*0.005);
		for(int i=0;i<12;i++){
			pojos[i] = new EconomyPoJo();
			pojos[i].setYear(yearPoJo.getYear());  //设置年数据
			pojos[i].setMonth(i+1);
			pojos[i].setCityGDP(monthGDP[i]);
			pojos[i].setEnergyConsumePerGDP(monthEnergyConsumePerGDP[i]);
			pojos[i].setExportTrade(monthExportTrade[i]);
			pojos[i].setForeignInvestMent(monthForeignInvestMent[i]);
			pojos[i].setGrossRetailSales(monthRetailSales[i]);
			pojos[i].setImportExportTrade(monthImportExportTrade[i]);
			pojos[i].setIndustryIncrement(monthIndustryIncrement[i]);
			pojos[i].setPopulation(monthPopulation[i]);
			pojos[i].setTax(monthTax[i]);
			
		}
		return pojos;
	}
	
	public static void printMonthAndYearData(double[] monthData, double yearData){
		System.out.print("year: "+yearData);
		for(int i=0;i<monthData.length;i++){
			System.out.println("month"+i+" "+monthData[i]);
		}
	}

	/**
	 * 总量数据离散化
	 * 根据年度经济数据来模拟月度数据，模拟数据年误差不超过10%
	 * @param yearData
	 * @param slope
	 * @return
	 */
	public static double[] randomGenerateMonthData(double yearData,double slope){
		double[] data = null;
		// 12*initial + 11*0.05 = yearData
		double initial  = (yearData - 11*slope)/12;
		data = generateData(initial,12,slope,slope/10);
		return data;
	}
	
	
	/**
	 * 渐变数据离散化
	 * @param preYearData
	 * @return
	 */
	public static double[] gradualChangeGenerateMonthData(double aberration, int dataNumber,double preYearData,double curYearData){
		double[] result = new double[dataNumber];
		double slope = (curYearData-preYearData)/(dataNumber-1); //计算线性渐变斜率
		Random random = new Random();
		for(int i=0;i<dataNumber;i++){
			result[i] = slope*i+preYearData+random.nextDouble()*aberration*(random.nextBoolean()?1:-1);
		}
		return result;
	}
	
	/**
	 * 均值数据离散化
	 * @param yearAverage
	 * @param slope
	 * @return
	 */
	public static double[] averageGenerateMonthData(double yearAverage,double slope){
		double[] result = null;
		randomGenerateMonthData(yearAverage*12,-yearAverage*0.05);
		return result;
	}
	
	/**
	 * 产生线性趋势的数据
	 * @param initial
	 * @param dataNumber
	 * @param slope
	 * @param aberration
	 * @return
	 */
	public static double[] generateData(double initial,int dataNumber, double slope, double aberration){	
		Random random = new Random();
		double[] result = new double[dataNumber];
		for(int i=0;i<dataNumber;i++){
			double current = slope*i + initial;
			result[i] = current+(random.nextBoolean()?1:(-1))*aberration*random.nextDouble();
			System.out.println(result[i]);
		}
		return result;
	}
}
