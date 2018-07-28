package Search.GUI;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JButton;


import com.jgoodies.forms.factories.DefaultComponentFactory;

import Core.WineHunterApplication;
import UserFunctions.Logic.UserProfile;
import UserFunctions.Logic.UserUpdate;
import WineObjects.Keyword;
import WineObjects.User;
import WineObjects.Variety;

import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class ViewWineSearch extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7966165569110154144L;
	private JTextField vintage;
	private JTextField country;
	private JTextField province;
	

	/**
	 * Create the panel to search for wines
	 */
	
	public ViewWineSearch() {

		
		setBounds(100, 100, WineHunterApplication.APPLICATION_WIDTH, WineHunterApplication.APPLICATION_HEIGHT);

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		
		gridBagLayout.columnWidths = new int[] {450};
		gridBagLayout.rowHeights = new int[]{16, 0, 0, 169, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		
		
		JLabel lblNewLabel = new JLabel("Search for wines!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		
		this.add(lblNewLabel, gbc_lblNewLabel);
		
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
				String vtext = vintage.getText();
				String ctext = country.getText(); 
				String ptext = province.getText(); 
				try {
					WineHunterApplication.wineSearch.wineSearch(vtext, ctext, ptext);
				} catch (SQLException e) {
					WineHunterApplication.searchWines(); //go back to search screen
				}
				WineHunterApplication.showWines(WineHunterApplication.wineSearch.getResults(), WineHunterApplication.wineSearch.getColumns()); 
			}
		});
		panel.add(btnSearch);
		
	}

}
