package Core;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;


public class Toolbar extends JPanel {
	
	//fields
	private static final long serialVersionUID = -7430223314313023616L;

	/**
	 * Create the panel.
	 */
	public Toolbar() {
		
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JPanel splitPane = new JPanel();
		splitPane.setLayout(new GridLayout(0, 2, 0, 0));
		splitPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 0, WineHunterApplication.APPLICATION_WIDTH, 20);
		
		
		if (WineHunterApplication.userSession.isLoggedIn()) {
			JLabel lblLoggedInAs = new JLabel("Logged in as " + WineHunterApplication.userSession.getUser().getUsername());
			lblLoggedInAs.setHorizontalAlignment(SwingConstants.LEFT);

			lblLoggedInAs.setBounds(0, 0, 70, 20);
			splitPane.add(lblLoggedInAs);
		}
		
		
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
		
		splitPane.add(toolBar);
		add(splitPane);

		
	}
	
}
