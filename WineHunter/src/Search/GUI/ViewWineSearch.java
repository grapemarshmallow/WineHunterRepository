package Search.GUI;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JButton;

import Core.WineHunterApplication;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.FlowLayout;

public class ViewWineSearch extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7966165569110154144L;
	private JTextField vintage;
	private JTextField country;
	private JTextField province;
	private int notEmpty; 
	

	/**
	 * Create the panel to search for wines
	 */
	
	public ViewWineSearch(int empty) {
		
		this.setMaximumSize(new Dimension(WineHunterApplication.APPLICATION_WIDTH - 100, WineHunterApplication.APPLICATION_HEIGHT - 100));
		
		notEmpty = 0; 

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[]{0};
		
		
		JLabel lblNewLabel = new JLabel("Search for wines!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		
		this.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblempty = new JLabel("Please enter valid search criteria before searching.");
		GridBagConstraints gbc_lblempty = new GridBagConstraints();
		gbc_lblempty.insets = new Insets(0, 0, 5, 0);
		gbc_lblempty.gridx = 0;
		gbc_lblempty.gridy = 2;
		if(empty == 2) {
			add(lblempty, gbc_lblempty);
		}
		
		JLabel lblnoresults = new JLabel("No results found. You may search again.");
		GridBagConstraints gbc_lblnoresults = new GridBagConstraints();
		gbc_lblnoresults.insets = new Insets(0, 0, 5, 0);
		gbc_lblnoresults.gridx = 0;
		gbc_lblnoresults.gridy = 2;
		if(empty == 0) {
			add(lblnoresults, gbc_lblnoresults);
		}
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(20);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		
		JLabel lblVintage = new JLabel("Vintage");
		panel.add(lblVintage);
		
		vintage = new JTextField();
		lblVintage.setLabelFor(vintage);
		panel.add(vintage);
		vintage.setColumns(10);
		
		JLabel lblCountry = new JLabel("Country");
		panel.add(lblCountry);
		
		country = new JTextField();
		lblCountry.setLabelFor(country);
		panel.add(country);
		country.setColumns(10);
		
		JLabel lblProvince = new JLabel("Province");
		panel.add(lblProvince);
		
		province = new JTextField();
		lblProvince.setLabelFor(province);
		panel.add(province);
		province.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String vtext = ""; 
				String ctext = ""; 
				String ptext = ""; 
				if (!vintage.getText().isEmpty()) {
					vtext=vintage.getText();
				}
				if (!country.getText().isEmpty()) {
					ctext=country.getText();
				}
				if (!province.getText().isEmpty()) {
					ptext=province.getText();
				}
				try {
					notEmpty = WineHunterApplication.wineSearch.wineSearch(vtext, ctext, ptext);
				} catch (SQLException e) {
					WineHunterApplication.searchWines(2); //go back to search screen
				}
				if(notEmpty == 2) {
					WineHunterApplication.searchWines(2);
				}
				else if(notEmpty == 0) {
					WineHunterApplication.searchWines(0);
				}
				else {
					String[][] data = WineHunterApplication.wineSearch.getResults();
					String[] columnNames = WineHunterApplication.wineSearch.getColumns();
					WineHunterApplication.showWines(data,columnNames); 
				}
			}
		});
		panel.add(btnSearch);
		
	}

}
