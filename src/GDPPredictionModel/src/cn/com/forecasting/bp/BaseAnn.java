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
		
		iptHidWeights = new double[inputSize+1][hiddenSize+1];
		hidOptWeights = new double[hiddenSize+1][outputSize+1];
		
		//初始化权值
		random = new Random(20120517);
		this.randomizeWeights(iptHidWeights);
		this.randomizeWeights(hidOptWeights);
		
		
		//收敛快慢
		this.eta=eta;
		this.momentum=momentum;
	}
	
	
	//随机初始化权值
	public void randomizeWeights(double[][] matrix)
	{
		for(int i=0; i<matrix.length;i++){
			for(int j=0;j<matrix[i].length;i++){
				double temp = random.nextDouble();
				matrix[i][j] = random.nextDouble()>0.5?temp:-temp;
			}
		}
	}
	
	
	//重构
	public BaseAnn(int inputSize, int hiddenSize, int outputSize){
		this(inputSize,hiddenSize,outputSize,0.25,0.9);
	}
	
	//导入输入数据
	public void loadInput(double[] inData){
		if(inData.length!=input.length-1){
			throw new IllegalArgumentException("size not match");
		}
		System.arraycopy(inData, 0, input, 1, input.length);
	}
	
	
	//导入目标数据
	public void loadTarget(double[] arg){
		if(arg.length!=target.length-1){
			throw new IllegalArgumentException("size not match");
		}
		
		System.arraycopy(arg, 0, target, 1, arg.length);
	}
	
	
	
	
	
	
	
	
	
}
