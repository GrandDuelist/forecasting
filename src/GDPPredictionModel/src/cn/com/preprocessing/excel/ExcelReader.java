package cn.com.preprocessing.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelReader {
	
	private String fileName;
	private FileInputStream fileInputStream;
	private HSSFWorkbook workbook;
	private HSSFSheet currentProcessSheet;
	
	

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
	public ExcelReader(String fileName){
		this.setFileName(fileName);
	}
	
	
	
	//read excel file by name setted
	public void readExcelFile() throws IOException{
	    this.fileInputStream = new FileInputStream(this.fileName);
		this.workbook = new HSSFWorkbook(this.fileInputStream);
	
		System.out.println("read the file success");
//		HSSFWorksheet worksheet = workbook.getSheet("")

	}
	
	
	public HSSFRow getTargetRow(String currentSheet, int rowNum){
		this.currentProcessSheet = this.workbook.getSheet(currentSheet);
		HSSFRow row = this.currentProcessSheet.getRow(rowNum);
		return row;
	}
	
	
	
	//close the file Stream
	public void closeInputStream() throws IOException{
		if(this.fileInputStream!=null){
			this.fileInputStream.close();
			System.out.println("file stream closed");
		}
	}
	
	
	public void printRowData(HSSFRow row)
	{
		for(int i=0;i<row.getRowNum();i++){
			System.out.println(row.getCell((short)i));
		}
	}
	
	
}
