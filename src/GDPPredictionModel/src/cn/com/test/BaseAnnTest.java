package cn.com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.com.forecasting.bp.BaseAnn;

public class BaseAnnTest {	
	public static void main(String[] args){
		BaseAnn bs = new BaseAnn(32,15,4);
		Random random = new Random();
		List<Integer> list = new ArrayList<Integer>();
	
		for(int i=0; i<1000;i++){
			int value = random.nextInt();
			list.add(value);
		}
		
		for(int i=0;i<200;i++){
			for(int value:list){
				double[] real = new double[4];
				if(value>=0){
					
				}
			}
		}
	}
}
