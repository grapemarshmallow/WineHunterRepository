
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class WineHunterMain {
	public static Connection conn = null;
	
	public static boolean loggedIn = false;
	
	public static Map<String, Integer> credentials = new HashMap<String, Integer>();
	
	static Scanner in;
	   
	public static void main(String[] args) {
		Statement stmt = null;
		credentials.put("USERID",-1);
		credentials.put("ADMIN",0);
		credentials.put("SUPERADMIN",0);
		
		try{
		   
			in = new Scanner(System.in);  // Reading from System.in
		   //initialize connection
		   conn = ConnectLibrary.init();
	      
		   login();
		   
		   if (loggedIn == false) {
			   System.out.println("Not logged in.");
			   System.exit(1);
		   }
		
		   menu();
  
		   System.out.println("Hit enter to quit:");
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

	/**
	 * API for login
	 * @throws IOException 
	 */
	public static void login() throws SQLException, IOException {
		
		String[] yNChoiceArray = new String[3];
		yNChoiceArray[0] = "Quit^Q";
		yNChoiceArray[1] = "Yes^Y";
		yNChoiceArray[2] = "No^N";
	   
	   String prompt = "Existing user? ";
	   String help = "Select 1-Yes if you are an existing user. Select 2-No if not.";
	   
	   int result = Library.ChoiceDriver(in, yNChoiceArray, prompt, help);
	   
	   
	   if (result == 1) {
		   validateUser();
	   }
	   else if (result == 2) {
		   createUser(0, 0);
		   validateUser();

	   }
	   
	   
	}
	
	/**
	 * API for menu
	 * @throws IOException 
	 */
	public static void menu() throws SQLException, IOException {
		Scanner in = new Scanner(System.in);  // Reading from System.in
		String[] choiceArray = new String[4];
		choiceArray[0] = "Quit^Q";
		choiceArray[1] = "Country^C";
		choiceArray[2] = "Region^R";
		choiceArray[3] = "Province^P";
	   
	   String prompt = "What criteria do you want to search on? ";
	   String help = "Select the criteria you would like to use to search for wines.";
	   
	   int result = Library.ChoiceDriver(in, choiceArray, prompt, help);
	   
	   
	   if (result == 0) {
		   return;
	   }
	   else {
		   locationSearch(result);
		   
	   }
	   
	   
	}
	
	/**
	 * API for location searching
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void locationSearch(int locationType) throws SQLException, IOException {
		
		
		
		String searchType = null;
		String searchColumn = null;
		
		if (locationType == 1) {
			searchType = "Country";
			searchColumn = "wy.CountryName";
		}
		else if (locationType == 2) {
			searchType = "Region";
			searchColumn = "wy.RegionName";
		}
		else if (locationType == 3) {
			searchType = "Province";
			searchColumn = "wy.ProvinceName";
		}
		else {
			System.out.println("Invalid search type.");
			return;
		}
		
		System.out.println("Searching " + searchType.toLowerCase() + ".");
		
		
		
		
		Statement stmt = conn.createStatement();
		

		System.out.println("Search Text: ");
		String searchString = in.next();
		
		String sql;
		sql = "SELECT w.wineName, w.vintage, w.price, wy.wineryName, wy.countryName, wy.regionName, wy.provinceName" + 
				" FROM wine w INNER JOIN wineries wy ON w.wineryID=wy.wineryID" + 
				" WHERE " + searchColumn + " LIKE '" + searchString + "'";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		if (size <= 0) {
			System.out.println("No results.");
		}
		
		String[] columnNames = {"Wine Name", "Vintage", "Price", "Winery Name", "Country Name", "Province Name", "Region Name"};
		String[][] data = new String[size][7];
		int row = 0;
		
		while(rs.next()){
			
			
			String wineName = rs.getString("wineName");
			int vintage = rs.getInt("vintage");
			double price = rs.getDouble("price");
			String wineryName = rs.getString("wineryName");
			String countryName = rs.getString("countryName");
			String regionName = rs.getString("regionName");
			String provinceName = rs.getString("provinceName");
			
			data[row][0] = wineName;
			data[row][1] = Integer.toString(vintage);
			data[row][2] = Double.toString(price);
			data[row][3] = wineryName;
			data[row][4] = countryName;
			data[row][5] = provinceName;
			data[row][6] = regionName;
			
			
			

		}
		
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		Frame frame = new Frame("Location Results Frame");

		frame.setPreferredSize(new Dimension(450, 1000));
		frame.add(scrollPane, BorderLayout.CENTER);
		
		frame.setVisible(true);

		
		rs.close();

		
		
		stmt.close();
		
		
	}
	
	/**
	 * API for login
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void validateUser() throws SQLException, IOException {
		
		boolean done = false;

		Scanner in = new Scanner(System.in);  // Reading from System.in
		
		
		Statement stmt = conn.createStatement();
		
		while (!done) {
			
			
			System.out.println("Username: ");
			String username = in.next();
			System.out.println("Password: ");
			String password = in.next();
			
			String sql;
			sql = "SELECT *"
					+ "FROM User "
					+ "WHERE User.Username = '" + username
					+ "' AND User.Password = '" + password + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				String[] yNChoiceArray = new String[3];
				yNChoiceArray[0] = "QUIT";
				yNChoiceArray[1] = "Yes^Y";
				yNChoiceArray[2] = "No^N";
				
				String prompt = "Error logging in. Try again? ";
				String help = "Select 1-Yes to try logging in again. Select 2-No to quit.";
				
				int check = Library.ChoiceDriver(in, yNChoiceArray, prompt, help);
				
				if (check == 2) {
					done = true;
				}
			} else {
				loggedIn = true;
				
				credentials.replace("USERID", rs.getInt("UserID"));
				credentials.replace("ADMIN", rs.getInt("AdminUser"));
				credentials.replace("SUPERADMIN", rs.getInt("SuperAdminUser"));
				
				System.out.println("\nWelcome, " + rs.getString("UserFullName") + "!");
				
				
				done = true;
			} 
			
			rs.close();
		}
		
		
		stmt.close();
		
		
	}
	
	/**
	 * API to create a user
	 * @param admin int 1 if a user is an admin, 0 otherwise
	 * @param superAdmin int 1 if a user is a super admin, 0 otherwise
	 * @throws SQLException
	 */
	public static void createUser(int admin, int superAdmin) throws SQLException {
		
		boolean done = false;
		
		Scanner in = new Scanner(System.in);  // Reading from System.in
		
		String sql= "INSERT INTO User"
				+ "(UserFullName, Username, EmailAddress, Password, AdminUser, SuperAdminUser) VALUES"
				+ "(?,?,?,?," + admin + "," + superAdmin + ")";
		PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		while (!done) {
			System.out.println("Enter a username: ");
			String username = in.next();
			System.out.println("Enter a password: ");
			String password = in.next();
			System.out.println("Enter your email address: ");
			String email = in.next();
			System.out.println("Enter your name: ");
			String name = in.next();
			
			statement.setString(1, name);
			statement.setString(2, username);
			statement.setString(3, email);
			statement.setString(4, password);

			int result = 0;
			// execute insert SQL statement
			try{
				result = statement.executeUpdate();
			}
			catch(SQLIntegrityConstraintViolationException e){
				System.out.println("Duplicate user. Usernames and emails must be unique.");
			}

			if (result == 0) {
				String[] yNChoiceArray = new String[3];
				yNChoiceArray[0] = "QUIT";
				yNChoiceArray[1] = "Yes^Y";
				yNChoiceArray[2] = "No^N";
				
				String prompt = "Error creating user. Try again? ";
				String help = "Select 1-Yes to try creating a user again. Select 2-No to quit.";
				
				int check = Library.ChoiceDriver(in, yNChoiceArray, prompt, help);
				
				if (check == 2) {
					done = true;
				}
				
			}
			
			else {
				System.out.println("User created! Now, log in as your user.");
				
				done = true;
			} 
		}
		
		statement.close();
		
		
		
	}

}
