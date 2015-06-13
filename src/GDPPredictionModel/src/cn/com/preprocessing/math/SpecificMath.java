package cn.com.preprocessing.math;

import java.io.Serializable;
import java.util.List;

import cn.com.forecasing.type.EconomyType;
import cn.com.forecasting.DAO.EconomyDAO;
import cn.com.forecasting.pojo.EconomyPoJo;
/**
 * 主要是归一化和反归一化处理
 * @author zhihan
 *
 */
public class SpecificMath implements Serializable {
	
	private double[] xMin;
	private double[] xMax;
	private double yMin;
	private double yMax;
	
	public static int ADJUST_TIME = 2;
	
	int xNumber;
	public SpecificMath(int xNumber){
		this.xNumber = xNumber;
		xMin = new double[this.xNumber];
		xMax = new double[this.xNumber];
	}

	
	/**
	 * normalize x
	 * @param pojo
	 * @return
	 */
	public double[] normalizeX(EconomyPoJo pojo){
		EconomyDAO handle = new EconomyDAO();
		double[] current = handle.changeGDPAttributeToArray(pojo);
		double[] result = new double[this.xNumber];
		for(int i=0;i<this.xNumber;i++){
			result[i] = (current[i]-this.xMin[i])/(this.xMax[i]-this.xMin[i]);
		}
		return result;
		
	}
	/**
	 * normalize y
	 * @param y
	 * @return
	 */
	public double normalizeY(double y){
		return (y-this.yMin)/(this.yMax*SpecificMath.ADJUST_TIME-this.yMin);
	}
	
	/**
	 * calculate the max and min value of both x and y
	 * @param pojos
	 */
	public void calculateMaxMinOfXY(List<EconomyPoJo> pojos,EconomyType type){
		EconomyDAO handle = new EconomyDAO();
		double[] init = handle.changeGDPAttributeToArray(pojos.get(0));
		if(this.xNumber!=init.length){
			throw new IllegalArgumentException("Size Do Not Match."); 
		}
		
		System.arraycopy(init,0, this.xMax,0,init.length);
		System.arraycopy(init, 0,this.xMin, 0, init.length);
	
		this.yMax = pojos.get(1).getCurrentY(type);
		this.yMin = pojos.get(1).getCurrentY(type);
		
		for(int i=0;i<pojos.size()-1;i++){
			double[] current = handle.changeGDPAttributeToArray(pojos.get(i));
			double currentY = pojos.get(i+1).getCurrentY(type);
			
			for(int j=0; j<this.xNumber;j++){
				if(current[j]>this.xMax[j]){
					this.xMax[j] = current[j];
				}
				if(current[j]<this.xMin[j]){
					this.xMin[j] = current[j];
				}
			}
			
			if(this.yMax < currentY) {
				this.yMax = currentY;
			}
			if(this.yMin > currentY){
				this.yMin = currentY;
			}
		}
		
		
	}
	
	/**
	 * 还原被归一的预测值
	 * @param y
	 * @return
	 */
	public double reverseY(double y){
		return y*(this.yMax*SpecificMath.ADJUST_TIME-this.yMin)+this.yMin;
	}

}
