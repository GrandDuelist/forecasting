package cn.com.preprocessing;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.com.preprocessing.excel.ColumnPoJo;
import cn.com.preprocessing.excel.ExcelReader;
import cn.com.preprocessing.math.MathCalculation;

public class AttributeFilter {
	
	private ExcelReader excelReader;
	
	public AttributeFilter(ExcelReader excelReader){
		this.excelReader = excelReader;
	}
	
	public List<ColumnPoJo> getAllDataWithCoefficient(String sheetName,int targetCol){
		List<ColumnPoJo> columns = new ArrayList<ColumnPoJo>();
		
		ColumnPoJo target = excelReader.getTargetCol(sheetName, targetCol);
		HSSFSheet currentSheet= excelReader.getCurrentProcessSheet();
		MathCalculation math = new MathCalculation();
		
		int firstCol = currentSheet.getRow(currentSheet.getFirstRowNum()).getFirstCellNum();
		int lastCol =  currentSheet.getRow(currentSheet.getFirstRowNum()).getLastCellNum();
		
		for(int i=firstCol;i<lastCol;i++){
			ColumnPoJo compare = excelReader.getTargetCol(sheetName, i);
			double coef = math.calcCoefficient(target.getData(),compare.getData(),target.getNumber());
			compare.setCoefficient(coef);
	
			if(i!=targetCol){
				columns.add(compare);
			}
			
			System.out.println(compare.getHeader()+"  "+compare.getCoefficient());
		}
									
		return columns;
		
	}
	
	
}
