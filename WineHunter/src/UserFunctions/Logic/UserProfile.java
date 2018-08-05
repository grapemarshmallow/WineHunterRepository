/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             UserProfile.java
 * Semester:         Summer 2018
 *
 *
 * Author:           Orbi Ish-Shalom (oishshalom@wisc.edu)
 * CS Login:         orbi
 * Lecturer's Name:  Hien Hguyen
 *
 *                    PAIR PROGRAMMERS COMPLETE THIS SECTION
 *  Pair Partner:     Jennifer Shih
 * //////////////////////////// 80 columns wide //////////////////////////////////
 *******************************************************************************/

package UserFunctions.Logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Core.*;
import WineObjects.*;


/**
 * This class contains methods to retrieve and update a user's profile.
 * @author orbiish-shalom
 *
 */
public class UserProfile {
	
	/**
	 * do nothing constructor
	 */
	public UserProfile() {
		// do nothing constructor
	}
	
	/**
	 * Find a user by user ID
	 * @param userId ID of user that will be searched for
	 * @param user the user object returned by the query
	 * @return 1 if a match was found, -1 for insufficient security, 0 otherwise
	 * @throws SQLException
	 */
	public int getUserInfo(int userId, User user) throws SQLException {
		
		int result = 0;
		
		if (userId != WineHunterApplication.userSession.getUser().getId()) {
			if ((WineHunterApplication.userSession.getUser().getAdmin() == 0) && ((WineHunterApplication.userSession.getUser().getSuperAdmin() == 0))) {
				return -1; // insufficient security
			}
		}

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM User"
				+ " WHERE User.UserID = " + userId;
		
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		if (size == 1) {
			rs.next();
			user.setAdmin(rs.getInt("AdminUser"));
			user.setFullName(rs.getString("UserFullName"));
			user.setId(rs.getInt("UserID"));
			user.setUsername(rs.getString("Username"));
			user.setSuperAdmin(rs.getInt("SuperAdminUser"));
			user.setEmail(rs.getString("EmailAddress"));
			
			result = 1;
		}
		
		rs.close();
		
		stmt.close();
		
		return result;
	}
	
	/**
	 * Finds a user by username.
	 * @param userId ID of user that will be searched for
	 * @param user the user object returned by the query
	 * @return 1 if a match was found, -1 for insufficient security, 0 otherwise
	 * @throws SQLException
	 */
	public int getUserInfoByUsername (String username, User user) throws SQLException {
		
		int result = 0;
		
		if ((WineHunterApplication.userSession.getUser().getAdmin() == 0) && ((WineHunterApplication.userSession.getUser().getSuperAdmin() == 0))) {
			return -1; // insufficient security
		}

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM User"
				+ " WHERE User.Username = '" + username + "'";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		if (size == 1) {
			rs.next();
			user.setAdmin(rs.getInt("AdminUser"));
			user.setFullName(rs.getString("UserFullName"));
			user.setId(rs.getInt("UserID"));
			user.setUsername(rs.getString("Username"));
			user.setSuperAdmin(rs.getInt("SuperAdminUser"));
			user.setEmail(rs.getString("EmailAddress"));
			result = 1;
		}
		
		rs.close();
		
		stmt.close();
		
		return result;
	}
	
	/**
	 * Finds a user by email
	 * @param userId ID of user that will be searched for
	 * @param user the user object returned by the query
	 * @return 1 if a match was found, -1 for insufficient security, 0 otherwise
	 * @throws SQLException
	 */
	public int getUserInfoByEmail(String email, User user) throws SQLException {
		
		int result = 0;
		
		if ((WineHunterApplication.userSession.getUser().getAdmin() == 0) && ((WineHunterApplication.userSession.getUser().getSuperAdmin() == 0))) {
			return -1; // insufficient security
		}

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM User"
				+ " WHERE User.EmailAddress = '" + email + "'";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		if (size == 1) {
			rs.next();
			user.setAdmin(rs.getInt("AdminUser"));
			user.setFullName(rs.getString("UserFullName"));
			user.setId(rs.getInt("UserID"));
			user.setUsername(rs.getString("Username"));
			user.setSuperAdmin(rs.getInt("SuperAdminUser"));
			user.setEmail(rs.getString("EmailAddress"));
			result = 1;
		}
		
		rs.close();
		
		stmt.close();
		
		return result;
	}
	
	
	/**
	 * Gets a user's information, and feeds it into a user object
	 * @param user object to get info for
	 * @return -1 for insufficient security
	 * @throws SQLException
	 */
	public int getTasterProfile(User user) throws SQLException {
		
		int result = 0;
		
		
		if (user.getId() != WineHunterApplication.userSession.getUser().getId()) {
			if ((WineHunterApplication.userSession.getUser().getAdmin() == 0) && ((WineHunterApplication.userSession.getUser().getSuperAdmin() == 0))) {
				return -1; // insufficient security
			}
		}

		user.cleanLists(); // refresh the user lists for filling
		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();

		String sql;
		sql = "SELECT *"
				+ " FROM KeywordLike kl"
				+ " INNER JOIN Keyword k"
				+ " ON kl.LikeKeywordID = k.KeywordID"
				+ " WHERE kl.KeywordLikeUserID = " + user.getId()
				+ " ORDER BY kl.Rank";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		
		while(rs.next()) {
			rs.toString();
			if (rs.getInt("KeywordLikeUser") == 1) {
				user.getUserLikeKeywordList().add(new Keyword(rs.getInt("KeywordID"), rs.getString("Word")));
			}
			else {
				user.getSysLikeKeywordList().add(new Keyword(rs.getInt("KeywordID"), rs.getString("Word")));
			}
		}
		
		rs.close();
		
		sql = "SELECT *"
				+ " FROM KeywordDislike kd"
				+ " INNER JOIN Keyword k"
				+ " ON kd.DislikeKeywordID = k.KeywordID"
				+ " WHERE kd.DislikeKeywordUserID = " + user.getId()
				+ " ORDER BY kd.Rank";
	
		
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			rs.toString();
			if (rs.getInt("KeywordDislikeUser") == 1) {
				user.getUserDislikeKeywordList().add(new Keyword(rs.getInt("KeywordID"), rs.getString("Word")));
			}
			else {
				user.getSysDislikeKeywordList().add(new Keyword(rs.getInt("KeywordID"), rs.getString("Word")));
			}
		}


		rs.close();
		
		sql = "SELECT *"
				+ " FROM VarietyDislike vd"
				+ " INNER JOIN varieties v"
				+ " ON vd.DislikeVarietyID = v.VarietyID"
				+ " WHERE vd.DislikeUserID = " + user.getId()
				+ " ORDER BY vd.Rank";
		
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			rs.toString();
			if (rs.getInt("VarietyDislikeUser") == 1) {
				user.getUserDislikeVarietyList().add(new Variety(rs.getInt("VarietyID"), rs.getString("VarietyName")));
			}
			else {
				user.getSysDislikeVarietyList().add(new Variety(rs.getInt("VarietyID"), rs.getString("VarietyName")));
			}
		}


		rs.close();
		
		sql = "SELECT *"
				+ " FROM VarietyLike vl"
				+ " INNER JOIN varieties v"
				+ " ON vl.LikeVariety = v.VarietyID"
				+ " WHERE vl.LikeUserID = " + user.getId()
				+ " ORDER BY vl.Rank";
		
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			rs.toString();
			if (rs.getInt("VarietyLikeUser") == 1) {
				user.getUserLikeVarietyList().add(new Variety(rs.getInt("VarietyID"), rs.getString("VarietyName")));
			}
			else {
				user.getSysLikeVarietyList().add(new Variety(rs.getInt("VarietyID"), rs.getString("VarietyName")));
			}
		}


		rs.close();
		
		stmt.close();
		
		return result;
	}
	
	/**
	 * Sets a taster profile based on a user object
	 * @param user object to get info for
	 * @return -1 for insufficient security, 1 is we get to the end without issues
	 * @throws SQLException
	 */
	public int setTasterProfile(User user) throws SQLException {
		
		int result = 0;
		
		
		if (user.getId() != WineHunterApplication.userSession.getUser().getId()) {
			if ((WineHunterApplication.userSession.getUser().getAdmin() == 0) && ((WineHunterApplication.userSession.getUser().getSuperAdmin() == 0))) {
				return -1; // insufficient security
			}
		}


		String sql;
		
		//purge keywords and varieties
		
		sql = "DELETE FROM KeywordLike"
				+ " WHERE KeywordLikeUserID = " + user.getId()
				+ " AND KeywordLikeUser = 1";
	
		PreparedStatement stmt = WineHunterApplication.connection.getConnection().prepareStatement(sql);
		stmt.executeUpdate();
		
		stmt.close();
		
		sql = "DELETE FROM KeywordDislike"
				+ " WHERE DislikeKeywordUserID = " + user.getId()
				+ " AND KeywordDislikeUser = 1";
	
	
		stmt = WineHunterApplication.connection.getConnection().prepareStatement(sql);
		stmt.executeUpdate();
		
		stmt.close();
		
		sql = "DELETE FROM VarietyLike"
				+ " WHERE LikeUserID = " + user.getId()
				+ " AND VarietyLikeUser = 1";
	
	
		stmt = WineHunterApplication.connection.getConnection().prepareStatement(sql);
		stmt.executeUpdate();
		
		stmt.close();
		
		
		sql = "DELETE FROM VarietyDislike"
				+ " WHERE DislikeUserID = " + user.getId()
				+ " AND VarietyDislikeUser = 1";
	
	
		stmt = WineHunterApplication.connection.getConnection().prepareStatement(sql);
		stmt.executeUpdate();
		
		stmt.close();
		
		
		// Update like keywords
	
		
		int lastUpdate = user.getUserLikeKeywordList().size();
		
		for (int i = 1; i <= lastUpdate; ++i) {
			
			sql = "INSERT INTO KeywordLike(KeywordLikeUserID, LikeKeywordID, Rank, KeywordLikeUser) values"
					+ "(" + user.getId() + "," + user.getUserLikeKeywordList().get(i - 1).getId() + "," + i + ", 1)";
			
			stmt = WineHunterApplication.connection.getConnection().prepareStatement(sql);
			stmt.executeUpdate();
			
			
			stmt.close();
		}
		
		
		//update dislike keywords
		lastUpdate = user.getUserDislikeKeywordList().size();
		
		for (int i = 1; i <= lastUpdate; ++i) {
			
			sql = "INSERT INTO KeywordDislike(DislikeKeywordUserID, DislikeKeywordID, Rank, KeywordDislikeUser) values"
					+ "(" + user.getId() + "," + user.getUserDislikeKeywordList().get(i - 1).getId() + "," + i + ", 1)";
			
			stmt = WineHunterApplication.connection.getConnection().prepareStatement(sql);
			stmt.executeUpdate();
			
			
			stmt.close();
		}
		
		
		// Update like varieties
		
		lastUpdate = user.getUserLikeVarietyList().size();
		
		for (int i = 1; i <= lastUpdate; ++i) {
			
			sql = "INSERT INTO VarietyLike(LikeUserID, LikeVariety, Rank, VarietyLikeUser) values"
					+ "(" + user.getId() + "," + user.getUserLikeVarietyList().get(i - 1).getId() + "," + i + ", 1)";
			
			stmt = WineHunterApplication.connection.getConnection().prepareStatement(sql);
			stmt.executeUpdate();
			
			
			stmt.close();
		}

		
		// Update dislike varieties
		
		lastUpdate = user.getUserDislikeVarietyList().size();
		
		for (int i = 1; i <= lastUpdate; ++i) {
			
			sql = "INSERT INTO VarietyDislike(DislikeUserID, DislikeVarietyID, Rank, VarietyDislikeUser) values"
					+ "(" + user.getId() + "," + user.getUserDislikeVarietyList().get(i - 1).getId() + "," + i + ", 1)";
			
			stmt = WineHunterApplication.connection.getConnection().prepareStatement(sql);
			stmt.executeUpdate();
			
			
			stmt.close();
		}
		
		
		result = 1;
		
		return result;
	}
}
