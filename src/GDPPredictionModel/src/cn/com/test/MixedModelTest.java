package cn.com.test;

import cn.com.forecasting.service.GDPBIService;
import cn.com.forecasting.service.PredictionPersistanceService;
import cn.com.sql.serviceimp.EconomyBIServiceImp;
import cn.com.sql.serviceimp.PredictionPersistanceServiceImp;

public class MixedModelTest {
	
	public static void main(String args[]){
		GDPBIService gdp = new EconomyBIServiceImp();
		PredictionPersistanceService s = new PredictionPersistanceServiceImp();
		try {
		
			/*System.out.println(gdp.mixedPredictGDP(2012, 4));
			 * 
			System.out.println(gdp.realMonthValueGDP(2012, 4));*/
			s.mixedPredictionPersistance(2012, 11);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
