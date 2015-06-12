package cn.com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlTest {
	public static void main(String args[]) {
		/*Yaml yaml = new Yaml();
		String document = "  a: 1\n  b:\n    c: 3\n    d: 4\n";
		System.out.println(document);
		System.out.println(yaml.dump(yaml.load(document)));
*/	
		 InputStream input;
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
		    
		}
	
}
