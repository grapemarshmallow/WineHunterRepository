
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class WineHunterMain {
	public static Connection conn = null;
	
	public static boolean loggedIn = false;
	
	public static Map<String, Integer> credentials = new HashMap<String, Integer>();
	
	public static Scanner in;
	
	public static JFrame frame;
	
	public static Gui myGui; 
	
	
	   
	public static void main(String[] args) {
		Statement stmt = null;
		credentials.put("USERID",-1);
		credentials.put("ADMIN",0);
		credentials.put("SUPERADMIN",0);
		
		
		try{
		   
			in = new Scanner(System.in);  // Reading from System.in
			
			//initialize connection
			conn = ConnectLibrary.init();
			
			frame = new JFrame("Wine Hunter");
			myGui = new Gui(frame);
			
			myGui.createAndShowGUI();
			
		   
		
	      
		   UserFunctions.login();
		   
		   
		   if (loggedIn == false) {
			   System.out.println("Not logged in.");
			   System.exit(1);
		   }
		   
		
		   LocationFunctions.locationMenu();
  
		   System.out.println("Hit enter to quit.");
		   System.in.read();
		   
		   
		   
		   ConnectLibrary.close(conn);
		}
		
		catch(SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		}
		
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		

		
		finally{
			//finally block used to close resources
			
			try {
				System.in.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			try{
				if(stmt!=null)
				stmt.close();
			}
			
			catch(SQLException se2){
				//nothing
			}
			
			try{
				ConnectLibrary.close(conn);
			}
			
			catch(SQLException se){
				se.printStackTrace();
			}
			
		}
		
		System.out.println("Connection Closed");

	}

	

	

}
