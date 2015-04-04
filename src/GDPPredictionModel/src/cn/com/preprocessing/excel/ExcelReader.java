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
	
	

	public HSSFWorkbook getWorkbook() {
		return workbook;
	}
	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}
	public HSSFSheet getCurrentProcessSheet() {
		return currentProcessSheet;
	}
	public void setCurrentProcessSheet(HSSFSheet currentProcessSheet) {
		this.currentProcessSheet = currentProcessSheet;
	}
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
	//		System.out.println(this.currentProcessSheet);
		HSSFRow row = this.currentProcessSheet.getRow(rowNum);
		return row;
	}
	
//	public void getTargetCol(String currentSheet, int colNum){
////		this.currentProcessSheet.get
//	}
//	
	
	//close the file Stream
	public void closeInputStream() throws IOException{
		if(this.fileInputStream!=null){
			this.fileInputStream.close();
			System.out.println("file stream closed");
		}
	}
	
	
	public void printRowData(HSSFRow row)
	{	
		for(int i=row.getFirstCellNum();i<row.getLastCellNum();i++){
			System.out.print(row.getCell((short)i).getNumericCellValue()+" ");
		}
		
		System.out.println();
	}
	
	
	
	
	//get column
	public ColumnPoJo getTargetCol(String currentSheet, int colNum){
		this.currentProcessSheet = this.workbook.getSheet(currentSheet);
		int firstRow = this.currentProcessSheet.getFirstRowNum();
		int lastRow = this.currentProcessSheet.getLastRowNum();
		
		ColumnPoJo column = new ColumnPoJo();
		
		for(int i=firstRow;i<lastRow;i++){
			HSSFRow currentRow = this.currentProcessSheet.getRow(i);
		
			if(i==0 || i==1){
				String header = column.getHeader()+currentRow.getCell((short)colNum).getStringCellValue();
				column.setHeader(header);
			}else{
				if(currentRow!=null && currentRow.getCell((short)colNum)!=null)
				{	column.getData()[i-2]=currentRow.getCell((short)colNum).getNumericCellValue();
					column.numberPlusOne();
				}
			}
		}
		
		return column;	
	}
	
	//print col data
	public void printColData(ColumnPoJo column) {
		// TODO Auto-generated method stub
		System.out.print("Header: "+ column.getHeader()+"\n");
		for(int i=0; i< column.getNumber();i++){
			System.out.print(column.getData()[i]+" ");
		}
		
		System.out.println();
		
	}
	
	
}
