/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             Toolbar.java
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

package Core;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import java.awt.Insets;

/**
 * This class creates the toolbar that is resent on every application screen.
 *
 */
public class Toolbar extends JPanel {
	
	//fields
	private static final long serialVersionUID = -7430223314313023616L;

	/**
	 * Create the panel.
	 */
	public Toolbar() {
		
		
		GridBagLayout gbl_toolbarLayout = new GridBagLayout();
		gbl_toolbarLayout.columnWidths = new int[]{0};
		gbl_toolbarLayout.rowHeights = new int[] {0};


		JPanel loginPanel = new JPanel();
		loginPanel.setName("loginPanel");
		GridBagLayout gbl_loginLayout = new GridBagLayout();
		gbl_loginLayout.columnWidths = new int[]{0};
		gbl_loginLayout.rowHeights = new int[] {0};
		
		loginPanel.setLayout(gbl_loginLayout);
		
		GridBagConstraints gbc_Login = new GridBagConstraints();
		gbc_Login.insets = new Insets(5, 5, 5, 5);
		gbc_Login.anchor = GridBagConstraints.WEST;
		gbc_Login.gridx = 0;
		gbc_Login.gridy = 0;
		gbc_Login.weightx = 1;
		gbc_Login.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel lblLoggedInAs = new JLabel();
		lblLoggedInAs.setName("Logged in as");
		if (WineHunterApplication.userSession.isLoggedIn()) {
			lblLoggedInAs.setText("Logged in as");
		}
		lblLoggedInAs.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoggedInAs.setFont(WineHunterApplication.format.getSubheadingFont2Base());
		
		GridBagConstraints gbc_lblLoginText = new GridBagConstraints();
		gbc_lblLoginText.insets = new Insets(5, 5, 5, 5);
		gbc_lblLoginText.anchor = GridBagConstraints.WEST;
		gbc_lblLoginText.gridx = 0;
		gbc_lblLoginText.gridy = 0;
		
		JLabel lblLoginName = new JLabel();
		lblLoginName.setName("lblLoginName");
		lblLoginName.setFont(WineHunterApplication.format.getSubheadingFont2Base());
		lblLoginName.setHorizontalAlignment(SwingConstants.LEFT);
		if (WineHunterApplication.userSession.isLoggedIn()) {
			lblLoginName.setText(WineHunterApplication.userSession.getUser().getUsername());
			if (WineHunterApplication.userSession.getUser().getSuperAdmin() == 1) {
				lblLoginName.setForeground(Color.RED);
				lblLoginName.setFont(WineHunterApplication.format.getSubheadingFont2());
			}
			else if (WineHunterApplication.userSession.getUser().getAdmin() == 1) {
				lblLoginName.setForeground(Color.BLUE);
				lblLoginName.setFont(WineHunterApplication.format.getSubheadingFont2());
			}
		}
		
		
		GridBagConstraints gbc_lblLoginName = new GridBagConstraints();
		gbc_lblLoginName.insets = new Insets(5, 5, 5, 5);
		gbc_lblLoginName.anchor = GridBagConstraints.WEST;
		gbc_lblLoginName.gridx = 1;
		gbc_lblLoginName.gridy = 0;
		gbc_lblLoginName.weightx = 1;
		
		this.setLayout(gbl_toolbarLayout);
		this.add(loginPanel, gbc_Login);
		
		loginPanel.add(lblLoggedInAs, gbc_lblLoginText);
		loginPanel.add(lblLoginName, gbc_lblLoginName);
		
		JToolBar toolBar = new JToolBar();
		toolBar.add(Box.createHorizontalGlue());
		toolBar.setName("Toolbar buttons");
		toolBar.setFloatable(false);
		GridBagConstraints gbc_lblToolbar = new GridBagConstraints();
		gbc_lblToolbar.insets = new Insets(5, 5, 5, 5);
		gbc_lblToolbar.anchor = GridBagConstraints.EAST;
		gbc_lblToolbar.gridx = 1;
		gbc_lblToolbar.gridy = 0;
		gbc_lblToolbar.weightx = 1;
		gbc_lblToolbar.fill = GridBagConstraints.HORIZONTAL;
		
		if (WineHunterApplication.userSession.isLoggedIn()) {
			JButton btnWineSearch = new JButton("Wine Search");
			btnWineSearch.setFont(WineHunterApplication.format.getSubheadingFont2Base());
			btnWineSearch.setHorizontalAlignment(SwingConstants.TRAILING);
			btnWineSearch.setName("btnWineSearch");
			btnWineSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WineHunterApplication.searchWines(1, WineHunterApplication.userSession.getUser()); 
				}
			});
			toolBar.add(btnWineSearch);
		}
		
		if (WineHunterApplication.userSession.isLoggedIn()) {
			JButton btnUser = new JButton("User Profile");
			btnUser.setFont(WineHunterApplication.format.getSubheadingFont2Base());
			btnUser.setHorizontalAlignment(SwingConstants.TRAILING);
			btnUser.setName("btnUser");
			btnUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					WineHunterApplication.viewUserProfile(WineHunterApplication.userSession.getUser(), 0, 0);
				}
			});
			toolBar.add(btnUser);
		}
		
		if (WineHunterApplication.userSession.getUser().getAdmin() == 1) {
			JButton btnAdminFunctions = new JButton("Admin");
			btnAdminFunctions.setFont(WineHunterApplication.format.getSubheadingFont2Base());
			btnAdminFunctions.setHorizontalAlignment(SwingConstants.TRAILING);
			btnAdminFunctions.setName("btnAdminFunctions");
			btnAdminFunctions.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WineHunterApplication.adminUserSearch(0);
				}
			});
			toolBar.add(btnAdminFunctions);
		}
		
		if (WineHunterApplication.userSession.isLoggedIn()) {
			JButton btnLogout = new JButton("Logout");
			btnLogout.setFont(WineHunterApplication.format.getSubheadingFont2Base());
			btnLogout.setHorizontalAlignment(SwingConstants.TRAILING);
			btnLogout.setName("btnLogout");
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WineHunterApplication.userSession.logOut();
					WineHunterApplication.userLogin(0);
				}
			});
			toolBar.add(btnLogout);
		}
		
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setFont(WineHunterApplication.format.getSubheadingFont2Base());
		btnQuit.setHorizontalAlignment(SwingConstants.TRAILING);
		btnQuit.setName("btnQuit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WineHunterApplication.quit();
			}
		});
		
		toolBar.add(btnQuit);
		
		this.add(toolBar, gbc_lblToolbar);

		
	}
	
}
