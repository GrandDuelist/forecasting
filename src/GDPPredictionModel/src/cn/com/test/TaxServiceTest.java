package cn.com.test;

import cn.com.forecasting.service.GDPBIService;
import cn.com.forecasting.service.TaxBIService;
import cn.com.sql.serviceimp.EconomyBIServiceImp;

public class TaxServiceTest {
	public static void main(String[] args) {
		TaxBIService service = new EconomyBIServiceImp();
		try {
			// service.trainTax();
			for(int j=2001;j<2013;j++){
				for (int i = 1; i < 13; i++) {
					double predict = service.bpPredictTax(j, i);
					double real = service.realMonthValueTax(j, i);
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