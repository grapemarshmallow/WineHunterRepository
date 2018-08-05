/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             UserCreate.java
 * Semester:         Summer 2018
 *
 *
 * Author:           Orbi Ish-Shalom (oishshalom@wisc.edu)
 * CS Login:         orbi
 * Lecturer's Name:  Hien Hguyen
 *
 *                    PAIR PROGRAMMERS COMPLETE THIS SECTION
 *  Pair Partner:     Jennifer Shih
 * //////////////////////////// 80 columns wide //////////////////////////////////
 *******************************************************************************/

package UserFunctions.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Core.WineHunterApplication;

/**
 * this creates a panel to create a user
 *
 */
public class UserCreate extends JPanel {
	
	//fields
	private static final long serialVersionUID = 8940618476377670089L;
	private JPasswordField passwordField;
	private JTextField name;
	private JTextField email;
	private JTextField username;

	
	/**
	 * Create the frame.
	 * @param set to 0 if first attempt, 1 for a duplicate, 2 for error, 3 for null input
	 */
	public UserCreate(int attemptFlag) {
		

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		
		JLabel lblInstructions = new JLabel("Fill out desired user credentials.");
		lblInstructions.setFont(WineHunterApplication.format.getHeadingFont());
		lblInstructions.setName("lblInstructions");
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);

		GridBagConstraints gbc_lblInstructions = new GridBagConstraints();
		gbc_lblInstructions.insets = new Insets(30, 30, 30, 30);
		gbc_lblInstructions.anchor = GridBagConstraints.NORTH;
		gbc_lblInstructions.fill = GridBagConstraints.BOTH;
		gbc_lblInstructions.gridx = 0;
		gbc_lblInstructions.gridy = 1;
		gbc_lblInstructions.weightx = 1;
		
		JLabel reprompt = new JLabel();
		reprompt.setFont(WineHunterApplication.format.getSubheadingFont2Base());
		reprompt.setName("reprompt");
		reprompt.setHorizontalAlignment(SwingConstants.CENTER);
		
		if (attemptFlag == 1) {
			reprompt.setText("Duplicate user detected. Verify that username and email are correct.");
			this.add(reprompt, gbc_lblInstructions);
			gbc_lblInstructions.gridy = 0;
		}
		else if (attemptFlag == 2) {
			reprompt.setText("Error in user creation. Try again.");
			this.add(reprompt, gbc_lblInstructions);
			gbc_lblInstructions.gridy = 0;
		}
		else if (attemptFlag == 3) {
			reprompt.setText("Empty field detected. Verify that all information is populated.");
			this.add(reprompt, gbc_lblInstructions);
			gbc_lblInstructions.gridy = 0;
		}
		
		
		this.add(lblInstructions, gbc_lblInstructions);
		
		
		JPanel panel = new JPanel();
		panel.setName("User Create Panel");
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		gbc_panel.weightx = 1;
		this.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		panel.setLayout(gbl_panel);
		
		JLabel lblFullName = new JLabel("Full Name: ");
		lblFullName.setName("lblFullName");
		lblFullName.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFullName = new GridBagConstraints();
		gbc_lblFullName.anchor = GridBagConstraints.EAST;
		gbc_lblFullName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFullName.gridx = 0;
		gbc_lblFullName.gridy = 0;
		panel.add(lblFullName, gbc_lblFullName);
		
		name = new JTextField();
		name.setName("name");
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.insets = new Insets(0, 0, 5, 5);
		gbc_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_name.gridx = 2;
		gbc_name.gridy = 0;
		panel.add(name, gbc_name);
		name.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setName("lblUsername");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 1;
		panel.add(lblUsername, gbc_lblUsername);
		
		passwordField = new JPasswordField();
		passwordField.setName("passwordField");
		
		username = new JTextField();
		username.setName("username");
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.insets = new Insets(0, 0, 5, 5);
		gbc_username.fill = GridBagConstraints.HORIZONTAL;
		gbc_username.gridx = 2;
		gbc_username.gridy = 1;
		panel.add(username, gbc_username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setName("lblPassword");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		panel.add(lblPassword, gbc_lblPassword);
		passwordField.setToolTipText("Enter your password.");
		passwordField.setActionCommand("password");
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		panel.add(passwordField, gbc_passwordField);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setName("lblEmail");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		panel.add(lblEmail, gbc_lblEmail);
		
		email = new JTextField();
		email.setName("email");
		GridBagConstraints gbc_email = new GridBagConstraints();
		gbc_email.insets = new Insets(0, 0, 5, 5);
		gbc_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_email.gridx = 2;
		gbc_email.gridy = 3;
		panel.add(email, gbc_email);
		email.setColumns(10);
		
		JPanel createPanel = new JPanel();
		createPanel.setName("createPanel");
		GridBagConstraints gbc_createPanel = new GridBagConstraints();
		gbc_createPanel.fill = GridBagConstraints.BOTH;
		gbc_createPanel.gridx = 0;
		gbc_createPanel.gridy = 3;
		gbc_createPanel.weightx = 1;

		this.add(createPanel, gbc_createPanel);
		
		GridBagLayout gbl_createPanel = new GridBagLayout();
		gbl_createPanel.columnWeights = new double[]{0.2, 0.2, 0.2, 0.2, 0.2};
		createPanel.setLayout(gbl_createPanel);
		
		JButton Accept = new JButton("Accept");
		Accept.setName("Accept");
		Accept.setMnemonic(KeyEvent.VK_D);
		Accept.setActionCommand("accept");
		Accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usernameIn = username.getText();
				String passwordIn = new String(passwordField.getPassword());
				String emailIn = email.getText();
				String nameIn = name.getText();
				
				if ((usernameIn.isEmpty()) || (passwordIn.isEmpty()) || (emailIn.isEmpty()) || (nameIn.isEmpty())) {
					WineHunterApplication.userCreation(3);
				}
				else {
					int result = 0;
	
					try {
						result = WineHunterApplication.userSession.createUser(nameIn, emailIn, usernameIn, passwordIn, 0, 0);
		
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
					if (result == 1) {
						WineHunterApplication.userLogin(2);
					} 
					else if (result == 0) {
						WineHunterApplication.userCreation(2);
					} 
					else {
						WineHunterApplication.userCreation(1);
					} 
				}
			}
		});
		Accept.setToolTipText("Click to accept the provided credentials.\n");
		GridBagConstraints gbc_Accept = new GridBagConstraints();
		gbc_Accept.insets = new Insets(0, 0, 5, 5);
		gbc_Accept.anchor = GridBagConstraints.CENTER;
		gbc_Accept.gridx = 3;
		gbc_Accept.gridy = 0;
		createPanel.add(Accept, gbc_Accept);
		

	}

	
	
	
}
