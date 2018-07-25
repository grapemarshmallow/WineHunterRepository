package Core;

import java.awt.Component;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.*;

import UserFunctions.UserCreate;
import UserFunctions.UserLogin;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class WineHunterApplication {
	
	private static JFrame frmWinehunter;
	
	
	public JFrame getFrmWinehunter() {
		return WineHunterApplication.frmWinehunter;
	}

	public void setFrmWinehunter(JFrame frmWinehunter) {
		WineHunterApplication.frmWinehunter = frmWinehunter;
	}

	
	
	public static Core.Connect connection;
	public static UserFunctions.UserSession userSession;
	public static UserLogin userLogin;
	public static UserCreate userCreate;
	public static Toolbar toolbar;
	public static MainMenu splash;
	private static JPanel toolbarPanel;
	private static JPanel mainPanel;
	
	public final static int APPLICATION_WIDTH = 600;
	public final static int APPLICATION_HEIGHT = 400;
	
	
	public Core.Connect getConnection() {
		return WineHunterApplication.connection;
	}

	public void setConnection(Core.Connect connection) {
		WineHunterApplication.connection = connection;
	}

	public UserFunctions.UserSession getUserSession() {
		return WineHunterApplication.userSession;
	}

	public void setUserSession(UserFunctions.UserSession userSession) {
		WineHunterApplication.userSession = userSession;
	}

	public UserLogin getUserLogin() {
		return WineHunterApplication.userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		WineHunterApplication.userLogin = userLogin;
	}

	public UserCreate getUserCreate() {
		return WineHunterApplication.userCreate;
	}

	public void setUserCreate(UserCreate userCreate) {
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
			userSession = new UserFunctions.UserSession();
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
		frmWinehunter.setBounds(100, 100, APPLICATION_WIDTH, APPLICATION_HEIGHT);
		frmWinehunter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{APPLICATION_WIDTH, 0};
		gridBagLayout.rowHeights = new int[]{20, 120};
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
		userCreate = new UserCreate(attemptFlag);
	
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
		
		userLogin = new UserLogin(attemptFlag);
		mainPanel.setVisible(true);
		mainPanel.add(userLogin);
		
		frmWinehunter.pack();
		
		
	}
	
	/**
	* Draws splash page
	 */
	public static void splash() {
		
		WineHunterApplication.cleanPanel();
		splash = new MainMenu();
		mainPanel.setVisible(true);
		mainPanel.add(splash);
		
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
