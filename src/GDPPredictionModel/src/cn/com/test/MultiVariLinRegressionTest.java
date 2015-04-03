package cn.com.test;

import cn.com.forecasting.mullinearregression.MultivariableLinearRegression;

public class MultiVariLinRegressionTest {

	public static void main(String[] args){
		MultivariableLinearRegression test  = new MultivariableLinearRegression();
		//double[] data = {1,2,3,4};
		
		//System.out.println(test.calArraySum(data, 4));
		
		
		// 测试代数余子式求解
		double[][] data = {{1,2,3},{2,2,1},{3,4,3}};
		
//		//double[][] r = test.getCofactorOfDeterminant(data, 3, 3, 1, 2);
//		for(int i=0;i<r.length;i++){
//			for(int j=0;j<r[0].length;j++){
//				System.out.print(r[i][j]+"  ");
//			}
//			System.out.println();
//		}
		
		System.out.println(test.calDeterminant(data, 3));
	}
}
