import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LocationFunctions {
	
	/**
	 * API for menu
	 * @throws IOException 
	 */
	public static void locationMenu() throws SQLException, IOException {
		Scanner in = new Scanner(System.in);  // Reading from System.in
		String[] choiceArray = new String[4];
		choiceArray[0] = "Quit^Q";
		choiceArray[1] = "Country^C";
		choiceArray[2] = "Region^R";
		choiceArray[3] = "Province^P";
	   
	   String prompt = "What criteria do you want to search on? ";
	   String help = "Select the criteria you would like to use to search for wines.";
	   
	   int result = Library.ChoiceDriver(in, choiceArray, prompt, help);
	   
	   
	   if (result == 0) {
		   return;
	   }
	   else {
		   locationSearch(result);
		   
	   }
	   
	   
	}
	
	/**
	 * API for location searching
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void locationSearch(int locationType) throws SQLException, IOException {
		
		
		
		String searchType = null;
		String searchColumn = null;
		
		if (locationType == 1) {
			searchType = "Country";
			searchColumn = "wy.CountryName";
		}
		else if (locationType == 2) {
			searchType = "Region";
			searchColumn = "wy.RegionName";
		}
		else if (locationType == 3) {
			searchType = "Province";
			searchColumn = "wy.ProvinceName";
		}
		else {
			System.out.println("Invalid search type.");
			return;
		}
		
		System.out.println("Searching " + searchType.toLowerCase() + ".");
		
		
		
		
		Statement stmt = WineHunterMain.conn.createStatement();
		

		System.out.println("Search Text: ");
		String searchString = WineHunterMain.in.next();
		
		String sql;
		sql = "SELECT w.wineName, w.vintage, w.price, wy.wineryName, wy.countryName, wy.regionName, wy.provinceName" + 
				" FROM wine w INNER JOIN wineries wy ON w.wineryID=wy.wineryID" + 
				" WHERE " + searchColumn + " LIKE '" + searchString + "'";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		if (size <= 0) {
			System.out.println("No results.");
		}
		
		String[] columnNames = {"Wine Name", "Vintage", "Price", "Winery Name", "Country Name", "Province Name", "Region Name"};
		String[][] data = new String[size][7];
		int row = 0;
		
		while(rs.next()){
			
			
			String wineName = rs.getString("wineName");
			int vintage = rs.getInt("vintage");
			double price = rs.getDouble("price");
			String wineryName = rs.getString("wineryName");
			String countryName = rs.getString("countryName");
			String regionName = rs.getString("regionName");
			String provinceName = rs.getString("provinceName");
			
			data[row][0] = wineName;
			data[row][1] = Integer.toString(vintage);
			data[row][2] = Double.toString(price);
			data[row][3] = wineryName;
			data[row][4] = countryName;
			data[row][5] = provinceName;
			data[row][6] = regionName;
			
			
			++row;

		}
		
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		scrollPane.setPreferredSize(new Dimension(800, 1000));
		table.setFillsViewportHeight(false);

		WineHunterMain.myGui.getFrame().getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		WineHunterMain.myGui.getFrame().pack();
		
		WineHunterMain.myGui.getFrame().setVisible(true);

		
		rs.close();

		
		
		stmt.close();
		
		
	}
	
	/**
	 * API for location searching
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static int locationSearch(int locationType, String searchText) throws SQLException, IOException {
		
		
		
		String searchType = null;
		String searchColumn = null;
		
		if (locationType == 1) {
			searchType = "Country";
			searchColumn = "wy.CountryName";
		}
		else if (locationType == 2) {
			searchType = "Region";
			searchColumn = "wy.RegionName";
		}
		else if (locationType == 3) {
			searchType = "Province";
			searchColumn = "wy.ProvinceName";
		}
		else {
			System.out.println("Invalid search type.");
			return -1;
		}

		
		
		
		
		Statement stmt = WineHunterMain.conn.createStatement();
		
		
		String sql;
		sql = "SELECT w.wineName, w.vintage, w.price, wy.wineryName, wy.countryName, wy.regionName, wy.provinceName" + 
				" FROM wine w INNER JOIN wineries wy ON w.wineryID=wy.wineryID" + 
				" WHERE " + searchColumn + " LIKE '" + searchText + "'";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		if (size <= 0) {
			return 0;
		}
		
		String[] columnNames = {"Wine Name", "Vintage", "Price", "Winery Name", "Country Name", "Province Name", "Region Name"};
		String[][] data = new String[size][7];
		int row = 0;
		
		while(rs.next()){
			
			
			String wineName = rs.getString("wineName");
			int vintage = rs.getInt("vintage");
			double price = rs.getDouble("price");
			String wineryName = rs.getString("wineryName");
			String countryName = rs.getString("countryName");
			String regionName = rs.getString("regionName");
			String provinceName = rs.getString("provinceName");
			
			data[row][0] = wineName;
			data[row][1] = Integer.toString(vintage);
			data[row][2] = Double.toString(price);
			data[row][3] = wineryName;
			data[row][4] = countryName;
			data[row][5] = provinceName;
			data[row][6] = regionName;
			
			
			++row;

		}
		
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		scrollPane.setPreferredSize(new Dimension(800, 1000));
		table.setFillsViewportHeight(false);

		WineHunterMain.myGui.getFrame().getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		WineHunterMain.myGui.getFrame().pack();
		
		WineHunterMain.myGui.getFrame().setVisible(true);

		
		rs.close();

		
		
		stmt.close();
		
		return 1;
	}
}
