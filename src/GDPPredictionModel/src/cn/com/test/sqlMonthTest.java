package cn.com.test;

import cn.com.sql.handle.EconomyHandle;

public class sqlMonthTest {

	public static void main(String[] args){
		
		EconomyHandle handle = new EconomyHandle();
		handle.connect();
		handle.updateMonthTax(1, 5000);
		
		handle.close();
	}
}
