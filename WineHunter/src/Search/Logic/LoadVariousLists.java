package Search.Logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Core.WineHunterApplication;
import WineObjects.*;

public class LoadVariousLists {
	
	/**
	 * Loads all keywords and returns them in a DefaultComboBoxModel
	 * @param keywords Vector of Keyword objects
	 * @throws SQLException 
	 */
	public static void loadAllKeywords(Vector<Keyword> keywords) throws SQLException  {
	
		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM Keyword";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		
		while (rs.next()) {
			int id = rs.getInt("KeywordID");
			Keyword keywordToAdd = new Keyword(id, rs.getString("Word"));
			
			keywords.addElement(keywordToAdd);
			
		}
		
	}
	
	/**
	 * Loads all varieties and returns to a combo box model for the UI
	 * @param varieties Vector of Variety objects
	 * @throws SQLException 
	 */
	public static void loadAllVarieties(Vector<Variety> varieties) throws SQLException  {
		
		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM varieties";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		
		while (rs.next()) {
			int id = rs.getInt("VarietyID");
			Variety varietyToAdd = new Variety(id, rs.getString("VarietyName"));
			
			varieties.addElement(varietyToAdd);
			
		}
		
		
	}
	
	/**
	 * Loads all countries into a vector for use with JComboBox
	 * @param countries Vector of Country objects
	 * @throws SQLException
	 */
	public static void loadAllCountries(Vector<Country> countries) throws SQLException {

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM country"
				+ " ORDER BY CountryID";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			Country countryToAdd = new Country(rs.getInt("CountryID"), rs.getString("CountryName"));
			countries.addElement(countryToAdd);
		}
		
		rs.close();
		stmt.close();
	}
	
	/**
	 * Loads all provinces into a vector for use with JComboBox based on a selected country
	 * @param provinces Vector of Province objects
	 * @param Country object that provinces will be filtered on
	 * @throws SQLException
	 */
	public static void loadProvinceOptions(Vector<Province> provinces, Country country) throws SQLException {
		
		provinces.removeAllElements();

		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
		int countryId = country.getId();
		
		String sql;
		sql = "SELECT *"
				+ " FROM province"
				+ " WHERE CountryID = " + countryId
				+ " ORDER BY ProvinceID";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			Province provinceToAdd = new Province(rs.getInt("ProvinceID"), rs.getString("ProvinceName"), country);
			provinces.addElement(provinceToAdd);
		}
		
		rs.close();
		stmt.close();
	}
	
	/**
	 * Loads all provinces into a vector for use with JComboBox based on a selected province
	 * @param provinces Vector of Region objects
	 * @param Province object that regions will be filtered on
	 * @throws SQLException
	 */
	public static void loadRegionOptions(Vector<Region> regions, Province province) throws SQLException {
		
		regions.removeAllElements(); // clean

		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
		int provinceId = province.getId();
		
		String sql;
		sql = "SELECT DISTINCT r.RegionID, r.RegionName"
				+ " FROM region r"
				+ " INNER JOIN wineries wy"
				+ " ON r.RegionID = wy.RegionID"
				+ " WHERE wy.ProvinceID = " + provinceId
				+ " ORDER BY r.RegionID";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			Region regionToAdd = new Region(rs.getInt("RegionID"), rs.getString("RegionName"));
			regions.addElement(regionToAdd);
		}
		
		rs.close();
		stmt.close();
	}
	
	/**
	 * Loads all wineries into a vector for use with JComboBox based on a selected province/region
	 * @param wineries Vector of Winery objects
	 * @param Province object that wineries will be filtered on
	 * @param Region object that wineries will be filtered on
	 * @throws SQLException
	 */
	public static void loadWineryOptions(Vector<Winery> wineries, Province province, Region region) throws SQLException {
		
		wineries.removeAllElements();
		
		int provinceId = province.getId();
		int regionId = -10;
		
		if (region != null) {
			regionId = region.getId();
		}

		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
		String sql;
		sql = "SELECT *"
				+ " FROM wineries wy"
				+ " INNER JOIN region r"
				+ " ON wy.RegionID = r.RegionID"
				+ " WHERE wy.ProvinceID = " + provinceId;
		
		if (regionId != -10) {
			sql = sql + " AND wy.RegionID = " + regionId;
		}
		
		sql = sql + " ORDER BY wy.WineryID";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			Winery wineryToAdd;
			if (regionId != -10) {
				wineryToAdd = new Winery(rs.getInt("WineryID"), rs.getString("WineryName"), region, province);
			}
			else {
				wineryToAdd = new Winery(rs.getInt("WineryID"), rs.getString("WineryName"), new Region(rs.getInt("RegionID"), rs.getString("RegionName")), province);
			}
			wineries.addElement(wineryToAdd);
		}
		
		rs.close();
		stmt.close();
	}
	

	
	
	
	
}
