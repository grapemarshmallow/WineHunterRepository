/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             Connect.java
 * Semester:         Summer 2018
 *
 *
 * Author:           Orbi Ish-Shalom (oishshalom@wisc.edu)
 * CS Login:         orbi
 * Lecturer's Name:  Hien Hguyen
 *
 *                    PAIR PROGRAMMERS COMPLETE THIS SECTION
 *  Pair Partner:     Jennifer Shih
 * //////////////////////////// 80 columns wide //////////////////////////////////
 *******************************************************************************/

package Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This class manages the connection to the database for the application.
 *
 */
public class Connect {
	
	//JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://winehunterprd.cmnipsjfltdi.us-east-2.rds.amazonaws.com:3306/wines?autoReconnect=true&useLegacyDatetimeCode=false&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC";
	//
	 //Database credentials
	static final String USER = "ornifer";
	static final String PASS = "xusIAaXBe84l";
	
	
	private Connection con;
	
	/**
	 * get our connection
	 * @return the connection object
	 */
	public Connection getConnection() {
		return this.con;
	}
	
	/**
	 * set the connection
	 * @param con the connection object to set this connection to
	 */
	public void setConnnection(Connection con) {
		this.con = con;
	}
	
	/**
	 * initiate connection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void init () throws SQLException, ClassNotFoundException {
		
		
		Class.forName(JDBC_DRIVER);
		con = DriverManager.getConnection(DB_URL, USER, PASS);
		
		System.out.println("Connected to Database.");
		
	}
	
	/**
	 * close connection
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if (!con.isClosed()) {
			con.close();
		}
	}
	
	
}
