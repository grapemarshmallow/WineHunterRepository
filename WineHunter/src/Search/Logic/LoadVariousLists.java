package Search.Logic;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Core.WineHunterApplication;
import WineObjects.Country;
import WineObjects.Keyword;
import WineObjects.User;
import WineObjects.Variety;

public class LoadVariousLists {
	
	/**
	 * Loads all keywords and returns them in an ArrayList
	 * @param keywords ArrayList of Keyword objects
	 * @return false if an error occurred
	 * @throws SQLException
	 * @throws IOException
	 */
	public boolean loadAllKeywords(LinkedList<Keyword> keywords) throws SQLException, IOException {
		
		boolean result = false;

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM Keyword";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			keywords.add(new Keyword(rs.getInt("KeywordID"), rs.getString("Word")));
		}
		
		rs.close();
		
		stmt.close();
		
		return result;
	}
	
	/**
	 * Loads all varieties and returns them in an ArrayList
	 * @param varieties ArrayList of Variety objects
	 * @return false if an error occurred
	 * @throws SQLException
	 * @throws IOException
	 */
	public boolean loadAllVarieties(LinkedList<Variety> varieties) throws SQLException, IOException {
		
		boolean result = false;

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		

		String sql;
		sql = "SELECT *"
				+ " FROM Variety";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			varieties.add(new Variety(rs.getInt("VarietyID"), rs.getString("VarietyName")));
		}
		
		rs.close();
		
		stmt.close();
		
		return result;
	}
	
	/**
	 * Loads all countries and returns them in an ArrayList
	 * @param countries ArrayList of Country objects
	 * @return false if an error occurred
	 * @throws SQLException
	 * @throws IOException
	 */
	public boolean loadAllCountries(LinkedList<Country> countries) throws SQLException, IOException {
		
		boolean result = false;

		
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
		
		return result;
	}
}
