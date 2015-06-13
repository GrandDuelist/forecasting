package cn.com.test;

import cn.com.forecasting.service.GDPBIService;
import cn.com.sql.serviceimp.EconomyBIServiceImp;

public class MixedModelTest {
	
	public static void main(String args[]){
		GDPBIService gdp = new EconomyBIServiceImp();
		try {
		
			System.out.println(gdp.mixedPredictGDP(2012, 4));
			System.out.println(gdp.realMonthValueGDP(2012, 4));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
