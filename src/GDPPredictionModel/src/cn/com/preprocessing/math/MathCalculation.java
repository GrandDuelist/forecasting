package cn.com.preprocessing.math;

public class MathCalculation {
	
	
	
	
	//calculate the average of a
	public double calcAverage(double[] a, int length){
		double sum = 0;
		for(int i=0; i<length;i++){
			sum +=a[i];
		}
		
		return (sum/length);
	}
	
	//covariance
	public double calcCovariance(double[] a, double[]b, int length){
		double a_average = this.calcAverage(a, length);
		double b_average = this.calcAverage(b, length);
		double result =0;
		
		for(int i=0;i<length;i++){
			result+=(a[i]-a_average)*(b[i]-b_average);
		}
		
		return result/length;
	}
	
	
	//calculate  the coefficient of two array
		public double calcCoefficient(double[] a, double[] b, int length){
			double co = 0;
			
			double a_var = this.calcCovariance(a, a, length);
			double b_var = this.calcCovariance(b, b, length);
			double a_b_cov = this.calcCovariance(a, b, length);
			
			co = a_b_cov/(Math.sqrt(a_var*b_var));
			
			return co;
		}
	

}
