package cn.com.forecasting.model;

import java.util.List;

import cn.com.forecasing.type.EconomyType;
import cn.com.forecasting.DAO.EconomyDAO;
import cn.com.forecasting.pojo.ColumnPoJo;
import cn.com.forecasting.pojo.EconomyPoJo;
import cn.com.sql.handle.EconomyBIServiceImp;;
public class MultivariableLinearRegression {
	
	/**
	 * apply regression to column pojo
	 * @param xColumnPoJo x自变量的集合 
	 * @param yColumnPoJo y应变量的集合
	 * @param coef	存放结果回归系数的数组
	 * @param judgeData	存放判定结果参数的矩阵
	 * @param v	偏相关系数矩阵	 vi 越大，表示xi对	y的作用越显著
	 */
	public double[] regressionByColumnPoJo(List<ColumnPoJo> xColumnPoJo,ColumnPoJo yColumnPoJo,
			double[] judgeData, double[] v){
		int numberX = xColumnPoJo.size()+1;
		int numberData = yColumnPoJo.getNumber();
		
		
		double[][] X =  new double[numberData][numberX];
		double[] Y = yColumnPoJo.getData();
		
		for(int i=0;i<numberData;i++){
			X[i][0] = 1;
		}
		for(int i=0; i < numberX-1; i++){	
			double[] currentX = xColumnPoJo.get(i).getData();
			for(int j=0; j < numberData; j++){
				X[j][i+1] = currentX[j];
			}
		}
		double[] coef = this.calRegressionCoef(X, Y, numberX, numberData);
		for(int i =1; i<numberX;i++){
			String name = xColumnPoJo.get(i-1).getHeader();
			System.out.println(name+" 回归系数： "+coef[i]);
			}
		return coef;
	}
	
	/**
	 * 通过EconomyPoJo回归 : 数据存在database  按年回归
	 * @param pojos 训练集
	 * @return
	 */
	public double[] regressionThroughDatabase(List<EconomyPoJo> pojos,EconomyType type){
		double[] coef=null;
		if(pojos==null) return coef;
		
		EconomyDAO handle = new 	EconomyDAO();
		int numberX =  handle.changeGDPAttributeToArray(pojos.get(0)).length+1;
		int numberData = pojos.size()-1;
		double[][] X = new double[numberData][numberX];  //x为前一年指标
		double[] Y = new double[numberData];      //此时的y为当年数据

		for(int i=1;i<pojos.size();i++){
			Y[i-1] = pojos.get(i).getCurrentY(type);
		}
		
		for(int i=0;i<numberData;i++){
			X[i][0] = 1;
		}
		for(int i=0;i<numberData;i++){
			double[] currentYear = handle.changeGDPAttributeToArray(pojos.get(i));
			for(int j=0;j<numberX-1;j++){
				X[i][j+1] = currentYear[j];
			}
		}
		
		coef = this.calRegressionCoef(X, Y, numberX, numberData);
	/*	for(int i =0; i<numberX;i++){
			
			String name = xColumnPoJo.get(i-1).getHeader();
			System.out.println(name+" 回归系数： "+coef[i]);
			}*/
		
		return coef;
	}
	
	/**
	 * 通过economy数据进行预测： 这里是利用上一年数据
	 * @param pojo  上一年经济数据
	 * @return 预测年GDP
	 */
	public double predictThroughDataBase(EconomyPoJo pojo,double []coef){
		double yearGDP=0;
		double[] X= new EconomyDAO().changeGDPAttributeToArray(pojo);
		for(int i=1;i<X.length+1;i++){
			yearGDP = yearGDP + X[i-1]*coef[i];
		//	System.out.println("X = "+X[i-1]+" coef = "+coef[i]);
		}
	//	System.out.println(coef[0]);
		yearGDP = yearGDP+coef[0];
		return yearGDP;
	}

	/**
	 * 对结果进行评价
	 * @param xPoJos
	 * @param yPoJo
	 * @param result
	 * @param coef
	 * @return
	 */
	public boolean evaluation(List<ColumnPoJo> xPoJos, ColumnPoJo yPoJo,ColumnPoJo result, double[] coef){
		int nX = xPoJos.size()+1;
		int nData = yPoJo.getNumber();
		
		for(int i=0;i<nData;i++){
			double currentResult = 0;
			for(int j=1;j<nX;j++){
				currentResult = currentResult + xPoJos.get(j-1).getData()[i]*coef[j];
			}
			currentResult = currentResult + coef[0];
			result.getData()[i]=currentResult;
			result.setNumber((result.getNumber()+1));
			System.out.println("预测值： "+currentResult+" 实际值： "+yPoJo.getData()[i]);
		}
	
		return true;
	}
	
	
	
	
	/**
	 *  求 X‘X阵
	 * @param X
	 * @param numberX
	 * @param numberData
	 * @return
	 */
	public double[][] calXX(double[][] X,int numberX,int numberData){
		double result[][] = new double[numberX][numberX];  //存储X’X矩阵
		
		int row = numberX;  //X'X矩阵的行数和列数
		int col = numberX;
		
		double[] temp; //存放计算元的矩阵
		double tempSum;//元的值
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				//通过X矩阵得到计算 i j 元所用的原始数据
				temp = this.rawDataOfXXElement(X, i, j, numberData);
				//计算X‘X矩阵中的每个元
				tempSum = this.calArraySum(temp,numberData);
				result[i][j] = tempSum;
			}
		}
		
		return result;
	}
	
	
	/**
	 *  得到计算 i j 元所用的原始数据, i j 从 0 开始
	 * @param X
	 * @param i
	 * @param j
	 * @param numberData
	 * @return
	 */
	public double[] rawDataOfXXElement(double[][] X, int i, int j, int numberData){
		double[] result = new double[numberData];
		for(int k=0;k<numberData;k++){
			result[k] = X[k][j]*X[k][i];
		}
		return result;
	}
	
	
	/**
	 * 求 n 元数组的和
	 * @param data
	 * @param n
	 * @return
	 */
	public double calArraySum(double[] data, int n){
		double result = 0;
		for(int i=0;i<n;i++){
		
			result+=data[i];
		}
		return result;
	}
	
	
	/**
	 * 就算X'Y向量
	 * @param X
	 * @param Y
	 * @param numberX
	 * @param numberData
	 * @return
	 */
	public double[] calXY(double[][] X,double[] Y, int numberX, int numberData){
		double[] result = new double[numberX]; //Y为number+1 元列向量
		int length = numberX;
		double[] temp;
		double tempSum;
		for(int i= 0;i<length;i++){
			//通过X Y矩阵得到计算 i 元所用的原始数据
			temp = this.rowDataOfXYElement(X, Y, i, numberData);
			tempSum = this.calArraySum(temp,numberData);
			result[i] = tempSum;
		}
		
		return result;
	}
	
	/**
	 * 计算X'Y每个元 i 所需的向量
	 * @param X
	 * @param Y
	 * @param i
	 * @param numberX
	 * @return
	 */
	public double[] rowDataOfXYElement(double[][]X, double[] Y, int i,int numberData)
	{
		double []result = new double[numberData];
		for(int k=0;k<numberData;k++){
			result[k] = X[k][i]*Y[k];
		}
		return result;
	}
	
	
	
	/**
	 * 求余子式 
	 * @param data 原行列式
	 * @param row  行数
	 * @param col  列数
	 * @param a	   求代数余子式的行位置
	 * @param b	   求代数余子式的列位置
	 * @return	a b 位置的代数余子式 ab 从0开始
	 */
	public double[][] getCofactorOfDeterminant(double[][] data, int row, int col, int a, int b){
		double[][] result = new double[row-1][col-1];
		for(int i=0;i<row;i++){
			int currentResultRow = i;
			//大于去除行往后退一行
			if(i>a){
				currentResultRow = i-1;
			}  //小于则搬过去，等于会被大于覆盖
			else if(i == a){
				continue;
			}
				
			for(int j=0;j<col;j++){
				int currentResultCol = j;
				if(j>b){  //大于去除行往后退一行
					currentResultCol = j-1;
				}else if(j == b){
					continue;
				}
				result[currentResultRow][currentResultCol] = data[i][j];
			}
		}
		
		return result;
	}
	
	
	/**
	 * 计算行列式的值
	 * @param data 行列式
	 * @param n   行列式阶数
	 * @return
	 */
	public double calDeterminant(double[][] data, int n){
		double result = 0;
		//递归求解, 转换换为低一阶行列式n，终止条件n=1
		if(n == 1){
			result = data[0][0];
		}else{
			//取第一行
			for(int i=0;i<n;i++){
	
			//取出 0,i的余子式
				double[][] nextData = this.getCofactorOfDeterminant(data,n,n, 0, i);
				if(i%2==0){
					result = result + this.calDeterminant(nextData, (n-1))*data[0][i];
				}else{
					result = result - this.calDeterminant(nextData, (n-1))*data[0][i];
				}
			}
		}
			
		return result;
	}
	
	
	
	/**
	 * 计算逆矩阵 
	 * @param data 矩阵
	 * @param n	矩阵的阶数
	 * @return
	 */
	public double[][] inverseMatrix(double[][] data, int n){
		double[][] inverseMatrix = new double[n][n];
		double currentElement;	
		//计算|data|
		double dataValue = this.calDeterminant(data, n);
		//计算伴随阵
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				//计算每个元，即代数余子式
				double[][] currentDeterminant = this.getCofactorOfDeterminant(data, n, n, i, j);
				currentElement = this.calDeterminant(currentDeterminant, n-1);
				if((i+j)%2==0){
					inverseMatrix[j][i]=currentElement/dataValue;
				}else{
					inverseMatrix[j][i]=-currentElement/dataValue;
				}
			}
		}
		return inverseMatrix;
	}
	
	/**
	 * 
	 * @param XX XX的逆矩阵
	 * @param XY 
	 * @param numberXX
	 * @return
	 */
	public double[] calMatrixMultiply(double[][] XX,double[] XY,int numberXX){
		double[] result = new double[numberXX];
		for(int i=0;i<numberXX;i++){
			double currentElement = 0;
			for(int j=0; j<numberXX;j++){
				currentElement = currentElement + XX[i][j]*XY[j];
			}
			result[i]=currentElement;
		}
		return result;
	}
	

	/**
	 * 计算回归系数
	 * @param X
	 * @param Y
	 * @param numberX
	 * @param numberData
	 * @return
	 */
	public double[] calRegressionCoef(double[][] X, double[] Y, int numberX,int numberData){
		double[] result = new double[numberX];
		double[][] XX = this.calXX(X, numberX, numberData);
		double[] XY = this.calXY(X, Y, numberX, numberData);
		double[][] XX_inverse = this.inverseMatrix(XX, numberX);	
		result = this.calMatrixMultiply(XX_inverse, XY, numberX);
		return result;
	}
}