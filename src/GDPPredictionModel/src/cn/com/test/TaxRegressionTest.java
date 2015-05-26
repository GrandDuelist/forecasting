package cn.com.test;

import cn.com.sql.handle.EconomyBIServiceImp;
import cn.com.sql.handle.GDPBIService;

public class TaxRegressionTest {
	public static void main(String [] args){
		GDPBIService service = new EconomyBIServiceImp();
		for(int j=2001;j<2013;j++){
		for(int i=1;i<13;i++){
			double monthGDP = service.regressionPredictGDP(j,i);
			double real = service.realMonthValueGDP(j,i);
			double error =  service.aberration(monthGDP, real);
			System.out.println(j+"年"+i+"月"+" "+monthGDP+" "+real+" "+error);
		 
		}
	}
	}
}
