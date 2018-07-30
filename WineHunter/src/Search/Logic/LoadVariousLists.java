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
			Keyword keywordToAdd = new Keyword(rs.getInt("KeywordID"), rs.getString("Word"));
			
			keywords.addElement(keywordToAdd);
			
		}
		
		rs.close();
		
		stmt.close();
		
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
			Variety varietyToAdd = new Variety(rs.getInt("VarietyID"), rs.getString("VarietyName"));
			
			varieties.addElement(varietyToAdd);
		}
		
		rs.close();
		
		stmt.close();
		
		
	}
	
	/**
	 * Loads all countries and returns them in an ArrayList
	 * @param countries Vector of Country objects
	 * @throws SQLException
	 */
	public static void loadAllCountries(Vector<Country> countries) throws SQLException  {
	

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM Country";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			countries.addElement(new Country(rs.getInt("CountryID"), rs.getString("CountryName")));
		}
		
		rs.close();
		
		stmt.close();
		
	}
	
	
}
