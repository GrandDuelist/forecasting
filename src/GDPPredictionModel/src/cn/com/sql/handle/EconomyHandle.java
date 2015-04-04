package cn.com.sql.handle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.com.sql.pojo.EconomyPoJo;


public class EconomyHandle {
	public String driver = "com.mysql.jdbc.Driver";
	public String url = "jdbc:mysql://localhost:3306/forecasting";
	public String user = "root";
	public String psword = "wancar";
	
	public void connect() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,user,psword);
		
		if(!conn.isClosed()) 
             System.out.println("Succeeded connecting to the Database!");

            // statement用来执行SQL语句
            Statement statement = conn.createStatement();
            // 要执行的SQL语句
            String sql = "select * from economy";
            // 结果集
            ResultSet rs = statement.executeQuery(sql);
            
            while(rs.next()){
            
            	
            }
            
            rs.close();
            conn.close();

	}
	
	
	public void insert(EconomyPoJo pojo){
		
	}

}
