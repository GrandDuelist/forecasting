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
	
	public double[] bpResultLastYear;
	public double[] regResultLastYear;
	public double[] bpErrorLastYear;
	public double[] regErrorLastYear;
	
	public double[] bpResult;
	public double[] regResult;
	public double[] bpError;
	public double[] regError;
	
	public int predictionLength = 12; //预测的时间长度
	public int predictionMonth; // 预测月份
	public double[] mixedResult; //混合模型的预测结果
	
	
	public MixedModel(){
		
	}
	public double getPredictionResult(int month){
		this.predictionMonth = month;
		improvedKNN.bpErrorCurrentYear =bpError;
		improvedKNN.bpErrorLastYear =this.bpErrorLastYear;
		improvedKNN.regErrorLastYear = this.regErrorLastYear;
		improvedKNN.regErrorCurrentYear = this.regError;
		improvedKNN.currentMonth=this.predictionMonth;
		ModelType type = improvedKNN.KnnVote();
		if(type == ModelType.BP) return this.bpResult[this.predictionMonth-1];
		else return this.regResult[this.predictionMonth-1];
	}
	
	
	
	
}
