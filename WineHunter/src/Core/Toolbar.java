package Core;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import java.awt.Insets;


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

		int column = 0;
	
		
		if (WineHunterApplication.userSession.isLoggedIn()) {
			JPanel loginPanel = new JPanel();
			
			GridBagLayout gbl_loginLayout = new GridBagLayout();
			gbl_loginLayout.columnWidths = new int[]{0};
			gbl_loginLayout.rowHeights = new int[] {0};
			
			
			loginPanel.setLayout(gbl_loginLayout);
			
			GridBagConstraints gbc_Login = new GridBagConstraints();
			gbc_Login.anchor = GridBagConstraints.EAST;
			gbc_Login.gridx = column;
			gbc_Login.gridy = 0;
			
			JLabel lblLoggedInAs = new JLabel("Logged in as ");
			lblLoggedInAs.setFont(WineHunterApplication.format.getBaseFont());
			
			GridBagConstraints gbc_lblLoginText = new GridBagConstraints();
			gbc_lblLoginText.anchor = GridBagConstraints.EAST;
			gbc_lblLoginText.gridx = 0;
			gbc_lblLoginText.gridy = 0;
			
			JLabel lblLoginName = new JLabel(WineHunterApplication.userSession.getUser().getUsername());
			if (WineHunterApplication.userSession.getUser().getSuperAdmin() == 1) {
				lblLoginName.setForeground(Color.RED);
				lblLoginName.setFont(WineHunterApplication.format.getBoldFont());
			}
			else if (WineHunterApplication.userSession.getUser().getAdmin() == 1) {
				lblLoginName.setForeground(Color.BLUE);
				lblLoginName.setFont(WineHunterApplication.format.getBoldFont());
			}
			
			GridBagConstraints gbc_lblLoginName = new GridBagConstraints();
			gbc_lblLoginName.anchor = GridBagConstraints.EAST;
			gbc_lblLoginName.gridx = 1;
			gbc_lblLoginName.gridy = 0;
			
			gbl_toolbarLayout.columnWeights = new double[] {0.5, 0.5};
			this.setLayout(gbl_toolbarLayout);
			this.add(loginPanel, gbc_Login);
			loginPanel.add(lblLoggedInAs, gbc_lblLoginText);
			loginPanel.add(lblLoginName, gbc_lblLoginName);
			++column;
		}
		else {
			this.setLayout(gbl_toolbarLayout);
		}
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_lblToolbar = new GridBagConstraints();
		gbc_lblToolbar.insets = new Insets(5, 5, 5, 5);
		gbc_lblToolbar.anchor = GridBagConstraints.EAST;
		gbc_lblToolbar.gridx = column;
		gbc_lblToolbar.gridy = 0;
		
		if (WineHunterApplication.userSession.isLoggedIn()) {
			JButton btnWineSearch = new JButton("Wine Search");
			btnWineSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			toolBar.add(btnWineSearch);
		}
		
		if (WineHunterApplication.userSession.isLoggedIn()) {
			JButton btnUser = new JButton("User Profile");
			btnUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WineHunterApplication.viewUserProfile(WineHunterApplication.userSession.getUser(), 0);
				}
			});
			toolBar.add(btnUser);
		}
		
		if (WineHunterApplication.userSession.getUser().getAdmin() == 1) {
			JButton btnAdminFunctions = new JButton("Admin");
			btnAdminFunctions.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WineHunterApplication.adminUserSearch(0);
				}
			});
			toolBar.add(btnAdminFunctions);
		}
		
		if (WineHunterApplication.userSession.isLoggedIn()) {
			JButton btnLogout = new JButton("Logout");
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WineHunterApplication.userSession.logOut();
					WineHunterApplication.userLogin(0);
				}
			});
			toolBar.add(btnLogout);
		}
		
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WineHunterApplication.quit();
			}
		});
		
		toolBar.add(btnQuit);
		
		this.add(toolBar, gbc_lblToolbar);

		
	}
	
}
