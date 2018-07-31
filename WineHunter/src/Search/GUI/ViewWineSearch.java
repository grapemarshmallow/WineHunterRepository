package Search.GUI;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import Core.WineHunterApplication;
import Search.Logic.LoadVariousLists;
import WineObjects.*;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

import javax.swing.border.EmptyBorder;

import javax.swing.SpinnerNumberModel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JToggleButton;

public class ViewWineSearch extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7966165569110154144L;
	private Vector<Country> countryList = new Vector<Country>();
	private Vector<Variety> varietyList = new Vector<Variety>();
	private Vector<Keyword> keywordList = new Vector<Keyword>();
	//private Vector<Region> regionList = new Vector<Region>();
	private Vector<Province> provinceList = new Vector<Province>();
	//private Vector<Winery> wineryList = new Vector<Winery>();
	//private Vector<Region> regionFilteredList = new Vector<Region>();
	private Vector<Province> provinceFilteredList = new Vector<Province>();
	//private Vector<Winery> wineryFilteredList = new Vector<Winery>();
	private JComboBox<Country> countryBox = new JComboBox<Country>(countryList);
	private JComboBox<Variety> varietyBox = new JComboBox<Variety>(varietyList);
	private JComboBox<Keyword> keywordBox = new JComboBox<Keyword>(keywordList);
//	private JComboBox<Region> regionBox = new JComboBox<Region>();
	private JComboBox<Province> provinceBox = new JComboBox<Province>();
//	private JComboBox<Winery> wineryBox = new JComboBox<Winery>();
	private JTextField wineryText = new JTextField();
	private JTextField regionText = new JTextField();
	private JSpinner minVintageSpinner;
	private JSpinner maxVintageSpinner;
	
	private int notEmpty;
	private JSpinner minPointsSpinner;
	private JSpinner maxPointsSpinner;
	private JSpinner minPriceSpinner;
	private JSpinner maxPriceSpinner; 
	
	public final static int MIN_VINTAGE = 1880;
	public final static int MAX_VINTAGE = 2020;
	public final static int MIN_PRICE = 0;
	public final static int MAX_PRICE = 5000;
	public final static int MIN_POINTS = 0;
	public final static int MAX_POINTS = 100;
	

	/**
	 * Create the panel to search for wines
	 */
	
	public ViewWineSearch(int empty) {

		init();
		
		notEmpty = 0; 

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[]{0};
		this.setLayout(gridBagLayout);
		
		JPanel labelPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) labelPanel.getLayout();
		flowLayout.setHgap(20);
		GridBagConstraints gbc_labelPanel = new GridBagConstraints();
		gbc_labelPanel.insets = new Insets(0, 0, 5, 0);
		gbc_labelPanel.anchor = GridBagConstraints.NORTH;
		gbc_labelPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelPanel.gridx = 0;
		gbc_labelPanel.gridy = 2;
		add(labelPanel, gbc_labelPanel);
		
		JLabel lblSearchPrompt = new JLabel("Search for wines!");
		lblSearchPrompt.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchPrompt.setFont(WineHunterApplication.format.getHeadingFont());
		GridBagConstraints gbc_lblSearchPrompt = new GridBagConstraints();
		gbc_lblSearchPrompt.insets = new Insets(15, 15, 15, 15);
		gbc_lblSearchPrompt.anchor = GridBagConstraints.NORTH;
		gbc_lblSearchPrompt.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSearchPrompt.gridx = 0;
		gbc_lblSearchPrompt.gridy = 0;
		
		labelPanel.add(lblSearchPrompt, gbc_lblSearchPrompt);
		
		JLabel lblempty = new JLabel("Enter valid search criteria before searching.");
		lblempty.setHorizontalAlignment(SwingConstants.CENTER);
		lblempty.setFont(WineHunterApplication.format.getSubheadingFont2Base());
		GridBagConstraints gbc_lblempty = new GridBagConstraints();
		gbc_lblempty.insets = new Insets(15, 15, 15, 15);
		gbc_lblempty.anchor = GridBagConstraints.NORTH;
		gbc_lblempty.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblempty.gridx = 0;
		gbc_lblempty.gridy = 1;
		if(empty == 2) {
			labelPanel.add(lblempty, gbc_lblempty);
		}
		
		JLabel lblnoresults = new JLabel("No results found. You may search again.");
		GridBagConstraints gbc_lblnoresults = new GridBagConstraints();
		lblnoresults.setHorizontalAlignment(SwingConstants.CENTER);
		lblnoresults.setFont(WineHunterApplication.format.getSubheadingFont2Base());
		gbc_lblnoresults.insets = new Insets(15, 15, 15, 15);
		gbc_lblnoresults.anchor = GridBagConstraints.NORTH;
		gbc_lblnoresults.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblnoresults.gridx = 0;
		gbc_lblnoresults.gridy = 1;
		if(empty == 0) {
			labelPanel.add(lblnoresults, gbc_lblnoresults);
		}
		
		JPanel searchPanel = new JPanel();
		GridBagConstraints gbc_searchPanel = new GridBagConstraints();
		gbc_searchPanel.anchor = GridBagConstraints.NORTH;
		gbc_searchPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchPanel.gridx = 0;
		gbc_searchPanel.gridy = 3;
		add(searchPanel, gbc_searchPanel);
		searchPanel.setLayout(new GridLayout(10, 1, 0, 0));
		
		JPanel vintagePanel = new JPanel();
		searchPanel.add(vintagePanel);
		
		JLabel lblVintage = new JLabel("Vintage: ");
		vintagePanel.add(lblVintage);
		
		minVintageSpinner = new JSpinner();
		minVintageSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int currentMinValue = (int) minVintageSpinner.getValue();
				int currentMaxValue = (int) maxVintageSpinner.getValue();
				if (currentMinValue > currentMaxValue) {
					maxVintageSpinner.setModel(new SpinnerNumberModel(currentMinValue + 1, MIN_VINTAGE, MAX_VINTAGE, 1));
				}
				
			}
		});
		minVintageSpinner.setToolTipText("Select the minimum vintage year.");
		minVintageSpinner.setModel(new SpinnerNumberModel(MIN_VINTAGE, MIN_VINTAGE, MAX_VINTAGE, 1));
		vintagePanel.add(minVintageSpinner);
		
		JLabel lblVintageInstrTo = new JLabel("to");
		vintagePanel.add(lblVintageInstrTo);
		
		maxVintageSpinner = new JSpinner();
		maxVintageSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int currentMinValue = (int) minVintageSpinner.getValue();
				int currentMaxValue = (int) maxVintageSpinner.getValue();
				if (currentMinValue > currentMaxValue) {
					minVintageSpinner.setModel(new SpinnerNumberModel(currentMaxValue - 1, MIN_VINTAGE, MAX_VINTAGE, 1));
				}
				
			}
		});
		maxVintageSpinner.setToolTipText("Select the maximum vintage year.");
		maxVintageSpinner.setModel(new SpinnerNumberModel(MAX_VINTAGE, MIN_VINTAGE, MAX_VINTAGE, 1));
		vintagePanel.add(maxVintageSpinner);
		
		JLabel lblVintageInstr = new JLabel("Choose the minimum and maximum vintage years.");
		vintagePanel.add(lblVintageInstr);
		
		JToggleButton noVintageToggle = new JToggleButton("Include results with no vintage");
		vintagePanel.add(noVintageToggle);
		
		
		JLabel locationLabel = new JLabel("Location:");
		locationLabel.setName("locationLabel");
		searchPanel.add(locationLabel);
		
		JPanel locationPanel = new JPanel();
		searchPanel.add(locationPanel);
		
		JLabel countryBoxLabel = new JLabel("Country: ");
		countryBoxLabel.setName("countryBoxLabel");
		locationPanel.add(countryBoxLabel);
		locationPanel.add(countryBox);
		
		JPanel locationPanel2 = new JPanel();
		searchPanel.add(locationPanel2);
		
		JLabel provinceBoxLabel = new JLabel("Province: ");
		provinceBoxLabel.setName("provinceBoxLabel");
		locationPanel2.add(provinceBoxLabel);
		locationPanel2.add(provinceBox);
		
		JPanel locationPanel3 = new JPanel();
		searchPanel.add(locationPanel3);
		
		JLabel regionBoxLabel = new JLabel("Region: ");
		regionBoxLabel.setName("regionBoxLabel");
		locationPanel3.add(regionBoxLabel);
		
		regionText.setColumns(10);
		locationPanel3.add(regionText);
		
		JPanel locationPanel4 = new JPanel();
		searchPanel.add(locationPanel4);
		
		JLabel wineryBoxLabel = new JLabel("Winery: ");
		wineryBoxLabel.setName("wineryBoxLabel");
		locationPanel4.add(wineryBoxLabel);
		
		wineryText.setColumns(10);
		locationPanel4.add(wineryText);
		
		
		JPanel pointsPanel = new JPanel();
		searchPanel.add(pointsPanel);
		
		JLabel lblPoints = new JLabel("Points: ");
		pointsPanel.add(lblPoints);
		
		minPointsSpinner = new JSpinner();
		minPointsSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int currentMinValue = (int) minPointsSpinner.getValue();
				int currentMaxValue = (int) maxPointsSpinner.getValue();
				if (currentMinValue > currentMaxValue) {
					maxPointsSpinner.setModel(new SpinnerNumberModel(currentMinValue + 1, MIN_POINTS, MAX_POINTS, 1));
				}
				
			}
		});
		minPointsSpinner.setToolTipText("Select the minimum points.");
		minPointsSpinner.setModel(new SpinnerNumberModel(MIN_POINTS, MIN_POINTS, MAX_POINTS, 1));
		pointsPanel.add(minPointsSpinner);
		
		JLabel lblPointsInstrTo = new JLabel("to");
		pointsPanel.add(lblPointsInstrTo);
		
		maxPointsSpinner = new JSpinner();
		maxPointsSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int currentMinValue = (int) minPointsSpinner.getValue();
				int currentMaxValue = (int) maxPointsSpinner.getValue();
				if (currentMinValue > currentMaxValue) {
					minPointsSpinner.setModel(new SpinnerNumberModel(currentMaxValue - 1, MIN_POINTS, MAX_POINTS, 1));
				}
				
			}
		});
		maxPointsSpinner.setToolTipText("Select the maximum points.");
		maxPointsSpinner.setModel(new SpinnerNumberModel(MAX_POINTS, MIN_POINTS, MAX_POINTS, 1));
		pointsPanel.add(maxPointsSpinner);
		
		JLabel lblPointsInstr = new JLabel("Choose the minimum and maximum scores.");
		pointsPanel.add(lblPointsInstr);
		
		JToggleButton noPointsToggle = new JToggleButton("Include results with no score");
		pointsPanel.add(noPointsToggle);
		
		JPanel pricePanel = new JPanel();
		searchPanel.add(pricePanel);
		
		JLabel lblPrice = new JLabel("Price: ");
		pricePanel.add(lblPrice);
		
		minPriceSpinner = new JSpinner();
		minPriceSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int currentMinValue = (int) minPriceSpinner.getValue();
				int currentMaxValue = (int) maxPriceSpinner.getValue();
				if (currentMinValue > currentMaxValue) {
					maxPriceSpinner.setModel(new SpinnerNumberModel(currentMinValue + 1, MIN_PRICE, MAX_PRICE, 1));
				}
				
			}
		});
		minPriceSpinner.setToolTipText("Select the minimum price.");
		minPriceSpinner.setModel(new SpinnerNumberModel(MIN_PRICE, MIN_PRICE, MAX_PRICE, 1));
		pricePanel.add(minPriceSpinner);
		
		JLabel lblPriceInstrTo = new JLabel("to");
		pricePanel.add(lblPriceInstrTo);
		
		maxPriceSpinner = new JSpinner();
		maxPriceSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int currentMinValue = (int) minPriceSpinner.getValue();
				int currentMaxValue = (int) maxPriceSpinner.getValue();
				if (currentMinValue > currentMaxValue) {
					minPriceSpinner.setModel(new SpinnerNumberModel(currentMaxValue - 1, MIN_PRICE, MAX_PRICE, 1));
				}
				
			}
		});
		maxPriceSpinner.setToolTipText("Select the maximum price.");
		maxPriceSpinner.setModel(new SpinnerNumberModel(MAX_PRICE, MIN_PRICE, MAX_PRICE, 1));
		pricePanel.add(maxPriceSpinner);
		
		JLabel lblPriceInstr = new JLabel("Choose the minimum and maximum prices.");
		pricePanel.add(lblPriceInstr);
		
		JToggleButton noPriceToggle = new JToggleButton("Include results with no price");
		pricePanel.add(noPriceToggle);
		
		JPanel lastPanel = new JPanel();
		searchPanel.add(lastPanel);
		
		JLabel lblKeyword = new JLabel("Keyword: ");
		lastPanel.add(lblKeyword);
		
		lastPanel.add(keywordBox);
		
		JLabel lblVariety = new JLabel("Variety: ");
		lastPanel.add(lblVariety);
		lastPanel.add(varietyBox);
		
		JPanel searchButtonPanel = new JPanel();
		searchPanel.add(searchButtonPanel);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int vintageMinimum = (int) minVintageSpinner.getValue();
				int vintageMaximum = (int) maxVintageSpinner.getValue();
				int priceMinimum = (int) minPriceSpinner.getValue();
				int priceMaximum = (int) maxPriceSpinner.getValue();
				int pointsMinimum = (int) minPointsSpinner.getValue();
				int pointsMaximum = (int) maxPointsSpinner.getValue();
				
				int countryId = -10;
				//int regionId = -10;
				int provinceId = -10;
				//int wineryId = -10;
				int varietyId = -10;
				int keywordId = -10;
				
				String regionTextIn = regionText.getText().toUpperCase();
				String wineryTextIn = wineryText.getText().toUpperCase();
				
				if (countryBox.getSelectedItem() != null) {
					countryId = ((Country) countryBox.getSelectedItem()).getId();
				}

				if (provinceBox.getSelectedItem() != null) {
					provinceId = ((Province) provinceBox.getSelectedItem()).getId();
				}

				if (keywordBox.getSelectedItem() != null) {
					keywordId = ((Keyword) keywordBox.getSelectedItem()).getId();
				}
				if (varietyBox.getSelectedItem() != null) {
					varietyId = ((Variety) varietyBox.getSelectedItem()).getId();
				}
				
				try {
					notEmpty = WineHunterApplication.wineSearch.wineSearch(countryId, provinceId, regionTextIn, wineryTextIn, keywordId, 
							varietyId, vintageMinimum, vintageMaximum, priceMinimum, priceMaximum, pointsMinimum, pointsMaximum,
							noPriceToggle.isSelected(), noPointsToggle.isSelected(), noVintageToggle.isSelected());
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
		searchButtonPanel.add(btnSearch);
		
		
	}
	
	/**
	 * initializes everything we need
	 */
	private void init() {
		try {
			LoadVariousLists.loadAllVarieties(varietyList);
			LoadVariousLists.loadAllKeywords(keywordList);
			LoadVariousLists.loadAllLocations(countryList, provinceList);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);

			e.printStackTrace();
			return;
		}
		
		countryBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (!e.equals(null)) {
					refreshLocation();
				}
			}
		});
		countryBox.setName("countryBox");
		
		varietyBox.setName("varietyBox");
		
		keywordBox.setName("keywordBox");
		
		
		provinceBox.setName("provinceBox");
		
		
		provinceBox.setSelectedItem(null);
		provinceBox.setEnabled(false);
		
		countryBox.setSelectedItem(null);
		countryBox.setEnabled(true);
		
		varietyBox.setModel(new DefaultComboBoxModel<Variety>(varietyList));
		varietyBox.setSelectedItem(null);
		varietyBox.setEnabled(true);
		
		keywordBox.setModel(new DefaultComboBoxModel<Keyword>(keywordList));
		
		keywordBox.setSelectedItem(null);
		keywordBox.setEnabled(true);
		
		
	}
	
	/**
	 * refreshes the location lists based on what is selected
	 */
	private void refreshLocation() {
		
		Province currentProvince = (Province) provinceBox.getSelectedItem();
		Country currentCountry = (Country) countryBox.getSelectedItem();
		
		
		if (currentCountry != null) {

			provinceFilteredList = new Vector<Province>();
			for (int j = 0; j < provinceList.size(); ++j) {
				currentProvince = provinceList.get(j);
				if (currentProvince != null) {
					if (currentProvince.getCountry().getId() == currentCountry.getId()) {
						provinceFilteredList.addElement(currentProvince);

					}
				}
			}
			
			
			provinceBox.setModel(new DefaultComboBoxModel<Province>(provinceFilteredList));
			provinceBox.setSelectedItem(null);
			provinceBox.setEnabled(true);
			
			
		
		}
		
		else {
			
			provinceBox.setSelectedItem(null);
			provinceBox.setEnabled(false);
			countryBox.setModel(new DefaultComboBoxModel<Country>(countryList));
			countryBox.setSelectedItem(null);
			countryBox.setEnabled(true);
		}
	}

}
