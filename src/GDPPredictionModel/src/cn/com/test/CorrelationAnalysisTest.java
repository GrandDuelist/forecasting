package cn.com.test;

import cn.com.sql.handle.CorrelationAnalysisServiceImp;

public class CorrelationAnalysisTest {
	public static void main(String[] args){
		CorrelationAnalysisServiceImp cor = new CorrelationAnalysisServiceImp(1981,2001);
		for(int i=2002;i<2015;i++){
			cor.setEndYear(i);
			double result = cor.yearCorrelation();
		
			System.out.println(cor.getGdp()[i-1981-1]+" "+cor.getTax()[i-1981-1]+" "+result);
		}
	}
}
