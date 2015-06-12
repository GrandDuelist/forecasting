package cn.com.preprocessing.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class YmlIO {
	private String filePath;
	private String ymlContent;
	
	
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getYmlContent() {
		return ymlContent;
	}
	public void setYmlContent(String ymlContent) {
		this.ymlContent = ymlContent;
	}
	public YmlIO(){
	}
	public YmlIO(String filePath){
		this.filePath = filePath;
	}
	public void readYmlFile() throws IOException
	{
		if(this.filePath == null){
			new NullPointerException("file path is null");
		}else{
			File file = new File(filePath);
			Long fileLength =  file.length();
			byte[] fileContent = new byte[fileLength.intValue()];
			FileInputStream in  = new FileInputStream(file);
			in.read(fileContent);
			this.ymlContent = new String(fileContent,"UTF-8");
			}
	}
	
	public void parseFromYmpToMap(){
		
	}
	
}
