package cn.com.forecasting.model;

import cn.com.preprocessing.math.ImprovedKNN;

/**
 * 混合模型
 * @author ezhihan
 *
 */
public class MixedModel {
	public ImprovedKNN improvateKNN = new ImprovedKNN();
	
	private double[] bpResult;
	private double[] regResult;
	private double[] bpError;
	private double[] regError;
}
