package UserFunctions;

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

public class UserCreate extends JPanel {
	
	//fields
	private static final long serialVersionUID = 8940618476377670089L;
	private JPasswordField passwordField;
	private JTextField name;
	private JTextField email;
	private JTextField username;

	
	/**
	 * Create the frame.
	 * @param set to 0 if first attempt, 1 for a duplicate, 2 for error
	 */
	public UserCreate(int attemptFlag) {
		setBounds(100, 100, WineHunterApplication.APPLICATION_WIDTH, WineHunterApplication.APPLICATION_HEIGHT);

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Fill out desired user credentials.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		
		JLabel reprompt = new JLabel();
		reprompt.setHorizontalAlignment(SwingConstants.CENTER);
		
		if (attemptFlag == 1) {
			reprompt.setText("Duplicate user detected. Verify that username and email are correct.");
			this.add(reprompt, gbc_lblNewLabel);
			gbc_lblNewLabel.gridy = 0;
		}
		else if (attemptFlag == 2) {
			reprompt.setText("Error in user creation. Try again.");
			this.add(reprompt, gbc_lblNewLabel);
			gbc_lblNewLabel.gridy = 0;
		}
		
		
		this.add(lblNewLabel, gbc_lblNewLabel);
		gridBagLayout.columnWidths = new int[] {450};
		gridBagLayout.rowHeights = new int[]{16, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		this.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 30, 0, 100, 30};
		gbl_panel.rowHeights = new int[] {20, 20, 20, 20, 20};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblFullName = new JLabel("Full Name: ");
		lblFullName.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFullName = new GridBagConstraints();
		gbc_lblFullName.anchor = GridBagConstraints.EAST;
		gbc_lblFullName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFullName.gridx = 0;
		gbc_lblFullName.gridy = 0;
		panel.add(lblFullName, gbc_lblFullName);
		
		name = new JTextField();
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.insets = new Insets(0, 0, 5, 5);
		gbc_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_name.gridx = 2;
		gbc_name.gridy = 0;
		panel.add(name, gbc_name);
		name.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 1;
		panel.add(lblUsername, gbc_lblUsername);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		username = new JTextField();
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.insets = new Insets(0, 0, 5, 5);
		gbc_username.fill = GridBagConstraints.HORIZONTAL;
		gbc_username.gridx = 2;
		gbc_username.gridy = 1;
		panel.add(username, gbc_username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password: ");
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
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		panel.add(lblEmail, gbc_lblEmail);
		
		email = new JTextField();
		GridBagConstraints gbc_email = new GridBagConstraints();
		gbc_email.insets = new Insets(0, 0, 5, 5);
		gbc_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_email.gridx = 2;
		gbc_email.gridy = 3;
		panel.add(email, gbc_email);
		email.setColumns(10);
		
		JPanel createPanel = new JPanel();
		GridBagConstraints gbc_createPanel = new GridBagConstraints();
		gbc_createPanel.fill = GridBagConstraints.BOTH;
		gbc_createPanel.gridx = 0;
		gbc_createPanel.gridy = 3;
		this.add(createPanel, gbc_createPanel);
		GridBagLayout gbl_createPanel = new GridBagLayout();
		gbl_createPanel.columnWidths = new int[] {75, 75, 75, 75, 75, 75};
		gbl_createPanel.rowHeights = new int[] {0, 30};
		gbl_createPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_createPanel.rowWeights = new double[]{0.0, 0.0};
		createPanel.setLayout(gbl_createPanel);
		
		JButton Accept = new JButton("Accept");
		Accept.setMnemonic(KeyEvent.VK_D);
		Accept.setActionCommand("accept");
		Accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usernameIn = username.getText();
				String passwordIn = new String(passwordField.getPassword());
				String emailIn = email.getText();
				String nameIn = name.getText();
				
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
		});
		Accept.setToolTipText("Click to accept the provided credentials.\n");
		GridBagConstraints gbc_Accept = new GridBagConstraints();
		gbc_Accept.insets = new Insets(0, 0, 5, 5);
		gbc_Accept.anchor = GridBagConstraints.CENTER;
		gbc_Accept.gridx = 4;
		gbc_Accept.gridy = 0;
		createPanel.add(Accept, gbc_Accept);
		

	}

	
	
	
}
