package next.xadmin.others.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import next.xadmin.others.bean.OtherBean;

public class OtherDao {
	private String dbUrl = "jdbc:mysql://localhost:3306/mydb";
	private String dbUname = "root";
	private String dbPassword = "AdminMax18!";
	private String dbDriver = "com.mysql.jdbc.Driver";

	public void loadDriver(String dbDriver)
	{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
	         Connection con = null;
	         try {
				con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         return con;
	}
	public boolean validate(OtherBean otherBean) {
		loadDriver(dbDriver);
		
		Connection con = getConnection();
		System.out.println("connection success");
		boolean status = false;
		
		String sql = "select * from others where username = ? and password=?";
		
		PreparedStatement ps;
		try {
			ps =con.prepareStatement(sql);
			ps.setString(1, otherBean.getUsername());
			ps.setString(2, otherBean.getPassword());
			
			ResultSet rs = ps.executeQuery();
			status = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		
		return status;
		
	}
	

}

