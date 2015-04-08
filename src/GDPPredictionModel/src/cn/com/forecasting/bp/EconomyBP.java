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
	
	public EconomyBP(int inputSize, int hiddenSize,int outputSize){
		this.bp = new BaseAnn(inputSize,hiddenSize,outputSize);
	}
	
	//直接通过EconomyPoJo的列表进行训练
	public void trainByEconomyList(List<EconomyPoJo> pojos, int trainTimes){
		if(this.bp==null){
			System.out.println("please init bp at first");
			return;
		}
		//对数据进行训练
		for(int i=0;i<trainTimes;i++){
			for(int j=0;j<pojos.size()-1;j++){
				EconomyPoJo currentPojo = pojos.get(j);
				EconomyPoJo nextPojo = pojos.get(j+1);
				double[] currentX = currentPojo.changeAttributeToArray();
				double[] target =new double[1];
				target[0] = nextPojo.getCityGDP();
				this.bp.train(currentX, target);	
			}
		}
		
		
		for(int j=0;j<pojos.size()-1;j++){
			
			EconomyPoJo currentPojo = pojos.get(j);
			EconomyPoJo nextPojo = pojos.get(j+1);
			double[] currentX = currentPojo.changeAttributeToArray();
			double[] target =new double[1];
			target[0] = nextPojo.getCityGDP();
			double [] result = this.bp.test(currentX);
			System.out.println("预测值 "+ result[0] + "  实际值 "+target[0] );
		}
	
	}
	

}
