package com.xadmin.othermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.othermanagement.bean.Otheruser;


public class OtherUserDao {
	
	 private String jdbcURL = "jdbc:mysql://localhost:3306/mydb";
	 private String jdbcUsername = "root";
	 private String jdbcPassword = "AdminMax18!";
	 private String jdbcDriver = "com.mysql.jdbc.Driver";
	 
	 private static final String INSERT_USERS_SQL ="INSERT INTO others" + "(username, password) VALUES "
			 + " (?, ?);";
			
	 private static final String SELECT_USER_BY_ID = "select id,username,password from others where id =?";
	 private static final String SELECT_ALL_USERS = "select * from others";
	 private static final String DELETE_USERS_SQL = "delete from others where id = ?;";
	 private static final String UPDATE_USERS_SQL = "update others set username = ?,password= ? where id = ?;";
	public OtherUserDao() {
	
	}
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			System.out.println("Connection Success");
		} catch (SQLException|ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Connection Lost");
			e.printStackTrace();
		}
		return connection;
		
	}

	//insert user
		public void insertUser(Otheruser otheruser)  throws SQLException
		{
			System.out.println(INSERT_USERS_SQL);
			try (Connection connection = getConnection();
			   PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			 preparedStatement.setString(1, otheruser.getUsername());
			 preparedStatement.setString(2,  otheruser.getPassword());
			 System.out.println(preparedStatement);
			 preparedStatement.executeUpdate();
			}catch (SQLException e) {
				   printSQLException(e);
			   }
			
		}
		//select user
		public Otheruser selectUser(int id) {
			Otheruser otheruser = null;
			// Step 1 -- Establish Connection
			try (Connection connection = getConnection();
			//Step 2 -- Create statement
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			//Step 3 -- Execute statement 
			ResultSet rs = preparedStatement.executeQuery();
			//Step 4 -- Process the ResultSet object
			while (rs.next()) {
				String username =  rs.getString("username");
				String password =  rs.getString("password");
				otheruser = new Otheruser(id, username, password);
			}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return otheruser;		
		}
		
		//select all users
		 public List<Otheruser> selectAllUsers(){
			 List<Otheruser> otherusers = new ArrayList<>();
			 try(Connection connection = getConnection();
					
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
				 System.out.println(preparedStatement);
				 ResultSet rs = preparedStatement.executeQuery();
				 while(rs.next()) {
					 int id = rs.getInt("id");
					 String username = rs.getString("username");
					 String password = rs.getString("password");
					 otherusers.add(new Otheruser(id, username, password));
				 }
			 } catch (SQLException e) {
				 printSQLException(e);
			 }
			  return otherusers;
		 }
		//update user
		public boolean updateUser(Otheruser otheruser) throws SQLException {
			boolean rowUpdated;
			try (Connection connection =getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) { 
				System.out.println("updated User:" +statement);
				statement.setString(1,  otheruser.getUsername());
				statement.setString(2, otheruser.getPassword());			
				statement.setInt(3, otheruser.getId());
				
				rowUpdated = statement.executeUpdate() > 0;
				
			}
			return rowUpdated;
		}
		
		//delete user
		public boolean deleteUser(int id) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
						statement.setInt(1, id);
						rowDeleted = statement.executeUpdate() > 0;
					}
					return rowDeleted;
		}
		 
		private void printSQLException(SQLException ex) {
			for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " +  e.getMessage());
					Throwable t =ex.getCause();
					while (t !=null) {
						System.out.println("Cause:"+ t);
						t = t.getCause();
					}
				}
			}
		}




}
