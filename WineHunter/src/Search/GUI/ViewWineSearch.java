package Search.GUI;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import java.awt.GridBagLayout;
import java.awt.Dimension;
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

public class ViewWineSearch extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7966165569110154144L;
	private ItemListener countryRefresh;
	private ItemListener provinceRefresh;
	private ItemListener regionRefresh;
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
		gbc_lblSearchPrompt.insets = new Insets(15, 15, 15, 15);
		gbc_lblSearchPrompt.anchor = GridBagConstraints.NORTH;
		gbc_lblSearchPrompt.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSearchPrompt.gridx = 0;
		gbc_lblSearchPrompt.gridy = 0;
		gbc_lblSearchPrompt.weightx = 1;
		labelPanel.add(lblSearchPrompt, gbc_lblSearchPrompt);
		
		JLabel lblempty = new JLabel();
		lblempty.setHorizontalAlignment(SwingConstants.CENTER);
		lblempty.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblempty = new GridBagConstraints();
		gbc_lblempty.insets = new Insets(15, 15, 15, 15);
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
		gbc_vintagePanel.fill = GridBagConstraints.BOTH;
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
		gbc_vintageSet.fill = GridBagConstraints.BOTH;
		gbc_vintageSet.insets = new Insets(5, 5, 5, 5);
		gbc_vintageSet.gridx = 0;
		gbc_vintageSet.gridy = 0;
		gbc_vintageSet.weightx = 1;
		vintagePanel.add(vintageSet, gbc_vintageSet);
		
		GridBagLayout gbl_vintageSet = new GridBagLayout();
		gbl_vintageSet.columnWidths = new int[]{0};
		gbl_vintageSet.rowHeights = new int[]{0};
		gbl_vintageSet.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_vintageSet.rowWeights = new double[]{Double.MIN_VALUE};
		vintageSet.setLayout(gbl_vintageSet);
		
		JLabel lblVintage = new JLabel("Vintage: ");
		GridBagConstraints gbc_lblVintage = new GridBagConstraints();
		gbc_lblVintage.anchor = GridBagConstraints.WEST;
		gbc_lblVintage.insets = new Insets(0, 0, 0, 5);
		gbc_lblVintage.gridx = 0;
		gbc_lblVintage.gridy = 0;
		vintageSet.add(lblVintage, gbc_lblVintage);
		
		minVintageSpinner = new JSpinner();
		GridBagConstraints gbc_minVintageSpinner = new GridBagConstraints();
		gbc_minVintageSpinner.anchor = GridBagConstraints.NORTHWEST;
		gbc_minVintageSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_minVintageSpinner.gridx = 1;
		gbc_minVintageSpinner.gridy = 0;
		vintageSet.add(minVintageSpinner, gbc_minVintageSpinner);
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
		
		JLabel lblVintageInstrTo = new JLabel("to");
		GridBagConstraints gbc_lblVintageInstrTo = new GridBagConstraints();
		gbc_lblVintageInstrTo.anchor = GridBagConstraints.WEST;
		gbc_lblVintageInstrTo.insets = new Insets(0, 0, 0, 5);
		gbc_lblVintageInstrTo.gridx = 2;
		gbc_lblVintageInstrTo.gridy = 0;
		vintageSet.add(lblVintageInstrTo, gbc_lblVintageInstrTo);
		
		maxVintageSpinner = new JSpinner();
		GridBagConstraints gbc_maxVintageSpinner = new GridBagConstraints();
		gbc_maxVintageSpinner.anchor = GridBagConstraints.NORTHWEST;
		gbc_maxVintageSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_maxVintageSpinner.gridx = 3;
		gbc_maxVintageSpinner.gridy = 0;
		vintageSet.add(maxVintageSpinner, gbc_maxVintageSpinner);
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
		
		JLabel lblVintageInstr = new JLabel("Choose the desired vintage range in years.");
		lblVintageInstr.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblVintageInstr = new GridBagConstraints();
		gbc_lblVintageInstr.anchor = GridBagConstraints.CENTER;
		gbc_lblVintageInstr.gridx = 0;
		gbc_lblVintageInstr.gridy = 1;
		gbc_lblVintageInstr.weightx = 1;
		gbc_lblVintageInstr.fill = GridBagConstraints.BOTH;
		vintagePanel.add(lblVintageInstr, gbc_lblVintageInstr);
		
		JToggleButton noVintageToggle = new JToggleButton("Include results with no vintage");
		noVintageToggle.setSelected(true);
		GridBagConstraints gbc_noVintageToggle = new GridBagConstraints();
		gbc_noVintageToggle.anchor = GridBagConstraints.NORTHWEST;
		gbc_noVintageToggle.gridx = 4;
		gbc_noVintageToggle.gridy = 0;
		vintageSet.add(noVintageToggle, gbc_noVintageToggle);
		
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
		gbc_locationLabel.fill = GridBagConstraints.BOTH;
		gbc_locationLabel.insets = new Insets(5, 5, 5, 5);
		gbc_locationLabel.gridx = 0;
		gbc_locationLabel.gridy = 2;
		searchPanel.add(locationLabel, gbc_locationLabel);
		
		JPanel locationPanel = new JPanel();
		GridBagConstraints gbc_locationPanel = new GridBagConstraints();
		gbc_locationPanel.fill = GridBagConstraints.BOTH;
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
		gbc_countryBox.fill = GridBagConstraints.BOTH;
		gbc_countryBox.insets = new Insets(5, 5, 5, 5);
		gbc_countryBox.gridx = 1;
		gbc_countryBox.gridy = 0;
		gbc_countryBox.weightx = 1;
		
		GridBagConstraints gbc_countryBoxLabel = new GridBagConstraints();
		gbc_countryBoxLabel.fill = GridBagConstraints.BOTH;
		gbc_countryBoxLabel.insets = new Insets(5, 5, 5, 5);
		gbc_countryBoxLabel.gridx = 0;
		gbc_countryBoxLabel.gridy = 0;
		
		JLabel countryBoxLabel = new JLabel("Country:");
		countryBoxLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		countryBoxLabel.setName("countryBoxLabel");
		
		locationPanel.add(countryBoxLabel, gbc_countryBoxLabel);
		locationPanel.add(countryBox, gbc_countryBox);
		
		GridBagConstraints gbc_provinceBox = new GridBagConstraints();
		gbc_provinceBox.fill = GridBagConstraints.BOTH;
		gbc_provinceBox.insets = new Insets(5, 5, 5, 5);
		gbc_provinceBox.gridx = 1;
		gbc_provinceBox.gridy = 1;
		gbc_provinceBox.weightx = 1;
		
		GridBagConstraints gbc_provinceBoxLabel = new GridBagConstraints();
		gbc_provinceBoxLabel.fill = GridBagConstraints.BOTH;
		gbc_provinceBoxLabel.insets = new Insets(5, 5, 5, 5);
		gbc_provinceBoxLabel.gridx = 0;
		gbc_provinceBoxLabel.gridy = 1;
		
		JLabel provinceBoxLabel = new JLabel("Province:");
		provinceBoxLabel.setName("provinceBoxLabel");
		provinceBoxLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		locationPanel.add(provinceBoxLabel, gbc_provinceBoxLabel);
		locationPanel.add(provinceBox, gbc_provinceBox);
		
		GridBagConstraints gbc_regionText = new GridBagConstraints();
		gbc_regionText.fill = GridBagConstraints.BOTH;
		gbc_regionText.insets = new Insets(5, 5, 5, 5);
		gbc_regionText.gridx = 1;
		gbc_regionText.gridy = 2;
		gbc_regionText.weightx = 1;
		
		GridBagConstraints gbc_regionTextLabel = new GridBagConstraints();
		gbc_regionTextLabel.fill = GridBagConstraints.BOTH;
		gbc_regionTextLabel.insets = new Insets(5, 5, 5, 5);
		gbc_regionTextLabel.gridx = 0;
		gbc_regionTextLabel.gridy = 2;
		
		JLabel regionBoxLabel = new JLabel("Region:");
		regionBoxLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		regionBoxLabel.setName("regionBoxLabel");
		locationPanel.add(regionBoxLabel, gbc_regionTextLabel);
		
		locationPanel.add(regionBox, gbc_regionText);
		
		GridBagConstraints gbc_wineryText = new GridBagConstraints();
		gbc_wineryText.fill = GridBagConstraints.BOTH;
		gbc_wineryText.insets = new Insets(5, 5, 5, 5);
		gbc_wineryText.gridx = 1;
		gbc_wineryText.gridy = 3;
		gbc_wineryText.weightx = 1;
		
		GridBagConstraints gbc_wineryTextLabel = new GridBagConstraints();
		gbc_wineryTextLabel.fill = GridBagConstraints.BOTH;
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
		gbc_pointsPanel.fill = GridBagConstraints.BOTH;
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
		gbc_pointsSet.fill = GridBagConstraints.BOTH;
		gbc_pointsSet.insets = new Insets(5, 5, 5, 5);
		gbc_pointsSet.gridx = 0;
		gbc_pointsSet.gridy = 0;
		pointsPanel.add(pointsSet, gbc_pointsSet);
		
		GridBagLayout gbl_pointsSet = new GridBagLayout();
		gbl_pointsSet.columnWidths = new int[]{0};
		gbl_pointsSet.rowHeights = new int[]{0};
		gbl_pointsSet.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_pointsSet.rowWeights = new double[]{Double.MIN_VALUE};
		pointsSet.setLayout(gbl_pointsSet);
		
		JLabel lblPoints = new JLabel("Score: ");
		GridBagConstraints gbc_lblPoints = new GridBagConstraints();
		gbc_lblPoints.anchor = GridBagConstraints.WEST;
		gbc_lblPoints.insets = new Insets(0, 0, 0, 5);
		gbc_lblPoints.gridx = 0;
		gbc_lblPoints.gridy = 0;
		pointsSet.add(lblPoints, gbc_lblPoints);
		
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
		GridBagConstraints gbc_minPointsSpinner = new GridBagConstraints();
		gbc_minPointsSpinner.anchor = GridBagConstraints.NORTHWEST;
		gbc_minPointsSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_minPointsSpinner.gridx = 1;
		gbc_minPointsSpinner.gridy = 0;
		pointsSet.add(minPointsSpinner, gbc_minPointsSpinner);
		
		JLabel lblPointsInstrTo = new JLabel("to");
		GridBagConstraints gbc_lblPointsInstrTo = new GridBagConstraints();
		gbc_lblPointsInstrTo.anchor = GridBagConstraints.WEST;
		gbc_lblPointsInstrTo.insets = new Insets(0, 0, 0, 5);
		gbc_lblPointsInstrTo.gridx = 2;
		gbc_lblPointsInstrTo.gridy = 0;
		pointsSet.add(lblPointsInstrTo, gbc_lblPointsInstrTo);
		
		
		GridBagConstraints gbc_maxPointsSpinner = new GridBagConstraints();
		gbc_maxPointsSpinner.anchor = GridBagConstraints.NORTHWEST;
		gbc_maxPointsSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_maxPointsSpinner.gridx = 3;
		gbc_maxPointsSpinner.gridy = 0;
		
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
		pointsSet.add(maxPointsSpinner, gbc_maxPointsSpinner);
		
		JLabel lblPointsInstr = new JLabel("Choose the Wine Enthusiast review score range in points (0 - 100).");
		lblPointsInstr.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPointsInstr = new GridBagConstraints();
		gbc_lblPointsInstr.anchor = GridBagConstraints.CENTER;
		gbc_lblPointsInstr.gridx = 0;
		gbc_lblPointsInstr.gridy = 1;
		gbc_lblPointsInstr.weightx = 1;
		gbc_lblPointsInstr.fill = GridBagConstraints.BOTH;
		pointsPanel.add(lblPointsInstr, gbc_lblPointsInstr);
		
		JToggleButton noPointsToggle = new JToggleButton("Include results with no score");
		noPointsToggle.setSelected(true);
		GridBagConstraints gbc_noPointsToggle = new GridBagConstraints();
		gbc_noPointsToggle.anchor = GridBagConstraints.NORTHWEST;
		gbc_noPointsToggle.gridx = 4;
		gbc_noPointsToggle.gridy = 0;
		pointsSet.add(noPointsToggle, gbc_noPointsToggle);
		
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
		gbc_pricePanel.fill = GridBagConstraints.BOTH;
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
		gbc_priceSet.fill = GridBagConstraints.BOTH;
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
		priceSet.setLayout(gbl_priceSet);
		
		JLabel lblPrice = new JLabel("Price:");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.insets = new Insets(0, 0, 0, 5);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 0;
		gbc_lblPrice.weightx = 1;
		gbc_lblPrice.fill = GridBagConstraints.BOTH;
		priceSet.add(lblPrice, gbc_lblPrice);
		
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
		
		GridBagConstraints gbc_minPriceSpinner = new GridBagConstraints();
		gbc_minPriceSpinner.anchor = GridBagConstraints.NORTHWEST;
		gbc_minPriceSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_minPriceSpinner.gridx = 1;
		gbc_minPriceSpinner.gridy = 0;
		priceSet.add(minPriceSpinner, gbc_minPriceSpinner);
		
		JLabel lblPriceInstrTo = new JLabel("to");
		GridBagConstraints gbc_lblPriceInstrTo = new GridBagConstraints();
		gbc_lblPriceInstrTo.anchor = GridBagConstraints.WEST;
		gbc_lblPriceInstrTo.insets = new Insets(0, 0, 0, 5);
		gbc_lblPriceInstrTo.gridx = 2;
		gbc_lblPriceInstrTo.gridy = 0;
		priceSet.add(lblPriceInstrTo, gbc_lblPriceInstrTo);
		
		
		GridBagConstraints gbc_maxPriceSpinner = new GridBagConstraints();
		gbc_maxPriceSpinner.anchor = GridBagConstraints.NORTHWEST;
		gbc_maxPriceSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_maxPriceSpinner.gridx = 3;
		gbc_maxPriceSpinner.gridy = 0;
		
		
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
		priceSet.add(maxPriceSpinner, gbc_maxPriceSpinner);
		
		JLabel lblPriceInstr = new JLabel("Select the price range for wine in USD ($).");
		lblPriceInstr.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPriceInstr = new GridBagConstraints();
		gbc_lblPriceInstr.anchor = GridBagConstraints.CENTER;
		gbc_lblPriceInstr.gridx = 0;
		gbc_lblPriceInstr.gridy = 1;
		gbc_lblPriceInstr.weightx = 1;
		gbc_lblPriceInstr.fill = GridBagConstraints.BOTH;
		pricePanel.add(lblPriceInstr, gbc_lblPriceInstr);
		
		
		JToggleButton noPriceToggle = new JToggleButton("Include results with no price");
		noPriceToggle.setSelected(true);
		GridBagConstraints gbc_noPriceToggle = new GridBagConstraints();
		gbc_noPriceToggle.anchor = GridBagConstraints.NORTHWEST;
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
		gbc_lastPanel.fill = GridBagConstraints.BOTH;
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
		gbc_searchButtonPanel.fill = GridBagConstraints.BOTH;
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
				
				WineHunterApplication.wineSearch = new WineSearch();
				int vintageMinimum = (int) minVintageSpinner.getValue();
				int vintageMaximum = (int) maxVintageSpinner.getValue();
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
					notEmpty = WineHunterApplication.wineSearch.wineSearch(countryId, provinceId, regionId, wineryId, keywordId, 
							varietyId, vintageMinimum, vintageMaximum, priceMinimum, priceMaximum, pointsMinimum, pointsMaximum,
							noPriceToggle.isSelected(), noPointsToggle.isSelected(), noVintageToggle.isSelected());
				} catch (SQLException e) {
					e.printStackTrace();
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
					int[] wineIDs = WineHunterApplication.wineSearch.getWineIDs();
					
					WineHunterApplication.showWines(data,columnNames, wineIDs); 
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
				} 
				else {
					regionBox.removeItemListener(regionRefresh);
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
		} catch (SQLException e1) {
			e1.printStackTrace();
			WineHunterApplication.splash(2);

			return;
		}
		
		
	}
	
}
