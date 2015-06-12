package cn.com.forecasting.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.forecasting.pojo.ColumnPoJo;
import cn.com.forecasting.pojo.EconomyPoJo;

/***
 * 经济实体代理，处理所有实体接口
 * 
 * @author zhihan
 * 
 */

public class EconomyDAO {
	public String driver = "com.mysql.jdbc.Driver";
	public String url = "jdbc:mysql://localhost:3306/forecasting";
	public String user = "root";
	public String psword = "wancar";
	Connection conn;

	/**
	 * 连接数据库
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void connect() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, psword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 关闭数据库
	 * 
	 * @throws SQLException
	 */
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 数据库全查
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<EconomyPoJo> selectAllYear() throws SQLException {
		List<EconomyPoJo> pojos = new ArrayList<EconomyPoJo>();
		/*if (!conn.isClosed())
			System.out.println("Succeeded connecting to the Database!");*/
		// statement用来执行SQL语句
		Statement statement = conn.createStatement();
		// 要执行的SQL语句
		String sql = "select * from economy";
		// 结果集
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			EconomyPoJo current = new EconomyPoJo();
			current.setMonth(rs.getInt("month"));
			current.setYear(rs.getInt("year"));
			current.setCityGDP(rs.getDouble("city_gdp"));
			current.setPopulation(rs.getDouble("population"));
			current.setEnergyConsumePerGDP(rs
					.getDouble("energy_consume_per_gdp"));
			current.setForeignInvestMent(rs.getDouble("foreign_investment"));
			current.setExportTrade(rs.getDouble("export_trade"));
			current.setGrossRetailSales(rs.getDouble("retail_sale"));
			current.setImportExportTrade(rs.getDouble("import_export_trade"));
			current.setIndustryIncrement(rs.getDouble("industry_increment"));
			current.setTax(rs.getDouble("tax"));
			pojos.add(current);
		}
		rs.close();
		return pojos;

	}
	
	/**
	 * 数据库全查
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<EconomyPoJo> selectAllMonth() throws SQLException {
		List<EconomyPoJo> pojos = new ArrayList<EconomyPoJo>();
		/*if (!conn.isClosed())
			System.out.println("Succeeded connecting to the Database!");*/
		// statement用来执行SQL语句
		Statement statement = conn.createStatement();
		// 要执行的SQL语句
		String sql = "select * from economy_month";
		// 结果集
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			EconomyPoJo current = new EconomyPoJo();
			current.setMonth(rs.getInt("month"));
			current.setYear(rs.getInt("year"));
			current.setCityGDP(rs.getDouble("city_gdp"));
			current.setPopulation(rs.getDouble("population"));
			current.setEnergyConsumePerGDP(rs
					.getDouble("energy_consume_per_gdp"));
			current.setForeignInvestMent(rs.getDouble("foreign_investment"));
			current.setExportTrade(rs.getDouble("export_trade"));
			current.setGrossRetailSales(rs.getDouble("retail_sale"));
			current.setImportExportTrade(rs.getDouble("import_export_trade"));
			current.setIndustryIncrement(rs.getDouble("industry_increment"));
			current.setTax(rs.getDouble("tax"));
			pojos.add(current);
		}
		rs.close();
		return pojos;

	}

	/**
	 * 查询月数据
	 * @param year
	 * @param month
	 * @return
	 * @throws SQLException
	 */
	public EconomyPoJo selectMonthEconomy(int year,int month) throws SQLException{
		EconomyPoJo current = null;
	/*	if (!conn.isClosed())
			System.out.println("Succeeded connecting to the Database!");*/
		// 要执行的SQL语句
		String sql = "select * from economy_month where economy_month.year=? and economy_month.month=?";
		// 结果集
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, year);
		pst.setInt(2,month);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			current = new EconomyPoJo();
			current.setMonth(rs.getInt("month"));
			current.setYear(rs.getInt("year"));
			current.setCityGDP(rs.getDouble("city_gdp"));
			current.setPopulation(rs.getDouble("population"));
			current.setEnergyConsumePerGDP(rs
					.getDouble("energy_consume_per_gdp"));
			current.setForeignInvestMent(rs.getDouble("foreign_investment"));
			current.setExportTrade(rs.getDouble("export_trade"));
			current.setGrossRetailSales(rs.getDouble("retail_sale"));
			current.setImportExportTrade(rs.getDouble("import_export_trade"));
			current.setIndustryIncrement(rs.getDouble("industry_increment"));
			current.setTax(rs.getDouble("tax"));
			
		}
		rs.close();
		return current;
	}
	/**
	 * 查找年度数据
	 * @param year
	 * @return
	 * @throws SQLException
	 */
	public EconomyPoJo selectYearEconomy(int year) throws SQLException{
		EconomyPoJo current = null;
		/*if (!conn.isClosed())
			System.out.println("Succeeded connecting to the Database!");*/
		// 要执行的SQL语句
		String sql = "select * from economy where economy.year=?";
		// 结果集
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, year);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			current = new EconomyPoJo();
			current.setMonth(rs.getInt("month"));
			current.setYear(rs.getInt("year"));
			current.setCityGDP(rs.getDouble("city_gdp"));
			current.setPopulation(rs.getDouble("population"));
			current.setEnergyConsumePerGDP(rs
					.getDouble("energy_consume_per_gdp"));
			current.setForeignInvestMent(rs.getDouble("foreign_investment"));
			current.setExportTrade(rs.getDouble("export_trade"));
			current.setGrossRetailSales(rs.getDouble("retail_sale"));
			current.setImportExportTrade(rs.getDouble("import_export_trade"));
			current.setIndustryIncrement(rs.getDouble("industry_increment"));
			current.setTax(rs.getDouble("tax"));
			
		}
		rs.close();
		return current;
	}
	/**
	 * 查询预测年之前的数据
	 * 
	 * @param year
	 * @return
	 * @throws SQLException
	 */
	public List<EconomyPoJo> selectPreviousYear(int year) throws SQLException {
		List<EconomyPoJo> pojos = new ArrayList<EconomyPoJo>();
		/*if (!conn.isClosed())
			System.out.println("Succeeded connecting to the Database!");*/
		// 要执行的SQL语句
		String sql = "select * from economy where economy.year<?";
		// 结果集
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, year);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			EconomyPoJo current = new EconomyPoJo();
			current.setMonth(rs.getInt("month"));
			current.setYear(rs.getInt("year"));
			current.setCityGDP(rs.getDouble("city_gdp"));
			current.setPopulation(rs.getDouble("population"));
			current.setEnergyConsumePerGDP(rs
					.getDouble("energy_consume_per_gdp"));
			current.setForeignInvestMent(rs.getDouble("foreign_investment"));
			current.setExportTrade(rs.getDouble("export_trade"));
			current.setGrossRetailSales(rs.getDouble("retail_sale"));
			current.setImportExportTrade(rs.getDouble("import_export_trade"));
			current.setIndustryIncrement(rs.getDouble("industry_increment"));
			current.setTax(rs.getDouble("tax"));
			pojos.add(current);
		}
		rs.close();
		return pojos;

	}

	/**
	 * 查询预测年之前相同月的数据
	 * 
	 * @param year
	 * @return
	 * @throws SQLException
	 */
	public List<EconomyPoJo> selectPreviousYearSameMonth(int year, int month)
			throws SQLException {
		List<EconomyPoJo> pojos = new ArrayList<EconomyPoJo>();
		/*if (!conn.isClosed())
			System.out.println("Succeeded connecting to the Database!");*/
		
		// 要执行的SQL语句
		String sql = "select * from economy_month where economy_month.year<? and economy_month.month=?";
		// 结果集
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, year);
		pst.setDouble(2, month);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			EconomyPoJo current = new EconomyPoJo();
			current.setMonth(rs.getInt("month"));
			current.setYear(rs.getInt("year"));
			current.setCityGDP(rs.getDouble("city_gdp"));
			current.setPopulation(rs.getDouble("population"));
			current.setEnergyConsumePerGDP(rs
					.getDouble("energy_consume_per_gdp"));
			current.setForeignInvestMent(rs.getDouble("foreign_investment"));
			current.setExportTrade(rs.getDouble("export_trade"));
			current.setGrossRetailSales(rs.getDouble("retail_sale"));
			current.setImportExportTrade(rs.getDouble("import_export_trade"));
			current.setIndustryIncrement(rs.getDouble("industry_increment"));
			current.setTax(rs.getDouble("tax"));
			pojos.add(current);
		}
		rs.close();
		return pojos;

	}

	public void insert(EconomyPoJo pojo) {
		try {
			if (!conn.isClosed()) {
				// 要执行的SQL语句
				String sql = "INSERT INTO `forecasting`.`economy` (`year`,`city_gdp`,`energy_consume_per_gdp`,`population`,"
						+ "`foreign_investment`,`export_trade`,`retail_sale`,`import_export_trade`,`industry_increment`,`month`) "
						+ "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
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
				pst.setInt(10, pojo.getMonth());
				pst.setDouble(11, pojo.getTax());
				pst.executeUpdate();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void updateMonthTax(int id, double tax){
		try {
			if (!conn.isClosed()) {
				// 要执行的SQL语句
				String sql = "Update `forecasting`.`economy_month` SET `tax`=? WHERE `id`=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setDouble(1,tax);
				pst.setInt(2,id);
				pst.executeUpdate();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 插入月份经济数据到 month表
	 * 
	 * @param pojo
	 */
	public void insertToEconomyMonth(EconomyPoJo pojo) {
		try {
			if (!conn.isClosed()) {
				// 要执行的SQL语句
				String sql = "INSERT INTO `forecasting`.`economy_month` (`year`,`city_gdp`,`energy_consume_per_gdp`,`population`,"
						+ "`foreign_investment`,`export_trade`,`retail_sale`,`import_export_trade`,`industry_increment`,`month`,`tax`) "
						+ "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
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
				pst.setInt(10, pojo.getMonth());
				pst.setDouble(11, pojo.getTax());
				pst.executeUpdate();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 讲预测gdp 自变量转化为数组
	 * 
	 * @param pojo
	 *            economypojo 经济数据表
	 * @return
	 */
	public double[] changeGDPAttributeToArray(EconomyPoJo pojo) {
		double[] result = new double[8];
		result[0] = pojo.getCityGDP();
		result[1] = pojo.getEnergyConsumePerGDP();
		result[2] = pojo.getExportTrade();
		result[3] = pojo.getForeignInvestMent();
		result[4] = pojo.getGrossRetailSales();
		result[5] = pojo.getImportExportTrade();
		result[6] = pojo.getIndustryIncrement();
		result[7] = pojo.getPopulation();
		return result;
	}
	/**
	 * @param tableName target table
	 * @param dataName data name
	 * @param startYear included
	 * @param endYear not included
	 * @return array of target data
	 * @throws SQLException 
	 */
	public double[] selectSpecificYearData(String dataName,int startYear, int endYear) throws SQLException{
		
		
		// 要执行的SQL语句
		String sql = "select * from economy where economy.year<? and economy.year>=?";
		// 结果集
		PreparedStatement pst = conn.prepareStatement(sql);
	
		pst.setInt(1, endYear);
		pst.setInt(2,startYear);
		ResultSet rs = pst.executeQuery();
		List<Double> resultList = new ArrayList<Double>(); 
		
		while (rs.next()) {
				resultList.add(rs.getDouble(dataName));
			}
		
		int length = resultList.size();
		double[] results = new double[length];
		for(int i=0;i<resultList.size();i++){
			results[i] = resultList.get(i);
		}
			return results;
		}
		
	public double[] selectSpecificYearGDP(int startYear, int endYear){
		try {
			return this.selectSpecificYearData("city_gdp", startYear, endYear);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public double[] selectSpecificYearTax(int startYear,int endYear){
		try {
			return this.selectSpecificYearData("tax", startYear, endYear);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	}