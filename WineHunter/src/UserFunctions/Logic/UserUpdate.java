package UserFunctions.Logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Core.WineHunterApplication;

/**
 * This class contains functions for users to update attributes about themselves and other users
 * @author orbiish-shalom
 *
 */
public class UserUpdate {
	
	/**
	 * Method to change a user's name or password
	 * @param userId ID of user that will be changed
	 * @param changeType set to 1 for a password change and 2 for a name change
	 * @param newField String to set the field to
	 * @return 1 if successfully changed, 0 if not, -1 for insufficient security, -2 for invalid change type
	 * @throws SQLException
	 */
	public int setUserInfoString(int userId, int changeType, String newField) throws SQLException {
		
		int result = 0;
		
		if (userId != WineHunterApplication.userSession.getUser().getId()) {
			if ((WineHunterApplication.userSession.getUser().getAdmin() == 0) && ((WineHunterApplication.userSession.getUser().getSuperAdmin() == 0))) {
				return -1; // insufficient security
			}
		}
		
		String column = "User.";
		
		if (changeType == 1) {
			column = column + "Password";
		}
		else if (changeType == 2) {
			column = column + "UserFullName";
		}
		else {
			return -2; //invalid input
		}
		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "UPDATE User"
				+ " SET " + column + " = '" + newField + "'"
				+ " WHERE User.UserID = '" + userId + "'";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		if (rs.next()) { // success!
			result = 1;
		}

		
		rs.close();
		
		stmt.close();
		
		return result;
	}
	
	
	/**
	 * Method to change admin security, switches security from current value to opposite
	 * @param userId ID of user that will be changed
	 * @return 1 if successfully changed, 0 if not, -1 for insufficient security 
	 * @throws SQLException
	 */
	public int setAdminSecurity(int userId) throws SQLException {
		
		int result = 0;
		

		if (WineHunterApplication.userSession.getUser().getSuperAdmin() == 0) {
			return -1; // insufficient security
		}

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
			
		String sql;
		
		sql = "Select AdminUser"
				+ " From User"
				+ " WHERE User.UserID = '" + userId + "'";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		if (!rs.next()) {
			rs.close();
			return 0;
		}
		
		int grant = rs.getInt("AdminUser");
				
		rs.close();
		
		sql = "UPDATE User"
				+ " SET User.AdminUser = '" + grant + "'"
				+ " WHERE User.UserID = '" + userId + "'";
		
		rs = stmt.executeQuery(sql);
		
		if (rs.next()) { // success!
			result = 1;
		}

		
		rs.close();
		
		stmt.close();
		
		return result;
	}
	
	/**
	 * Method to delete users
	 * @param userId ID of user that will be changed
	 * @return 1 if successfully deleted, 0 if not, -1 for insufficient security 
	 * @throws SQLException
	 */
	public int deleteUser(int userId) throws SQLException {
		
		int result = 0;
		

		if (WineHunterApplication.userSession.getUser().getSuperAdmin() == 0) {
			return -1; // insufficient security
		}

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
			
		String sql;
		sql = "DELETE FROM User"
				+ " WHERE User.UserID = '" + userId + "'";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		if (rs.next()) { // success!
			result = 1;
		}

		
		rs.close();
		
		stmt.close();
		
		return result;
	}
	
	
}