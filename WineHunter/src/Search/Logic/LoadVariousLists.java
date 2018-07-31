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
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		keywords.setSize(size + 5);
		
		while (rs.next()) {
			int id = rs.getInt("KeywordID");
			Keyword keywordToAdd = new Keyword(id, rs.getString("Word"));
			
			keywords.set(id, keywordToAdd);
			
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
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		varieties.setSize(size + 5);
		
		while (rs.next()) {
			int id = rs.getInt("VarietyID");
			Variety varietyToAdd = new Variety(id, rs.getString("VarietyName"));
			
			varieties.set(id, varietyToAdd);
		}
		
		
		
		
		
	}
	
	/**
	 * Loads all location info and returns it in vectors
	 * @param countries Vector of Country objects
	 * @param provinces Vector of province objects
	 * @param regions Vector of Region objects
	 * @param wineries Vector of Winery objects
	 * @throws SQLException
	 */
	public static void loadAllLocations(Vector<Country> countries, Vector<Province> provinces, Vector<Region> regions, Vector<Winery> wineries) throws SQLException  {
	

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM country"
				+ " ORDER BY CountryID";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		
		countries.setSize(100);
		
		while (rs.next()) {
			Country countryToAdd = new Country(rs.getInt("CountryID"), rs.getString("CountryName"));
			countries.set(countryToAdd.getId(), countryToAdd);
			String provinceSql = "SELECT *"
					+ " FROM province"
					+ " WHERE CountryID = " + countryToAdd.getId()
					+ " ORDER BY ProvinceID";
			
			Statement provinceStmt = WineHunterApplication.connection.getConnection().createStatement();
			
			ResultSet province = provinceStmt.executeQuery(provinceSql);
			
			provinces.setSize(400);
			
			while (province.next()) {
				Province provinceToAdd = new Province(province.getInt("ProvinceID"), province.getString("ProvinceName"), countryToAdd);
				provinces.addElement(provinceToAdd);
				String winerySql = "SELECT *"
						+ " FROM wineries"
						+ " LEFT OUTER JOIN region ON"
						+ " wineries.RegionID = region.RegionID"
						+ " WHERE wineries.ProvinceID = " + provinceToAdd.getId()
						+ " ORDER BY wineries.RegionID";
				
				Statement wineryStmt = WineHunterApplication.connection.getConnection().createStatement();
				
				ResultSet winery = wineryStmt.executeQuery(winerySql);
				
				wineries.setSize(17000);
				regions.setSize(1000);
				
				while (winery.next()) {
					int regionId = winery.getInt("RegionID");
					int wineryId = winery.getInt("WineryID");
					String regionName = winery.getString("RegionName");
					String wineryName = winery.getString("WineryName");
					Region regionToAdd = regions.get(regionId);
					if (regionToAdd == null) {
						regionToAdd = new Region(regionId, regionName);
						regions.set(regionId, regionToAdd);

					}
					
					Winery wineryToAdd = new Winery(wineryId, wineryName, regionToAdd, provinceToAdd);
					
					wineries.set(wineryId, wineryToAdd);
				}
			}
		}
		
		
		
		
		
	}
	
	
	
	
}
