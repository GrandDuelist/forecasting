package cn.com.forecasting.bp;

import java.util.List;

import cn.com.sql.pojo.EconomyPoJo;

/**
 * 对BP进行封装
 * @author zhihan
 *
 */
public class EconomyBP {
	public BaseAnn bp;
	private int xNumber;
	public EconomyBP(int inputSize, int hiddenSize,int outputSize,int xNumber){
		this.bp = new BaseAnn(inputSize,hiddenSize,outputSize);
		this.xNumber=xNumber;
	}
	
	//直接通过EconomyPoJo的列表进行训练
	public void trainByEconomyList(List<EconomyPoJo> pojos, int trainTimes){
		if(this.bp==null){
			System.out.println("please init bp at first");
			return;
		}
		
		
		
		for(int i=0;i<trainTimes;i++){
			
			for(int j=0;j<pojos.size()-1;j++){
				double[] currentX = new double[xNumber];
				double[] target = new double[1];
				
				
			}
		}
	
	}
	

}
