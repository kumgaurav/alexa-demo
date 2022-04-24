/**
 * 
 */
package com.amazon.ask.alexademo.lambda.custom.handlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amazon.ask.alexademo.lambda.custom.util.SnowFlakeUtil;

/**
 * @author gaurav
 *
 */
public class TestConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String org="EDWARDS CRITICAL CARE";
		String noOfUnits = "0";
    	String sql = "select sum(INV_NET_SALES_UNITS) AS number_of_units from \"EDW\".\"TAKEOFF\".\"S360_TRIM\" where YEAR = 2021 and EPAC_X7_DESC = '"+org+"'";
    	System.out.println("SQL -> "+sql);
    	try {
    		Connection connection = SnowFlakeUtil.getConnection();
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery(sql);
    		while(resultSet.next()) {
    			noOfUnits = resultSet.getBigDecimal(1).toPlainString();
    			System.out.println(resultSet.getBigDecimal(1));
    		}
    		resultSet.close();
    		statement.close();
    		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	System.out.println(noOfUnits);
	}

}
