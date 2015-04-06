package cn.com.forecasting.bp;

import java.util.Random;

public class BaseAnn {
	
	//输入层
	private double[] input;
	//隐藏层
	private double[] hidden;
	//输出层
	private double[] output;
	//目标
	private double[] target;
	//隐藏层的delta值
	private double[] hidDelta;
	//输出层的delta值
	private double[] optDelta;
	//学习效率
	private double eta;
	//动量
	private double momentum;
	//输入层到影藏层的权值 
	private double[][] iptHidWeights;
	//上一次权值更新
	private double[][] iptHidPreUptWeights;
	
	//隐藏层到到输出层的权值
	private double[][] hidOptWeights;
	private double[][] hidOptPreUptWeights;
	
	
	public double optErrSum=0; //输出与实际的差和
	public double hidErrSum=0; //隐藏的差和
	
	private Random random; //生成
	
	
	
	public BaseAnn(int inputSize, int hiddenSize,int outputSize,double eta,double momentum){
		
		input = new double[inputSize+1];
		hidden = new double[hiddenSize+1];
		output = new double[outputSize+1];
		target = new double[outputSize+1];
		
		hidDelta = new double[hiddenSize+1];
		optDelta = new double[outputSize+1];
		
		
		
		
		
	}
	
}
