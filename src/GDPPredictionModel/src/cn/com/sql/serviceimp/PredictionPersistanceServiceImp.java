package cn.com.sql.serviceimp;

import java.io.IOException;

import cn.com.forecasing.type.DataMapping;
import cn.com.forecasting.pojo.EconomyYmlPoJo;
import cn.com.forecasting.service.CorrelationAnalysisService;
import cn.com.forecasting.service.GDPBIService;
import cn.com.forecasting.service.PredictionPersistanceService;
import cn.com.forecasting.service.TaxBIService;
import cn.com.preprocessing.io.PredictionResultPersistance;

public class PredictionPersistanceServiceImp implements PredictionPersistanceService{

	private PredictionResultPersistance persistance = new PredictionResultPersistance();
	private GDPBIService gdp  = new EconomyBIServiceImp();
	private TaxBIService tax = new EconomyBIServiceImp();
	private CorrelationAnalysisService cor = new CorrelationAnalysisServiceImp();
	EconomyBIServiceImp eco = new EconomyBIServiceImp();
	
	@Override
	public void bpPredictionPersistance(int year, int month) {
		// TODO Auto-generated method stub
		double[] tax_pre = 	new double[month];
		double[] tax_err = new double[month];
		try {
			eco.bpYearMonthsTax(year, tax_pre, tax_err, month);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		double[] tax_real = this.pastRealMonthTax(year, month);
		//double[] gdp_pre = {1632.37,1645.93,1661.09,1675.70,1687.24,1697.07,1704.65,1705.01,1703.57,1706.29,1709.29,1712.86};
		

		double[] gdp_pre = 	new double[month];
		double[] gdp_err = new double[month];
		try {
			eco.bpYearMonthsGDP(year, gdp_pre, gdp_err, month);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		double[] gdp_real = this.pastRealMonthGDP(year, month);
		double[] cor_real = this.pastYearCor(year);
		
		EconomyYmlPoJo pojo = new EconomyYmlPoJo(year, gdp_pre,gdp_real,tax_pre,tax_real, month, cor_real);
		this.persistance.setContentPoJo(pojo);
		try {
			this.persistance.savePoJoToYml("../html/_data/economy_bp.yml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void mixedPredictionPersistance(int year, int month) {
		// TODO Auto-generated method stub
		
		double[] tax_pre = 	new double[month];
		double[] tax_err = new double[month];
		eco.mixedYearMonthTax(year, tax_pre, tax_err, month);
		
		double[] tax_real = this.pastRealMonthTax(year, month);
		//double[] gdp_pre = {1632.37,1645.93,1661.09,1675.70,1687.24,1697.07,1704.65,1705.01,1703.57,1706.29,1709.29,1712.86};
		

		double[] gdp_pre = 	new double[month];
		double[] gdp_err = new double[month];
		eco.mixedYearMonthGDP(year, gdp_pre, gdp_err, month);
		
		double[] gdp_real = this.pastRealMonthGDP(year, month);
		double[] cor_real = this.pastYearCor(year);
		
		EconomyYmlPoJo pojo = new EconomyYmlPoJo(2012, gdp_pre,gdp_real,tax_pre,tax_real, month, cor_real);
		this.persistance.setContentPoJo(pojo);
		try {
			this.persistance.savePoJoToYml("../html/_data/economy.yml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public double[] pastRealMonthGDP(int year,int predictionMonth){
		double[] result = new double[predictionMonth-1];
		for(int i=0;i<result.length;i++){
			result[i] = this.gdp.realMonthValueGDP(year, i+1);
		}
		return result;
	}
	
	public double[] pastRealMonthTax(int year,int predictionMonth){
		double[] result = new double[predictionMonth-1];
		for(int i=0;i<result.length;i++){
			result[i] = this.tax.realMonthValueTax(year, i+1);
		}
		return result;
	}
	
	public double[] pastYearCor(int currentYear){
		double[] result = new double[DataMapping.PAST_YEAR_NUMBER];
		int startYear = currentYear - DataMapping.PAST_YEAR_NUMBER;
		cor.setStartYear(DataMapping.EARLEST_YEAR);
		for(int i=0;i<DataMapping.PAST_YEAR_NUMBER;i++){
			cor.setEndYear((i+startYear));
			result[i]=cor.yearCorrelation();
		}
		return result;
	}
	@Override
	public void regPredictionPersistance(int year, int month) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		double[] tax_pre = 	new double[month];
		double[] tax_err = new double[month];
		try {
			eco.regYearMonthsTax(year, tax_pre, tax_err, month);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		double[] tax_real = this.pastRealMonthTax(year, month);
		//double[] gdp_pre = {1632.37,1645.93,1661.09,1675.70,1687.24,1697.07,1704.65,1705.01,1703.57,1706.29,1709.29,1712.86};
		

		double[] gdp_pre = 	new double[month];
		double[] gdp_err = new double[month];
		try {
			eco.regYearMonthsGDP(year, gdp_pre, gdp_err, month);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		double[] gdp_real = this.pastRealMonthGDP(year, month);
		double[] cor_real = this.pastYearCor(year);
		
		EconomyYmlPoJo pojo = new EconomyYmlPoJo(year, gdp_pre,gdp_real,tax_pre,tax_real, month, cor_real);
		this.persistance.setContentPoJo(pojo);
		try {
			this.persistance.savePoJoToYml("../html/_data/economy_reg.yml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
