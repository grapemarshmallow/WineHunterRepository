package Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://winehunterprd.cmnipsjfltdi.us-east-2.rds.amazonaws.com:3306/wines";

	//  Database credentials
	static final String USER = "ornifer";
	static final String PASS = "xusIAaXBe84l";
	   
	private Connection con;
	   
	   
	public Connection getConnection() {
		return this.con;
	}

	public void setConnnection(Connection con) {
		this.con = con;
	}

	public void init () throws SQLException, ClassNotFoundException {
		   
		   Class.forName(JDBC_DRIVER);
		   con = DriverManager.getConnection(DB_URL, USER, PASS);
		   
		   System.out.println("Connected to Database.");

	}

	   public void close() throws SQLException {
		   if (!con.isClosed()) {
			   con.close();
		   }
	   }
	   

}
