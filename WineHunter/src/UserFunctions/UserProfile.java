package UserFunctions;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Core.WineHunterApplication;
import WineObjects.Keyword;
import WineObjects.User;
import WineObjects.Variety;

/**
 * This class contains methods to retrieve and update a user's profile.
 * @author orbiish-shalom
 *
 */
public class UserProfile {
	
	/**
	 * Gets a user's information, and feeds it into two arrays provided by caller.
	 * @param userId ID of user that will be searched for
	 * @param user the user object returned by the query
	 * @return 1 if a match was found, -1 for insufficient security, 0 otherwise
	 * @throws SQLException
	 * @throws IOException
	 */
	public int getUserInfo(int userId, User user) throws SQLException, IOException {
		
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
			user.setAdmin(rs.getInt("AdminUser"));
			user.setFullName(rs.getString("UserFullName"));
			user.setId(rs.getInt("UserID"));
			user.setUsername(rs.getString("Username"));
			user.setSuperAdmin(rs.getInt("SuperAdminUser"));
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
	 * @throws IOException
	 */
	public int getTasterProfile(User user) throws SQLException, IOException {
		
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
				+ " WHERE KeywordLike.KeywordLikeUserID = '" + user.getId() + "'"
				+ " ORDER BY kl.Rank";
		
		
		ResultSet rs = stmt.executeQuery(sql);
		
		
		while(rs.next()) {
			if (rs.getInt("KeywordLikeUser") == 1) {
				user.getUserLikeKeywordList().add(new Keyword(rs.getInt("KeywordID"), rs.getString("Word")));
			}
			else {
				user.getSysLikeKeywordList().add(new Keyword(rs.getInt("KeywordID"), rs.getString("Word")));
			}
		}
		
		sql = "SELECT *"
				+ " FROM KeywordDislike kd"
				+ " INNER JOIN Keyword k"
				+ " ON kd.DislikeKeywordID = k.KeywordID"
				+ " WHERE kd.DislikeKeywordUserID = '" + user.getId() + "'"
				+ " ORDER BY kd.Rank";
		
		
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
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
				+ " WHERE vd.DislikeUserID = '" + user.getId() + "'"
				+ " ORDER BY vd.Rank";
		
		
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
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
				+ " WHERE vl.LikeUserID = '" + user.getId() + "'"
				+ " ORDER BY vl.Rank";
		
		
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
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
	 * @return -1 for insufficient security
	 * @throws SQLException
	 * @throws IOException
	 */
	public int setTasterProfile(User user) throws SQLException, IOException {
		
		int result = 0;
		
		
		if (user.getId() != WineHunterApplication.userSession.getUser().getId()) {
			if ((WineHunterApplication.userSession.getUser().getAdmin() == 0) && ((WineHunterApplication.userSession.getUser().getSuperAdmin() == 0))) {
				return -1; // insufficient security
			}
		}


		String sql;
		
		// Update like keywords
		
		int lastUpdate = user.getUserLikeKeywordList().size();
		
		for (int i = 1; i < 6; ++i) {
			if (i <= lastUpdate) {
				sql = "IF EXISTS"
						+ "(SELECT * "
						+ "FROM KeywordLike "
						+ " WHERE KeywordLike.KeywordLikeUserID = '" + user.getId() + "'"
						+ " AND KeywordLike.Rank = '" + i + "'"
						+ " AND KeywordLikeUser = 1)"
						+ "   UPDATE KeywordLike set LikeKeywordID = '" + user.getUserLikeKeywordList().get(i - 1) + "'"
						+ " WHERE KeywordLike.KeywordLikeUserID = '" + user.getId() + "'" 
						+ " AND KeywordLike.Rank = '" + i + "'" 
						+ " AND KeywordLikeUser = 1)"
						+ "ELSE"
						+ "   INSERT INTO KeywordLike(KeywordLikeUserID, LikeKeywordID, Rank, KeywordLikeUser) values"
						+ "(" + user.getId() + "," + user.getUserLikeKeywordList().get(i - 1) + "," + i + ",)";
			}
			else {
				sql = "DELETE FROM KeywordLike"
						+ " WHERE KeywordLike.KeywordLikeUserID = '" + user.getId() + "'"
						+ " AND KeywordLike.Rank = '" + i + "'"
						+ " AND KeywordLikeUser = 1)";
			}
			
			PreparedStatement stmt = WineHunterApplication.connection.getConnection().prepareStatement(sql);
			stmt.executeUpdate();
			stmt.executeUpdate();
			stmt.close();
		}
		
		
		//update dislike keywords
		lastUpdate = user.getUserDislikeKeywordList().size();
		
		for (int i = 1; i < 6; ++i) {
			if (i <= lastUpdate) {
				sql = "IF EXISTS"
						+ "(SELECT * "
						+ "FROM KeywordDislike "
						+ " WHERE KeywordDislike.KeywordDislikeUserID = '" + user.getId() + "'"
						+ " AND KeywordDislike.Rank = '" + i + "'"
						+ " AND KeywordDislikeUser = 1)"
						+ "   UPDATE KeywordDislike set DislikeKeywordID = '" + user.getUserDislikeKeywordList().get(i - 1) + "'"
						+ " WHERE KeywordDislike.DisikeKeywordUserID = '" + user.getId() + "'" 
						+ " AND KeywordDislike.Rank = '" + i + "'" 
						+ " AND KeywordDislikeUser = 1)"
						+ "ELSE"
						+ "   INSERT INTO KeywordDislike(DisikeKeywordUserID, DislikeKeywordID, Rank, KeywordDislikeUser) values"
						+ "(" + user.getId() + "," + user.getUserDislikeKeywordList().get(i - 1) + "," + i + ",)";
			}
			else {
				sql = "DELETE FROM KeywordDislike"
						+ " WHERE KeywordDislike.DisikeKeywordUserID = '" + user.getId() + "'"
						+ " AND KeywordDislike.Rank = '" + i + "'"
						+ " AND KeywordDislikeUser = 1)";
			}
			
			PreparedStatement stmt = WineHunterApplication.connection.getConnection().prepareStatement(sql);
			stmt.executeUpdate();
			stmt.close();
			
		}
		
		// Update like varieties
		
		lastUpdate = user.getUserLikeVarietyList().size();
		
		for (int i = 1; i < 6; ++i) {
			if (i <= lastUpdate) {
				sql = "IF EXISTS"
						+ "(SELECT * "
						+ "FROM varieties "
						+ " WHERE varieties.LikeUserID = '" + user.getId() + "'"
						+ " AND varieties.Rank = '" + i + "'"
						+ " AND VarietyLikeUser = 1)"
						+ "   UPDATE VarietyLike set LikeVariety = '" + user.getUserLikeVarietyList().get(i - 1) + "'"
						+ " WHERE varieties.LikeUserID = '" + user.getId() + "'" 
						+ " AND varieties.Rank = '" + i + "'" 
						+ " AND VarietyLikeUser = 1)"
						+ "ELSE"
						+ "   INSERT INTO VarietyLike(LikeUserID, LikeVariety, Rank, VarietyLikeUser) values"
						+ "(" + user.getId() + "," + user.getUserLikeVarietyList().get(i - 1) + "," + i + ",)";
			}
			else {
				sql = "DELETE FROM varieties"
						+ " WHERE varieties.LikeUserID = '" + user.getId() + "'"
						+ " AND varieties.Rank = '" + i + "'"
						+ " AND VarietyLikeUser = 1)";
			}
			
			PreparedStatement stmt = WineHunterApplication.connection.getConnection().prepareStatement(sql);
			stmt.executeUpdate();
			stmt.executeUpdate();
			stmt.close();
		}
		
		// Update dislike varieties
		
		lastUpdate = user.getUserDislikeVarietyList().size();
		
		for (int i = 1; i < 6; ++i) {
			if (i <= lastUpdate) {
				sql = "IF EXISTS"
						+ "(SELECT * "
						+ "FROM varieties "
						+ " WHERE varieties.DislikeUserID = '" + user.getId() + "'"
						+ " AND varieties.Rank = '" + i + "'"
						+ " AND VarietyDislikeUser = 1)"
						+ "   UPDATE VarietyDislike set DislikeVarietyID = '" + user.getUserDislikeVarietyList().get(i - 1) + "'"
						+ " WHERE varieties.DislikeUserID = '" + user.getId() + "'" 
						+ " AND varieties.Rank = '" + i + "'" 
						+ " AND VarietyDislikeUser = 1)"
						+ "ELSE"
						+ "   INSERT INTO VarietyDislike(DislikeUserID, DislikeVarietyID, Rank, VarietyDislikeUser) values"
						+ "(" + user.getId() + "," + user.getUserDislikeVarietyList().get(i - 1) + "," + i + ",)";
			}
			else {
				sql = "DELETE FROM varieties"
						+ " WHERE varieties.DislikeUserID = '" + user.getId() + "'"
						+ " AND varieties.Rank = '" + i + "'"
						+ " AND VarietyDislikeUser = 1)";
			}
			
			PreparedStatement stmt = WineHunterApplication.connection.getConnection().prepareStatement(sql);
			stmt.executeUpdate();
			stmt.executeUpdate();
			stmt.close();
		}
		
		
		
		return result;
	}
}
