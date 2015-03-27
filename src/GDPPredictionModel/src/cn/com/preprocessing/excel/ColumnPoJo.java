package cn.com.preprocessing.excel;

public class ColumnPoJo {

	public static final int MAX_COLUMN_NUMBER = 100;

	private double[] data = new double[MAX_COLUMN_NUMBER];
	private String header="";
	private int number = 0;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double[] getData() {
		return data;
	}

	public void setData(double[] data) {
		this.data = data;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
	public void numberPlusOne(){
		this.number++;
	}
	

}
