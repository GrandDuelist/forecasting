package cn.com.sql.handle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.sql.pojo.EconomyPoJo;


public class EconomyHandle {
	public String driver = "com.mysql.jdbc.Driver";
	public String url = "jdbc:mysql://localhost:3306/forecasting";
	public String user = "root";
	public String psword = "wancar";
	Connection conn;
	
	/**
	 * 连接数据库
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void connect(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,psword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	/**
	 * 关闭数据库
	 * @throws SQLException
	 */
	public void close(){
		  try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 数据库全查
	 * @return
	 * @throws SQLException
	 */
	public List<EconomyPoJo> selectAll() throws SQLException{
		
		
		List<EconomyPoJo> pojos = new ArrayList<EconomyPoJo>();
		if(!conn.isClosed()) 
             System.out.println("Succeeded connecting to the Database!");

            // statement用来执行SQL语句
            Statement statement = conn.createStatement();
            // 要执行的SQL语句
            String sql = "select * from economy";
            // 结果集
            ResultSet rs = statement.executeQuery(sql);
            
            while(rs.next()){
            	EconomyPoJo current = new EconomyPoJo();
            	
            	current.setYear(rs.getInt("year"));
            	current.setCityGDP(rs.getDouble("cityGDP"));
            	current.setEnergyConsumePerGDP(rs.getDouble("单位GDP能耗"));
            	current.setForeignInvestMent(rs.getDouble("实际利用外资"));
            	current.setExportTrade(rs.getDouble("货物一般贸易出口总额"));
            	current.setGrossRetailSales(rs.getDouble("社会消费品零售总额"));
            	current.setImportExportTrade(rs.getDouble("进出口贸易总额"));
            	current.setIndustryIncrement(rs.getDouble("工业增加值"));
            	
            	pojos.add(current);
            }
            
            rs.close();
            return pojos;
   
	}
	
	
	public void insert(EconomyPoJo pojo){
		try {
			if(!conn.isClosed()) {
				 // 要执行的SQL语句
	           String sql = "INSERT INTO `forecasting`.`economy`(`year`,`cityGDP`,`单位GDP能耗`,` 人口数`," +
	           		"`实际利用外资`,`货物一般贸易出口总额`,`社会消费品零售总额`,`进出口贸易总额`,`工业增加值`)" +
	           		"VALUES (?,?,?,?,?,?,?,?,?);";
	           PreparedStatement pst = conn.prepareStatement(sql);
	           pst.setInt(1, pojo.getYear());
	           pst.setDouble(2, pojo.getCityGDP());
	           pst.setDouble(3, pojo.getEnergyConsumePerGDP());
	           pst.setDouble(4, pojo.getPopulation());
	           pst.setDouble(5, pojo.getForeignInvestMent());
	           pst.setDouble(6, pojo.getExportTrade());
	           pst.setDouble(7, pojo.getGrossRetailSales());
	           pst.setDouble(8, pojo.getImportExportTrade());
	           pst.setDouble(9, pojo.getIndustryIncrement());
	           pst.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
