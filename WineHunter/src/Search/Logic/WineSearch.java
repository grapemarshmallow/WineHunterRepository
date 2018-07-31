package Search.Logic;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import Core.*;
import Search.GUI.ViewWineSearch;
import WineObjects.*;



public class WineSearch {
	private String[][] data; 
	private String[] columnNames= {"Wine", "Vintage", "Price ($)", "Winery", "Country", "Province"}; 
	
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
	
	/**
	 * API for wine searching
	 * @throws SQLException 
	 * @param vintage
	 * @param country
	 * @param province
	 * @return 1 if successful, 0 error, 2 no user input
	 * 
	 */
	
	//TODO: add keyword functionality
	
	public int wineSearch(String vintage, String country, String province) throws SQLException {
		
		if((vintage == "") && (country == "") && (province == "")) {
			return 2; //no user input for criteria, so don't search
		}

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
		String sql; 
		String wheresql = "WHERE "; 
		int needAnd = 0; //set to 1 when need an and
		if(vintage != "") {
			wheresql = wheresql + "vintage = " + vintage;
			needAnd = 1; 
		}
		if(country != "") {
			if (needAnd == 1) {
				wheresql = wheresql + " AND ";
			}
			else {
				needAnd = 1; 
			}
			wheresql = wheresql + "countryName LIKE '" + country + "%'";
		}
		if(province != "") {
			if (needAnd == 1) {
				wheresql = wheresql + " AND ";
			}
			else {
				needAnd = 1; 
			}
			wheresql = wheresql + "provinceName LIKE '" + province + "%'";
		}
		sql = "SELECT w.wineName, w.vintage, w.price, wy.wineryName, countryName, provinceName" + 
				" FROM wine w INNER JOIN wineries wy ON w.wineryID=wy.wineryID" + 
				" INNER JOIN province p ON p.ProvinceID = wy.ProvinceID" + 
				" INNER JOIN country c ON c.CountryID = p.CountryID " + wheresql; 

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
		int row = 0;
		
		while(rs.next()){
			
			
			String wineName = rs.getString("wineName");
			int vintageval = rs.getInt("vintage");
			double price = rs.getDouble("price");
			String wineryName = rs.getString("wineryName");
			String countryName = rs.getString("countryName");
			String provinceName = rs.getString("provinceName");
			
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
	 * API for wine searching based on taster profile
	 * @throws SQLException 
	 * @return 1 if successful, 0 error, 1 for no taster profile
	 * 
	 */
	
	
	public int wineSearchTasterProfile(User user) throws SQLException {
		
		int userId = user.getId();
		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
		String sql; 
		String wheresql = "WHERE wk.KeywordID IN "
				+ "(SELECT LikeKeywordID "
				+ "FROM KeywordLike WHERE "
				+ "KeywordLikeUserID = " + userId + " ) "
				+ "AND wk.KeywordID NOT IN "
				+ "(SELECT DislikeKeywordID "
				+ "FROM KeywordDislike WHERE "
				+ "DislikeKeywordUserID = " + userId + " ) "
				+ "AND wv.VarietyID IN "
				+ "(SELECT LikeVariety "
				+ "FROM VarietyLike WHERE "
				+ "LikeUserID = " + userId + " ) "
				+ "AND wv.VarietyID NOT IN "
				+ "(SELECT DislikeVarietyID FROM "
				+ "VarietyDislike WHERE "
				+ "DislikeUserID = " + userId + " )";
						
		sql = "SELECT w.wineName, w.vintage, w.price, wy.wineryName, countryName, provinceName" + 
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
		int row = 0;
		
		while(rs.next()){
			
			
			String wineName = rs.getString("wineName");
			int vintageval = rs.getInt("vintage");
			double price = rs.getDouble("price");
			String wineryName = rs.getString("wineryName");
			String countryName = rs.getString("countryName");
			String provinceName = rs.getString("provinceName");
			
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
		
		sql = "SELECT w.wineName, w.vintage, w.price, wy.wineryName, countryName, provinceName" + 
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
		int row = 0;
		
		while(rs.next()){
			
			
			String wineName = rs.getString("wineName");
			int vintageval = rs.getInt("vintage");
			double price = rs.getDouble("price");
			String wineryName = rs.getString("wineryName");
			String countryName = rs.getString("countryName");
			String provinceName = rs.getString("provinceName");
			
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
		
		sql = "SELECT w.wineName, w.vintage, w.price, wy.wineryName, countryName, provinceName" + 
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
		int row = 0;
		
		while(rs.next()){
			
			
			String wineName = rs.getString("wineName");
			int vintageval = rs.getInt("vintage");
			double price = rs.getDouble("price");
			String wineryName = rs.getString("wineryName");
			String countryName = rs.getString("countryName");
			String provinceName = rs.getString("provinceName");
			
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
		
		int needAndInner = 0;
		int needOr = 0;
		
		String vintageString = "";
		if (vintageMinimum != ViewWineSearch.MIN_VINTAGE) {
			vintageString = "wine.vintage > " + vintageMinimum;
			needAnd = 1;
			needAndInner = 1;
			needOr = 1;
		}
		if (vintageMaximum == ViewWineSearch.MAX_VINTAGE) {
			if (needAndInner == 1) {
				vintageString = vintageString + " AND ";
			}
			else {
				needAnd = 1;
			}
			vintageString = vintageString + "wine.vintage < " + vintageMaximum;
			needOr = 1;
		}
		
		if (noVintageToggle) {
			if (needOr == 1) {
				vintageString = "((" + vintageString + ") OR (wine.vintage = 0))";
			}

		}
		
		wheresql = wheresql + " " + vintageString;
		
		needAndInner = 0;
		needOr = 0;
		
		String priceString = "";
		if (priceMinimum != ViewWineSearch.MIN_PRICE) {
			priceString = "wine.price > " + priceMinimum;
			needAnd = 1;
			needAndInner = 1;
			needOr = 1;
		}
		if (priceMaximum == ViewWineSearch.MAX_PRICE) {
			if (needAndInner == 1) {
				priceString = priceString + " AND ";
			}
			else {
				needAnd = 1;
			}
			priceString = priceString + "wine.price < " + priceMaximum;
			needOr = 1;
		}
		
		if (noPriceToggle) {
			if (needOr == 1) {
				priceString = "(" + priceString + ") OR (wine.price = 0)";
			}

		}
		
		if (needAnd == 1) {
			wheresql = wheresql + " AND " + priceString;
		}
		else {
			wheresql = wheresql + priceString;
		}
		
		needAndInner = 0;
		needOr = 0;
		
		String pointsString = "";
		if (pointsMinimum != ViewWineSearch.MIN_POINTS) {
			pointsString = "wr.Points > " + pointsMinimum;
			needAnd = 1;
			needAndInner = 1;
			needOr = 1;
		}
		if (pointsMaximum == ViewWineSearch.MAX_POINTS) {
			if (needAndInner == 1) {
				pointsString = pointsString + " AND ";
			}
			else {
				needAnd = 1;
			}
			pointsString = pointsString + "wr.Points < " + pointsMaximum;
			needOr = 1;
		}
		
		if (noPointsToggle) {
			if (needOr == 1) {
				pointsString = "(" + pointsString + ") OR (wr.Points = 0)";
			}

		}
		
		if (needAnd == 1) {
			wheresql = wheresql + " AND " + pointsString;
		}
		else {
			wheresql = wheresql + pointsString;
		}
		
		if (wineryId != -10) {
			String wineryString = "wine.WineryID = " + wineryId;
			if (needAnd == 1) {
				wheresql = wheresql + " AND " + wineryString;
			}
			else {
				wheresql = wheresql + wineryString;
			}
		}
		else if (regionId != -10) {
			String regionString = "winery.RegionID = " + regionId;
			if (needAnd == 1) {
				wheresql = wheresql + " AND " + regionString;
			}
			else {
				wheresql = wheresql + regionString;
			}
		}
		else if (provinceId != -10) {
			String provinceString = "winery.ProvinceID = " + provinceId;
			if (needAnd == 1) {
				wheresql = wheresql + " AND " + provinceString;
			}
			else {
				wheresql = wheresql + provinceString;
			}
		}
		else if (countryId != -10) {
			String countryString = "region.CountryID = " + countryId;
			if (needAnd == 1) {
				wheresql = wheresql + " AND " + countryString;
			}
			else {
				wheresql = wheresql + countryString;
			}
		}
		
		
		sql = "SELECT w.wineName, w.vintage, w.price, wy.wineryName, countryName, provinceName" + 
				" FROM wine w INNER JOIN wineries wy ON w.wineryID=wy.wineryID" + 
				" INNER JOIN province p ON p.ProvinceID = wy.ProvinceID" + 
				" INNER JOIN country c ON c.CountryID = p.CountryID "
				+ "INNER JOIN WineReview wr on w.WineID = wr.WineID " + wheresql; 
		
		System.out.println(sql);

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
		int row = 0;
		
		while(rs.next()){
			
			
			String wineName = rs.getString("wineName");
			int vintageval = rs.getInt("vintage");
			double price = rs.getDouble("price");
			String wineryName = rs.getString("wineryName");
			String countryName = rs.getString("countryName");
			String provinceName = rs.getString("provinceName");
			
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
