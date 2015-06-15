package cn.com.test;

import cn.com.forecasting.service.GDPBIService;
import cn.com.forecasting.service.TaxBIService;
import cn.com.sql.serviceimp.EconomyBIServiceImp;

public class EconomyServiceBITest {
	public static void main(String[] args) {
		GDPBIService service = new EconomyBIServiceImp();
		TaxBIService tax = new EconomyBIServiceImp();
		try {
			tax.trainTax();
			 //service.trainGDP();
			// System.out.println(service.bpPredictGDP(2004));
			// System.out.println(service.regressionPredictGDP(2012,11));
			for(int j=2001;j<2013;j++){
				for (int i = 1; i < 13; i++) {
					double predict = service.bpPredictGDP(j, i);
					double real = service.realMonthValueGDP(j, i);
					double error = service.aberration(real, predict);
					System.out.println(j+"年"+i+"月"+" "+predict+" "+real+" "+error);
				
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
