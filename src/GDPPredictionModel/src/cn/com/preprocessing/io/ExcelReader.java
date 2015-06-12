package cn.com.preprocessing.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.com.forecasting.DAO.EconomyDAO;
import cn.com.forecasting.pojo.ColumnPoJo;
import cn.com.forecasting.pojo.EconomyPoJo;

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
	
	/**
	 * 将excel表格中的数据插入到数据库中
	 * @param xpojos
	 * @param ypojo
	 */
	public void insertDataFromExcel(List<ColumnPoJo> xpojos, ColumnPoJo ypojo){
		
		ColumnPoJo  populationPoJo=null,foreignInvestmentPoJo=null,exportTrade=null,retailSale=null,
		importExportTrade=null,industryIncrement=null,energyConsume=null;
		
		for(int i=0;i<xpojos.size();i++){
			ColumnPoJo current = xpojos.get(i);
			switch(current.getHeader()){
			case "单位GDP能耗吨标准煤/万元":energyConsume=current;break;
			case "人口数万人":populationPoJo=current;break;
			case "实际利用外资亿美元":foreignInvestmentPoJo=current;break;
			case "货物一般贸易出口总额亿美元":exportTrade=current;break;
			case "社会消费品零售总额 亿元":retailSale=current;break;
			case "进出口贸易总额亿美元":importExportTrade=current;break;
			case "工业增加值亿元":industryIncrement=current;break;
			}
		}
		
		EconomyDAO handle = new EconomyDAO();
		handle.connect();
		
		for(int i=0;i<ypojo.getNumber();i++){
			EconomyPoJo pojo = new EconomyPoJo();
			pojo.setYear(1980+i);
			pojo.setCityGDP(ypojo.getData()[i]);
			pojo.setEnergyConsumePerGDP(energyConsume.getData()[i]);
			pojo.setExportTrade(exportTrade.getData()[i]);
			pojo.setIndustryIncrement(industryIncrement.getData()[i]);
			pojo.setGrossRetailSales(retailSale.getData()[i]);
			pojo.setImportExportTrade(importExportTrade.getData()[i]);
			pojo.setPopulation(populationPoJo.getData()[i]);
			pojo.setForeignInvestMent(foreignInvestmentPoJo.getData()[i]);
			handle.insert(pojo);
		}
		
		handle.close();
	}

	/**
	 * 
	 * @param pojos 数据库全查结果
	 * @return List<ColumnPoJo>  回归自变量X，包含前一个时间段gdp；  Y： 本年gdp
	 */
	public List<ColumnPoJo> transFromSqlToColumn(List<EconomyPoJo> pojos,ColumnPoJo Y){
		
		
		List<ColumnPoJo> xPoJos = new ArrayList<ColumnPoJo>();
		
		ColumnPoJo cityGDP = new ColumnPoJo(); cityGDP.setHeader("市GDP亿元");
		ColumnPoJo population = new ColumnPoJo(); population.setHeader("人口数万人");
		ColumnPoJo foreignTrade = new ColumnPoJo(); foreignTrade.setHeader("实际利用外资亿美元");
		ColumnPoJo retailSale = new ColumnPoJo(); retailSale.setHeader("社会消费品零售总额亿元");
		ColumnPoJo energyConsumePerGDP = new ColumnPoJo(); energyConsumePerGDP.setHeader("单位GDP能耗吨标准煤/万元");
		ColumnPoJo importExportTrade = new ColumnPoJo(); importExportTrade.setHeader("进出口贸易总额亿美元");
		ColumnPoJo industryIncrement = new ColumnPoJo(); industryIncrement.setHeader("工业增加值亿元");
		ColumnPoJo exportTrade = new ColumnPoJo(); exportTrade.setHeader("货物一般贸易出口总额亿美元");
		
		
		
		for(int i=0;i<pojos.size()-1;i++){
			EconomyPoJo pojo = pojos.get(i);
			EconomyPoJo nextPoJo = pojos.get(i+1);
			
			cityGDP.getData()[i] = pojo.getCityGDP(); cityGDP.setNumber(cityGDP.getNumber()+1);
			population.getData()[i] = pojo.getPopulation(); population.setNumber(population.getNumber()+1);
			foreignTrade.getData()[i] = pojo.getForeignInvestMent(); foreignTrade.setNumber(foreignTrade.getNumber()+1);
			retailSale.getData()[i] = pojo.getGrossRetailSales(); retailSale.setNumber(retailSale.getNumber()+1);
			energyConsumePerGDP.getData()[i] = pojo.getEnergyConsumePerGDP(); energyConsumePerGDP.setNumber(energyConsumePerGDP.getNumber()+1);
			importExportTrade.getData()[i] = pojo.getImportExportTrade(); importExportTrade.setNumber(importExportTrade.getNumber()+1);
			industryIncrement.getData()[i] = pojo.getIndustryIncrement(); industryIncrement.setNumber(industryIncrement.getNumber()+1);
			exportTrade.getData()[i] = pojo.getExportTrade(); exportTrade.setNumber(exportTrade.getNumber()+1);
			
			Y.getData()[i] = nextPoJo.getCityGDP(); Y.setNumber(Y.getNumber()+1);
		}
		xPoJos.add(cityGDP); xPoJos.add(population); xPoJos.add(foreignTrade);
		xPoJos.add(retailSale);xPoJos.add(energyConsumePerGDP);xPoJos.add(importExportTrade);
		xPoJos.add(industryIncrement);xPoJos.add(exportTrade);
		return xPoJos;
	}
	
}
