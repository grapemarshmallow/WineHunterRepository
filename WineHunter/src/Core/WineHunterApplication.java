package Core;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.*;

import WineObjects.*;
import UserFunctions.GUI.AdminUserSearch;
import UserFunctions.GUI.EditTasterProfile;
import UserFunctions.GUI.ViewUserProfile;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class WineHunterApplication {
	
	private static JFrame frmWinehunter;
	
	
	public static JFrame getFrmWinehunter() {
		return WineHunterApplication.frmWinehunter;
	}
	
	public void setFrmWinehunter(JFrame frmWinehunter) {
		WineHunterApplication.frmWinehunter = frmWinehunter;
	}
	
	public static Formatting format;
	public static Core.Connect connection;
	public static UserFunctions.Logic.UserSession userSession;
	public static UserFunctions.GUI.UserLogin userLogin;
	public static UserFunctions.GUI.UserCreate userCreate;
	public static Toolbar toolbar;
	public static MainMenu splash;
	public static ViewUserProfile viewUserProfile;
	public static Search.Logic.WineSearch wineSearch; 
	public static Search.GUI.ViewWineResults viewWineResults; 
	public static Search.GUI.ViewWineSearch viewWineSearch; 
	public static Search.GUI.WinePage winePage; 
	public static Search.GUI.WriteWineReview wineReview; 
	public static AdminUserSearch adminUserSearch;
	public static GridBagConstraints gbc_Main; // default gbc for the main panel that we can use everywhere
	public static Dimension mainDimension; // default size for main panel content
	
	private static JPanel toolbarPanel;
	private static JPanel mainPanel;
	private static JSeparator sep;
	public static EditTasterProfile editTasterProfile; 
	
	public final static int APPLICATION_WIDTH = 900;
	public final static int APPLICATION_HEIGHT = 700;
	public final static int APPLICATION_MAIN_HEIGHT = 650;
	
	
	
	public Core.Connect getConnection() {
		return WineHunterApplication.connection;
	}
	
	public void setConnection(Core.Connect connection) {
		WineHunterApplication.connection = connection;
	}
	
	public UserFunctions.Logic.UserSession getUserSession() {
		return WineHunterApplication.userSession;
	}
	
	public void setUserSession(UserFunctions.Logic.UserSession userSession) {
		WineHunterApplication.userSession = userSession;
	}
	
	public UserFunctions.GUI.UserLogin getUserLogin() {
		return WineHunterApplication.userLogin;
	}
	
	public void setUserLogin(UserFunctions.GUI.UserLogin userLogin) {
		WineHunterApplication.userLogin = userLogin;
	}
	
	public UserFunctions.GUI.UserCreate getUserCreate() {
		return WineHunterApplication.userCreate;
	}
	
	public void setUserCreate(UserFunctions.GUI.UserCreate userCreate) {
		WineHunterApplication.userCreate = userCreate;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new WineHunterApplication();
					WineHunterApplication.frmWinehunter.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public WineHunterApplication() {
		
		try {
			
			connection = new Core.Connect();
			connection.init();
			userSession = new UserFunctions.Logic.UserSession();
			format = new Core.Formatting();
			initialize();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWinehunter = new JFrame();
		frmWinehunter.setTitle("WineHunter");
		frmWinehunter.setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
		frmWinehunter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{APPLICATION_WIDTH};
		frmWinehunter.getContentPane().setLayout(gridBagLayout);
		
		
		toolbarPanel = new JPanel();
		
		GridBagConstraints gbc_toolbarPanel = new GridBagConstraints();
		gbc_toolbarPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_toolbarPanel.gridx = 0;
		gbc_toolbarPanel.gridy = 0;
		gbc_toolbarPanel.weightx = 1;
		gbc_toolbarPanel.fill = GridBagConstraints.BOTH;
		toolbarPanel.setPreferredSize(new Dimension(APPLICATION_WIDTH, 42));
		toolbarPanel.setMaximumSize(new Dimension(APPLICATION_WIDTH, 42));
		frmWinehunter.getContentPane().add(toolbarPanel, gbc_toolbarPanel);
		
		
		toolbarPanel.setLayout(new GridLayout(1, 1, 0, 0));
		
		sep = new JSeparator(SwingConstants.HORIZONTAL);
		sep.setName("toolbar/main panel separater");
		GridBagConstraints gbc_Sep = new GridBagConstraints();
		sep.setMinimumSize(new Dimension(APPLICATION_WIDTH,5));
		gbc_Sep.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sep.weightx = 1;
		gbc_Sep.gridy = 1;
		
		frmWinehunter.getContentPane().add(sep, gbc_Sep);
		
		mainPanel = new JPanel();
		
		gbc_Main = new GridBagConstraints();
		gbc_Main.anchor = GridBagConstraints.NORTH;
		gbc_Main.gridx = 0;
		gbc_Main.gridy = 2;
		gbc_Main.weightx = 1;
		gbc_Main.weighty = 1;
		gbc_Main.fill = GridBagConstraints.BOTH;
		mainDimension = new Dimension(APPLICATION_WIDTH, APPLICATION_MAIN_HEIGHT);
		mainPanel.setPreferredSize(mainDimension); 
		mainPanel.setMaximumSize(mainDimension);
		frmWinehunter.getContentPane().add(mainPanel, gbc_Main);
		
		
		WineHunterApplication.toolbar();
		WineHunterApplication.userLogin(0);
		
		
	}
	
	/**
	 * Draws user login prompt with optional additional text.
	 * @param attemptFlag
	 */
	public static void userCreation(int attemptFlag) {
		
		WineHunterApplication.cleanPanel();
		userCreate = new UserFunctions.GUI.UserCreate(attemptFlag);
		userCreate.setName("userCreate");
		userCreate.setPreferredSize(mainDimension); 
		userCreate.setMaximumSize(getMainPanelDimensions());
		mainPanel.setVisible(true);
		mainPanel.add(userCreate);
		
		
		frmWinehunter.pack();
		
		
	}
	
	/**
	 * Draw wine search page
	 */
	public static void searchWines(int empty) {
		//draw wine search page
		WineHunterApplication.cleanPanel();
		
		viewWineSearch = new Search.GUI.ViewWineSearch(empty);
		
		wineSearch = new Search.Logic.WineSearch();
		viewWineSearch.setName("wineSearch");
		viewWineSearch.setPreferredSize(mainDimension); 
		viewWineSearch.setMaximumSize(getMainPanelDimensions());
		mainPanel.setVisible(true);
		mainPanel.add(viewWineSearch);
		
		
		frmWinehunter.pack();
		
		
	}
	
	/**
	 * Draw wine results page
	 */
	public static void showWines(String[][] data, String[] columnNames, int[] wineIDs) {
		
		WineHunterApplication.cleanPanel();
		
		viewWineResults = new Search.GUI.ViewWineResults(data, columnNames, wineIDs); 
		viewWineResults.setName("viewWineResults");
		viewWineResults.setPreferredSize(mainDimension); 
		viewWineResults.setMaximumSize(getMainPanelDimensions());
		mainPanel.setVisible(true);
		mainPanel.add(viewWineResults);
		
		
		frmWinehunter.pack();
		
		
	}
	
	/**
	 * Draw specific wine page
	 */
	public static void viewWine(int wineID, int userID) {
		
		WineHunterApplication.cleanPanel();
		
		winePage = new Search.GUI.WinePage(wineID, userID); 
		winePage.setName("winePage");
		mainPanel.setVisible(true);
		mainPanel.add(winePage);
		
		
		frmWinehunter.pack();
		
		
	}
	
	/**
	 * Draw page to write/edit a wine review
	 * @param wineID
	 * @param userID
	 * @param score - current score for this user/wine
	 * @param wineName
	 * @param notes
	 * @param invalid
	 */
	public static void writeReview(int wineID, int userID, int score, String wineName, String notes, int invalid) {
		
		WineHunterApplication.cleanPanel();
		
		wineReview = new Search.GUI.WriteWineReview(wineID, userID, score, wineName, notes, invalid); 
		wineReview.setName("wineReview");
		mainPanel.setVisible(true);
		mainPanel.add(wineReview);
		
		
		frmWinehunter.pack();
		
		
	}
	/**
	 * quit safely
	 */
	public static void quit() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			System.exit(1);
		}
	}
	
	/**
	 * cleans main panel to swap components
	 */
	public static void cleanPanel() {
		Component[] components = mainPanel.getComponents();
		
		//testing

		/**
		System.out.println("\nFrme size: " + frmWinehunter.getSize());
		System.out.println("\nToolbar size: " + toolbar.getSize());
		System.out.println("\nMain panel size: " + mainPanel.getSize());

		for (int i = 0; i < components.length; ++i) {
			System.out.print("\n\t" + components[i].getName() + " size: " + components[i].getSize());
			writeComponents((JComponent) components[i], 1);
		}*/

		
		//writeLocations();
		
		
		
		for (int i = 0; i < components.length; ++i) {
			mainPanel.remove(components[i]);
		}
		
		mainPanel.repaint();
		WineHunterApplication.toolbarReload();
		
	}
	
	/**
	 */
	public static void writeLocations() {
		Component[] components = frmWinehunter.getComponents();
		
		System.out.println("\n\nRELOAD\n\nFRAME: " + frmWinehunter.getSize());
		
		for (int i = 0; i < components.length; ++i) {
			System.out.print("\n\t" + components[i].getName() + " size: " + components[i].getSize());
			writeComponents((JComponent) components[i], 1);
		}
		
	}
	
	public static void writeComponents(JComponent component, int depth) {
		Component[] components = component.getComponents();
		for (int i = 0; i < components.length; ++i) {
			System.out.print("\n");
			for (int j = 0; j <= depth; ++j) {
				System.out.print("\t");
			}
			System.out.print(components[i].getName() + " size: " + components[i].getSize());
			writeComponents((JComponent) components[i], depth + 1);
		}
	}
	
	/**
	 * cleans toolbar to reload the buttons
	 */
	public static void cleanToolbar() {
		Component[] components = toolbarPanel.getComponents();
		
		for (int i = 0; i < components.length; ++i) {
			toolbarPanel.remove(components[i]);
		}
	}
	
	/**
	 * Draws user login prompt with optional additional text.
	 * @param attemptFlag
	 */
	public static void userLogin(int attemptFlag) {
		
		WineHunterApplication.cleanPanel();
		
		userLogin = new UserFunctions.GUI.UserLogin(attemptFlag);
		userLogin.setName("User Login");
		userLogin.setPreferredSize(mainDimension); 
		userLogin.setMaximumSize(getMainPanelDimensions());
		mainPanel.setVisible(true);
		mainPanel.add(userLogin);
		
		frmWinehunter.pack();
		
		
	}
	
	/**
	 * Draws splash page
	 */
	public static void splash(int subsequent) {
		
		WineHunterApplication.cleanPanel();
		splash = new MainMenu(subsequent);
		splash.setName("Splash");
		splash.setPreferredSize(mainDimension); 
		splash.setMaximumSize(getMainPanelDimensions());
		mainPanel.setVisible(true);
		mainPanel.add(splash);
		
		frmWinehunter.pack();
		
		
	}
	
	public static void viewUserProfile(User user, int subsequent, int editMode) {
		WineHunterApplication.cleanPanel();
		
		viewUserProfile = new ViewUserProfile(user, subsequent, editMode);
		viewUserProfile.setPreferredSize(mainDimension); 
		viewUserProfile.setMaximumSize(getMainPanelDimensions());
		
		viewUserProfile.setName("User Profile");
		
		mainPanel.setVisible(true);
		mainPanel.add(viewUserProfile);
		
		
		frmWinehunter.pack();
		
	}
	
	public static void adminUserSearch(int subsequent) {
		WineHunterApplication.cleanPanel();
		
		adminUserSearch = new AdminUserSearch(subsequent);
		adminUserSearch.setName("Admin User Search");
		adminUserSearch.setPreferredSize(mainDimension); 
		adminUserSearch.setMaximumSize(getMainPanelDimensions());
		mainPanel.setVisible(true);
		mainPanel.add(adminUserSearch);
		
		frmWinehunter.pack();
		
	}
	
	public static void editTasterProfile(User user) {
		WineHunterApplication.cleanPanel();
		
		editTasterProfile = new EditTasterProfile(user);
		editTasterProfile.setName("editTasterProfile");
		editTasterProfile.setPreferredSize(mainDimension); 
		editTasterProfile.setMaximumSize(getMainPanelDimensions());
		mainPanel.setVisible(true);
		mainPanel.add(editTasterProfile);
		
		frmWinehunter.pack();
		
	}
	
	
	/**
	 * Reloads main toolbar
	 */
	public static void toolbarReload() {
		
		WineHunterApplication.cleanToolbar();
		toolbarPanel.setVisible(true);
		WineHunterApplication.toolbar();
		frmWinehunter.pack();
		
		
	}
	
	/**
	 * Draws main toolbar
	 */
	public static void toolbar() {
		
		toolbar = new Toolbar();
		toolbar.setName("The toolbar");
		GridBagConstraints gbc_toolbarPanel = new GridBagConstraints();
		gbc_toolbarPanel.anchor = GridBagConstraints.NORTHEAST;
		gbc_toolbarPanel.gridx = 0;
		gbc_toolbarPanel.gridy = 0;
		gbc_toolbarPanel.weightx = 1;
		gbc_toolbarPanel.fill = GridBagConstraints.BOTH;
		toolbarPanel.setPreferredSize(new Dimension(APPLICATION_WIDTH, 42));
		toolbarPanel.add(toolbar, gbc_toolbarPanel);
		toolbarPanel.setVisible(true);
		frmWinehunter.pack();
		
		
	}
	
	public static Dimension getMainPanelDimensions() {
		return new Dimension(mainPanel.getWidth(), mainPanel.getHeight());
	}
	
	public static GridBagConstraints getGbc_Main() {
		return gbc_Main;
	}
	
	public static void setGbc_Main(GridBagConstraints gbc_Main) {
		WineHunterApplication.gbc_Main = gbc_Main;
	}
	
}
