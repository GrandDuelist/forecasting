package cn.com.test;

import cn.com.forecasting.model.MultivariableLinearRegression;

public class MultiVariLinRegressionTest {

	public static void main(String[] args){
		MultivariableLinearRegression test  = new MultivariableLinearRegression();
		//double[] data = {1,2,3,4};
		
		//System.out.println(test.calArraySum(data, 4));
		
		
		// 测试代数余子式求解
		double[][] data2 = {{1,2,3},{2,2,1},{3,4,3}};
		//double[][] r= test.inverseMatrix(data2, 3);
		// double[][] r=test.calXX(data2, 3, 3);
		//double[][] r = test.getCofactorOfDeterminant(data, 3, 3, 1, 2);
		double[] data3 = {22,25,32};
		double[] r = test.calRegressionCoef(data2, data3, 3, 3);
//		
//		for(int i=0;i<r.length;i++){
//			for(int j=0;j<r[0].length;j++){
//				System.out.print(r[i][j]+"  ");
//			}
//			System.out.println();
//		}
		
		for(int i=0;i<r.length;i++){
			System.out.print(r[i]+"  ");
		}
		
		
		//System.out.println(test.calDeterminant(data, 3));
	}
	
	
	/**
	 * 多元线性回归
	 * @param X
	 * @param Y
	 * @param numberX
	 * @param numberData
	 * @param coef
	 * @param judgeData
	 * @param v
	 *//*
	public void resolve(double [][] X, double [] Y,int numberX,int numberData, double []coef,double[] judgeData
			,double[] v){
		int a = numberX+1;
		double yy;
		double[] tempArray  = new double[a*a];
		tempArray[a*a-1] = numberData;
		
		double p = 0;
		for(int i=0;i<numberX;i++){
			p=0;
			for(int j=0;j<numberData;j++){	
				p=p+X[i][j];
				}
			
			tempArray[numberX*a+i]=p;
			tempArray[i*a+numberX]=p;
			
		}
		
		for(int i=0;i<numberX;i++){
			for(int j=i;j<numberX;j++){
				p=0;
				for(int k=0;k<numberData;k++){
					p=p+X[i][k]*X[j][k];
				}
				
				tempArray[j*a+i]=p;
				tempArray[i*a+j]=p;	
			}
		}
		
		coef[numberX]=0;
		
		for(int i=0;i<numberData;i++){
			coef[numberX]=coef[numberX]+Y[i];
		}
		
		for(int i=0;i<numberX;i++){
			coef[i]=0;
			
			for(int j=0;j<numberData;j++){
				coef[i]=coef[i]+X[i][j]*Y[j];
			}
		}
		
		calChlk(tempArray,a,1,coef);
		yy=0;
		for(int i=0;i<numberData;i++){
			yy=yy+Y[i]/numberData;
		}
		double q=0, e=0,u=0;
		for(int i=0;i<numberData;i++){
			p=coef[numberX];
			for(int j=0;j<numberX;j++){
				p = p+coef[j]*X[j][i];
			}
			
			q=q+(Y[i]-p)*(Y[i]-p);
			e=e+(Y[i]-yy)*(Y[i]-yy);
			u=u+(yy-p)*(yy-p);
			
		}
		
		
		double s,r,pp;
		s=Math.sqrt(q/numberData);
		r=Math.sqrt(1-q/e);
		
		for(int j=0;j<numberX;j++){
			p=0;
			for(int i=0;i<numberData;i++){
				pp=coef[numberX];
				for(int k=0;k<numberX;k++){
					if(k!=j){
						pp=pp+coef[k]*X[k][i];
					}
				}
				
				p=p+(Y[i]-pp)*(Y[i]-pp);
			}
			
			v[j]= Math.sqrt(1-q/p);
		}
		
		judgeData[0] = q;
		judgeData[1] = s;
		judgeData[2] = r;
		judgeData[3] = u;
	}
	
	
	public boolean calChlk(double[] a,int n,int m, double[] d){
		
		int u,v;
		if((a[0]+1.0==1.0) || (a[0]<0)){	
			return false;
		}
		a[0]=Math.sqrt(a[0]);
		
		for(int i=1;i<n;i++){
			a[i]=a[i]/a[0];
		}
		
		for(int i=1;i<n;i++){
			u=i*n+i;
			for(int j=1;j<i+1;j++){
				v=(j-1)*n+i;
				a[u]=a[u]-a[v]*a[v];
			}
			
			if((a[u]+1==1)||(a[u]<0)){
				return false;
			}
			a[u]=Math.sqrt(a[u]);
			
			if(i!=(n-1)){
				for(int j=i+1;j<n;j++){
					v=i*n+j;
					for(int k=1;k<i+1;k++){
						a[v]=a[v]-a[(k-1)*n+i]*a[(k-1)*n+j];
					}
					a[v]=a[v]/a[u];
				}
			}
		}
		
		for(int i=0;i<m;i++){
			d[i]=d[i]/d[0];
			for(int j=1;j<n;j++){
				u=j*n+j;
				v=j*m+i;
				for(int k=1;k<j+1;k++){
					d[v]=d[v]-a[(k-1)*n+j]*d[(k-1)*m+i];
				}
				d[v]=d[v]/a[u];
			}
		}
		
		for(int i=0;i<m;i++){
			u=(n-1)*m+i;
			d[u]=d[u]/a[n*n-1];
			for(int k=n-1;k>0;k--){
				u=(k-1)*m+i;
				for(int j=k;j<n;j++){
					v=(k-1)*n+j;
					d[u]=d[u]-a[v]*d[j*m+i];
				}
				
				v=(k-1)*n+k-1;
				d[u]=d[u]/a[v];
			}
		}
		return true;
	}*/
}
