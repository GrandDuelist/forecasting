package cn.com.sql.serviceimp;

import cn.com.forecasting.pojo.EconomyYmlPoJo;
import cn.com.forecasting.service.GDPBIService;
import cn.com.forecasting.service.PredictionPersistanceService;
import cn.com.forecasting.service.TaxBIService;
import cn.com.preprocessing.io.PredictionResultPersistance;

public class PredictionPersistanceServiceImp implements PredictionPersistanceService{

	private PredictionResultPersistance persistance = new PredictionResultPersistance();
	private GDPBIService gdp  = new EconomyBIServiceImp();
	private TaxBIService tax = new EconomyBIServiceImp();
	EconomyBIServiceImp eco = new EconomyBIServiceImp();
	
	@Override
	public void bpPredictionPersistance(int year, int month) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mixedPredictionPersistance(int year, int month) {
		// TODO Auto-generated method stub
		/*
		double[] tax_pre = 	eco.mixedYearMonthTax(year, mixedResult, mixedError, month);
		double[] tax_real = {284.34,285.60,286.98,288.57,290.04,291.38,292.85,294.37,295.59,297.04,298.41,299.82};
		double[] gdp_pre = {1632.37,1645.93,1661.09,1675.70,1687.24,1697.07,1704.65,1705.01,1703.57,1706.29,1709.29,1712.86};
		double[] gdp_real = {1674.71,1681.88,1691.65,1699.69,1707.79,1716.01,1724.40,1732.84,1742.15,1749.80,1758.85,1765.97};
		double[] cor = {0.958,0.959,0.960,0.965,0.972,0.968,0.973,0.978,0.982,0.986,0.988};
		EconomyYmlPoJo pojo = new EconomyYmlPoJo(2012, gdp_pre,gdp_real,tax_pre,tax_real, 12, cor);
		p.setContentPoJo(pojo);
		p.savePoJoToYml("../html/_data/economy.yml");*/
		
	}

	@Override
	public void regPredictionPersistance(int year, int month) {
		// TODO Auto-generated method stub
		
	}

}
