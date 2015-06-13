package cn.com.forecasting.pojo;

import cn.com.forecasing.type.DataMapping;

public class EconomyYmlPoJo{
	private int year;
	private double[] predictionGDPMonths;
	private double[] realGDPMonths;
	private double[] predictionGDPYears;
	private double[] realGDPYears;

	private double[] predictionTaxMonths;
	private double[] realTaxMonths;
	private double[] predictionTaxYears;
	private double[] realTaxYears;
	
	private int[] pastYears;
	private int[] pastCurrentMonths;
	private int currentMonth;
	private double[] cor;

	
	
	public EconomyYmlPoJo(int year, double[] predictionGDPMonths,
			double[] realGDPMonths, double[] predictionGDPYears,
			double[] realGDPYears, double[] predictionTaxMonths,
			double[] realTaxMonths, double[] predictionTaxYears,
			double[] realTaxYears, int[] pastYears, int[] pastCurrentMonths,
			int currentMonth, double[] cor) {
		super();
		this.year = year;
		this.predictionGDPMonths = predictionGDPMonths;
		this.realGDPMonths = realGDPMonths;
		this.predictionGDPYears = predictionGDPYears;
		this.realGDPYears = realGDPYears;
		this.predictionTaxMonths = predictionTaxMonths;
		this.realTaxMonths = realTaxMonths;
		this.predictionTaxYears = predictionTaxYears;
		this.realTaxYears = realTaxYears;
		this.pastYears = pastYears;
		this.pastCurrentMonths = pastCurrentMonths;
		this.currentMonth = currentMonth;
		this.cor = cor;
		
		this.setPastAndCurrentMonths();
		this.setPastYears();
	}
	

	
	public EconomyYmlPoJo(int year, double[] predictionGDPMonths,
			double[] realGDPMonths, double[] predictionTaxMonths,
			double[] realTaxMonths, int currentMonth, double[] cor) {
		super();
		this.year = year;
		this.predictionGDPMonths = predictionGDPMonths;
		this.realGDPMonths = realGDPMonths;
		this.predictionTaxMonths = predictionTaxMonths;
		this.realTaxMonths = realTaxMonths;
		this.currentMonth = currentMonth;
		this.cor = cor;
		
		this.setPastAndCurrentMonths();
		this.setPastYears();
	}



	public double[] getPredictionTaxMonths() {
		return predictionTaxMonths;
	}
	public void setPredictionTaxMonths(double[] predictionTaxMonths) {
		this.predictionTaxMonths = predictionTaxMonths;
	}
	public double[] getRealTaxMonths() {
		return realTaxMonths;
	}
	public void setRealTaxMonths(double[] realTaxMonths) {
		this.realTaxMonths = realTaxMonths;
	}
	public double[] getPredictionTaxYears() {
		return predictionTaxYears;
	}
	public void setPredictionTaxYears(double[] predictionTaxYears) {
		this.predictionTaxYears = predictionTaxYears;
	}
	public double[] getRealTaxYears() {
		return realTaxYears;
	}
	public void setRealTaxYears(double[] realTaxYears) {
		this.realTaxYears = realTaxYears;
	}
	
	public double[] getCor() {
		return cor;
	}
	public void setCor(double[] cor) {
		this.cor = cor;
	}
	public double[] getPredictionGDPMonths() {
		return predictionGDPMonths;
	}
	public void setPredictionGDPMonths(double[] predictionGDPMonths) {
		this.predictionGDPMonths = predictionGDPMonths;
	}
	public double[] getRealGDPMonths() {
		return realGDPMonths;
	}
	public void setRealGDPMonths(double[] realGDPMonths) {
		this.realGDPMonths = realGDPMonths;
	}
	public double[] getPredictionGDPYears() {
		return predictionGDPYears;
	}
	public void setPredictionGDPYears(double[] predictionGDPYears) {
		this.predictionGDPYears = predictionGDPYears;
	}
	public double[] getRealGDPYears() {
		return realGDPYears;
	}
	public void setRealGDPYears(double[] realGDPYears) {
		this.realGDPYears = realGDPYears;
	}
	public int[] getPastCurrentMonths() {
		return pastCurrentMonths;
	}
	public void setPastCurrentMonths(int[] pastCurrentMonths) {
		this.pastCurrentMonths = pastCurrentMonths;
	}
	public void setPastYears(int[] pastYears) {
		this.pastYears = pastYears;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}
	
	public void setPastYears(){
		this.pastYears = new int[DataMapping.PAST_YEAR_NUMBER];
		for(int i=0;i<DataMapping.PAST_YEAR_NUMBER;i++){
			this.pastYears[i] = this.year-DataMapping.PAST_YEAR_NUMBER+i;
		}
	}
	public void setPastAndCurrentMonths(){
		this.pastCurrentMonths = new int[this.getCurrentMonth()];
		for(int i=0;i<this.currentMonth;i++){
			this.pastCurrentMonths[i] = i+1;
		}
	}
	public int[] getPastYears(){
		return this.pastYears;
	}
	public int[] getPastMonths(){
		return this.pastCurrentMonths;
	}
	
	
}
