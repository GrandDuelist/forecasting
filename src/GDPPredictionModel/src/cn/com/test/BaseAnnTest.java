package cn.com.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.com.forecasing.type.EconomyType;
import cn.com.forecasting.DAO.EconomyDAO;
import cn.com.forecasting.model.BaseAnn;
import cn.com.forecasting.model.EconomyBP;
import cn.com.forecasting.pojo.EconomyPoJo;

public class BaseAnnTest {	
	public static void main(String[] args){
	/*	BaseAnn bs = new BaseAnn(32,15,4);
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
		}*/
		
//		int a =1 ;
//		a >>>= 1;
	//	System.out.println((-Integer.MIN_VALUE));
		EconomyDAO handle = new EconomyDAO();
		
		//神经网络训练测试
	 
		List<EconomyPoJo> pojos;
		try {
			handle.connect();
			pojos = handle.selectAllYear();
			handle.close();
			EconomyBP bp = new EconomyBP(8,4,1);
		//	bp.trainByEconomyList(pojos, 200);
			bp.trainByNormalizedEconomy(pojos,EconomyType.GDP,10000);
			System.out.println();
			bp.trainByNormalizedEconomy(pojos.subList(0,pojos.size()-3),pojos.subList(pojos.size()-4, pojos.size()),EconomyType.GDP,10000);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	/*	
		//权值输出测试  保存权值到文件中
		EconomyBP bp = new EconomyBP(8,4,1);
		EconomyBP bp2 = new EconomyBP(8,4,1);
		try {
			handle.connect();
			List<EconomyPoJo>pojos = handle.selectAllYear();
			handle.close();
			bp.trainOnlyByNormalizedEconomy(pojos.subList(0,pojos.size()-1), 10000);
			bp.outputBaseBpToFile(2012); //训练输出到文件
			bp2.readBaseBpFromFile(2012); //从文件读取输出
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}
