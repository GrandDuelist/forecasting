package cn.com.forecasting.mullinearregression;

import java.util.List;

import cn.com.preprocessing.excel.ColumnPoJo;

public class MultivariableLinearRegression {
	
	/**
	 * apply regression to column pojo
	 * @param xColumnPoJo x自变量的集合 
	 * @param yColumnPoJo y应变量的集合
	 * @param coef	存放结果回归系数的数组
	 * @param judgeData	存放判定结果参数的矩阵
	 * @param v	偏相关系数矩阵	 vi 越大，表示xi对	y的作用越显著
	 */
	public void regressionByColumnPoJo(List<ColumnPoJo> xColumnPoJo,ColumnPoJo yColumnPoJo,
			double[] coef,double[] judgeData, double[] v){
		int numberX = xColumnPoJo.size();
		int numberData = yColumnPoJo.getNumber();
		
		
		double[][] X =  new double[numberX][numberData];
		double[] Y = yColumnPoJo.getData();
		
		for(int i=0; i < numberX; i++){
			double[] currentX = xColumnPoJo.get(i).getData();
			for(int j=0; j < numberData; j++){
				X[i][j] = currentX[j];
			}
		}
		
		this.resolve(X, Y, numberX, numberData, coef, judgeData, v);
		
		for(int i =0; i<numberX;i++){
			String name = xColumnPoJo.get(i).getHeader();
			System.out.println(name+" 回归系数： "+coef[i]+ " 偏相关系数： "+v[i]);
		}
	}
	
	
	
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
				tempArray[numberX*a+i]=p;
				tempArray[i*a+numberX]=p;
			}
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
			
			if(a[u]+1==1||(a[u]<0)){
				return false;
			}
			a[u]=Math.sqrt(a[u]);
			
			if(i!=(n-1)){
				for(int j=i+1;j<n-1;j++){
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
				u=i*n+i;
				v=i*m+j;
				for(int k=1;k<i;k++){
					d[v]=d[v]-a[(k-1)*n+i]*d[(k-1)*m+j];
				}
				d[v]=d[v]/a[u];
			}
		}
		
		for(int i=0;i<m;i++){
			u=(n-1)*m+i;
			d[u]=d[u]/a[n*n-1];
			for(int k=n-1;k>0;i--){
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
	}
	
	public boolean evaluation(List<ColumnPoJo> xPoJos, ColumnPoJo yPoJo,ColumnPoJo result, double[] coef){
		int nX = xPoJos.size();
		int nData = yPoJo.getNumber();
		
		for(int i=0;i<nData;i++){
			double currentResult = 0;
			for(int j=0;j<nX;j++){
				currentResult += xPoJos.get(j).getData()[i]*xPoJos.get(j).getCoefficient();
			}
			currentResult += coef[nX];
			result.getData()[i]=currentResult;
			result.setNumber((result.getNumber()+1));
			System.out.println("预测值： "+currentResult+" 实际值： "+yPoJo.getData()[i]);
		}
	
		return true;
	}
}
