package cn.com.test;

import cn.com.sql.handle.EconomyBIServiceImp;
public class EconomyServiceBITest {
	public static void main(String[] args){
		EconomyBIServiceImp service = new EconomyBIServiceImp();
		try {
			System.out.println(service.bpPredict(2011));
			System.out.println(service.bpPredict(2010));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
