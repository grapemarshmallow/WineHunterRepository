package UserFunctions.Logic;


import java.io.*;
import java.sql.*;
import Core.WineHunterApplication;
import WineObjects.User;



public class UserSession {


	private boolean loggedIn = false;
	private User user;
	
	
	/**
	 * @param loggedIn
	 * @param credentials
	 */
	public UserSession() {
		loggedIn = false;
		user = new User();
	}

	
	public User getUser() {
		return this.user;
	}


	public void setUser(User user) {
		this.user = user;
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
			
			user.setAdmin(rs.getInt("AdminUser"));
			user.setFullName(rs.getString("UserFullName"));
			user.setId(rs.getInt("UserID"));
			user.setUsername(rs.getString("Username"));
			user.setSuperAdmin(rs.getInt("SuperAdminUser"));
			user.setEmail(rs.getString("EmailAddress"));
			
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
		user.setAdmin(0);
		user.setFullName(null);
		user.setId(-1);
		user.setUsername(null);
		user.setSuperAdmin(0);

	}
	

	public boolean isLoggedIn() {
		return this.loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}


}
