package uk.co.andyfennell.database;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import uk.co.andyfennell.database.JdbcAccess;

public class DataConnectionTest {

	@Test
	public void testConnection() throws SQLException {
		JdbcAccess access = new JdbcAccess("test");
		
		Connection connection = null;
		try {
			connection = access.getConnection();
			assertFalse(connection.isClosed());
		} finally {
			connection.close();
		}
		
	}
	
	@Test
	public void testExecute() throws SQLException {
		JdbcAccess access = new JdbcAccess("test");

		try {
			access.execute("drop table testTable");
		} catch (SQLException e) {
		}
		
		access.execute("create table testTable (fieldA integer)");

		try {
			String field = access.getFirstRowFirstColumn("desc testTable");
			assertEquals("fieldA", field);
		} finally {
			access.execute("drop table testTable");
		}
	}
	
	@Test
	public void testPrepared() throws SQLException {
		JdbcAccess access = new JdbcAccess("test");

		try {
			access.execute("drop table testPrepared");
		} catch (SQLException e) {
			
		}
		access.execute("create table testPrepared (id integer, name varchar(100))");
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			access.execute("insert into testPrepared (id, name) values (1, 'Fred')"); 
			access.execute("insert into testPrepared (id, name) values (2, 'Jane')");
			
			statement = access.prepare("select id, name from testPrepared where id = ?");
			assertTrue(statement != null);

			// note JDBC is index based 1
			statement.setString(1, "1");
			resultSet = statement.executeQuery();
			resultSet.next();
			assertEquals("1", resultSet.getString(1));
			assertEquals("Fred", resultSet.getString(2));
			
			statement.setString(1, "2");
			resultSet = statement.executeQuery();
			resultSet.next();
			assertEquals("2", resultSet.getString(1));
			assertEquals("Jane", resultSet.getString(2));
			
		} finally {
			statement.close();
			access.execute("drop table testPrepared");
		}
	}
}
