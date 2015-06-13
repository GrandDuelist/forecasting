package cn.com.forecasting.model;

import cn.com.forecasing.type.ModelType;
import cn.com.preprocessing.math.ImprovedKNN;

/**
 * 混合模型
 * @author ezhihan
 *
 */
public class MixedModel {
	public ImprovedKNN improvedKNN = new ImprovedKNN();
	
	private double[] bpResultLastYear;
	private double[] regResultLastYear;
	private double[] bpErrorLastYear;
	private double[] regErrorLastYear;
	
	private double[] bpResult;
	private double[] regResult;
	private double[] bpError;
	private double[] regError;
	
	private int predictionLength = 12; //预测的时间长度
	private int predictionMonth; // 预测月份
	private double[] mixedResult; //混合模型的预测结果
	
	
	public double getPredictionResult(){
		improvedKNN.bpErrorCurrentYear =bpError;
		improvedKNN.bpErrorLastYear =this.bpErrorLastYear;
		improvedKNN.regErrorLastYear = this.regErrorLastYear;
		improvedKNN.regErrorCurrentYear = this.regError;
		
		ModelType type = improvedKNN.KnnVote();
		if(type == ModelType.BP) return this.bpResult[this.predictionMonth-1];
		else return this.regResult[this.predictionMonth-1];
	}
	
	
	
	
}
