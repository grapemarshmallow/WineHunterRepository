package UserFunctions.GUI;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Core.WineHunterApplication;


public class UserLogin extends JPanel {
	

	// fields
	private static final long serialVersionUID = -8493859429340869685L;
	private JTextField username;
	private JPasswordField passwordField;
	
	public JTextField getUsername() {
		return this.username;
	}


	public JPasswordField getPasswordField() {
		return this.passwordField;
	}

	
	/**
	 * Create the frame.
	 * @param set to 0 if first attempt, 1 for a subsequent attempt, 2 for new user creation
	 */

	public UserLogin(int attemptFlag) {
		

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0};
		
		
		JLabel lblNewLabel = new JLabel("Welcome to WineHunter!");
		lblNewLabel.setFont(WineHunterApplication.format.getHeadingFont());
		lblNewLabel.setName("lblNewLabel");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(15, 15, 15, 15);
		gbc_lblNewLabel.anchor = GridBagConstraints.CENTER;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		
		if (attemptFlag != 0) {
			JPanel subsPanel = new JPanel();
			subsPanel.setName("subsPanel");
			GridBagConstraints gbc_subsPanel = new GridBagConstraints();
			gbc_subsPanel.insets = new Insets(0, 0, 5, 0);
			gbc_subsPanel.fill = GridBagConstraints.BOTH;
			gbc_subsPanel.gridx = 0;
			gbc_subsPanel.gridy = 2;
			this.add(subsPanel, gbc_subsPanel);
			JLabel reprompt = new JLabel();
			reprompt.setName("reprompt");
			reprompt.setHorizontalAlignment(SwingConstants.CENTER);
			reprompt.setFont(WineHunterApplication.format.getSubheadingFont2Base());
			GridBagConstraints gbc_lblReprompt = new GridBagConstraints();
			gbc_lblReprompt.insets = new Insets(15, 15, 15, 15);
			gbc_lblReprompt.anchor = GridBagConstraints.CENTER;
			gbc_lblReprompt.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblReprompt.gridx = 0;
			gbc_lblReprompt.gridy = 2;
			if (attemptFlag == 1) {
				reprompt.setText("Login failed. Check your username and password.");
				
			}
			else if (attemptFlag == 2) {
				reprompt.setText("User creation successful! Now, you can login with your new credentials.");
			}
			subsPanel.add(reprompt, gbc_lblReprompt);
		}
		
		
		this.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		this.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0};
		gbl_panel.rowHeights = new int[] {0};
		panel.setLayout(gbl_panel);
		
		JLabel lblUsername = new JLabel("Username: ");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		panel.add(lblUsername, gbc_lblUsername);
		
		username = new JTextField();
		username.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		username.setToolTipText("Enter your username.");
		GridBagConstraints gbc_txtEnterYourUsername = new GridBagConstraints();
		gbc_txtEnterYourUsername.anchor = GridBagConstraints.WEST;
		gbc_txtEnterYourUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtEnterYourUsername.gridx = 1;
		gbc_txtEnterYourUsername.gridy = 0;
		panel.add(username, gbc_txtEnterYourUsername);
		username.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblPassword = new JLabel("Password: ");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		panel.add(lblPassword, gbc_lblPassword);
		passwordField.setToolTipText("Enter your password.");
		passwordField.setActionCommand("password");
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		panel.add(passwordField, gbc_passwordField);
		
		JPanel panelLogin = new JPanel();
		GridBagConstraints gbc_loginPanel = new GridBagConstraints();
		gbc_loginPanel.fill = GridBagConstraints.BOTH;
		gbc_loginPanel.gridx = 0;
		gbc_loginPanel.gridy = 5;
		this.add(panelLogin, gbc_loginPanel);
		GridBagLayout gbl_panelLogin = new GridBagLayout();
		gbl_panelLogin.columnWidths = new int[] {0};
		gbl_panelLogin.rowHeights = new int[]{0};
		gbl_panelLogin.columnWeights = new double[]{0.0};
		gbl_panelLogin.rowWeights = new double[]{0.0};
		panelLogin.setLayout(gbl_panelLogin);
		
		JButton login = new JButton("Login");
		login.setMnemonic(KeyEvent.VK_D);
		login.setActionCommand("login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usernameIn = username.getText();
				String passwordIn = new String(passwordField.getPassword());
				

				try {
					if (WineHunterApplication.userSession.validateUser(usernameIn, passwordIn)) {
						WineHunterApplication.splash(0);
					} else {
						WineHunterApplication.userLogin(1);
					} 
				} catch (Exception e2) {
					e2.printStackTrace();
					WineHunterApplication.userLogin(1); // try to login again
				}
			}
		});
		
		JButton newuser = new JButton("New User");
		newuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WineHunterApplication.userCreation(0);
			}
		});
		newuser.setMnemonic(KeyEvent.VK_D);
		newuser.setActionCommand("newuser");
		newuser.setToolTipText("Click to create a new user.");
		GridBagConstraints gbc_newuser = new GridBagConstraints();
		gbc_newuser.anchor = GridBagConstraints.CENTER;
		gbc_newuser.insets = new Insets(0, 0, 5, 5);
		gbc_newuser.gridx = 0;
		gbc_newuser.gridy = 0;
		panelLogin.add(newuser, gbc_newuser);
		login.setToolTipText("Click to login with provided credentials\n");
		GridBagConstraints gbc_login = new GridBagConstraints();
		gbc_login.anchor = GridBagConstraints.CENTER;
		gbc_login.insets = new Insets(0, 0, 5, 5);
		gbc_login.gridx = 1;
		gbc_login.gridy = 0;
		panelLogin.add(login, gbc_login);
		
	}
	
}
