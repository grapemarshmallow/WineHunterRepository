package UserFunctions;


import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import Core.WineHunterApplication;



public class UserSession {


	private boolean loggedIn = false;
	
	private Map<String, Integer> credentials = new HashMap<String, Integer>();
	private Map<String, String> userInfo = new HashMap<String, String>();
	
	
	/**
	 * @param loggedIn
	 * @param credentials
	 */
	public UserSession() {
		loggedIn = false;
		credentials.put("USERID",-1);
		credentials.put("ADMIN",0);
		credentials.put("SUPERADMIN",0);
		userInfo.put("USERNAME", null);
		userInfo.put("NAME", null);
	}

	
	/**
	 * Method to log in a user.
	 * @param username
	 * @param password
	 * @return true if successfully validated
	 * @throws SQLException
	 * @throws IOException
	 */
	public boolean validateUser(String username, String password) throws SQLException, IOException {
		
		boolean result = false;
		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
			
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
			loggedIn = true;
			
			credentials.replace("USERID", rs.getInt("UserID"));
			credentials.replace("ADMIN", rs.getInt("AdminUser"));
			credentials.replace("SUPERADMIN", rs.getInt("SuperAdminUser"));
			userInfo.replace("USERNAME", rs.getString("Username"));
			userInfo.replace("NAME", rs.getString("UserFullName"));
			
			result = true;
			
			
			
		} 
		
		rs.close();
		
		
		
		stmt.close();
		
		return result;
	}
	
	
	
	/**
	 * API to create a user
	 * 
	 * @param name user full name
	 * @param email user email address
	 * @param password user password
	 * @param username user username to login with
	 * @param admin int 1 if a user is an admin, 0 otherwise
	 * @param superAdmin int 1 if a user is a super admin, 0 otherwise
	 * 
	 * @return 0 if user not successfully created, -1 if duplicate issue, 1 otherwise
	 * @throws SQLException
	 */
	public int createUser(String name, String email, String password, String username, int admin, int superAdmin) throws SQLException {
		
		
		int result = 0;
		
		String sql= "INSERT INTO User"
				+ "(UserFullName, Username, EmailAddress, Password, AdminUser, SuperAdminUser) VALUES"
				+ "(?,?,?,?," + admin + "," + superAdmin + ")";
		PreparedStatement statement = WineHunterApplication.connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		
			
		statement.setString(1, name);
		statement.setString(2, username);
		statement.setString(3, email);
		statement.setString(4, password);

		
		// execute insert SQL statement
		try{
			result = statement.executeUpdate();
		}
		catch(SQLIntegrityConstraintViolationException e){
			result = -1;
		}

		if (result > 0) {
			
			result = 1;
		}
		
		statement.close();
		
		return result;
		
	}
	
	public void logOut() {
		loggedIn = false;
		credentials.put("USERID",-1);
		credentials.put("ADMIN",0);
		credentials.put("SUPERADMIN",0);
		userInfo.put("USERNAME", null);
		userInfo.put("NAME", null);
	}
	

	public Map<String, String> getUserInfo() {
		return this.userInfo;
	}


	public void setUserInfo(Map<String, String> userInfo) {
		this.userInfo = userInfo;
	}


	public boolean isLoggedIn() {
		return this.loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Map<String, Integer> getCredentials() {
		return this.credentials;
	}

	public void setCredentials(Map<String, Integer> credentials) {
		this.credentials = credentials;
	}
}
