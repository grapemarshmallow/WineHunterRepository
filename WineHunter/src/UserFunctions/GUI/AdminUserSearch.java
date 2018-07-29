package UserFunctions.GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import Core.WineHunterApplication;
import UserFunctions.Logic.UserProfile;
import WineObjects.User;

public class AdminUserSearch extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8981424315449345382L;

	/**
	 * Create the panel.
	 * @param subsequent 1 if this is for an initial failed search, 2 to select a search type, 3 for non-numeric ID detected
	 */
	public AdminUserSearch(int subsequent) {
		
		setBounds(100, 100, WineHunterApplication.APPLICATION_WIDTH, WineHunterApplication.APPLICATION_HEIGHT);

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0};
		
		boolean isSuperAdmin = (WineHunterApplication.userSession.getUser().getSuperAdmin() == 1);
		boolean isAdmin = (WineHunterApplication.userSession.getUser().getAdmin() == 1);
		
		if ((!isSuperAdmin) && (!isAdmin)) {
				JLabel userSecurityMessage = DefaultComponentFactory.getInstance().createTitle("Insufficent security.");
				
				add(userSecurityMessage);

				return;
			
		}
		
		JLabel lblNewLabel = new JLabel("Search for a user!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(WineHunterApplication.format.getHeadingFont());
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		
		JLabel lblGap = new JLabel("");
		lblGap.setMinimumSize(new Dimension(5,5));
		lblGap.setHorizontalAlignment(SwingConstants.CENTER);
		lblGap.setFont(WineHunterApplication.format.getHeadingFont());
		GridBagConstraints gbc_lblGap = new GridBagConstraints();
		gbc_lblGap.insets = new Insets(0, 0, 5, 0);
		gbc_lblGap.anchor = GridBagConstraints.NORTH;
		gbc_lblGap.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGap.gridx = 0;
		gbc_lblGap.gridy = 1;
		
		if (subsequent != 0) {
			JLabel reprompt = new JLabel();
			reprompt.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblReprompt = new GridBagConstraints();
			gbc_lblReprompt.insets = new Insets(0, 0, 5, 0);
			gbc_lblReprompt.anchor = GridBagConstraints.NORTH;
			gbc_lblReprompt.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblReprompt.gridx = 0;
			gbc_lblReprompt.gridy = 2;
			if (subsequent == 1) {
				reprompt.setText("Search did not find a user.");
				
			}
			else if (subsequent == 2) {
				reprompt.setText("Select a search type.");
				
			}
			else if (subsequent == 3) {
				reprompt.setText("Non-numeric search string detected. Re-enter the user ID or choose a different search type.");
				
			}
			this.add(reprompt, gbc_lblReprompt);
		}
		
		
		this.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel buttonPanel = new JPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.insets = new Insets(0, 0, 5, 0);
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 4;
		gbc_buttonPanel.weightx = 1;
		this.add(buttonPanel, gbc_buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[] {0, 50, 50, 50, 0};
		gbl_buttonPanel.rowHeights = new int[] {0};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		JRadioButton ID = new JRadioButton("User ID");
        ID.setVerticalTextPosition(AbstractButton.CENTER);
		ID.setHorizontalTextPosition(AbstractButton.LEADING);
		ID.setMnemonic(KeyEvent.VK_I);
		ID.setActionCommand("ID");

		ID.setToolTipText("Click this button to find a user by ID.");
		
		JRadioButton username = new JRadioButton("Username");
		username.setVerticalTextPosition(AbstractButton.CENTER);
		username.setHorizontalTextPosition(AbstractButton.LEADING);
		username.setMnemonic(KeyEvent.VK_U);
		username.setActionCommand("username");
		username.setToolTipText("Click this button to find a user by username.");
		
		JRadioButton email = new JRadioButton("Email Address");
		email.setVerticalTextPosition(AbstractButton.CENTER);
		email.setHorizontalTextPosition(AbstractButton.LEADING);
		email.setMnemonic(KeyEvent.VK_E);
		email.setActionCommand("email");

		
		email.setToolTipText("Click this button to find a user by email address.");
		
		ID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ID.setSelected(true);
				username.setSelected(false);
				email.setSelected(false);
			}
		});
		GridBagConstraints gbc_ID = new GridBagConstraints();
		gbc_ID.gridx = 1;
		gbc_ID.gridy = 0;
		gbc_ID.weightx = 1;
		buttonPanel.add(ID, gbc_ID);
		
		
		username.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ID.setSelected(false);
				username.setSelected(true);
				email.setSelected(false);
			}
		});
		GridBagConstraints gbc_Username = new GridBagConstraints();
		gbc_Username.gridx = 2;
		gbc_Username.gridy = 0;
		gbc_Username.weightx = 1;
		buttonPanel.add(username, gbc_Username);
		
		
		email.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ID.setSelected(false);
				username.setSelected(false);
				email.setSelected(true);
			}
		});
        
		GridBagConstraints gbc_Email = new GridBagConstraints();
		gbc_Email.gridx = 3;
		gbc_Email.gridy = 0;
		gbc_Email.weightx = 1;
		buttonPanel.add(email, gbc_Email);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 6;
		this.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblSearch = new JLabel("Search text: ");
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.anchor = GridBagConstraints.EAST;
		gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearch.gridx = 0;
		gbc_lblSearch.gridy = 0;
		panel.add(lblSearch, gbc_lblSearch);
		
		JTextField searchText = new JTextField();
		searchText.setToolTipText("Enter your search text.");
		GridBagConstraints gbc_txtSearchText = new GridBagConstraints();
		gbc_txtSearchText.anchor = GridBagConstraints.WEST;
		gbc_txtSearchText.insets = new Insets(0, 0, 5, 0);
		gbc_txtSearchText.gridx = 1;
		gbc_txtSearchText.gridy = 0;
		panel.add(searchText, gbc_txtSearchText);
		searchText.setColumns(10);
		
		
		JButton search = new JButton("Search");
		search.setMnemonic(KeyEvent.VK_D);
		search.setActionCommand("search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchTextIn = searchText.getText();
				UserProfile userProfile = new UserProfile();
				User searchUser = new User();
				
				if (ID.isSelected() == true) {
					try {
						int searchId = Integer.parseInt(searchTextIn);
						if (userProfile.getUserInfo(searchId, searchUser) == 1) {
							WineHunterApplication.viewUserProfile(searchUser, 0, 0);
						} else {
							WineHunterApplication.adminUserSearch(1);
						} 
					} catch (NumberFormatException e2) {
						WineHunterApplication.adminUserSearch(3);
				    } catch (SQLException e1) {
				    	WineHunterApplication.adminUserSearch(1);
						e1.printStackTrace();
					}
					
				}
				else if (username.isSelected() == true) {
					try {
						
						if (userProfile.getUserInfoByUsername(searchTextIn, searchUser) == 1) {
							WineHunterApplication.viewUserProfile(searchUser, 0, 0);
						} else {
							WineHunterApplication.adminUserSearch(1);
						} 
				    } catch (SQLException e1) {
				    	WineHunterApplication.adminUserSearch(1);
						e1.printStackTrace();
					}
				}
				else if (email.isSelected() == true) {
					try {
						
						if (userProfile.getUserInfoByEmail(searchTextIn, searchUser) == 1) {
							WineHunterApplication.viewUserProfile(searchUser, 0, 0);
						} else {
							WineHunterApplication.adminUserSearch(1);
						} 
				    } catch (SQLException e1) {
				    	WineHunterApplication.adminUserSearch(1);
						e1.printStackTrace();
					}
				}
				else {
					WineHunterApplication.adminUserSearch(2);
				}

				
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.anchor = GridBagConstraints.WEST;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.gridx = 2;
		gbc_btnSearch.gridy = 0;
		panel.add(search, gbc_btnSearch);
		
		
		
	}
	
}
