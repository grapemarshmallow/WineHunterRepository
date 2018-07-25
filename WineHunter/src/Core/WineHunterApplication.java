package Core;

import java.awt.Component;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.*;

import UserFunctions.UserCreate;
import UserFunctions.UserLogin;

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
			initialize();
			connection = new Core.Connect();
			connection.init();
			userSession = new UserFunctions.UserSession();
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
		frmWinehunter.setBounds(100, 100, 450, 300);
		frmWinehunter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		WineHunterApplication.userLogin(0);
	
	}
	
	/**
	 * Draws user login prompt with optional additional text.
	 * @param attemptFlag
	 */
	public static void userCreation(int attemptFlag) {
		
		userCreate = new UserCreate(attemptFlag);
	
		frmWinehunter.setVisible(true);
		frmWinehunter.setContentPane(userCreate);
		
		frmWinehunter.pack();
		
		
	}

	/**
	 * 
	 */
	public static void cleanFrame() {
		Component[] components = frmWinehunter.getComponents();
		
		for (int i = 0; i < components.length; ++i) {
			System.out.println(components[i].toString());
			frmWinehunter.remove(components[i]);
		}
	}
	
	/**
	 * Draws user login prompt with optional additional text.
	 * @param attemptFlag
	 */
	public static void userLogin(int attemptFlag) {
		
		userLogin = new UserLogin(attemptFlag);
		frmWinehunter.setVisible(true);
		
		frmWinehunter.setContentPane(userLogin);
		frmWinehunter.pack();
		
		
	}
	
}
