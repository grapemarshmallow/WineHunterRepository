package Core;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.*;

import Core.*;
import WineObjects.*;
import Search.*;
import UserFunctions.GUI.ViewUserProfile;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class WineHunterApplication {
	
	private static JFrame frmWinehunter;
	
	
		return WineHunterApplication.frmWinehunter;
	}

	public void setFrmWinehunter(JFrame frmWinehunter) {
		WineHunterApplication.frmWinehunter = frmWinehunter;
	}

	public static Core.Connect connection;
	public static UserFunctions.Logic.UserSession userSession;
	public static UserFunctions.GUI.UserLogin userLogin;
	public static UserFunctions.GUI.UserCreate userCreate;
	public static Toolbar toolbar;
	public static MainMenu splash;
	public static ViewUserProfile viewUserProfile;
	private static JPanel toolbarPanel;
	private static JPanel mainPanel;
	
	public final static int APPLICATION_WIDTH = 1000;
	public final static int APPLICATION_HEIGHT = 650;
	
	
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
		gridBagLayout.columnWidths = new int[]{APPLICATION_WIDTH, 0};
		gridBagLayout.rowHeights = new int[]{20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		frmWinehunter.getContentPane().setLayout(gridBagLayout);
		
		
		toolbarPanel = new JPanel();
		
		GridBagConstraints gbc_toolbarPanel = new GridBagConstraints();
		gbc_toolbarPanel.anchor = GridBagConstraints.NORTHEAST;
		gbc_toolbarPanel.gridx = 0;
		gbc_toolbarPanel.gridy = 0;
		frmWinehunter.getContentPane().add(toolbarPanel, gbc_toolbarPanel);

		toolbarPanel.setLayout(new GridLayout(1, 1, 0, 0));
		
		mainPanel = new JPanel();
		
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.anchor = GridBagConstraints.CENTER;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 1;
		frmWinehunter.getContentPane().add(mainPanel, gbc_mainPanel);
		
		
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
	
		mainPanel.setVisible(true);
		mainPanel.add(userCreate);
		
		
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
		
		for (int i = 0; i < components.length; ++i) {
			mainPanel.remove(components[i]);
		}
		
		WineHunterApplication.toolbarReload();
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
		mainPanel.setVisible(true);
		mainPanel.add(userLogin);
		
		frmWinehunter.pack();
		
		
	}
	
	/**
	* Draws splash page
	 */
		
		WineHunterApplication.cleanPanel();
		mainPanel.setVisible(true);
		mainPanel.add(splash);
		
		frmWinehunter.pack();
		
		
	}
	
		WineHunterApplication.cleanPanel();
		
		viewUserProfile = new ViewUserProfile(user, subsequent);
		mainPanel.setVisible(true);
		mainPanel.add(viewUserProfile);
		
		
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
		toolbarPanel.add(toolbar);
		toolbarPanel.setVisible(true);
		frmWinehunter.pack();
		
		
	}
	
}
