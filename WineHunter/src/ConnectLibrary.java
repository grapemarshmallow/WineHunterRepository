import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectLibrary {

	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://winehunterprd.cmnipsjfltdi.us-east-2.rds.amazonaws.com:3306/wines";

	   //  Database credentials
	   static final String USER = "ornifer";
	   static final String PASS = "xusIAaXBe84l";
	   
	   
	   public static Connection init() throws SQLException, ClassNotFoundException {
		   
		   Class.forName(JDBC_DRIVER);
		   Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
		   
		   System.out.println("Connected to Database.");
		   return con;
	   }

	   public static void close(Connection connection) throws SQLException {
		   if (!connection.isClosed()) {
			   connection.close();
		   }
	   }
	   

}
