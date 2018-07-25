package Core;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class Toolbar extends JPanel {
	
	//fields
	private static final long serialVersionUID = -7430223314313023616L;

	/**
	 * Create the panel.
	 */
	public Toolbar() {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		add(toolBar);
		
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
				}
			});
			toolBar.add(btnUser);
		}
		
		if (WineHunterApplication.userSession.getCredentials().get("SUPERADMIN") == 1) {
			JButton btnSuperAdmin = new JButton("Super Admin");
			btnSuperAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			toolBar.add(btnSuperAdmin);
		}
		
		if (WineHunterApplication.userSession.getCredentials().get("ADMIN") == 1) {
			JButton btnAdminFunctions = new JButton("Admin");
			btnAdminFunctions.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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
		
	}
	
}
