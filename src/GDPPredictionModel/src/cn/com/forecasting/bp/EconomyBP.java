package cn.com.forecasting.bp;

import java.util.List;

import cn.com.sql.pojo.EconomyPoJo;

/**
 * 对BP进行封装
 * 
 * @author zhihan
 * 
 */
public class EconomyBP {
	public BaseAnn bp;
	
	private int inputSize ;
	private int outputSize;
	public EconomyBP(int inputSize, int hiddenSize, int outputSize) {
		this.bp = new BaseAnn(inputSize, hiddenSize, outputSize);
		this.inputSize=inputSize;
		this.outputSize=outputSize;
	}

	// 直接通过EconomyPoJo的列表进行训练
	public void trainByEconomyList(List<EconomyPoJo> pojos, int trainTimes) {
		if (this.bp == null) {
			System.out.println("please init bp at first");
			return;
		}
		// 对数据进行训练
		for (int i = 0; i < trainTimes; i++) {
			for (int j = 0; j < pojos.size() - 1; j++) {
				EconomyPoJo currentPojo = pojos.get(j);
				EconomyPoJo nextPojo = pojos.get(j + 1);
				double[] currentX = currentPojo.changeAttributeToArray();
				double[] target = new double[1];
				target[0] = nextPojo.getCityGDP();
				this.bp.train(currentX, target);
			}
		}

		for (int j = 0; j < pojos.size() - 1; j++) {

			EconomyPoJo currentPojo = pojos.get(j);
			EconomyPoJo nextPojo = pojos.get(j + 1);
			double[] currentX = currentPojo.changeAttributeToArray();
			double[] target = new double[1];
			target[0] = nextPojo.getCityGDP();
			double[] result = this.bp.test(currentX);
			System.out.println("预测值 " + result[0] + "  实际值 " + target[0]+ "误差： "+ 
					(result[0]-target[0])/target[0]);
		}

	}

	/**
	 * normalize the economy and train networks
	 * 
	 * @param pojos
	 * @param trainTimes
	 */
	public void trainByNormalizedEconomy(List<EconomyPoJo> pojos, int trainTimes) {
		if (this.bp == null) {
			System.out.println("please init bp at first");
			return;
		}
		
		SpecificMath preprocessing = new SpecificMath(this.inputSize);
		preprocessing.calculateMaxMinOfXY(pojos);

		// 对数据进行训练
		for (int i = 0; i < trainTimes; i++) {
			for (int j = 0; j < pojos.size() - 1; j++) {
				EconomyPoJo currentPojo = pojos.get(j);
				EconomyPoJo nextPojo = pojos.get(j + 1);
				double[] currentX = preprocessing.normalizeX(currentPojo);
				double[] target = new double[1];
				target[0] = preprocessing.normalizeY(nextPojo.getCityGDP());
				this.bp.train(currentX, target);
			}
		}
		
		
		//训练测试集
		for (int j = 0; j < pojos.size() - 1; j++) {

			EconomyPoJo currentPojo = pojos.get(j);
			EconomyPoJo nextPojo = pojos.get(j + 1);
			double[] currentX = preprocessing.normalizeX(currentPojo);
			double[] target = new double[1];
			target[0] = preprocessing.normalizeY(nextPojo.getCityGDP());
			double[] result = this.bp.test(currentX);
			System.out.println("预测值 " + preprocessing.reverseY(result[0]) + "  实际值 " + nextPojo.getCityGDP() + "误差： 百分之"+ 
			100*Math.abs((preprocessing.reverseY(result[0])-nextPojo.getCityGDP())/nextPojo.getCityGDP()));
		}

	}
	
	
	/**
	 * seperate normalize the economy and train networks
	 * 
	 * @param pojos
	 * @param trainTimes
	 */
	public void trainByNormalizedEconomy(List<EconomyPoJo> pojos,List<EconomyPoJo> test, int trainTimes) {
		if (this.bp == null) {
			System.out.println("please init bp at first");
			return;
		}
		
		SpecificMath preprocessing = new SpecificMath(this.inputSize);
		preprocessing.calculateMaxMinOfXY(pojos);

		// 对数据进行训练
		for (int i = 0; i < trainTimes; i++) {
			for (int j = 0; j < pojos.size() - 1; j++) {
				EconomyPoJo currentPojo = pojos.get(j);
				EconomyPoJo nextPojo = pojos.get(j + 1);
				double[] currentX = preprocessing.normalizeX(currentPojo);
				double[] target = new double[1];
				target[0] = preprocessing.normalizeY(nextPojo.getCityGDP());
				this.bp.train(currentX, target);
			}
		}
		
		
		//训练测试集
		for (int j = 0; j < test.size() - 1; j++) {

			EconomyPoJo currentPojo = test.get(j);
			EconomyPoJo nextPojo = test.get(j + 1);
			double[] currentX = preprocessing.normalizeX(currentPojo);
			double[] target = new double[1];
			target[0] = preprocessing.normalizeY(nextPojo.getCityGDP());
			double[] result = this.bp.test(currentX);
			System.out.println("预测值 " + preprocessing.reverseY(result[0]) + "  实际值 " + nextPojo.getCityGDP()+  "误差： 百分之"+ 
					100*Math.abs((preprocessing.reverseY(result[0])-nextPojo.getCityGDP())/nextPojo.getCityGDP()));
		}

	}
	
	

}
