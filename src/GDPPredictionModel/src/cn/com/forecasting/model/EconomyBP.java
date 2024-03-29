package cn.com.forecasting.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import cn.com.forecasing.type.DataMapping;
import cn.com.forecasing.type.EconomyType;
import cn.com.forecasting.DAO.EconomyDAO;
import cn.com.forecasting.pojo.EconomyPoJo;
import cn.com.preprocessing.math.SpecificMath;

/**
 * 对BP进行封装
 * 
 * @author zhihan
 * 
 */
public class EconomyBP {
	public BaseAnn bp;
	private int inputSize ;
	private int outputSize;
	public EconomyBP(int inputSize, int hiddenSize, int outputSize) {
		this.bp = new BaseAnn(inputSize, hiddenSize, outputSize);
		this.inputSize=inputSize;
		this.outputSize=outputSize;
	}

	// 直接通过EconomyPoJo的列表进行训练
	public void trainByEconomyList(List<EconomyPoJo> pojos, int trainTimes) {
		EconomyDAO handle = new EconomyDAO();
		if (this.bp == null) {
			System.out.println("please init bp at first");
			return;
		}
		// 对数据进行训练
		for (int i = 0; i < trainTimes; i++) {
			for (int j = 0; j < pojos.size() - 1; j++) {
				EconomyPoJo currentPojo = pojos.get(j);
				EconomyPoJo nextPojo = pojos.get(j + 1);
				double[] currentX = handle.changeGDPAttributeToArray(currentPojo);
				double[] target = new double[1];
				target[0] = nextPojo.getCityGDP();
				this.bp.train(currentX, target);
			}
		}

		for (int j = 0; j < pojos.size() - 1; j++) {

			EconomyPoJo currentPojo = pojos.get(j);
			EconomyPoJo nextPojo = pojos.get(j + 1);
			double[] currentX = handle.changeGDPAttributeToArray(currentPojo);
			double[] target = new double[1];
			target[0] = nextPojo.getCityGDP();
			double[] result = this.bp.test(currentX);
			System.out.println("预测值 " + result[0] + "  实际值 " + target[0]+ "误差： "+ 
					(result[0]-target[0])/target[0]);
		}

	}

	/**
	 * normalize the economy and train networks
	 * 
	 * @param pojos 
	 * @param trainTimes 
	 */
	public void trainByNormalizedEconomy(List<EconomyPoJo> pojos,EconomyType type, int trainTimes) {
		if (this.bp == null) {
			System.out.println("please init bp at first");
			return;
		}
		
		SpecificMath preprocessing = new SpecificMath(this.inputSize);
		preprocessing.calculateMaxMinOfXY(pojos,type);

		// 对数据进行训练
		for (int i = 0; i < trainTimes; i++) {
			for (int j = 0; j < pojos.size() - 1; j++) {
				EconomyPoJo currentPojo = pojos.get(j);
				EconomyPoJo nextPojo = pojos.get(j + 1);
				double[] currentX = preprocessing.normalizeX(currentPojo);
				double[] target = new double[1];
				target[0] = preprocessing.normalizeY(nextPojo.getCurrentY(type));
				this.bp.train(currentX, target);
			}
		}
		
		
		//训练测试集
		for (int j = 0; j < pojos.size() - 1; j++) {

			EconomyPoJo currentPojo = pojos.get(j);
			EconomyPoJo nextPojo = pojos.get(j + 1);
			double[] currentX = preprocessing.normalizeX(currentPojo);
			double[] target = new double[1];
			target[0] = preprocessing.normalizeY(nextPojo.getCurrentY(type));
			double[] result = this.bp.test(currentX);
			System.out.println("预测值 " + preprocessing.reverseY(result[0]) + "  实际值 " + nextPojo.getCurrentY(type) + "误差： 百分之"+ 
			100*Math.abs((preprocessing.reverseY(result[0])-nextPojo.getCurrentY(type))/nextPojo.getCurrentY(type)));
		}

	}
	
	/**
	 * 
	 * @param pojos
	 * @param trainTimes
	 */
	public void trainOnlyByNormalizedEconomy(List<EconomyPoJo> pojos,EconomyType type,int trainTimes){
		if (this.bp == null) {
			System.out.println("please init bp at first");
			return;
		}
		
		this.bp.preprocessing = new SpecificMath(this.inputSize);
		this.bp.preprocessing.calculateMaxMinOfXY(pojos,type);

		// 对数据进行训练
		for (int i = 0; i < trainTimes; i++) {
			for (int j = 0; j < pojos.size() - 1; j++) {
				EconomyPoJo currentPojo = pojos.get(j);
				EconomyPoJo nextPojo = pojos.get(j + 1);
				double[] currentX = this.bp.preprocessing.normalizeX(currentPojo);
				double[] target = new double[1];
				target[0] = this.bp.preprocessing.normalizeY(nextPojo.getCurrentY(type));
				this.bp.train(currentX, target);
			}
		}
		
	}
	
	/**
	 * output the weight of bp to file 
	 * @param year target year 
	 * @throws IOException 
	 */
	public boolean outputBaseBpToFile(int year,EconomyType type) throws IOException{
		boolean isExisted = true;
		File file = new File(DataMapping.BP_WEIGHT_DIRECTORY+type+"/"+year);
		//file existed
		if(!file.exists()){
			file.createNewFile();
			isExisted = false;
		}
		//写入对象
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
		os.writeObject(this.bp);
		os.close();
		return isExisted;
	}
	public boolean outputBaseBpToFile(int year, int month,EconomyType type) throws Exception{
		boolean isExisted = true;
		File file = new File(DataMapping.BP_WEIGHT_DIRECTORY+type+"/"+year+"_"+month);
		if(!file.exists()){
			file.createNewFile();
			isExisted = false;
		}
		//写入对象
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
		os.writeObject(this.bp);
		os.close();
		return isExisted;
	}
	
	/**
	 * 从文件读取bp对象
	 * @param year
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 * @return 文件是否存在，不存在返回false
	 */
	public boolean readBaseBpFromFile(int year,EconomyType type) throws IOException, ClassNotFoundException{
		boolean isExisted = true;
		File file = new File(DataMapping.BP_WEIGHT_DIRECTORY+type+"/"+year);
		//file existed
		if(!file.exists()){
			isExisted = false; return isExisted;
		}
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
		this.bp = (BaseAnn)is.readObject();
		return isExisted;
	}
	
	public boolean readBaseBpFromFile(int year,int month,EconomyType type) throws Exception {
		boolean isExisted = true;
		File file = new File(DataMapping.BP_WEIGHT_DIRECTORY+type+"/"+year+"_"+month);
		if(!file.exists()){
			isExisted=false; return isExisted;
		}
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
		this.bp = (BaseAnn)is.readObject();
		return isExisted;
	}
	/**
	 * seperate normalize the economy and train networks
	 * 
	 * @param pojos
	 * @param trainTimes
	 */
	public void trainByNormalizedEconomy(List<EconomyPoJo> pojos,List<EconomyPoJo> test,EconomyType type, int trainTimes) {
		if (this.bp == null) {
			System.out.println("please init bp at first");
			return;
		}
		
		this.bp.preprocessing = new SpecificMath(this.inputSize);
		this.bp.preprocessing.calculateMaxMinOfXY(pojos,type);

		// 对数据进行训练
		for (int i = 0; i < trainTimes; i++) {
			for (int j = 0; j < pojos.size() - 1; j++) {
				EconomyPoJo currentPojo = pojos.get(j);
				EconomyPoJo nextPojo = pojos.get(j + 1);
				double[] currentX = this.bp.preprocessing.normalizeX(currentPojo);
				double[] target = new double[1];
				target[0] = this.bp.preprocessing.normalizeY(nextPojo.getCurrentY(type));
				this.bp.train(currentX, target);
			}
		}
		
		
		//训练测试集
		for (int j = 0; j < test.size() - 1; j++) {

			EconomyPoJo currentPojo = test.get(j);
			EconomyPoJo nextPojo = test.get(j + 1);
			double[] currentX = this.bp.preprocessing.normalizeX(currentPojo);
			double[] target = new double[1];
			target[0] = this.bp.preprocessing.normalizeY(nextPojo.getCurrentY(type));
			double[] result = this.bp.test(currentX);
			System.out.println("预测值 " + this.bp.preprocessing.reverseY(result[0]) + "  实际值 " + nextPojo.getCurrentY(type)+  "误差： 百分之"+ 
					100*Math.abs((this.bp.preprocessing.reverseY(result[0])-nextPojo.getCurrentY(type))/nextPojo.getCurrentY(type)));
		}
	}
	
	public  double[] test(EconomyPoJo pojo){
		double[] currentX = this.bp.preprocessing.normalizeX(pojo);
		double[] result = this.bp.test(currentX);
		return result;
	}

}
