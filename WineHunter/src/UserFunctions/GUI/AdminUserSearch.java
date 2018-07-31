package UserFunctions.GUI;


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
		
		JLabel lblSearchPrompt = new JLabel("Search for a user!");
		lblSearchPrompt.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchPrompt.setFont(WineHunterApplication.format.getHeadingFont());
		GridBagConstraints gbc_lblSearchPrompt = new GridBagConstraints();
		gbc_lblSearchPrompt.insets = new Insets(15, 15, 15, 15);
		gbc_lblSearchPrompt.anchor = GridBagConstraints.NORTH;
		gbc_lblSearchPrompt.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSearchPrompt.gridx = 0;
		gbc_lblSearchPrompt.gridy = 0;
		
		
		if (subsequent != 0) {
			JLabel reprompt = new JLabel();
			reprompt.setFont(WineHunterApplication.format.getSubheadingFont2Base());
			reprompt.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblReprompt = new GridBagConstraints();
			gbc_lblReprompt.insets = new Insets(15, 15, 15, 15);
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
		
		
		this.add(lblSearchPrompt, gbc_lblSearchPrompt);
		
		JPanel buttonPanel = new JPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.insets = new Insets(15, 15, 15, 15);
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 4;
		gbc_buttonPanel.weightx = 1;
		this.add(buttonPanel, gbc_buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWeights = new double[] {0.35, 0.1, 0.1, 0.1, 0.35};
		gbl_buttonPanel.rowHeights = new int[] {0};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		JLabel blankLabel = new JLabel("");
		blankLabel.setHorizontalAlignment(SwingConstants.CENTER);
		blankLabel.setFont(WineHunterApplication.format.getHeadingFont());
		GridBagConstraints gbc_lblblankLabel = new GridBagConstraints();
		gbc_lblblankLabel.insets = new Insets(15, 15, 15, 15);
		gbc_lblblankLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblblankLabel.fill = GridBagConstraints.BOTH;
		gbc_lblblankLabel.gridx = 0;
		gbc_lblblankLabel.gridy = 0;
		gbc_lblblankLabel.weightx = 1;
		buttonPanel.add(blankLabel, gbc_lblblankLabel);
		
		JRadioButton ID = new JRadioButton("User ID");
        ID.setVerticalTextPosition(AbstractButton.CENTER);
        ID.setHorizontalAlignment(SwingConstants.TRAILING);
		ID.setHorizontalTextPosition(AbstractButton.LEADING);
		ID.setMnemonic(KeyEvent.VK_I);
		ID.setActionCommand("ID");

		ID.setToolTipText("Click this button to find a user by ID.");
		
		JRadioButton username = new JRadioButton("Username");
		username.setVerticalTextPosition(AbstractButton.CENTER);
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setHorizontalTextPosition(AbstractButton.LEADING);
		username.setMnemonic(KeyEvent.VK_U);
		username.setActionCommand("username");
		username.setToolTipText("Click this button to find a user by username.");
		
		JRadioButton email = new JRadioButton("Email Address");
		email.setVerticalTextPosition(AbstractButton.CENTER);
		email.setHorizontalAlignment(SwingConstants.LEADING);
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
		
		gbc_lblblankLabel.gridx = 4;
		gbc_lblblankLabel.gridy = 0;
		buttonPanel.add(blankLabel, gbc_lblblankLabel);
		
		JPanel searchPanel = new JPanel();
		GridBagConstraints gbc_SearchPanel = new GridBagConstraints();
		gbc_SearchPanel.insets = new Insets(5, 5, 5, 5);
		gbc_SearchPanel.weightx = 1;
		gbc_SearchPanel.fill = GridBagConstraints.BOTH;
		gbc_SearchPanel.gridx = 0;
		gbc_SearchPanel.gridy = 6;
		this.add(searchPanel, gbc_SearchPanel);
		GridBagLayout gbl_SearchPanel = new GridBagLayout();
		gbl_SearchPanel.columnWidths = new int[] {0};
		gbl_SearchPanel.rowHeights = new int[] {0};
		gbl_SearchPanel.columnWeights = new double[]{0.35, 0.1, 0.1, 0.1, 0.35};
		gbl_SearchPanel.rowWeights = new double[]{0.0};
		searchPanel.setLayout(gbl_SearchPanel);
		
		gbc_lblblankLabel.gridx = 0;
		gbc_lblblankLabel.gridy = 0;
		searchPanel.add(blankLabel, gbc_lblblankLabel);
		
		JLabel lblSearch = new JLabel("Search text:");
		lblSearch.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.anchor = GridBagConstraints.EAST;
		gbc_lblSearch.insets = new Insets(5, 5, 5, 5);
		gbc_lblSearch.gridx = 1;
		gbc_lblSearch.gridy = 0;
		gbc_lblSearch.weightx = 1;
		searchPanel.add(lblSearch, gbc_lblSearch);
		
		JTextField searchText = new JTextField();
		searchText.setToolTipText("Enter your search text.");
		GridBagConstraints gbc_txtSearchText = new GridBagConstraints();
		gbc_txtSearchText.anchor = GridBagConstraints.WEST;
		gbc_txtSearchText.insets = new Insets(5, 5, 5, 5);
		gbc_txtSearchText.gridx = 2;
		gbc_txtSearchText.gridy = 0;
		gbc_txtSearchText.weightx = 1;
		gbc_txtSearchText.fill = GridBagConstraints.HORIZONTAL;
		searchPanel.add(searchText, gbc_txtSearchText);
		searchText.setColumns(10);
		
		
		JButton search = new JButton("Search");
		search.setHorizontalAlignment(SwingConstants.LEADING);
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
		gbc_btnSearch.insets = new Insets(5, 5, 5, 5);
		gbc_btnSearch.gridx = 3;
		gbc_btnSearch.gridy = 0;
		searchPanel.add(search, gbc_btnSearch);
		
		gbc_lblblankLabel.gridx = 4;
		gbc_lblblankLabel.gridy = 0;
		searchPanel.add(blankLabel, gbc_lblblankLabel);
		
		
	}
	
}
