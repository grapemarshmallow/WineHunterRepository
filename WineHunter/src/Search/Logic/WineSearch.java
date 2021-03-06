/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             WineSearch.java
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
package Search.Logic;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import Core.*;
import Search.GUI.ViewWineSearch;
import WineObjects.*;


/**
 * this class holds an instance of a wine search
 *
 */
public class WineSearch {
	private String[][] data; 
	private String[] columnNames= {"Wine", "Vintage", "Price ($)", "Winery", "Country", "Province"}; 
	private int[] wineIDs; 
	
	/**
	 * do nothing constructor
	 */
	public WineSearch() {
		// do nothing constructor
	}
	
	public String[][] getResults() {
		return this.data;
	}
	
	
	public void setResults(String[][] data) {
		this.data = data;
	}
	
	public String[] getColumns() {
		return this.columnNames;
	}
	
	
	public void setColumns(String[] columns) {
		this.columnNames = columns;
	}
	
	public int[] getWineIDs() {
		return this.wineIDs;
	}
	
	
	public void setWineIDs(int[] wineIDs) {
		this.wineIDs = wineIDs;
	}
	
	/**
	 * API for wine searching based on taster profile
	 * @throws SQLException 
	 * @return 1 if successful, 0 error, 1 for no taster profile
	 * 
	 */
	
	public int wineSearchTasterProfile(User user) throws SQLException {
		
		int userId = user.getId();
		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();

		Statement stmtKeyLike = WineHunterApplication.connection.getConnection().createStatement(); // checks if we have keywords to like
		
		Statement stmtVarLike = WineHunterApplication.connection.getConnection().createStatement(); // checks if we have varieties to like
		
		String sql;
		
		sql = "SELECT LikeKeywordID "
				+ "FROM KeywordLike WHERE "
				+ "KeywordLikeUserID = " + userId;
		
		ResultSet rsKeyLike = stmtKeyLike.executeQuery(sql);
		
		
		rsKeyLike.last(); 
		
		int sizeKeyLike = rsKeyLike.getRow();
		
		sql = "SELECT LikeVariety "
				+ "FROM VarietyLike WHERE "
				+ "LikeUserID = " + userId;
		
		ResultSet rsVarLike = stmtVarLike.executeQuery(sql);
	
		
		rsVarLike.last(); 
		
		int sizeVarLike = rsVarLike.getRow();
		
		
		String wheresql = "WHERE wk.KeywordID NOT IN "
				+ "(SELECT DislikeKeywordID "
				+ "FROM KeywordDislike WHERE "
				+ "DislikeKeywordUserID = " + userId + " ) "
				+ "AND wv.VarietyID NOT IN "
				+ "(SELECT DislikeVarietyID FROM "
				+ "VarietyDislike WHERE "
				+ "DislikeUserID = " + userId + " )";
		
		if (sizeKeyLike > 0) {
			wheresql = wheresql + " AND wk.KeywordID IN "
					+ "(SELECT LikeKeywordID "
					+ "FROM KeywordLike WHERE "
					+ "KeywordLikeUserID = " + userId + " ) ";
					
		}
		
		if (sizeVarLike > 0) {
			wheresql = wheresql + "AND wv.VarietyID IN "
					+ "(SELECT LikeVariety "
					+ "FROM VarietyLike WHERE "
					+ "LikeUserID = " + userId + " ) ";
					
		}
		
		sql = "SELECT *" + 
				" FROM wine w INNER JOIN wineries wy ON w.wineryID=wy.wineryID" + 
				" INNER JOIN province p ON p.ProvinceID = wy.ProvinceID" + 
				" INNER JOIN country c ON c.CountryID = p.CountryID "
				+ "INNER JOIN WineKeyword wk ON wk.WineID = w.WineID "
				+ "INNER JOIN WineVariety wv ON wv.WineID = w.WineID " + wheresql; 
		
		
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		if (size <= 0) {
			rs.close();
			
			stmt.close();
			return 0;
		}
		
		data = new String[size][6];
		wineIDs = new int[size]; 
		int row = 0;
		
		while(rs.next()){
			
			
			int wineID = rs.getInt("wineID");
			String wineName = rs.getString("wineName");
			int vintageval = rs.getInt("vintage");
			double price = rs.getDouble("price");
			String wineryName = rs.getString("wineryName");
			String countryName = rs.getString("countryName");
			String provinceName = rs.getString("provinceName");
			
			wineIDs[row] = wineID; 
			data[row][0] = wineName;
			if(vintageval == 0) {
				data[row][1] = "UNKNOWN";
			}
			else {
				data[row][1] = Integer.toString(vintageval);
			}
			if(price == -1) {
				data[row][2] = "UNKNOWN";
			}
			else {
				data[row][2] = Double.toString(price);
			}
			data[row][3] = wineryName;
			data[row][4] = countryName;
			data[row][5] = provinceName;
			
			++row;
			
		}
		rs.close();
		
		stmt.close();
		
		return 1;
	}
	
	/**
	 * API for wine searching based on user favorite
	 * @throws SQLException 
	 * @param user we are getting favorites for
	 * @return 1 if successful, 0 error
	 * 
	 */
	
	public int wineSearchUserFavorites(User user) throws SQLException {
		
		int userId = user.getId();
		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
		String sql; 
		
		sql = "SELECT w.wineID, w.wineName, w.vintage, w.price, wy.wineryName, countryName, provinceName" + 
				" FROM wine w INNER JOIN wineries wy ON w.wineryID=wy.wineryID" + 
				" INNER JOIN province p ON p.ProvinceID = wy.ProvinceID" + 
				" INNER JOIN country c ON c.CountryID = p.CountryID INNER JOIN UserWine wr" + 
				" ON wr.WineID = w.wineID" + 
				" WHERE" + 
				" wr.UserID = " + userId + 
				" AND" + 
				" wr.Favorite = 1";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		
		if (size <= 0) {
			rs.close();
			
			stmt.close();
			return 0;
		}
		
		data = new String[size][6];
		wineIDs = new int[size]; 
		int row = 0;
		
		while(rs.next()){
			
			
			int wineID = rs.getInt("wineID");
			String wineName = rs.getString("wineName");
			int vintageval = rs.getInt("vintage");
			double price = rs.getDouble("price");
			String wineryName = rs.getString("wineryName");
			String countryName = rs.getString("countryName");
			String provinceName = rs.getString("provinceName");
			
			wineIDs[row] = wineID; 
			data[row][0] = wineName;
			if(vintageval == 0) {
				data[row][1] = "UNKNOWN";
			}
			else {
				data[row][1] = Integer.toString(vintageval);
			}
			if(price == -1) {
				data[row][2] = "UNKNOWN";
			}
			else {
				data[row][2] = Double.toString(price);
			}
			data[row][3] = wineryName;
			data[row][4] = countryName;
			data[row][5] = provinceName;
			
			++row;
			
		}
		
		rs.close();
		
		stmt.close();
		
		return 1;
	}
	
	/**
	 * API for wine searching based on user-reviewed Wines
	 * @throws SQLException 
	 * @param user we are getting favorites for
	 * @return 1 if successful, 0 error
	 * 
	 */
	
	public int wineSearchUserReviewed(User user) throws SQLException {
		
		int userId = user.getId();
		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
		String sql; 
		
		sql = "SELECT w.wineID, w.wineName, w.vintage, w.price, wy.wineryName, countryName, provinceName" + 
				" FROM wine w INNER JOIN wineries wy ON w.wineryID=wy.wineryID" + 
				" INNER JOIN province p ON p.ProvinceID = wy.ProvinceID" + 
				" INNER JOIN country c ON c.CountryID = p.CountryID INNER JOIN UserWine wr" + 
				" ON wr.WineID = w.wineID" + 
				" WHERE" + 
				" wr.UserID = " + userId;
		
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		
		if (size <= 0) {
			rs.close();
			
			stmt.close();
			return 0;
		}
		
		data = new String[size][6];
		wineIDs = new int[size]; 
		int row = 0;
		
		while(rs.next()){
			
			
			int wineID = rs.getInt("wineID");
			String wineName = rs.getString("wineName");
			int vintageval = rs.getInt("vintage");
			double price = rs.getDouble("price");
			String wineryName = rs.getString("wineryName");
			String countryName = rs.getString("countryName");
			String provinceName = rs.getString("provinceName");
			
			wineIDs[row] = wineID; 
			data[row][0] = wineName;
			if(vintageval == 0) {
				data[row][1] = "UNKNOWN";
			}
			else {
				data[row][1] = Integer.toString(vintageval);
			}
			if(price == -1) {
				data[row][2] = "UNKNOWN";
			}
			else {
				data[row][2] = Double.toString(price);
			}
			data[row][3] = wineryName;
			data[row][4] = countryName;
			data[row][5] = provinceName;
			
			++row;
			
		}
		
		rs.close();
		
		stmt.close();
		
		return 1;
	}
	
	/**
	 * API for wine searching
	 * @param countryId country to search on, -10 if not specified
	 * @param provinceId province to search on, -10 if not specified
	 * @param regionId region to search on, -10 if not specified
	 * @param wineryId winery to search on, -10 if not specified
	 * @param keywordId keyword to search on, -10 if not specified
	 * @param varietyId variety to search on, -10 if not specified
	 * @param vintageMinimum minimum vintage to search for
	 * @param vintageMaximum maximum vintage to search for
	 * @param priceMinimum minimum price to search for
	 * @param priceMaximum maximum price to search for
	 * @param pointsMinimum minimum points to search for
	 * @param pointsMaximum maximum points to search for
	 * @param noPriceToggle true to search for wines without price
	 * @param noPointsToggle true to search for wines without points
	 * @param noVintageToggle true to search for wines without vintage
	 * @return 1 for successful search, 2 if there were no user inputted criteria
	 * @throws SQLException
	 */
	public int wineSearch(int countryId, int provinceId, int regionId, int wineryId, int keywordId, int varietyId,
			int vintageMinimum, int vintageMaximum, int priceMinimum, int priceMaximum, int pointsMinimum,
			int pointsMaximum, boolean noPriceToggle, boolean noPointsToggle, boolean noVintageToggle) throws SQLException {
		
		if((countryId == -10) && (provinceId == -10) && (regionId == -10) && (wineryId == -10) && (keywordId == -10) && (varietyId == -10) 
				&& (vintageMinimum == ViewWineSearch.MIN_VINTAGE) && (vintageMaximum == ViewWineSearch.MAX_VINTAGE) 
				&& (priceMinimum == ViewWineSearch.MIN_PRICE) && (priceMaximum == ViewWineSearch.MAX_PRICE) && 
				(pointsMinimum == ViewWineSearch.MIN_POINTS) && (pointsMaximum == ViewWineSearch.MAX_POINTS)) {
			return 2; //no user input for criteria, so don't search
		}
		
		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
		String sql; 
		String wheresql = "WHERE"; 
		int needAnd = 0; //set to 1 when need an and
		int needAndAfter = 0;
		int needAndAfter2 = 0;
	
		int needAndInner = 0;
		int needOr = 0;
		
		String vintageString = "";
		if (vintageMinimum != ViewWineSearch.MIN_VINTAGE) {
			vintageString = "w.vintage >= " + vintageMinimum;
			needAnd = 1;
			needAndInner = 1;
			needOr = 1;
		}
		if (vintageMaximum != ViewWineSearch.MAX_VINTAGE) {
			if (needAndInner == 1) {
				vintageString = vintageString + " AND ";
			}
			else {
				needAnd = 1;
			}
			vintageString = vintageString + "w.vintage <= " + vintageMaximum;
			needOr = 1;
		}
		
		if (noVintageToggle) {
			if (needOr == 1) {
				vintageString = "((" + vintageString + ") OR (w.vintage = 0))";
			}
			
		}
		
		wheresql = wheresql + " " + vintageString;
		
		
		needAndInner = 0;
		needOr = 0;
		
		String priceString = "";
		if (priceMinimum != ViewWineSearch.MIN_PRICE) {
			priceString = "w.price >= " + priceMinimum;
			needAndAfter = 1;
			needAndInner = 1;
			needOr = 1;
		}
		if (priceMaximum != ViewWineSearch.MAX_PRICE) {
			if (needAndInner == 1) {
				priceString = priceString + " AND ";
			}
			else {
				needAndAfter = 1;
			}
			priceString = priceString + "w.price <= " + priceMaximum;
			needOr = 1;
		}
		
		if (noPriceToggle) {
			if (needOr == 1) {
				priceString = "((" + priceString + ") OR (w.price = -1))";
			}
			
		}
		
		if ((needAnd == 1) && (needAndAfter == 1)) {
			wheresql = wheresql + " AND " + priceString;
		}
		else {
			wheresql = wheresql + priceString;
		}
		
		needAndInner = 0;
		needOr = 0;
		
		String pointsString = "";
		if (pointsMinimum != ViewWineSearch.MIN_POINTS) {
			pointsString = "wr.Points >= " + pointsMinimum;
			needAndAfter2 = 1;
			needAndInner = 1;
			needOr = 1;
		}
		if (pointsMaximum != ViewWineSearch.MAX_POINTS) {
			if (needAndInner == 1) {
				pointsString = pointsString + " AND ";
			}
			else {
				needAndAfter2 = 1;
			}
			pointsString = pointsString + "wr.Points <= " + pointsMaximum;
			needOr = 1;
		}
		
		if (noPointsToggle) {
			if (needOr == 1) {
				pointsString = "((" + pointsString + ") OR (wr.Points = 0))";
			}
			
		}
		
		if (((needAnd == 1) || (needAndAfter == 1)) && (needAndAfter2 == 1)) {
			wheresql = wheresql + " AND " + pointsString;
		}
		else {
			wheresql = wheresql + pointsString;
		}
		
		
		if (wineryId != -10) {
			String wineryString = "wy.WineryID = " + wineryId;
			if ((needAnd == 1) || (needAndAfter == 1) || (needAndAfter2 == 1)) {
				wheresql = wheresql + " AND " + wineryString;
			}
			else {
				wheresql = wheresql + wineryString;
			}
			needAnd = 1;
		}
		else if (regionId != -10) {
			String regionString = "wy.RegionID = " + regionId;
			if ((needAnd == 1) || (needAndAfter == 1) || (needAndAfter2 == 1)) {
				wheresql = wheresql + " AND " + regionString;
			}
			else {
				wheresql = wheresql + regionString;
			}
			
			needAnd = 1;
		}
		else if (provinceId != -10) {
			String provinceString = "wy.ProvinceID = " + provinceId;
			if ((needAnd == 1) || (needAndAfter == 1) || (needAndAfter2 == 1)) {
				wheresql = wheresql + " AND " + provinceString;
			}
			else {
				wheresql = wheresql + provinceString;
			}
			
			needAnd = 1;
		}
		else if (countryId != -10) {
			String countryString = "c.CountryID = " + countryId;
			if ((needAnd == 1) || (needAndAfter == 1) || (needAndAfter2 == 1)) {
				wheresql = wheresql + " AND " + countryString;
			}
			else {
				wheresql = wheresql + countryString;
			}
			needAnd = 1;
		}
		
		
		if (keywordId != -10) {
			String keywordString = "w.wineID IN "
					+ "(SELECT wk.WineID FROM WineKeyword wk "
					+ "WHERE wk.KeywordID = " + keywordId + ")";
			if ((needAnd == 1) || (needAndAfter == 1) || (needAndAfter2 == 1)) {
				wheresql = wheresql + " AND " + keywordString;
			}
			else {
				wheresql = wheresql + keywordString;
			}
			needAnd = 1;
			
		}
		
		if (varietyId != -10) {
			String varietyString = "w.wineID IN "
					+ "(SELECT wv.WineID FROM WineVariety wv "
					+ "WHERE wv.VarietyID = " + varietyId + ")";
			if ((needAnd == 1) || (needAndAfter == 1) || (needAndAfter2 == 1)) {
				wheresql = wheresql + " AND " + varietyString;
			}
			else {
				wheresql = wheresql + varietyString;
			}
			needAnd = 1;
			
		}
		
		
		sql = "SELECT w.wineID, w.wineName, w.vintage, w.price, wy.wineryName, countryName, provinceName" + 
				" FROM wine w LEFT OUTER JOIN wineries wy ON w.wineryID=wy.wineryID" + 
				" LEFT OUTER JOIN province p ON p.ProvinceID = wy.ProvinceID"
				+ " LEFT OUTER JOIN region r ON wy.RegionID = r.RegionID" + 
				" LEFT OUTER JOIN country c ON c.CountryID = p.CountryID "
				+ "LEFT OUTER JOIN winereview wr on w.WineID = wr.WineID "
				+ wheresql; 
		
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		
		if (size <= 0) {
			rs.close();
			
			stmt.close();
			return 0;
		}
		
		data = new String[size][6];
		wineIDs = new int[size]; 
		int row = 0;
		
		while(rs.next()){
			
			
			int wineID = rs.getInt("wineID");
			String wineName = rs.getString("wineName");
			int vintageval = rs.getInt("vintage");
			double price = rs.getDouble("price");
			String wineryName = rs.getString("wineryName");
			String countryName = rs.getString("countryName");
			String provinceName = rs.getString("provinceName");
			
			wineIDs[row] = wineID; 
			data[row][0] = wineName;
			if(vintageval == 0) {
				data[row][1] = "UNKNOWN";
			}
			else {
				data[row][1] = Integer.toString(vintageval);
			}
			if(price == -1) {
				data[row][2] = "UNKNOWN";
			}
			else {
				data[row][2] = Double.toString(price);
			}
			data[row][3] = wineryName;
			data[row][4] = countryName;
			data[row][5] = provinceName;
			
			++row;
			
		}
		rs.close();
		
		stmt.close();
		
		return 1;
		
	}
	
}
