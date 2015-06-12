package cn.com.test;

import cn.com.forecasting.DAO.EconomyDAO;

public class sqlMonthTest {

	public static void main(String[] args){
		
		EconomyDAO handle = new EconomyDAO();
		handle.connect();
		handle.updateMonthTax(1, 5000);
		
		handle.close();
	}
}
