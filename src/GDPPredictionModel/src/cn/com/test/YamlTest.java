package cn.com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import cn.com.preprocessing.io.YmlIO;

public class YamlTest {
	public static void main(String args[]) {
		Yaml yaml = new Yaml();
		String document = "  a: 1\n  b:\n    c: 3\n    d: 4\n";
		System.out.println(document);
		System.out.println(yaml.dump(yaml.load(document)));

	/*	 InputStream input;
		try {
			input = new FileInputStream(new File(
			            "test_data/test.yml"));
			Yaml yaml = new Yaml();
			Map<String, Object> object = (Map<String, Object>) yaml.load(input);
		    System.out.println(object);
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
		    
		}
	
}
