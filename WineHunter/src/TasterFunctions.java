import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TasterFunctions {
	
	public static void main(String[] args) {
			Connection conn = null;
		    Statement stmt = null;
		   try{
			  
			  conn = ConnectLibrary.init();
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM taster";
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("TasterID");
		         String tasterTwitter = rs.getString("TasterTwitter");
		         String tasterName = rs.getString("TasterName");

		         //Display values
		         System.out.println("ID: " + id);
		        
		         System.out.println("Taster Twitter: " + tasterTwitter);
		         System.out.println("Taster Name: " + tasterName);
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         ConnectLibrary.close(conn);
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");

	}
}
