package uk.co.andyfennell.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcAccess {

	private String database;
	
	public JdbcAccess(String database) {
		this.database = database;
	}

	public Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			throw new SQLException(e);
		}
		
		String url = "jdbc:mysql://localhost/" + database;
		return DriverManager.getConnection(url, "admin", "adminmysql");
	}
	
	public void execute(String sql) throws SQLException {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public String getFirstRowFirstColumn(String sql) throws SQLException {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.next();
			return resultSet.getString(1);
			
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		
	}
	
	public PreparedStatement prepare(String sql) throws SQLException {
		Connection connection = getConnection();
		return connection.prepareStatement(sql);
	}
}
