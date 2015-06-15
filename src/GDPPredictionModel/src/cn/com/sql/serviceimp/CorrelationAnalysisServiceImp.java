package cn.com.sql.serviceimp;

import cn.com.forecasting.DAO.EconomyDAO;
import cn.com.forecasting.service.CorrelationAnalysisService;
import cn.com.preprocessing.math.MathCalculation;

public class CorrelationAnalysisServiceImp implements CorrelationAnalysisService {
	
	
	private int startYear;
	private int endYear;
	
	private double[] gdp;
	private double[] tax;
	
	
	
	public double[] getGdp() {
		return gdp;
	}


	public void setGdp(double[] gdp) {
		this.gdp = gdp;
	}


	public double[] getTax() {
		return tax;
	}


	public void setTax(double[] tax) {
		this.tax = tax;
	}

	public CorrelationAnalysisServiceImp(){
		
	}
	public CorrelationAnalysisServiceImp(int startYear,int endYear){
		this.startYear=startYear;
		this.endYear=endYear;
	}
	
	@Override
	public void setStartYear(int startYear)
	{
		this.startYear=startYear;
	}
	@Override
	public void setEndYear(int endYear){
		this.endYear=endYear;
	}
	@Override
	public void setStartEndYear(int startYear, int endYear){
		this.startYear=startYear;
		this.endYear=endYear;
	}
	@Override
	public double yearCorrelation(){
		EconomyDAO handle = new EconomyDAO();
		handle.connect();
		double[] gdp = handle.selectSpecificYearGDP(startYear, endYear);
		double[] tax = handle.selectSpecificYearTax(startYear, endYear);
		
		this.gdp=gdp;
		this.tax=tax;
		
		double result=0;
		MathCalculation math = new MathCalculation();
		result = math.calcCoefficient(gdp,tax,gdp.length);
		return result;
	}


	@Override
	public double getStartYear() {
		// TODO Auto-generated method stub
		return this.startYear;
	}


	@Override
	public double getEndYear() {
		// TODO Auto-generated method stub
		return this.endYear;
	}
	
	

}
