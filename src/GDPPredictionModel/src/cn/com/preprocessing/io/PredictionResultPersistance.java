package cn.com.preprocessing.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import cn.com.forecasing.type.DataMapping;
import cn.com.forecasting.pojo.EconomyYmlPoJo;

/**
 * 将预测结果持久化
 * @author ezhihan
 *
 */
public class PredictionResultPersistance {
	
	private String filePath;
	private Map<String,Object> mapContent;
	private EconomyYmlPoJo contentPoJo;
	private String ymlCotent;
	public Map<String, Object> getMapContent() {
		return mapContent;
	}

	public void setMapContent(Map<String, Object> mapContent) {
		this.mapContent = mapContent;
	}

	public EconomyYmlPoJo getContentPoJo() {
		return contentPoJo;
	}

	public void setContentPoJo(EconomyYmlPoJo contentPoJo) {
		this.contentPoJo = contentPoJo;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Map<String,Object> readFromYml() throws FileNotFoundException{
		 InputStream input;
			input = new FileInputStream(new File(
			            filePath));
			Yaml yaml = new Yaml();
			Map<String, Object> object = (Map<String, Object>) yaml.load(input);
		    return object;
	}
	
	public void savePoJoToYml() throws IOException{
		this.transFromPoJoToMap();
		this.saveMapToYml();
	}
	
	public void saveMapToYml() throws IOException{	
		Yaml yaml = new Yaml();
		this.ymlCotent = yaml.dump(this.mapContent);
		File file = new File(this.filePath);
		if(!file.exists()){
			file.createNewFile();
		}
		//FileOutputStream fs = new FileOutputStream(file);
		FileWriter fw = new FileWriter(file);
		yaml.dump(this.mapContent,fw);
	//	fs.write(this.ymlCotent.getBytes());
		System.out.println(yaml.dump(this.mapContent));
		
	}
	
	public void savePoJoToYml(String filePath) throws IOException{
		this.filePath = filePath;
		this.savePoJoToYml();
	}
	
	public String changeArrayToString(double[] values, int length){
		String label = "[";
		for(int i=0; i<length;i++){
			label += values[i];
			if(i==length-1)label+="]";else label+=",";
		}
		return label;
	}
	
	public String changeArrayToString(int[] values, int length){
		String label = "[";
		for(int i=0; i<length;i++){
			label += values[i];
			if(i==length-1)label+="]";else label+=",";
		}
		return label;
	}
	
	public Map<String, Object>[] tranArrayToMap(String secondLayerKey, double[]values,int length ){
		
		Map<String,Object>[] valueMap = new Map[length];
		for(int i=0; i<length;i++){
			valueMap[i] = new HashMap<String,Object>();
			valueMap[i].put(secondLayerKey,values[i]);
		}
		
		return valueMap;
	}
	
	public void transFromPoJoToMap(){
		this.mapContent = new HashMap<String,Object>();
		Map<String,Object> gdpMap = new HashMap<String,Object>();
		Map<String,Object> taxMap = new HashMap<String,Object>();
		Map<String,Object> corMap = new HashMap<String,Object>();
		
		gdpMap.put("year",this.contentPoJo.getYear());
		gdpMap.put("prediction",this.changeArrayToString(this.getContentPoJo().getPredictionGDPMonths(),5));
		gdpMap.put("realvalue", this.changeArrayToString(this.getContentPoJo().getRealGDPMonths(), this.getContentPoJo().getCurrentMonth()-1));
		gdpMap.put("prediction_months",this.tranArrayToMap("predition_month",this.getContentPoJo().getPredictionGDPMonths(),12));
		gdpMap.put("realvalue_months",this.tranArrayToMap("realvalue_month",this.getContentPoJo().getRealGDPMonths(),this.getContentPoJo().getCurrentMonth()-1));
		
		taxMap.put("year", this.contentPoJo.getYear());
		taxMap.put("prediction",this.changeArrayToString(this.getContentPoJo().getPredictionTaxMonths(),5));
		taxMap.put("realvalue", this.changeArrayToString(this.getContentPoJo().getRealTaxMonths(), this.getContentPoJo().getCurrentMonth()-1));
		taxMap.put("prediction_months",this.tranArrayToMap("predition_month",this.getContentPoJo().getPredictionTaxMonths(),12));
		taxMap.put("realvalue_months",this.tranArrayToMap("realvalue_month",this.getContentPoJo().getRealTaxMonths(),this.getContentPoJo().getCurrentMonth()-1));
		
		corMap.put("year", this.changeArrayToString(this.contentPoJo.getPastYears(), DataMapping.PAST_YEAR_NUMBER));
		corMap.put("value",this.changeArrayToString(this.contentPoJo.getCor(), DataMapping.PAST_YEAR_NUMBER));
		
		mapContent.put("gdp", gdpMap);
		mapContent.put("tax", taxMap);
		mapContent.put("cor", corMap);
	}
	
}
