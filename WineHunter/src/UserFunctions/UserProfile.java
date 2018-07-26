package UserFunctions;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Core.WineHunterApplication;

/**
 * This class contains methods to retrieve and update a user's profile.
 * @author orbiish-shalom
 *
 */
public class UserProfile {
	
	/**
	 * Gets a user's information, and feeds it into two arrays provided by caller.
	 * @param userId ID of user that will be searched for
	 * @param intInfo is an array of integer info for the user: intInfo[0] = userId, intInfo[1] = admin, intInfo[2] = super admin
	 * @param stringInfo is an array of string info for the user: stringInfo[0] = username, stringInfo[0] = full name
	 * @return 1 if a match was found, -1 for insufficient security, 0 otherwise
	 * @throws SQLException
	 * @throws IOException
	 */
	public int getUserInfo(int userId, int[] intInfo, String[] stringInfo) throws SQLException, IOException {
		
		int result = 0;
		
		if (userId != WineHunterApplication.userSession.getUser().getId()) {
			if ((WineHunterApplication.userSession.getUser().getAdmin() == 0) && ((WineHunterApplication.userSession.getUser().getSuperAdmin() == 0))) {
				return -1; // insufficient security
			}
		}

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM User'"
				+ " WHERE User.UserID = '" + userId + "'";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		if (size == 1) {
			rs.next();
			intInfo[0] = rs.getInt("UserID");;
			intInfo[1] = rs.getInt("AdminUser");
			intInfo[2] = rs.getInt("SuperAdminUser");
			stringInfo[0] = rs.getString("Username");
			stringInfo[1] = rs.getString("UserFullName");
		}
		
		rs.close();
		
		stmt.close();
		
		return result;
	}
	
	/**
	 * Gets a user's information, and feeds it into two arrays provided by caller.
	 * @param userId ID of user that will be searched for
	 * @param intInfo is an array of integer info for the user: intInfo[0] = userId, intInfo[1] = admin, intInfo[2] = super admin
	 * @param stringInfo is an array of string info for the user: stringInfo[0] = username, stringInfo[0] = full name
	 * @return 1 if a match was found, -1 for insufficient security, 0 otherwise
	 * @throws SQLException
	 * @throws IOException
	 */
	public int getTasterProfile(int userId, int[] intInfo, String[] stringInfo) throws SQLException, IOException {
		
		int result = 0;
		
		
		if (userId != WineHunterApplication.userSession.getUser().getId()) {
			if ((WineHunterApplication.userSession.getUser().getAdmin() == 0) && ((WineHunterApplication.userSession.getUser().getSuperAdmin() == 0))) {
				return -1; // insufficient security
			}
		}

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM User'"
				+ " WHERE User.UserID = '" + userId + "'";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		if (size == 1) {
			rs.next();
			intInfo[0] = rs.getInt("UserID");;
			intInfo[1] = rs.getInt("AdminUser");
			intInfo[2] = rs.getInt("SuperAdminUser");
			stringInfo[0] = rs.getString("Username");
			stringInfo[1] = rs.getString("UserFullName");
		}
		
		rs.close();
		
		stmt.close();
		
		return result;
	}
}
