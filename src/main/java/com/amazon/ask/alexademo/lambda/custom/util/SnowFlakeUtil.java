/**
 * 
 */
package com.amazon.ask.alexademo.lambda.custom.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author gaurav
 *
 */
public class SnowFlakeUtil {
	public static Connection getConnection() throws SQLException {
		// build connection properties
	    Properties properties = new Properties();
	    properties.put("user", "ameet_ubhayaker");     // replace "" with your username
	    properties.put("password", "6DEuu1e0"); // replace "" with your password
	    properties.put("account", "edwards-edwardsdev");  // replace "" with your account name
	    properties.put("db", "EDW");       // replace "" with target database name
	    properties.put("schema", "TAKEOFF");   // replace "" with target schema name
	    //properties.put("tracing", "on");
	    properties.put("jdbc_query_result_format", "json");
		String  connectStr = "jdbc:snowflake://edwards-edwardsdev.snowflakecomputing.com"; // replace accountName with your account name
		DriverManager.registerDriver(new net.snowflake.client.jdbc.SnowflakeDriver());
		Connection connection = DriverManager.getConnection(connectStr, properties);
		//System.out.println("Create JDBC statement");
	    //Statement statement = connection.createStatement();
	    //System.out.println("Done creating JDBC statementn");
	    // create a table
	    //System.out.println("Create demo table");
	    //statement.executeUpdate("create or replace table demo(C1 STRING)");
	    //statement.close();
	    //System.out.println("Done creating demo table");
		return connection;
	}
}
