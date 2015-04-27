package cn.com.test;

import cn.com.sql.handle.EconomyBIServiceImp;
public class EconomyServiceBITest {
	public static void main(String[] args){
		EconomyBIServiceImp service = new EconomyBIServiceImp();
		try {
			//service.train();
			System.out.println(service.bpPredict(2004));
			System.out.println(service.regressionPredict(2013,11));
			System.out.println(service.bpPredict(2013,11));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
