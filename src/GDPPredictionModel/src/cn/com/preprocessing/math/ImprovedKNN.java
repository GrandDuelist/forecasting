package cn.com.preprocessing.math;

import cn.com.forecasing.type.ModelType;

public class ImprovedKNN {
	
	public double[] bpErrorLastYear = new double[12];
	public double[] regErrorLastYear = new double[12];
	public double[] bpErrorCurrentYear = new double[12];
	public double[] regErrorCurrentYear = new double[12];
	
	
	public int currentMonth= 0;
	public ImprovedKNN(){
	}
	public ImprovedKNN(double[] bpErrorLastYear,double[] regErrorLastYear){
		this.bpErrorLastYear = bpErrorLastYear;
		this.regErrorLastYear = regErrorLastYear;
	}
	public ModelType KnnVote(){
		if(currentMonth == 1)  //如果是一月，由去年最好的决定
		{
			return voteForFirstMonth();
		}else{
			return voteForNotFirstMonth();
		}
	}
	
	public ModelType voteForNotFirstMonth(){
		int voteBP = 0;
		int voteReg = 0;
		ModelType nearestBetterModel = this.betterModelLastYear();
		for(int i = 0;i<this.currentMonth;i++){
			if(this.bpErrorCurrentYear[i]<this.regErrorCurrentYear[i]){
				voteBP++;
				nearestBetterModel = ModelType.BP;
			}else if(this.regErrorCurrentYear[i] < this.bpErrorCurrentYear[i]){
				voteReg++;
				nearestBetterModel = ModelType.Reg;
			}else{
				voteBP++;
				voteReg++;
			}
		}
		
		if(voteBP>voteReg) return ModelType.BP;
		else if(voteReg>voteBP) return ModelType.Reg;
		else return nearestBetterModel;
	}
	
	public ModelType voteForFirstMonth(){
		return this.betterModelLastYear();
	}
	
	public ModelType betterModelLastYear(){
		int voteBP = 0;
		int voteReg = 0;
		ModelType nearestBetterModel =  ModelType.BP;
		for(int i=0;i<12;i++){
			if(this.bpErrorLastYear[i] < this.regErrorLastYear[i]){  //bp更优
				voteBP ++ ;
				nearestBetterModel = ModelType.BP;
			}else if(this.bpErrorLastYear[i] > this.regErrorLastYear[i]){  //reg更优
				voteReg++;
				nearestBetterModel = ModelType.Reg;
			}else{  //相等
				voteBP++;
				voteReg++;
			}
		}
		
		if(voteBP > voteReg) return ModelType.BP;
		else if(voteBP < voteReg) return ModelType.Reg;
		else return nearestBetterModel;
		
	}
}
