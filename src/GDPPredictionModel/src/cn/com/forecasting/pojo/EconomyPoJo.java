package cn.com.forecasting.pojo;

import cn.com.sql.handle.EconomyType;

public class EconomyPoJo {
	private int year;   //年份
	private double cityGDP;   //市GDP
	private double energyConsumePerGDP; //单位GDP能耗
	private double population;		//人口
	private double foreignInvestMent; //实际利用外资
	private double exportTrade;  // 货物一般贸易出口总额
	private double grossRetailSales; //社会消费品零售总额
	private double importExportTrade; //进出口贸易总额
	private double industryIncrement; //工业增加值
	private int month;  //月份
	private double tax;
	
	public EconomyPoJo(){}	
	public EconomyPoJo(int year, double cityGDP, double energyConsumePerGDP,
			double population, double foreignInvestMent, double exportTrade,
			double grossRetailSales, double importExportTrade,
			double industryIncrement) {
		super();
		this.year = year;
		this.cityGDP = cityGDP;
		this.energyConsumePerGDP = energyConsumePerGDP;
		this.population = population;
		this.foreignInvestMent = foreignInvestMent;
		this.exportTrade = exportTrade;
		this.grossRetailSales = grossRetailSales;
		this.importExportTrade = importExportTrade;
		this.industryIncrement = industryIncrement;
	}
	
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getCityGDP() {
		return cityGDP;
	}
	public void setCityGDP(double cityGDP) {
		this.cityGDP = cityGDP;
	}
	public double getEnergyConsumePerGDP() {
		return energyConsumePerGDP;
	}
	public void setEnergyConsumePerGDP(double energyConsumePerGDP) {
		this.energyConsumePerGDP = energyConsumePerGDP;
	}
	public double getPopulation() {
		return population;
	}
	public void setPopulation(double population) {
		this.population = population;
	}
	public double getForeignInvestMent() {
		return foreignInvestMent;
	}
	public void setForeignInvestMent(double foreignInvestMent) {
		this.foreignInvestMent = foreignInvestMent;
	}
	public double getExportTrade() {
		return exportTrade;
	}
	public void setExportTrade(double exportTrade) {
		this.exportTrade = exportTrade;
	}
	public double getGrossRetailSales() {
		return grossRetailSales;
	}
	public void setGrossRetailSales(double grossRetailSales) {
		this.grossRetailSales = grossRetailSales;
	}
	public double getImportExportTrade() {
		return importExportTrade;
	}
	public void setImportExportTrade(double importExportTrade) {
		this.importExportTrade = importExportTrade;
	}
	public double getIndustryIncrement() {
		return industryIncrement;
	}
	public void setIndustryIncrement(double industryIncrement) {
		this.industryIncrement = industryIncrement;
	}
	public void setMonth(int month){
		this.month=month;
	}
	public int getMonth(){
		return this.month;
	}
	public double getTax(){
		return this.tax;
	}
	public void setTax(double tax){
		this.tax=tax;
	}
	public double getCurrentY(EconomyType type){
		if(EconomyType.GDP==type){
			return this.cityGDP;
		}
		if(EconomyType.TAX==type){
			return this.tax;
		}
		return 0;
	}
	
		
}
