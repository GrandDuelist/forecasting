package cn.com.test;

import java.beans.PersistenceDelegate;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.external.com.google.gdata.util.common.base.PercentEscaper;

import cn.com.forecasting.pojo.EconomyYmlPoJo;
import cn.com.preprocessing.io.PredictionResultPersistance;



public class YamlTest {
	public static void main(String args[]) throws IOException {
		/*Yaml yaml = new Yaml();
		String document = "  a: 1\n  b:\n    c: 3\n    d: 4\n";
		System.out.println(document);
		System.out.println(yaml.dump(yaml.load(document)));*/

		/* InputStream input;
		try {
			input = new FileInputStream(new File(
			            "test_data/test.yml"));
			Yaml yaml = new Yaml();
			Map<String, Object> object = (Map<String, Object>) yaml.load(input);
		    System.out.println(object);
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		YmlIO io = new YmlIO("test_data/test.yml");
		try {
			io.readYmlFile();
			System.out.println(io.getYmlContent());
			Yaml yaml = new Yaml();
			Map<String, Object> object = (Map<String, Object>) yaml.load(io.getYmlContent());
		    System.out.println(object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
/*		    
		PredictionResultPersistance p = new PredictionResultPersistance();
		p.setFilePath("../html/_data/economy.yml");
//		p.setFilePath("test_data/test.yml");
		Map<String,Object> object = p.readFromYml();
	//	System.out.println(p.readFromYml());
		System.out.println(object);
		for(String s:object.keySet()){
			Object value = object.get(s);
			System.out.print(s+" ");
			System.out.println(value);
		}*/
		
		
	/*	Constructor constructor = new Constructor(TestPojo.class);
		TypeDescription carDescription = new TypeDescription(TestPojo.class);
		//carDescription.putMapPropertyType("wheels", MyWheel.class, Object.class);
//		carDescription.pu
		constructor.addTypeDescription(carDescription);
		
		FileInputStream input = new FileInputStream(new File(
	            "../html/_data/economy.yml"));
		Yaml yaml = new Yaml(constructor);
		TestPojo pojo = (TestPojo) yaml.load(input);
		System.out.println(pojo.getTestString());
		System.out.println(pojo.getTestDouble());*/
		
		
		PredictionResultPersistance p = new PredictionResultPersistance();
		double[] tax_pre = {226.08,305.85,307.04,307.34,308.33,309.08,309.42,309.72,310.36,311.39,311.91,312.88};
		double[] tax_real = {284.34,285.60,286.98,288.57,290.04,291.38,292.85,294.37,295.59,297.04,298.41,299.82};
		double[] gdp_pre = {1632.37,1645.93,1661.09,1675.70,1687.24,1697.07,1704.65,1705.01,1703.57,1706.29,1709.29,1712.86};
		double[] gdp_real = {1674.71,1681.88,1691.65,1699.69,1707.79,1716.01,1724.40,1732.84,1742.15,1749.80,1758.85,1765.97};
		double[] cor = {0.958,0.959,0.960,0.965,0.972,0.968,0.973,0.978,0.982,0.986,0.988};
		EconomyYmlPoJo pojo = new EconomyYmlPoJo(2012, gdp_pre,gdp_real,tax_pre,tax_real, 12, cor);
		p.setContentPoJo(pojo);
		p.savePoJoToYml("../html/_data/economy.yml");
		
	}
	
}
