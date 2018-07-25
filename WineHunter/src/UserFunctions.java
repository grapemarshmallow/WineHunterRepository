import java.io.*;
import java.sql.*;


public class UserFunctions {

	/**
	 * API for login
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void validateUser() throws SQLException, IOException {
		
		boolean done = false;
		
		Statement stmt = WineHunterMain.conn.createStatement();
		
		while (!done) {
			
			
			System.out.println("Username: ");
			String username = WineHunterMain.in.next();
			System.out.println("Password: ");
			String password = WineHunterMain.in.next();
			
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
				
				int check = Library.ChoiceDriver(WineHunterMain.in, yNChoiceArray, prompt, help);
				
				if (check == 2) {
					done = true;
				}
			} else {
				WineHunterMain.loggedIn = true;
				
				WineHunterMain.credentials.replace("USERID", rs.getInt("UserID"));
				WineHunterMain.credentials.replace("ADMIN", rs.getInt("AdminUser"));
				WineHunterMain.credentials.replace("SUPERADMIN", rs.getInt("SuperAdminUser"));
				
				System.out.println("\nWelcome, " + rs.getString("UserFullName") + "!");
				
				
				done = true;
			} 
			
			rs.close();
		}
		
		
		stmt.close();
		
		
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return true if successfully validated
	 * @throws SQLException
	 * @throws IOException
	 */
	public static boolean validateUser(String username, String password) throws SQLException, IOException {
		
		boolean result = false;
		
		Statement stmt = WineHunterMain.conn.createStatement();
		
			
		String sql;
		sql = "SELECT *"
				+ "FROM User "
				+ "WHERE User.Username = '" + username
				+ "' AND User.Password = '" + password + "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (!rs.next()) {
			//
		}
		else {
			WineHunterMain.loggedIn = true;
			
			WineHunterMain.credentials.replace("USERID", rs.getInt("UserID"));
			WineHunterMain.credentials.replace("ADMIN", rs.getInt("AdminUser"));
			WineHunterMain.credentials.replace("SUPERADMIN", rs.getInt("SuperAdminUser"));
			
			result = true;
			
			
			
		} 
		
		rs.close();
		
		
		
		stmt.close();
		
		return result;
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
	   
	   int result = Library.ChoiceDriver(WineHunterMain.in, yNChoiceArray, prompt, help);
	   
	   
	   if (result == 1) {
		   validateUser();
	   }
	   else if (result == 2) {
		   createUser(0, 0);
		   validateUser();

	   }
	   
	   
	}
	
	/**
	 * API to create a user
	 * @param admin int 1 if a user is an admin, 0 otherwise
	 * @param superAdmin int 1 if a user is a super admin, 0 otherwise
	 * @throws SQLException
	 */
	public static void createUser(int admin, int superAdmin) throws SQLException {
		
		boolean done = false;
		
		
		String sql= "INSERT INTO User"
				+ "(UserFullName, Username, EmailAddress, Password, AdminUser, SuperAdminUser) VALUES"
				+ "(?,?,?,?," + admin + "," + superAdmin + ")";
		PreparedStatement statement = WineHunterMain.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		while (!done) {
			System.out.println("Enter a username: ");
			String username = WineHunterMain.in.next();
			System.out.println("Enter a password: ");
			String password = WineHunterMain.in.next();
			System.out.println("Enter your email address: ");
			String email = WineHunterMain.in.next();
			System.out.println("Enter your name: ");
			String name = WineHunterMain.in.next();
			
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
				
				int check = Library.ChoiceDriver(WineHunterMain.in, yNChoiceArray, prompt, help);
				
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
