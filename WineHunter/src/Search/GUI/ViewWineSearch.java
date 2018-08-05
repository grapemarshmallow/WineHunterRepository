/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 * //                   
 * // Main Class File:  WineHunterApplication.java
 * // File:             ViewWineSearch.java
 * // Semester:         Summer 2018
 * //
 * //
 * // Author:           Orbi Ish-Shalom (oishshalom@wisc.edu)
 * // CS Login:         orbi
 * // Lecturer's Name:  Hien Hguyen
 * //
 * //                   PAIR PROGRAMMERS COMPLETE THIS SECTION
 * // Pair Partner:     Jennifer Shih
 * //////////////////////////// 80 columns wide //////////////////////////////////
 *******************************************************************************/
package Search.GUI;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import Core.WineHunterApplication;
import Search.Logic.LoadVariousLists;
import Search.Logic.WineSearch;
import WineObjects.*;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

import javax.swing.border.EmptyBorder;

import javax.swing.SpinnerNumberModel;

import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JToggleButton;
import javax.swing.SpinnerDateModel;

import java.awt.GridLayout;

/**
 * The class generates a panel to view a wine search
 *
 */
public class ViewWineSearch extends JPanel{
	
	// fields
	private static final long serialVersionUID = -7966165569110154144L;
	private ItemListener countryRefresh;
	private ItemListener provinceRefresh;
	private ItemListener regionRefresh;
	private ItemListener wineryRefresh;
	private Vector<Country> countryList = new Vector<Country>();
	private Vector<Variety> varietyList = new Vector<Variety>();
	private Vector<Keyword> keywordList = new Vector<Keyword>();
	private Vector<Region> regionList = new Vector<Region>();
	private Vector<Province> provinceList = new Vector<Province>();
	private Vector<Winery> wineryList = new Vector<Winery>();
	private JComboBox<Country> countryBox = new JComboBox<Country>();
	private JComboBox<Variety> varietyBox = new JComboBox<Variety>();
	private JComboBox<Keyword> keywordBox = new JComboBox<Keyword>();
	private JComboBox<Region> regionBox = new JComboBox<Region>();
	private JComboBox<Province> provinceBox = new JComboBox<Province>();
	private JComboBox<Winery> wineryBox = new JComboBox<Winery>();
	private JSpinner minVintageSpinner;
	private JSpinner maxVintageSpinner;
	private int notEmpty;
	private JSpinner minPointsSpinner;
	private JSpinner maxPointsSpinner;
	private JSpinner minPriceSpinner;
	private JSpinner maxPriceSpinner;
	protected WineSearch wineSearch; 
	
	public static int MIN_VINTAGE;
	public static int MAX_VINTAGE;
	public static int MIN_PRICE;
	public static int MAX_PRICE;
	public static int MIN_POINTS;
	public static int MAX_POINTS;
	
	
	/**
	 * Create the panel to search for wines
	 * @param empty non-zero if no results
	 * @param user the search is for
	 */
	public ViewWineSearch(int empty, User user) {
		
		init();
		
		notEmpty = 0; 
		
		this.setBorder(new EmptyBorder(5, 30, 5, 30));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[]{0};
		this.setLayout(gridBagLayout);
		
		JPanel labelPanel = new JPanel();
		GridBagLayout gbl_labelPanel = new GridBagLayout();
		gbl_labelPanel.columnWidths = new int[] {0};
		gbl_labelPanel.rowHeights = new int[]{0};
		labelPanel.setLayout(gbl_labelPanel);
		GridBagConstraints gbc_labelPanel = new GridBagConstraints();
		gbc_labelPanel.insets = new Insets(5, 5, 5, 5);
		gbc_labelPanel.anchor = GridBagConstraints.NORTH;
		gbc_labelPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelPanel.gridx = 0;
		gbc_labelPanel.gridy = 0;
		gbc_labelPanel.weightx = 1;
		this.add(labelPanel, gbc_labelPanel);
		
		JLabel lblSearchPrompt = new JLabel("Search for wines!");
		lblSearchPrompt.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchPrompt.setFont(WineHunterApplication.format.getHeadingFont());
		GridBagConstraints gbc_lblSearchPrompt = new GridBagConstraints();
		gbc_lblSearchPrompt.insets = new Insets(10, 10, 10, 10);
		gbc_lblSearchPrompt.anchor = GridBagConstraints.NORTH;
		gbc_lblSearchPrompt.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSearchPrompt.gridx = 0;
		gbc_lblSearchPrompt.gridy = 0;
		gbc_lblSearchPrompt.weightx = 1;
		labelPanel.add(lblSearchPrompt, gbc_lblSearchPrompt);
		
		JLabel lblempty = new JLabel();
		lblempty.setHorizontalAlignment(SwingConstants.CENTER);
		lblempty.setFont(WineHunterApplication.format.getSubheadingFont2Base());
		GridBagConstraints gbc_lblempty = new GridBagConstraints();
		gbc_lblempty.insets = new Insets(5, 5, 5, 5);
		gbc_lblempty.anchor = GridBagConstraints.NORTH;
		gbc_lblempty.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblempty.gridx = 0;
		gbc_lblempty.gridy = 1;
		gbc_lblempty.weightx = 1;
		if(empty == 2) {
			lblempty.setText("Enter valid search criteria before searching.");
			labelPanel.add(lblempty, gbc_lblempty);
		}
		else if (empty == 0) {
			lblempty.setText("No results found. You may search again.");
			labelPanel.add(lblempty, gbc_lblempty);
		}
		
		JPanel searchPanel = new JPanel();
		GridBagConstraints gbc_searchPanel = new GridBagConstraints();
		gbc_searchPanel.anchor = GridBagConstraints.NORTH;
		gbc_searchPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchPanel.gridx = 0;
		gbc_searchPanel.gridy = 1;
		gbc_searchPanel.weightx = 1;
		this.add(searchPanel, gbc_searchPanel);
		
		GridBagLayout gbl_searchPanel = new GridBagLayout();
		gbl_searchPanel.columnWidths = new int[] {0};
		gbl_searchPanel.columnWeights = new double[]{0.0};
		searchPanel.setLayout(gbl_searchPanel);
		
		JPanel vintagePanel = new JPanel();
		GridBagConstraints gbc_vintagePanel = new GridBagConstraints();
		gbc_vintagePanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_vintagePanel.insets = new Insets(5, 5, 5, 5);
		gbc_vintagePanel.gridx = 0;
		gbc_vintagePanel.gridy = 0;
		gbc_vintagePanel.weightx = 1;
		searchPanel.add(vintagePanel, gbc_vintagePanel);
		GridBagLayout gbl_vintagePanel = new GridBagLayout();
		gbl_vintagePanel.columnWidths = new int[]{0};
		gbl_vintagePanel.rowHeights = new int[]{0};
		gbl_vintagePanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_vintagePanel.rowWeights = new double[]{Double.MIN_VALUE};
		vintagePanel.setLayout(gbl_vintagePanel);
		
		JPanel vintageSet = new JPanel();
		GridBagConstraints gbc_vintageSet = new GridBagConstraints();
		gbc_vintageSet.fill = GridBagConstraints.HORIZONTAL;
		gbc_vintageSet.insets = new Insets(5, 5, 5, 5);
		gbc_vintageSet.gridx = 0;
		gbc_vintageSet.gridy = 0;
		gbc_vintageSet.weightx = 1;
		vintagePanel.add(vintageSet, gbc_vintageSet);
		vintageSet.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblVintage = new JLabel("Vintage: ");
		vintageSet.add(lblVintage);
		
		JPanel vintageSpinnerPanel = new JPanel();
		vintageSet.add(vintageSpinnerPanel);
		vintageSpinnerPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		Calendar calendar = Calendar.getInstance();
	    calendar.set(MIN_VINTAGE, 0, 1);
	    Date earliestDate = calendar.getTime();
	    calendar.add(Calendar.YEAR, 1);
	    Date initialMinDate = calendar.getTime();
	    calendar.set(MAX_VINTAGE, 0, 1);
	    Date latestDate = calendar.getTime();
		
		minVintageSpinner = new JSpinner();
		minVintageSpinner.setToolTipText("Select the minimum vintage year.");
		minVintageSpinner.setModel(new SpinnerDateModel(initialMinDate, earliestDate, latestDate, Calendar.YEAR));
		minVintageSpinner.setEditor(new JSpinner.DateEditor(minVintageSpinner, "yyyy"));
		minVintageSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Date currentMinValue = (Date) minVintageSpinner.getValue();
				Date currentMaxValue = (Date) maxVintageSpinner.getValue();
				if (currentMinValue.after(currentMaxValue)) {
					calendar.setTime(currentMinValue);
					calendar.add(Calendar.YEAR, 1);
					Date newMax = calendar.getTime();
					maxVintageSpinner.setValue(newMax);
				}
				
			}
		});
		vintageSpinnerPanel.add(minVintageSpinner);
		
		JLabel lblVintageInstrTo = new JLabel("to");
		lblVintageInstrTo.setHorizontalAlignment(SwingConstants.CENTER);
		vintageSpinnerPanel.add(lblVintageInstrTo);
		
		maxVintageSpinner = new JSpinner();
		maxVintageSpinner.setToolTipText("Select the maximum vintage year.");
		maxVintageSpinner.setModel(new SpinnerDateModel(latestDate, earliestDate, latestDate, Calendar.YEAR));
		maxVintageSpinner.setEditor(new JSpinner.DateEditor(maxVintageSpinner, "yyyy"));
		maxVintageSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Date currentMinValue = (Date) minVintageSpinner.getValue();
				Date currentMaxValue = (Date) maxVintageSpinner.getValue();
				if (currentMinValue.after(currentMaxValue)) {
					calendar.setTime(currentMaxValue);
					calendar.add(Calendar.YEAR, -1);
					Date newMin = calendar.getTime();
					minVintageSpinner.setValue(newMin);
				}
				
			}
		});
		vintageSpinnerPanel.add(maxVintageSpinner);
		
		JLabel lblVintageInstr = new JLabel("Choose the desired vintage range in years.");
		lblVintageInstr.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblVintageInstr = new GridBagConstraints();
		gbc_lblVintageInstr.anchor = GridBagConstraints.CENTER;
		gbc_lblVintageInstr.gridx = 0;
		gbc_lblVintageInstr.gridy = 1;
		gbc_lblVintageInstr.weightx = 1;
		gbc_lblVintageInstr.fill = GridBagConstraints.HORIZONTAL;
		vintagePanel.add(lblVintageInstr, gbc_lblVintageInstr);
		
		JToggleButton noVintageToggle = new JToggleButton("Include results with no vintage");
		noVintageToggle.setSelected(true);
		vintageSet.add(noVintageToggle);
		
		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		sep.setName("Separator");
		GridBagConstraints gbc_sep = new GridBagConstraints();
		sep.setPreferredSize(new Dimension(this.getWidth(),5));
		gbc_sep.fill = GridBagConstraints.HORIZONTAL;
		gbc_sep.gridx = 0;
		gbc_sep.gridy = 1;
		gbc_sep.weightx = 1;
		searchPanel.add(sep, gbc_sep);
		
		JLabel locationLabel = new JLabel("Specify Location");
		locationLabel.setName("locationLabel");
		locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		locationLabel.setFont(WineHunterApplication.format.getSubheadingFont2Base());
		GridBagConstraints gbc_locationLabel = new GridBagConstraints();
		gbc_locationLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_locationLabel.insets = new Insets(5, 5, 5, 5);
		gbc_locationLabel.gridx = 0;
		gbc_locationLabel.gridy = 2;
		searchPanel.add(locationLabel, gbc_locationLabel);
		
		JPanel locationPanel = new JPanel();
		GridBagConstraints gbc_locationPanel = new GridBagConstraints();
		gbc_locationPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_locationPanel.insets = new Insets(5, 5, 5, 5);
		gbc_locationPanel.gridx = 0;
		gbc_locationPanel.gridy = 3;
		searchPanel.add(locationPanel, gbc_locationPanel);
		
		GridBagLayout gbl_locationPanel = new GridBagLayout();
		gbl_locationPanel.columnWidths = new int[]{0};
		gbl_locationPanel.rowHeights = new int[]{0};
		gbl_locationPanel.columnWeights = new double[]{0.0, 1.0};
		gbl_locationPanel.rowWeights = new double[]{Double.MIN_VALUE};
		locationPanel.setLayout(gbl_locationPanel);
		
		GridBagConstraints gbc_countryBox = new GridBagConstraints();
		gbc_countryBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_countryBox.insets = new Insets(5, 5, 5, 5);
		gbc_countryBox.gridx = 1;
		gbc_countryBox.gridy = 0;
		gbc_countryBox.weightx = 1;
		
		GridBagConstraints gbc_countryBoxLabel = new GridBagConstraints();
		gbc_countryBoxLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_countryBoxLabel.insets = new Insets(5, 5, 5, 5);
		gbc_countryBoxLabel.gridx = 0;
		gbc_countryBoxLabel.gridy = 0;
		
		JLabel countryBoxLabel = new JLabel("Country:");
		countryBoxLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		countryBoxLabel.setName("countryBoxLabel");
		
		locationPanel.add(countryBoxLabel, gbc_countryBoxLabel);
		locationPanel.add(countryBox, gbc_countryBox);
		
		GridBagConstraints gbc_provinceBox = new GridBagConstraints();
		gbc_provinceBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_provinceBox.insets = new Insets(5, 5, 5, 5);
		gbc_provinceBox.gridx = 1;
		gbc_provinceBox.gridy = 1;
		gbc_provinceBox.weightx = 1;
		
		GridBagConstraints gbc_provinceBoxLabel = new GridBagConstraints();
		gbc_provinceBoxLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_provinceBoxLabel.insets = new Insets(5, 5, 5, 5);
		gbc_provinceBoxLabel.gridx = 0;
		gbc_provinceBoxLabel.gridy = 1;
		
		JLabel provinceBoxLabel = new JLabel("Province:");
		provinceBoxLabel.setName("provinceBoxLabel");
		provinceBoxLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		locationPanel.add(provinceBoxLabel, gbc_provinceBoxLabel);
		locationPanel.add(provinceBox, gbc_provinceBox);
		
		GridBagConstraints gbc_regionText = new GridBagConstraints();
		gbc_regionText.fill = GridBagConstraints.HORIZONTAL;
		gbc_regionText.insets = new Insets(5, 5, 5, 5);
		gbc_regionText.gridx = 1;
		gbc_regionText.gridy = 2;
		gbc_regionText.weightx = 1;
		
		GridBagConstraints gbc_regionTextLabel = new GridBagConstraints();
		gbc_regionTextLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_regionTextLabel.insets = new Insets(5, 5, 5, 5);
		gbc_regionTextLabel.gridx = 0;
		gbc_regionTextLabel.gridy = 2;
		
		JLabel regionBoxLabel = new JLabel("Region:");
		regionBoxLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		regionBoxLabel.setName("regionBoxLabel");
		locationPanel.add(regionBoxLabel, gbc_regionTextLabel);
		
		locationPanel.add(regionBox, gbc_regionText);
		
		GridBagConstraints gbc_wineryText = new GridBagConstraints();
		gbc_wineryText.fill = GridBagConstraints.HORIZONTAL;
		gbc_wineryText.insets = new Insets(5, 5, 5, 5);
		gbc_wineryText.gridx = 1;
		gbc_wineryText.gridy = 3;
		gbc_wineryText.weightx = 1;
		
		GridBagConstraints gbc_wineryTextLabel = new GridBagConstraints();
		gbc_wineryTextLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_wineryTextLabel.insets = new Insets(5, 5, 5, 5);
		gbc_wineryTextLabel.gridx = 0;
		gbc_wineryTextLabel.gridy = 3;
		
		JLabel wineryBoxLabel = new JLabel("Winery:");
		wineryBoxLabel.setName("wineryBoxLabel");
		wineryBoxLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		locationPanel.add(wineryBoxLabel, gbc_wineryTextLabel);
		
		locationPanel.add(wineryBox, gbc_wineryText);
		
		JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
		sep.setName("Separator2");
		GridBagConstraints gbc_sep2 = new GridBagConstraints();
		sep.setPreferredSize(new Dimension(this.getWidth(),5));
		gbc_sep2.fill = GridBagConstraints.HORIZONTAL;
		gbc_sep2.gridx = 0;
		gbc_sep2.gridy = 4;
		gbc_sep2.weightx = 1;
		searchPanel.add(sep2, gbc_sep2);
		
		JPanel pointsPanel = new JPanel();
		GridBagConstraints gbc_pointsPanel = new GridBagConstraints();
		gbc_pointsPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_pointsPanel.insets = new Insets(5, 5, 5, 5);
		gbc_pointsPanel.gridx = 0;
		gbc_pointsPanel.gridy = 5;
		searchPanel.add(pointsPanel, gbc_pointsPanel);
		
		GridBagLayout gbl_pointsPanel = new GridBagLayout();
		gbl_pointsPanel.columnWidths = new int[]{0};
		gbl_pointsPanel.rowHeights = new int[]{0};
		gbl_pointsPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_pointsPanel.rowWeights = new double[]{Double.MIN_VALUE};
		pointsPanel.setLayout(gbl_pointsPanel);
		
		JPanel pointsSet = new JPanel();
		GridBagConstraints gbc_pointsSet = new GridBagConstraints();
		gbc_pointsSet.fill = GridBagConstraints.HORIZONTAL;
		gbc_pointsSet.insets = new Insets(5, 5, 5, 5);
		gbc_pointsSet.gridx = 0;
		gbc_pointsSet.gridy = 0;
		pointsPanel.add(pointsSet, gbc_pointsSet);
		pointsSet.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblPoints = new JLabel("Score: ");
		pointsSet.add(lblPoints);
		
		JPanel pointsSpinnerPanel = new JPanel();
		pointsSet.add(pointsSpinnerPanel);
		pointsSpinnerPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		minPointsSpinner = new JSpinner();
		minPointsSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int currentMinValue = (int) minPointsSpinner.getValue();
				int currentMaxValue = (int) maxPointsSpinner.getValue();
				if (currentMinValue > currentMaxValue) {
					maxPointsSpinner.setValue(currentMinValue + 1);
					
				}
				
			}
		});
		minPointsSpinner.setToolTipText("Select the minimum points.");
		minPointsSpinner.setModel(new SpinnerNumberModel(MIN_POINTS, MIN_POINTS, MAX_POINTS, 1));
		
		pointsSpinnerPanel.add(minPointsSpinner);
		
		JLabel lblPointsInstrTo = new JLabel("to");
		lblPointsInstrTo.setHorizontalAlignment(SwingConstants.CENTER);
		pointsSpinnerPanel.add(lblPointsInstrTo);
		
		maxPointsSpinner = new JSpinner();
		maxPointsSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int currentMinValue = (int) minPointsSpinner.getValue();
				int currentMaxValue = (int) maxPointsSpinner.getValue();
				if (currentMinValue > currentMaxValue) {
					minPointsSpinner.setValue(currentMaxValue - 1);
				}
				
			}
		});
		maxPointsSpinner.setToolTipText("Select the maximum points.");
		maxPointsSpinner.setModel(new SpinnerNumberModel(MAX_POINTS, MIN_POINTS, MAX_POINTS, 1));
		pointsSpinnerPanel.add(maxPointsSpinner);
		
		JToggleButton noPointsToggle = new JToggleButton("Include results with no score");
		noPointsToggle.setSelected(true);
		pointsSet.add(noPointsToggle);
		
		JLabel lblPointsInstr = new JLabel("Choose the Wine Enthusiast review score range in points (0 - 100).");
		lblPointsInstr.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPointsInstr = new GridBagConstraints();
		gbc_lblPointsInstr.anchor = GridBagConstraints.CENTER;
		gbc_lblPointsInstr.gridx = 0;
		gbc_lblPointsInstr.gridy = 1;
		gbc_lblPointsInstr.weightx = 1;
		gbc_lblPointsInstr.fill = GridBagConstraints.HORIZONTAL;
		pointsPanel.add(lblPointsInstr, gbc_lblPointsInstr);
		
		JSeparator sep3 = new JSeparator(SwingConstants.HORIZONTAL);
		sep.setName("Separator3");
		GridBagConstraints gbc_sep3 = new GridBagConstraints();
		sep.setPreferredSize(new Dimension(this.getWidth(),5));
		gbc_sep3.fill = GridBagConstraints.HORIZONTAL;
		gbc_sep3.gridx = 0;
		gbc_sep3.gridy = 6;
		gbc_sep3.weightx = 1;
		searchPanel.add(sep3, gbc_sep3);
		
		JPanel pricePanel = new JPanel();
		GridBagConstraints gbc_pricePanel = new GridBagConstraints();
		gbc_pricePanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_pricePanel.insets = new Insets(5, 5, 5, 5);
		gbc_pricePanel.gridx = 0;
		gbc_pricePanel.gridy = 7;
		searchPanel.add(pricePanel, gbc_pricePanel);
		
		GridBagLayout gbl_pricePanel = new GridBagLayout();
		gbl_pricePanel.columnWidths = new int[]{0};
		gbl_pricePanel.rowHeights = new int[]{0};
		gbl_pricePanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_pricePanel.rowWeights = new double[]{Double.MIN_VALUE};
		pricePanel.setLayout(gbl_pricePanel);
		
		JPanel priceSet = new JPanel();
		GridBagConstraints gbc_priceSet = new GridBagConstraints();
		gbc_priceSet.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceSet.insets = new Insets(5, 5, 5, 5);
		gbc_priceSet.gridx = 0;
		gbc_priceSet.gridy = 0;
		gbc_priceSet.weightx = 1;
		pricePanel.add(priceSet, gbc_priceSet);
		
		GridBagLayout gbl_priceSet = new GridBagLayout();
		gbl_priceSet.columnWidths = new int[]{0};
		gbl_priceSet.rowHeights = new int[]{0};
		gbl_priceSet.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_priceSet.rowWeights = new double[]{Double.MIN_VALUE};
		
		priceSet.setLayout(new GridLayout(0, 3, 0, 0));
		
		
		JLabel lblPrice = new JLabel("Price:");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.insets = new Insets(0, 0, 0, 5);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 0;
		gbc_lblPrice.weightx = 1;
		gbc_lblPrice.fill = GridBagConstraints.BOTH;
		priceSet.add(lblPrice);
		
		JPanel priceSpinnerPanel = new JPanel();
		priceSet.add(priceSpinnerPanel);
		priceSpinnerPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		minPriceSpinner = new JSpinner();
		minPriceSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int currentMinValue = (int) minPriceSpinner.getValue();
				int currentMaxValue = (int) maxPriceSpinner.getValue();
				if (currentMinValue > currentMaxValue) {
					maxPriceSpinner.setValue(currentMinValue + 1);
				}
				
			}
		});
		minPriceSpinner.setToolTipText("Select the minimum price.");
		minPriceSpinner.setModel(new SpinnerNumberModel(MIN_PRICE, MIN_PRICE, MAX_PRICE, 1));
		
		GridBagConstraints gbc_minPriceSpinner = new GridBagConstraints();
		gbc_minPriceSpinner.anchor = GridBagConstraints.WEST;
		gbc_minPriceSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_minPriceSpinner.gridx = 1;
		gbc_minPriceSpinner.gridy = 0;
		priceSpinnerPanel.add(minPriceSpinner);
		
		JLabel lblPriceInstrTo = new JLabel("to");
		lblPriceInstrTo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPriceInstrTo = new GridBagConstraints();
		gbc_lblPriceInstrTo.anchor = GridBagConstraints.WEST;
		gbc_lblPriceInstrTo.insets = new Insets(0, 0, 0, 5);
		gbc_lblPriceInstrTo.gridx = 2;
		gbc_lblPriceInstrTo.gridy = 0;
		priceSpinnerPanel.add(lblPriceInstrTo);
		
		
		GridBagConstraints gbc_maxPriceSpinner = new GridBagConstraints();
		gbc_maxPriceSpinner.anchor = GridBagConstraints.WEST;
		gbc_maxPriceSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_maxPriceSpinner.gridx = 3;
		gbc_maxPriceSpinner.gridy = 0;
		
		
		maxPriceSpinner = new JSpinner();
		maxPriceSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int currentMinValue = (int) minPriceSpinner.getValue();
				int currentMaxValue = (int) maxPriceSpinner.getValue();
				if (currentMinValue > currentMaxValue) {
					minPriceSpinner.setValue(currentMaxValue - 1);
				}
				
			}
		});
		maxPriceSpinner.setToolTipText("Select the maximum price.");
		maxPriceSpinner.setModel(new SpinnerNumberModel(MAX_PRICE, MIN_PRICE, MAX_PRICE, 1));
		priceSpinnerPanel.add(maxPriceSpinner);
		
		JLabel lblPriceInstr = new JLabel("Select the price range for wine in USD ($).");
		lblPriceInstr.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPriceInstr = new GridBagConstraints();
		gbc_lblPriceInstr.anchor = GridBagConstraints.CENTER;
		gbc_lblPriceInstr.gridx = 0;
		gbc_lblPriceInstr.gridy = 1;
		gbc_lblPriceInstr.weightx = 1;
		gbc_lblPriceInstr.fill = GridBagConstraints.HORIZONTAL;
		pricePanel.add(lblPriceInstr, gbc_lblPriceInstr);
		
		
		JToggleButton noPriceToggle = new JToggleButton("Include results with no price");
		noPriceToggle.setSelected(true);
		GridBagConstraints gbc_noPriceToggle = new GridBagConstraints();
		gbc_noPriceToggle.anchor = GridBagConstraints.WEST;
		gbc_noPriceToggle.gridx = 4;
		gbc_noPriceToggle.gridy = 0;
		priceSet.add(noPriceToggle, gbc_noPriceToggle);
		
		JSeparator sep4 = new JSeparator(SwingConstants.HORIZONTAL);
		sep.setName("Separator4");
		GridBagConstraints gbc_sep4 = new GridBagConstraints();
		sep.setPreferredSize(new Dimension(this.getWidth(),5));
		gbc_sep4.fill = GridBagConstraints.HORIZONTAL;
		gbc_sep4.gridx = 0;
		gbc_sep4.gridy = 8;
		gbc_sep4.weightx = 1;
		searchPanel.add(sep4, gbc_sep4);
		
		JPanel lastPanel = new JPanel();
		GridBagConstraints gbc_lastPanel = new GridBagConstraints();
		gbc_lastPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastPanel.insets = new Insets(5, 5, 5, 5);
		gbc_lastPanel.gridx = 0;
		gbc_lastPanel.gridy = 9;
		searchPanel.add(lastPanel, gbc_lastPanel);
		
		GridBagLayout gbl_lastPanel = new GridBagLayout();
		gbl_lastPanel.columnWidths = new int[]{0};
		gbl_lastPanel.rowHeights = new int[]{0};
		gbl_lastPanel.columnWeights = new double[]{0.0, 1};
		gbl_lastPanel.rowWeights = new double[]{Double.MIN_VALUE};
		lastPanel.setLayout(gbl_lastPanel);
		
		JLabel lblKeyword = new JLabel("Keyword:");
		lblKeyword.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblKeyword = new GridBagConstraints();
		gbc_lblKeyword.anchor = GridBagConstraints.WEST;
		gbc_lblKeyword.gridx = 0;
		gbc_lblKeyword.gridy = 0;
		gbc_lblKeyword.fill = GridBagConstraints.BOTH;
		lastPanel.add(lblKeyword, gbc_lblKeyword);
		
		GridBagConstraints gbc_keywordBox = new GridBagConstraints();
		gbc_keywordBox.anchor = GridBagConstraints.WEST;
		gbc_keywordBox.gridx = 1;
		gbc_keywordBox.weightx = 1;
		gbc_keywordBox.gridy = 0;
		gbc_keywordBox.fill = GridBagConstraints.BOTH;
		lastPanel.add(keywordBox, gbc_keywordBox);
		
		JLabel lblVariety = new JLabel("Variety:");
		lblVariety.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblVariety = new GridBagConstraints();
		gbc_lblVariety.anchor = GridBagConstraints.WEST;
		gbc_lblVariety.gridx = 0;
		gbc_lblVariety.gridy = 1;
		gbc_lblVariety.fill = GridBagConstraints.BOTH;
		lastPanel.add(lblVariety, gbc_lblVariety);
		
		GridBagConstraints gbc_varietyBox = new GridBagConstraints();
		gbc_varietyBox.anchor = GridBagConstraints.WEST;
		gbc_varietyBox.gridx = 1;
		gbc_varietyBox.gridy = 1;
		gbc_varietyBox.weightx = 1;
		gbc_varietyBox.fill = GridBagConstraints.BOTH;
		lastPanel.add(varietyBox, gbc_varietyBox);
		
		JSeparator sep5 = new JSeparator(SwingConstants.HORIZONTAL);
		sep.setName("Separator5");
		GridBagConstraints gbc_sep5 = new GridBagConstraints();
		sep.setPreferredSize(new Dimension(this.getWidth(),5));
		gbc_sep5.fill = GridBagConstraints.HORIZONTAL;
		gbc_sep5.gridx = 0;
		gbc_sep5.gridy = 10;
		gbc_sep5.weightx = 1;
		gbc_sep5.fill = GridBagConstraints.BOTH;
		searchPanel.add(sep5, gbc_sep5);
		
		JPanel searchButtonPanel = new JPanel();
		GridBagConstraints gbc_searchButtonPanel = new GridBagConstraints();
		gbc_searchButtonPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchButtonPanel.gridx = 0;
		gbc_searchButtonPanel.gridy = 11;
		searchPanel.add(searchButtonPanel, gbc_searchButtonPanel);
		
		GridBagLayout gbl_searchButtonPanel = new GridBagLayout();
		gbl_searchButtonPanel.columnWidths = new int[]{0};
		gbl_searchButtonPanel.rowHeights = new int[]{0};
		gbl_searchButtonPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_searchButtonPanel.rowWeights = new double[]{Double.MIN_VALUE};
		searchButtonPanel.setLayout(gbl_searchButtonPanel);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				wineSearch = new WineSearch();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime((Date) minVintageSpinner.getValue());
				int vintageMinimum = calendar.get(Calendar.YEAR);
				calendar.setTime((Date) maxVintageSpinner.getValue());
				int vintageMaximum = calendar.get(Calendar.YEAR);
				int priceMinimum = (int) minPriceSpinner.getValue();
				int priceMaximum = (int) maxPriceSpinner.getValue();
				int pointsMinimum = (int) minPointsSpinner.getValue();
				int pointsMaximum = (int) maxPointsSpinner.getValue();
				
				int countryId = -10;
				int regionId = -10;
				int provinceId = -10;
				int wineryId = -10;
				int varietyId = -10;
				int keywordId = -10;
				
				
				if (countryBox.getSelectedItem() != null) {
					countryId = ((Country) countryBox.getSelectedItem()).getId();
				}
				
				if (provinceBox.getSelectedItem() != null) {
					provinceId = ((Province) provinceBox.getSelectedItem()).getId();
				}
				
				if (wineryBox.getSelectedItem() != null) {
					wineryId = ((Winery) wineryBox.getSelectedItem()).getId();
				}
				
				if (regionBox.getSelectedItem() != null) {
					regionId = ((Region) regionBox.getSelectedItem()).getId();
				}
				
				if (keywordBox.getSelectedItem() != null) {
					keywordId = ((Keyword) keywordBox.getSelectedItem()).getId();
				}
				if (varietyBox.getSelectedItem() != null) {
					varietyId = ((Variety) varietyBox.getSelectedItem()).getId();
				}
				
				try {
					notEmpty = wineSearch.wineSearch(countryId, provinceId, regionId, wineryId, keywordId, 
							varietyId, vintageMinimum, vintageMaximum, priceMinimum, priceMaximum, pointsMinimum, pointsMaximum,
							noPriceToggle.isSelected(), noPointsToggle.isSelected(), noVintageToggle.isSelected());
				} catch (SQLException e) {
					e.printStackTrace();
					WineHunterApplication.searchWines(2, user); //go back to search screen
				}
				if(notEmpty == 2) {
					WineHunterApplication.searchWines(2, user);
				}
				else if(notEmpty == 0) {
					WineHunterApplication.searchWines(0, user);
				}
				else {
					
					WineHunterApplication.showWines(wineSearch, user); 
				}
			}
		});
		
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.anchor = GridBagConstraints.EAST;
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 0;
		searchButtonPanel.add(btnSearch, gbc_btnSearch);
		
		
	}
	
	/**
	 * initializes everything we need
	 */
	private void init() {
		try {
			LoadVariousLists.loadAllVarieties(varietyList);
			LoadVariousLists.loadAllKeywords(keywordList);
			LoadVariousLists.loadAllCountries(countryList);
			MIN_VINTAGE = LoadVariousLists.getMaxOrMinValue(1, false);
			MAX_VINTAGE = LoadVariousLists.getMaxOrMinValue(1, true);
			MIN_PRICE = LoadVariousLists.getMaxOrMinValue(2, false);
			MAX_PRICE = LoadVariousLists.getMaxOrMinValue(2, true);
			MIN_POINTS = LoadVariousLists.getMaxOrMinValue(3, false);
			MAX_POINTS = LoadVariousLists.getMaxOrMinValue(3, true);
		} catch (SQLException e) {
			e.printStackTrace();
			WineHunterApplication.splash(2);

			return;
		}
		
		countryRefresh = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				refreshLocation(e);
			}
		};
		
		provinceRefresh = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				refreshLocation(e);
			}
		};
		
		regionRefresh = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				refreshLocation(e);
			}
		};
		
		wineryRefresh = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				refreshLocation(e);
			}
		};
		
		provinceBox = new JComboBox<Province>();
		provinceBox.setName("provinceBox");
		provinceBox.setSelectedItem(null);
		provinceBox.setEnabled(false);
		
		regionBox = new JComboBox<Region>();
		regionBox.setName("regionBox");
		regionBox.setSelectedItem(null);
		regionBox.setEnabled(false);
		
		wineryBox.setName("wineryBox");
		wineryBox.setSelectedItem(null);
		wineryBox.setEnabled(false);
		
		countryBox.setModel(new DefaultComboBoxModel<Country>(countryList));
		countryBox.setSelectedItem(null);
		countryBox.setEnabled(true);
		countryBox.addItemListener(countryRefresh);
		countryBox.setName("countryBox");
		
		varietyBox.setModel(new DefaultComboBoxModel<Variety>(varietyList));
		varietyBox.setSelectedItem(null);
		varietyBox.setEnabled(true);
		varietyBox.setName("varietyBox");
		
		keywordBox.setModel(new DefaultComboBoxModel<Keyword>(keywordList));
		keywordBox.setSelectedItem(null);
		keywordBox.setEnabled(true);
		keywordBox.setName("keywordBox");
		
	}
	
	/**
	 * refreshes the location lists based on what is selected
	 */
	private void refreshLocation(ItemEvent e) {
		
		try {
			if (e.getSource().equals(countryBox)) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Country currentCountry = (Country) countryBox.getSelectedItem();
					LoadVariousLists.loadProvinceOptions(provinceList, currentCountry);
					provinceBox.setModel(new DefaultComboBoxModel<Province>(provinceList));
					provinceBox.setSelectedItem(null);
					provinceBox.setEnabled(true);
					provinceBox.addItemListener(provinceRefresh);
				} 
				else {
					provinceBox.removeItemListener(provinceRefresh);
					regionBox.removeItemListener(regionRefresh);
					wineryBox.removeItemListener(wineryRefresh);
					provinceBox.setSelectedItem(null);
					regionBox.setSelectedItem(null);
					wineryBox.setSelectedItem(null);
					provinceBox.setEnabled(false);
					regionBox.setEnabled(false);
					wineryBox.setEnabled(false);
				}
			}
			
			else if (e.getSource().equals(provinceBox)) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Province currentProvince = (Province) provinceBox.getSelectedItem();
					LoadVariousLists.loadRegionOptions(regionList, currentProvince);
					LoadVariousLists.loadWineryOptions(wineryList, currentProvince, null);
					regionBox.setModel(new DefaultComboBoxModel<Region>(regionList));
					regionBox.setSelectedItem(null);
					regionBox.setEnabled(true);
					wineryBox.setModel(new DefaultComboBoxModel<Winery>(wineryList));
					wineryBox.setSelectedItem(null);
					wineryBox.setEnabled(true);
					regionBox.addItemListener(regionRefresh);
					wineryBox.addItemListener(wineryRefresh);
				} 
				else {
					regionBox.removeItemListener(regionRefresh);
					wineryBox.removeItemListener(wineryRefresh);
					regionBox.setEnabled(false);
					wineryBox.setEnabled(false);
					regionBox.setSelectedItem(null);
					wineryBox.setSelectedItem(null);
				}
			}
			
			else if (e.getSource().equals(regionBox)) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Province currentProvince = (Province) provinceBox.getSelectedItem();
					Region currentRegion = (Region) regionBox.getSelectedItem();
					LoadVariousLists.loadWineryOptions(wineryList, currentProvince, currentRegion);
					wineryBox.setModel(new DefaultComboBoxModel<Winery>(wineryList));
					wineryBox.setSelectedItem(null);
					wineryBox.setEnabled(true);
				}
			}
			
			else if (e.getSource().equals(wineryBox)) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Winery currentWinery = (Winery) wineryBox.getSelectedItem();
					regionBox.removeItemListener(regionRefresh);
					regionBox.setSelectedItem(currentWinery.getRegion());
					regionBox.setEnabled(false);
				}
				else {
					Province currentProvince = (Province) provinceBox.getSelectedItem();
					LoadVariousLists.loadRegionOptions(regionList, currentProvince);
					regionBox.setModel(new DefaultComboBoxModel<Region>(regionList));
					regionBox.setSelectedItem(null);
					regionBox.setEnabled(true);
					
					regionBox.addItemListener(regionRefresh);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			WineHunterApplication.splash(2);

			return;
		}
		
		
	}
	
}
