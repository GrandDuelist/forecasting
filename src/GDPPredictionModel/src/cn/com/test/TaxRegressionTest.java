package cn.com.test;

import cn.com.forecasting.service.GDPBIService;
import cn.com.forecasting.service.TaxBIService;
import cn.com.sql.serviceimp.EconomyBIServiceImp;

public class TaxRegressionTest {
	public static void main(String [] args){
		TaxBIService service = new EconomyBIServiceImp();
		for(int j=2001;j<2013;j++){
		for(int i=1;i<13;i++){
			double monthTax = service.regressionPredictTax(j,i);
			double real = service.realMonthValueTax(j,i);
			double error =  service.aberration(monthTax, real);
			System.out.println(j+"年"+i+"月"+" "+monthTax+" "+real+" "+error);
		 
		}
	}
	}
}
