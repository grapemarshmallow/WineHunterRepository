package Search.Logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;

import Core.WineHunterApplication;
import WineObjects.*;

public class LoadVariousLists {
	
	/**
	 * Loads all keywords and returns them in a DefaultComboBoxModel
	 * @param keywords DefaultComboBoxModel of Keyword objects
	 * @throws SQLException 
	 */
	public static void loadAllKeywords(DefaultComboBoxModel<Keyword> keywords) throws SQLException  {
	

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM Keyword";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			keywords.addElement(new Keyword(rs.getInt("KeywordID"), rs.getString("Word")));
		}
		
		rs.close();
		
		stmt.close();
		
	}
	
	/**
	 * Loads all varieties and returns to a combo box model for the UI
	 * @param varieties DefaultComboBoxModel of Variety objects
	 * @throws SQLException 
	 */
	public static void loadAllVarieties(DefaultComboBoxModel<Variety> varieties) throws SQLException  {

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM varieties";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			varieties.addElement(new Variety(rs.getInt("VarietyID"), rs.getString("VarietyName")));
		}
		
		rs.close();
		
		stmt.close();
		
		
	}
	
	/**
	 * Loads all countries and returns them in an ArrayList
	 * @param countries ArrayList of Country objects
	 * @throws SQLException
	 */
	public static void loadAllCountries(LinkedList<Country> countries) throws SQLException  {
	

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM Country";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			countries.add(new Country(rs.getInt("CountryID"), rs.getString("CountryName")));
		}
		
		rs.close();
		
		stmt.close();
		
	}
	
	
}
