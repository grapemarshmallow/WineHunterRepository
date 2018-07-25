import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.sql.*;
import javax.swing.*;

public class LocationFunctions implements ActionListener {
	
	private JTextField search;
	private JButton searchButton;
	private JButton cancel;
	private JRadioButton country;
	private JRadioButton region;
	private JRadioButton province;
	private JPanel panel;
	
	protected JComponent locationButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        country = new JRadioButton("Country");
        country.setVerticalTextPosition(AbstractButton.CENTER);
		country.setHorizontalTextPosition(AbstractButton.LEADING);
		country.setMnemonic(KeyEvent.VK_D);
		country.setActionCommand("country");

		country.addActionListener(this);
		country.setToolTipText("Click this button to search wineries by country.");
		
		panel.add(country);
		
		region = new JRadioButton("Region");
        region.setVerticalTextPosition(AbstractButton.CENTER);
		region.setHorizontalTextPosition(AbstractButton.LEADING);
		region.setMnemonic(KeyEvent.VK_D);
		region.setActionCommand("region");

		region.addActionListener(this);
		region.setToolTipText("Click this button to search wineries by region.");
		
		panel.add(region);
		
		province = new JRadioButton("Province");
        province.setVerticalTextPosition(AbstractButton.CENTER);
		province.setHorizontalTextPosition(AbstractButton.LEADING);
		province.setMnemonic(KeyEvent.VK_D);
		province.setActionCommand("province");

		province.addActionListener(this);
		province.setToolTipText("Click this button to search wineries by province.");
		
		panel.add(province);
		
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return panel;
    }
	
	protected JComponent searchButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

		
		searchButton = new JButton("Search");
        searchButton.setVerticalTextPosition(AbstractButton.CENTER);
		searchButton.setHorizontalTextPosition(AbstractButton.LEADING);
		searchButton.setMnemonic(KeyEvent.VK_D);
		searchButton.setActionCommand("search");

		searchButton.addActionListener(this);
		searchButton.setToolTipText("Click this button to search.");
		
		panel.add(searchButton);
		
		cancel = new JButton("Cancel");
		cancel.setVerticalTextPosition(AbstractButton.CENTER);
		cancel.setHorizontalTextPosition(AbstractButton.LEADING);
		cancel.setMnemonic(KeyEvent.VK_D);
		cancel.setActionCommand("cancel");

		cancel.addActionListener(this);
		cancel.setToolTipText("Click this button to cancel/quit.");
		
		panel.add(cancel);
		
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return panel;
    }
	
	protected JComponent searchField() {
		JPanel panel = new JPanel(new SpringLayout());
		
		panel.setPreferredSize(new Dimension(400,100));

        search = new JTextField();
        search.setColumns(20);

        JLabel searchLabel = new JLabel("Search: ", JLabel.TRAILING);

        searchLabel.setLabelFor(search);
        panel.add(searchLabel);
        panel.add(search);

        //Add listeners to each field.
        JTextField tf = (JTextField) search;

        tf.addActionListener(this);

        
        Gui.makeCompactGrid(panel, 1, 2, 10, 10, 10, 10);
        
        return panel;
    }
	
	/**
	 * API for location searching
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public int locationSearch(int locationType, String searchText) throws SQLException, IOException {
		
		
		
		String searchColumn = null;
		
		if (locationType == 1) {
			searchColumn = "wy.CountryName";
		}
		else if (locationType == 2) {
			searchColumn = "wy.RegionName";
		}
		else if (locationType == 3) {
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
				" WHERE " + searchColumn + " LIKE '" + searchText + "%'";
		
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
	
	/**
	 * @param i 
	 * 
	 */
	public void drawSearch(int help) {
		
		panel = new JPanel();
		
		
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		JLabel reprompt = new JLabel();
		
		
		if (help == 1) {
			reprompt.setText("Select a location search type.");
			panel.add(reprompt,BorderLayout.CENTER);
			panel.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
		}
		else if (help == 2) {
			reprompt.setText("No results.");
			panel.add(reprompt,BorderLayout.CENTER);
			panel.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
		}
		
		panel.setPreferredSize(new Dimension(400,150));
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		panel.add(locationButtons());
		panel.add(searchField());
		panel.add(searchButtons());
		
		WineHunterMain.myGui.getFrame().getContentPane().add(panel, BorderLayout.CENTER);
 
		//Display the window.
		WineHunterMain.myGui.getFrame().pack();
		WineHunterMain.myGui.getFrame().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if ("cancel".equals(e.getActionCommand())) {
				System.exit(1);
			} else if ("country".equals(e.getActionCommand())) {
				country.setSelected(true);
				region.setSelected(false);
				province.setSelected(false);
			} else if ("region".equals(e.getActionCommand())) {
				country.setSelected(false);
				region.setSelected(true);
				province.setSelected(false);
			} else if ("province".equals(e.getActionCommand())) {
				country.setSelected(false);
				region.setSelected(false);
				province.setSelected(true);
			} else if ("search".equals(e.getActionCommand())) {
				String searchText = search.getText();
				
				int ret = -1;
				
				if (country.isSelected()) {
					ret = locationSearch(1, searchText);
				} else if (region.isSelected()) {
					ret = locationSearch(2, searchText);
				} else if (province.isSelected()) {
					ret = locationSearch(3, searchText);
				}
				
				if (ret == -1) {
					WineHunterMain.myGui.getFrame().remove(panel);
					drawSearch(1);
				} else if (ret == 0) {
					WineHunterMain.myGui.getFrame().remove(panel);
					drawSearch(2);
				}
			} 
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
	}


}
