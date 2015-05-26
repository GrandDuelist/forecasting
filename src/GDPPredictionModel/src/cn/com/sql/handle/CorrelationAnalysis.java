package cn.com.sql.handle;

import cn.com.preprocessing.math.MathCalculation;

public class CorrelationAnalysis {
	
	
	private int startYear;
	private int endYear;
	
	public CorrelationAnalysis(int startYear,int endYear){
		this.startYear=startYear;
		this.endYear=endYear;
	}
	
	
	public void setStartYear(int startYear)
	{
		this.startYear=startYear;
	}
	
	public void setEndYear(int endYear){
		this.endYear=endYear;
	}

	public void setStartEndYear(int startYear, int endYear){
		this.startYear=startYear;
		this.endYear=endYear;
	}
	
	public double yearCorrelation(){
		EconomyHandle handle = new EconomyHandle();
		handle.connect();
		double[] gdp = handle.selectSpecificYearGDP(startYear, endYear);
		double[] tax = handle.selectSpecificYearTax(startYear, endYear);
		
		double result=0;
		MathCalculation math = new MathCalculation();
		result = math.calcCoefficient(gdp,tax,gdp.length);
		return result;
	}
	
	

}
