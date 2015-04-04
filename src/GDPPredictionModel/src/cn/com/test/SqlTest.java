package cn.com.test;

import java.sql.SQLException;

import cn.com.sql.handle.EconomyHandle;

public class SqlTest {
	
	public static void main(String[] args){
		EconomyHandle handle = new EconomyHandle();
		try {
			handle.connect();
			handle.selectAll();
			handle.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
