package Search.Logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Core.*;
import WineObjects.*;

public class WineSearch {
	private String[][] data; 
	private String[] columnNames= {"Wine Name", "Vintage", "Price", "Winery Name", "Country Name", "Province Name"}; 
	
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
	 * @return 1 if successful, 0 error, 2 if no user input
	 * 
	 */
	
	//TODO: add keyword functionality
	
	public int wineSearch(String vintage, String country, String province) throws SQLException {
		
		if((vintage == null) && (country == null) && (province == null)) {
			return 2; //no user input for criteria, so don't search
		}

		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
		String sql; 
		String wheresql = "WHERE "; 
		int needAnd = 0; //set to 1 when need an and
		if(vintage != null) {
			wheresql = wheresql + "vintage = " + vintage;
			needAnd = 1; 
		}
		if(country != null) {
			if (needAnd == 1) {
				wheresql = wheresql + " AND ";
			}
			else {
				needAnd = 1; 
			}
			wheresql = wheresql + "countryName LIKE '" + country + "%'";
		}
		if(province != null) {
			if (needAnd == 1) {
				wheresql = wheresql + " AND ";
			}
			else {
				needAnd = 1; 
			}
			wheresql = wheresql + "provinceName LIKE '" + province + "%'";
		}
		sql = "SELECT w.wineName, w.vintage, w.price, wy.wineryName, wy.countryName, wy.provinceName" + 
				" FROM wine w INNER JOIN wineries wy ON w.wineryID=wy.wineryID" + 
				" INNER JOIN province p ON p.ProvinceID = wy.ProvinceID" + 
				" INNER JOIN country c ON c.CountryID = p.CountryID" + wheresql; 
		
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
			data[row][1] = Integer.toString(vintageval);
			data[row][2] = Double.toString(price);
			data[row][3] = wineryName;
			data[row][4] = countryName;
			data[row][5] = provinceName;
			
			++row;

		}
		
		/**
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		scrollPane.setPreferredSize(new Dimension(800, 1000));
		table.setFillsViewportHeight(false);

		WineHunterMain.myGui.getFrame().getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		WineHunterMain.myGui.getFrame().pack();
		
		WineHunterMain.myGui.getFrame().setVisible(true);
		**/

		
		rs.close();

		stmt.close();
		
		return 1;
	}

}
