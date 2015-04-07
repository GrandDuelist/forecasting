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

	//前馈函数，求预测结果
	public void forward(double[] layer0, double[] layer1, double[][] weight){
		layer0[0]=1;
		for(int j=1;j<layer1.length;j++){
			double sum = 0;
			for(int i=0; i<layer0.length;i++){
				sum += weight[i][j] * layer0[i];  //第0层数乘以权重求和
			}
			
			layer1[j] = sigmoid(sum); //激活函数 传到第二层
		}
	}
	
	public void forward(){
		forward(input,hidden,iptHidWeights); //从输入层到隐藏层,结果放入隐藏层
		forward(hidden,output,hidOptWeights); //隐藏层到输出层，结果放入输出层
	}
	
	
	// 调整权值
	public void adjustWeight(double[] delta, double[] layer, double[][] weight, double[][] preWeight){
		
		layer[0] = 1;
		for(int i=1;i<delta.length;i++){
			for(int j=0;j<layer.length;j++){
				double newVal = momentum*preWeight[j][i]+eta*delta[i]*layer[j];  //
				weight[j][i]+=newVal;
				preWeight[j][i] = newVal;
			}
		}
	}
	//调整误差
	public void adjustWeight(){
		adjustWeight(optDelta,hidden,hidOptWeights,hidOptPreUptWeights);  //从输出层到隐藏层的权值
		adjustWeight(hidDelta,input,iptHidWeights,iptHidPreUptWeights);  //从隐藏层到输入层的权值
	}
	
	//隐藏层的误差
	public void hiddenErr(){
		double errSum = 0;
		for(int j=0;j<hidDelta.length;j++){
			double o = hidden[j];
			double sum=0;
			for(int k=1;k<optDelta.length;k++){
					sum += hidOptWeights[j][k] * optDelta[k];
			}
			
			hidDelta[j] = o*(1-o)*sum;
			errSum += Math.abs(hidDelta[j]);
		}
		
		hidErrSum = errSum;
	}
	
	//输出层的误差
	public void outputErr(){
		double errSum = 0;
		for(int i=0; i<optDelta.length;i++){
			double o = output[i];
			optDelta[i] = o*(1-o)*(target[i]-o);
			errSum += Math.abs(optDelta[i]);
		}
		
		this.optErrSum = errSum;
	}
	
	
	public void calculateDelta(){
		outputErr();
		this.hiddenErr();
	}
	
	
	//激活函数
	public double sigmoid(double val){
		return 1/(1+Math.exp(-val));
	}
	
	
	//得到输出
	public double [] getNetworkOutput(){
		double[] result = new double[output.length-1];
		for(int i=0;i<output.length-1;i++){
			result[i] = output[i+1];
		}
		return result;
	}
	
	public void train(double[] trainData, double[] target){
		loadInput(trainData);
		loadTarget(target);
		forward();
		calculateDelta();
		adjustWeight();
	}
	
	
	
	
	
	
	
	
}
