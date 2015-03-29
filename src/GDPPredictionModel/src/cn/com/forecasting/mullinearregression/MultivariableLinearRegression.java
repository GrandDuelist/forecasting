package cn.com.forecasting.mullinearregression;

public class MultivariableLinearRegression {
		
	public void resolve(double [][] X, double [] Y,int numberX,int numberData, double []coef,double judgeData
			,double[] v){
		int a = numberX+1;
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
				for(int k=0;i<numberData;k++){
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
	}
	
	
	public boolean calChlk(double[] a,int n,int m, double[] d){
		
		int u,v;
		if((a[0]+1.0==1.0) || (a[0]<0)){	
			return false;
		}
		a[0]=Math.sqrt(a[0]);
		
		for(int i=0;i<n;i++){
			a[i]=a[i]/a[0];
		}
		
		for(int i=0;i<n;i++){
			u=i*n+i;
			for(int j=0;j<i+1;j++){
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
}
