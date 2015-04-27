package cn.com.test;

import cn.com.sql.handle.EconomyBIServiceImp;
import cn.com.sql.handle.GDPBIService;
public class EconomyServiceBITest {
	public static void main(String[] args){
		GDPBIService service = new EconomyBIServiceImp();
		try {
			//service.train();
			System.out.println(service.bpPredictGDP(2004));
			System.out.println(service.regressionPredictGDP(2013,11));
			System.out.println(service.bpPredictGDP(2013,11));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
