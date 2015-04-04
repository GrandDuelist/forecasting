package cn.com.test;

import java.sql.SQLException;

import cn.com.sql.handle.EconomyHandle;

public class SqlTest {
	
	public static void main(String[] args){
		EconomyHandle handle = new EconomyHandle();
		try {
			handle.connect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
