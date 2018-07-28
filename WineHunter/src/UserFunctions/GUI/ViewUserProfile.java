package UserFunctions.GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;


import com.jgoodies.forms.factories.DefaultComponentFactory;

import Core.WineHunterApplication;
import UserFunctions.Logic.UserProfile;
import UserFunctions.Logic.UserUpdate;
import WineObjects.Keyword;
import WineObjects.User;
import WineObjects.Variety;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;

public class ViewUserProfile extends JPanel {
	
	//fields
	private static final long serialVersionUID = -4768934525868550643L;

	/**
	 * Create the panel with a specific user object.
	 * @param user the user object to generate the profile for
	 * @param subsequent 0 for the first visit, 1 if a delete failed, 2 if an update failed
	 */
	public ViewUserProfile(User user, int subsequent) {
		this.setBorder(BorderFactory.createBevelBorder(2));
		setLayout(new MigLayout("", "[" + (WineHunterApplication.APPLICATION_WIDTH - 100) + "] px", "[" + (WineHunterApplication.APPLICATION_HEIGHT - 100) + "] px"));
		
		JPanel userInfoScroll = new JPanel();
		
		GridBagLayout gbl_userInfoScroll = new GridBagLayout();
		gbl_userInfoScroll.columnWidths = new int[]{0};
		gbl_userInfoScroll.rowHeights = new int[]{0};
		userInfoScroll.setLayout(gbl_userInfoScroll);
		userInfoScroll.setMaximumSize(new Dimension(WineHunterApplication.APPLICATION_WIDTH - 300, WineHunterApplication.APPLICATION_HEIGHT - 200));
		
		JScrollPane userScroll = new JScrollPane(userInfoScroll, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		userScroll.setViewportBorder(null);
		userScroll.setMaximumSize(new Dimension(WineHunterApplication.APPLICATION_WIDTH - 100, WineHunterApplication.APPLICATION_HEIGHT - 100));
		
		this.add(userScroll, "cell 0 0,grow");
		
		if (subsequent == 1) {
			userInfoScroll.add(new JLabel("User deletion failed."));
		}
		else if (subsequent == 2) {
			userInfoScroll.add(new JLabel("User update failed."));
		}
		else if (subsequent == 3) {
			userInfoScroll.add(new JLabel("Update successful!"));
		}
		else if (subsequent == 4) {
			userInfoScroll.add(new JLabel("Insufficient security for update."));
		}
		
		int other = 0;
		
		boolean isSuperAdmin = (WineHunterApplication.userSession.getUser().getSuperAdmin() == 1);
		boolean isAdmin = (WineHunterApplication.userSession.getUser().getAdmin() == 1);
		
		if (user.getId() != WineHunterApplication.userSession.getUser().getId()) {
			other = 1;
			if ((!isSuperAdmin) && (!isAdmin)) {
				JLabel userSecurityMessage = DefaultComponentFactory.getInstance().createTitle("Insufficent security to view user " + user.getId() + " (" + user.getUsername() + ").");
				userSecurityMessage.setLabelFor(userInfoScroll);
				GridBagConstraints gbc_userSecurityMessage = new GridBagConstraints();
				gbc_userSecurityMessage.fill = GridBagConstraints.BOTH;
				gbc_userSecurityMessage.insets = new Insets(5, 5, 5, 5);
				gbc_userSecurityMessage.gridx = 0;
				gbc_userSecurityMessage.gridy = 0;
				gbc_userSecurityMessage.weightx = 1;
				userInfoScroll.add(userSecurityMessage, gbc_userSecurityMessage);
				userSecurityMessage.setHorizontalAlignment(SwingConstants.CENTER);
				
				return;
			}
		}

		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
	    GridBagConstraints gbc = new GridBagConstraints();
	    sep.setMinimumSize(new Dimension(5,5));
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.weightx = 1;
	    
		String profileString = "Your User Profile";
		
		if (other == 1) {
			profileString = "User Profile for user "+ user.getId() + " (" + user.getUsername() + ")";
		}
		
		
		UserProfile userProfile = new UserProfile();
		UserUpdate userUpdate = new UserUpdate();
		try {
			userProfile.getTasterProfile(user);
		} catch (SQLException e) {
			JLabel userSQLError = DefaultComponentFactory.getInstance().createTitle("Could not load user " + user.getId() + " (" + user.getUsername() + ").");
			GridBagConstraints gbc_userSQLError = new GridBagConstraints();
			userSQLError.setHorizontalAlignment(SwingConstants.CENTER);
			gbc_userSQLError.fill = GridBagConstraints.BOTH;
			gbc_userSQLError.insets = new Insets(5, 5, 5, 5);
			gbc_userSQLError.gridx = 0;
			gbc_userSQLError.gridy = 0;
			gbc_userSQLError.weightx = 1;
			userInfoScroll.add(userSQLError, gbc_userSQLError);
			userSQLError.setHorizontalAlignment(SwingConstants.CENTER);

			e.printStackTrace();
			return;
		}
		
		JPanel profileTitle = new JPanel();
		GridBagConstraints gbc_profileTitle = new GridBagConstraints();
		gbc_profileTitle.fill = GridBagConstraints.BOTH;
		gbc_profileTitle.insets = new Insets(5, 5, 5, 5);
		gbc_profileTitle.gridx = 0;
		gbc_profileTitle.gridy = 0;
		gbc_profileTitle.weightx = 1;
		userInfoScroll.add(profileTitle, gbc_profileTitle);
		GridBagLayout gbl_profileTitle = new GridBagLayout();
		gbl_profileTitle.columnWidths = new int[]{0};
		gbl_profileTitle.rowHeights = new int[] {0};
		profileTitle.setLayout(gbl_profileTitle);
		
		
		JLabel userProfileTitle = new JLabel(profileString);
		userProfileTitle.setHorizontalAlignment(SwingConstants.CENTER);
		userProfileTitle.setFont(WineHunterApplication.format.getHeadingFont());
		GridBagConstraints gbc_userProfileTitle = new GridBagConstraints();
		gbc_userProfileTitle.fill = GridBagConstraints.BOTH;
		gbc_userProfileTitle.insets = new Insets(5, 5, 5, 5);
		gbc_userProfileTitle.anchor = GridBagConstraints.CENTER;
		gbc_userProfileTitle.gridx = 0;
		gbc_userProfileTitle.gridy = 0;
		gbc_userProfileTitle.weightx = 1;
		profileTitle.add(userProfileTitle, gbc_userProfileTitle);
		
		gbc.gridy = 1;
		userInfoScroll.add(sep, gbc);
		
		JPanel buttonPanel = new JPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 2;
		gbc_buttonPanel.weightx = 1;
		userInfoScroll.add(buttonPanel, gbc_buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0};
		gbl_buttonPanel.rowHeights = new int[]{0};

		buttonPanel.setLayout(gbl_buttonPanel);
		
		JButton btnViewUserReviews = new JButton("View User Reviews");
		btnViewUserReviews.setFont(WineHunterApplication.format.getBaseFont());
		btnViewUserReviews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnViewUserReviews = new GridBagConstraints();
		gbc_btnViewUserReviews.insets = new Insets(5, 5, 5, 5);
		gbc_btnViewUserReviews.gridx = 0;
		gbc_btnViewUserReviews.gridy = 0;
		buttonPanel.add(btnViewUserReviews, gbc_btnViewUserReviews);
		
		JButton btnViewUserFavorites = new JButton("View User Favorites");
		btnViewUserFavorites.setFont(WineHunterApplication.format.getBaseFont());
		btnViewUserFavorites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnViewUserFavorites = new GridBagConstraints();
		gbc_btnViewUserFavorites.insets = new Insets(5, 5, 5, 5);
		gbc_btnViewUserFavorites.gridx = 1;
		gbc_btnViewUserFavorites.gridy = 0;
		buttonPanel.add(btnViewUserFavorites, gbc_btnViewUserFavorites);
		
		JButton btnEditTasterProfile = new JButton("Edit Taster Profile");
		btnEditTasterProfile.setFont(WineHunterApplication.format.getBaseFont());
		btnEditTasterProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnEditTasterProfile = new GridBagConstraints();
		gbc_btnEditTasterProfile.insets = new Insets(5, 5, 5, 5);
		gbc_btnEditTasterProfile.gridx = 2;
		gbc_btnEditTasterProfile.gridy = 0;
		buttonPanel.add(btnEditTasterProfile, gbc_btnEditTasterProfile);
		
		JButton btnSearchTasterProfile = new JButton("Search With Taster Profile");
		btnSearchTasterProfile.setFont(WineHunterApplication.format.getBaseFont());
		btnSearchTasterProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnSearchTasterProfile = new GridBagConstraints();
		gbc_btnSearchTasterProfile.insets = new Insets(5, 5, 5, 5);
		gbc_btnSearchTasterProfile.gridx = 3;
		gbc_btnSearchTasterProfile.gridy = 0;
		buttonPanel.add(btnSearchTasterProfile, gbc_btnSearchTasterProfile);
		
		if ((user != WineHunterApplication.userSession.getUser()) && isSuperAdmin) {
			String buttonText = "Make Admin";
			if (user.getAdmin() == 1) {
				buttonText = "Remove Admin";
			}
			JButton btnToggleAdmin = new JButton(buttonText);
			btnToggleAdmin.setFont(WineHunterApplication.format.getBaseFont());
			btnToggleAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int res = userUpdate.setAdminSecurity(user);
						if (res == 1) {
							WineHunterApplication.viewUserProfile(user, 3);
						}
						else if (res == 0) {
							WineHunterApplication.viewUserProfile(user, 4);
						}
						else {
							WineHunterApplication.viewUserProfile(user, 2);
						}
					} catch (SQLException e1) {
						WineHunterApplication.viewUserProfile(user, 2);
						e1.printStackTrace();
					}
				}
			});
			GridBagConstraints gbc_btnToggleAdmin = new GridBagConstraints();
			gbc_btnToggleAdmin.insets = new Insets(5, 5, 5, 5);
			gbc_btnToggleAdmin.gridx = 0;
			gbc_btnToggleAdmin.gridy = 2;
			buttonPanel.add(btnToggleAdmin, gbc_btnToggleAdmin);
			
			JButton btnDeleteUser = new JButton("Delete User");
			btnDeleteUser.setFont(WineHunterApplication.format.getBaseFont());
			btnDeleteUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(WineHunterApplication.getFrmWinehunter(), 
							new JLabel("Are you sure you would like to delete " + user.getId() + " (" + user.getUsername() + ")? "
									+ "Any reviews written by this user will also be deleted. This change cannot be undone."), 
							"Confirm Delete", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
					if (result == JOptionPane.YES_OPTION) {
						try {
							if (userUpdate.deleteUser(user.getId()) != 1) {
								WineHunterApplication.viewUserProfile(user, 2);
							}
							else {
								WineHunterApplication.splash(1);
							}
						} catch (SQLException e1) {
							WineHunterApplication.viewUserProfile(user, 2);
							e1.printStackTrace();
						}
					}
					else {
						WineHunterApplication.viewUserProfile(user, 0);
					}
				}
			});
			GridBagConstraints gbc_btnDeleteUser = new GridBagConstraints();
			gbc_btnDeleteUser.insets = new Insets(5, 5, 5, 5);
			gbc_btnDeleteUser.gridx = 1;
			gbc_btnDeleteUser.gridy = 2;
			buttonPanel.add(btnDeleteUser, gbc_btnDeleteUser);
		}
		
		gbc.gridy = 3;
		userInfoScroll.add(sep, gbc);
		
		JPanel profileBucket = new JPanel();
		GridBagConstraints gbc_profileBucket = new GridBagConstraints();
		gbc_profileBucket.fill = GridBagConstraints.BOTH;
		gbc_profileBucket.insets = new Insets(5, 5, 5, 5);
		gbc_profileBucket.gridx = 0;
		gbc_profileBucket.gridy = 4;
		gbc_profileBucket.weightx = 1;
		userInfoScroll.add(profileBucket, gbc_profileBucket);
		GridBagLayout gbl_profileBucket = new GridBagLayout();
		gbl_profileBucket.columnWidths = new int[]{0};
		gbl_profileBucket.rowHeights = new int[] {0};
		profileBucket.setLayout(gbl_profileBucket);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(5, 5, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		profileBucket.add(lblUsername, gbc_lblUsername);
		
		JLabel lblNameText = new JLabel(user.getUsername());
		lblNameText.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblNameText = new GridBagConstraints();
		gbc_lblNameText.anchor = GridBagConstraints.WEST;
		gbc_lblNameText.insets = new Insets(5, 5, 5, 5);
		gbc_lblNameText.gridx = 1;
		gbc_lblNameText.gridy = 0;
		profileBucket.add(lblNameText, gbc_lblNameText);
		
		JLabel lblFullName = new JLabel("Full Name:");
		lblFullName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFullName.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblFullName = new GridBagConstraints();
		gbc_lblFullName.insets = new Insets(5, 5, 5, 5);
		gbc_lblFullName.anchor = GridBagConstraints.EAST;
		gbc_lblFullName.gridx = 0;
		gbc_lblFullName.gridy = 1;
		profileBucket.add(lblFullName, gbc_lblFullName);
		
		JLabel lblUsernameText = new JLabel(user.getFullName());
		lblUsernameText.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblUsernameText = new GridBagConstraints();
		gbc_lblUsernameText.insets = new Insets(5, 5, 5, 5);
		gbc_lblUsernameText.anchor = GridBagConstraints.WEST;
		gbc_lblUsernameText.gridx = 1;
		gbc_lblUsernameText.gridy = 1;
		profileBucket.add(lblUsernameText, gbc_lblUsernameText);
		
		JButton btnEditName = new JButton("Edit Name");
		btnEditName.setFont(WineHunterApplication.format.getBaseFont());
		GridBagConstraints gbc_btnEditName = new GridBagConstraints();
		gbc_btnEditName.insets = new Insets(5, 5, 5, 5);
		gbc_btnEditName.anchor = GridBagConstraints.WEST;
		gbc_btnEditName.gridx = 2;
		gbc_btnEditName.gridy = 1;
		profileBucket.add(btnEditName, gbc_btnEditName);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(5, 5, 5, 5);
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 2;
		profileBucket.add(lblEmail, gbc_lblEmail);
		
		JLabel lblEmailtext = new JLabel(user.getEmail());
		lblEmailtext.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblEmailtext = new GridBagConstraints();
		gbc_lblEmailtext.insets = new Insets(5, 5, 5, 5);
		gbc_lblEmailtext.anchor = GridBagConstraints.WEST;
		gbc_lblEmailtext.gridx = 1;
		gbc_lblEmailtext.gridy = 2;
		profileBucket.add(lblEmailtext, gbc_lblEmailtext);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(5, 5, 5, 5);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 3;
		profileBucket.add(lblPassword, gbc_lblPassword);
		
		JLabel lblPasswordText = new JLabel("(protected)");
		lblPasswordText.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblPasswordText = new GridBagConstraints();
		gbc_lblPasswordText.insets = new Insets(5, 5, 5, 5);
		gbc_lblPasswordText.anchor = GridBagConstraints.WEST;
		gbc_lblPasswordText.gridx = 1;
		gbc_lblPasswordText.gridy = 3;
		profileBucket.add(lblPasswordText, gbc_lblPasswordText);
		
		JButton btnEditPassword = new JButton("Edit Password");
		btnEditPassword.setFont(WineHunterApplication.format.getBaseFont());
		GridBagConstraints gbc_btnEditPassword = new GridBagConstraints();
		gbc_btnEditPassword.insets = new Insets(5, 5, 5, 5);
		gbc_btnEditPassword.anchor = GridBagConstraints.WEST;
		gbc_btnEditPassword.gridx = 2;
		gbc_btnEditPassword.gridy = 3;
		profileBucket.add(btnEditPassword, gbc_btnEditPassword);
		
		JLabel lblAdmin = new JLabel("User Type:");
		lblAdmin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAdmin.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblAdmin = new GridBagConstraints();
		gbc_lblAdmin.insets = new Insets(5, 5, 5, 5);
		gbc_lblAdmin.anchor = GridBagConstraints.EAST;
		gbc_lblAdmin.gridx = 0;
		gbc_lblAdmin.gridy = 4;
		profileBucket.add(lblAdmin, gbc_lblAdmin);
		
		JLabel lblAdminText = new JLabel("No");
		lblAdminText.setFont(WineHunterApplication.format.getSubheadingFontBase());
		if (user.getSuperAdmin() == 1) {
			lblAdminText.setForeground(Color.RED);
			lblAdminText.setFont(WineHunterApplication.format.getSubheadingFont());
			lblAdminText.setText("Administrator");
		}
		else if (user.getAdmin() == 1) {
			lblAdminText.setForeground(Color.BLUE);
			lblAdminText.setFont(WineHunterApplication.format.getSubheadingFont());
			lblAdminText.setText("Moderator");
		}
		GridBagConstraints gbc_lblAdminText = new GridBagConstraints();
		gbc_lblAdminText.insets = new Insets(5, 5, 5, 5);
		gbc_lblAdminText.anchor = GridBagConstraints.WEST;
		gbc_lblAdminText.gridx = 1;
		gbc_lblAdminText.gridy = 4;
		profileBucket.add(lblAdminText, gbc_lblAdminText);
		
		gbc.gridy = 5;
		userInfoScroll.add(sep, gbc);
		
		//our taster profile panel
		JPanel tasterProfile = new JPanel();
		GridBagConstraints gbc_tasterProfile = new GridBagConstraints();
		gbc_tasterProfile.insets = new Insets(5, 5, 5, 5);
		gbc_tasterProfile.fill = GridBagConstraints.BOTH;
		gbc_tasterProfile.gridx = 0;
		gbc_tasterProfile.gridy = 6;
		gbc_tasterProfile.weightx = 1;
		userInfoScroll.add(tasterProfile, gbc_tasterProfile);
		GridBagLayout gbl_tasterProfile = new GridBagLayout();
		gbl_tasterProfile.columnWidths = new int[] {0};
		gbl_tasterProfile.rowHeights = new int[]{0};
		gbl_tasterProfile.columnWeights = new double[]{1.0};
		gbl_tasterProfile.rowWeights = new double[]{1.0};
		tasterProfile.setLayout(gbl_tasterProfile);
		
		String[] sysVarietyLikeLabels = createVarietyLabelArray(user.getSysLikeVarietyList(), "No system-generated liked varieties.");
		String[] sysVarietyDislikeLabels = createVarietyLabelArray(user.getSysDislikeVarietyList(), "No system-generated disliked varieties.");
		String[] userVarietyLikeLabels = createVarietyLabelArray(user.getUserLikeVarietyList(), "No user-generated liked varieties.");
		String[] userVarietyDislikeLabels = createVarietyLabelArray(user.getUserDislikeVarietyList(), "No user-generated disliked varieties.");
		String[] sysKeywordLikeLabels = createKeywordLabelArray(user.getSysLikeKeywordList(), "No system-generated liked keywords.");
		String[] sysKeywordDislikeLabels = createKeywordLabelArray(user.getSysDislikeKeywordList(), "No system-generated disliked keywords.");
		String[] userKeywordLikeLabels = createKeywordLabelArray(user.getUserLikeKeywordList(), "No user-generated liked keywords.");
		String[] userKeywordDislikeLabels = createKeywordLabelArray(user.getUserDislikeKeywordList(), "No user-generated disliked keywords.");
		
		//set up variety panel
		buildCategoryPanel(tasterProfile, sysVarietyLikeLabels, sysVarietyDislikeLabels, userVarietyLikeLabels,
				userVarietyDislikeLabels, "Grape Variety Preferences",0);
		
		//set up keyword panel
		buildCategoryPanel(tasterProfile, sysKeywordLikeLabels, sysKeywordDislikeLabels, userKeywordLikeLabels,
				userKeywordDislikeLabels, "Wine Keyword Preferences",1);
		

	}

	/**
	 * Builds the panel for a particular category
	 * @param parentPanel The parent panel where it will be placed
	 * @param sysLikeLabels labels for the sys like columns
	 * @param sysDislikeLabels labels for the sys dislike columns
	 * @param userLikeLabels labels for the user like columns
	 * @param userDislikeLabels labels for the user dislike columns
	 * @param row in the taster profile
	 */
	public void buildCategoryPanel(JPanel parentPanel, String[] sysLikeLabels, String[] sysDislikeLabels,
			String[] userLikeLabels, String[] userDislikeLabels, String title, int row) {
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 0;
		gbc_panel.gridy = row;
		gbc_panel.weightx = 1;
		parentPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0};
		gbl_panel.rowHeights = new int[]{0};
		panel.setLayout(gbl_panel);
		
		JLabel lblTitlePreferences = DefaultComponentFactory.getInstance().createTitle(title);
		lblTitlePreferences.setFont(WineHunterApplication.format.getHeadingFont());
		lblTitlePreferences.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTitlePreferences = new GridBagConstraints();
		gbc_lblTitlePreferences.insets = new Insets(5, 5, 5, 5);
		gbc_lblTitlePreferences.gridx = 0;
		gbc_lblTitlePreferences.gridy = 0;
		panel.add(lblTitlePreferences, gbc_lblTitlePreferences);
		
		JLabel lblGap3Pref = new JLabel();
		lblGap3Pref.setMinimumSize(new Dimension(5,5));
		GridBagConstraints gbc_lblGap3Pref = new GridBagConstraints();
		gbc_lblGap3Pref.insets = new Insets(5, 5, 5, 5);
		gbc_lblGap3Pref.gridx = 0;
		gbc_lblGap3Pref.gridy = 1;
		panel.add(lblGap3Pref, gbc_lblGap3Pref);
		
		//build out our liked panel
		buildListPanel(panel, sysLikeLabels, userLikeLabels, "Likes", 2);
		
		JLabel lblGapPref = new JLabel();
		lblGapPref.setMinimumSize(new Dimension(5,5));
		GridBagConstraints gbc_lblGapPref = new GridBagConstraints();
		gbc_lblGapPref.insets = new Insets(5, 5, 5, 5);
		gbc_lblGapPref.gridx = 0;
		gbc_lblGapPref.gridy = 4;
		panel.add(lblGapPref, gbc_lblGapPref);
		
		// build out our disliked panel
		buildListPanel(panel, sysDislikeLabels, userDislikeLabels, "Dislikes", 5);
		
		JLabel lblGap2Pref = new JLabel();
		lblGap2Pref.setMinimumSize(new Dimension(5,5));
		GridBagConstraints gbc_lblGap2Pref = new GridBagConstraints();
		gbc_lblGap2Pref.insets = new Insets(5, 5, 5, 5);
		gbc_lblGap2Pref.gridx = 0;
		gbc_lblGap2Pref.gridy = 7;
		panel.add(lblGap2Pref, gbc_lblGap2Pref);
	}

	/**
	 * Builds a panel with the user and system lists for a category in the taster profile
	 * @param panelParent parent panel this panel will be added to
	 * @param sysLabels array of labels for the system column
	 * @param userLabels array of labels for the user column
	 * @param row where to add the list
	 * @param title Like or dislike
	 */
	public void buildListPanel(JPanel panelParent, String[] sysLabels, String[] userLabels, String title, int row) {
		JPanel panel = new JPanel();
		
		JLabel lblPanels = new JLabel(title);
		lblPanels.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanels.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblPanels = new GridBagConstraints();
		gbc_lblPanels.insets = new Insets(5, 5, 5, 5);
		gbc_lblPanels.gridx = 0;
		gbc_lblPanels.gridy = row;
		gbc_lblPanels.weightx = 1;
		panelParent.add(lblPanels, gbc_lblPanels);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.CENTER;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = row + 1;
		gbc_panel.weightx = 1;
		panelParent.add(panel, gbc_panel);
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[] {0.5, 0.5};
		panel.setLayout(gbl_panel);
		
		JLabel lblSysGenPanel = new JLabel("System-Generated");
		lblSysGenPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblSysGenPanel.setFont(WineHunterApplication.format.getSubheadingFont2());
		GridBagConstraints gbc_lblSysGenPanel = new GridBagConstraints();
		gbc_lblSysGenPanel.insets = new Insets(5, 5, 5, 5);
		gbc_lblSysGenPanel.gridx = 0;
		gbc_lblSysGenPanel.gridy = 0;
		gbc_lblSysGenPanel.weightx = 1;
		panel.add(lblSysGenPanel, gbc_lblSysGenPanel);
		
		JLabel lblUserGenPanel = new JLabel("User-Generated");
		lblSysGenPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserGenPanel.setFont(WineHunterApplication.format.getSubheadingFont2());
		GridBagConstraints gbc_lblUserGenPanel = new GridBagConstraints();
		gbc_lblUserGenPanel.insets = new Insets(5, 5, 5, 5);
		gbc_lblUserGenPanel.gridx = 1;
		gbc_lblUserGenPanel.gridy = 0;
		gbc_lblUserGenPanel.weightx = 1;
		panel.add(lblUserGenPanel, gbc_lblUserGenPanel);
		
		buildList(panel, sysLabels, userLabels);

	}

	/**
	 * builds a list of up to 5 varieties or keywords depending on the context
	 * @param panel panel this is being added to
	 * @param labels array of labels to be added
	 * @param userLabels column where we should add the list (0 for user, 1 for system)
	 */
	public void buildList(JPanel panel, String[] sysLabels, String[] userLabels) {
	
	for (int i = 0; i < 5; ++i) {
		String sysText = sysLabels[i];
		String userText = userLabels[i];
		if (sysText.equals("") && userText.equals("")) {
			return;
		}
		buildListMemberPanel(panel, sysLabels[i], i + 1, 0);
		buildListMemberPanel(panel, userLabels[i], i + 1, 1);
	}
		
	}

	/**
	 * Builds a specific list entry with the provided text and panel information
	 * @param row where the value will be added
	 * @param panel panel this is being added to
	 * @param text label to be added
	 * @param column column where we should add the list (1 for user, 2 for system)
	 * 
	 */
	public void buildListMemberPanel(JPanel panel, String text, int row, int column) {
		JLabel newLabel = new JLabel(text);
		newLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newLabel.setFont(WineHunterApplication.format.getSubheadingFont3Base());
		GridBagConstraints gbc_newLabel = new GridBagConstraints();
		gbc_newLabel.insets = new Insets(1, 5, 1, 5);
		gbc_newLabel.gridx = column;
		gbc_newLabel.gridy = row;
		gbc_newLabel.weightx = 1;
		panel.add(newLabel, gbc_newLabel);
	}

	private String[] createVarietyLabelArray(ArrayList<Variety> varietyList, String string) {
		String[] returnArray = new String[5];
		int size = varietyList.size();
		
		for (int i = 0; i < 5; ++i) {
			if (i < size) {
				returnArray[i] = varietyList.get(i).getName();
			}
			else if ((i == 0) && (size == 0)) {
				returnArray[i] = string;
			}
			else {
				returnArray[i] = "";
			}
		}
		
		return returnArray;
	}
	
	private String[] createKeywordLabelArray(ArrayList<Keyword> keywordList, String string) {
		String[] returnArray = new String[5];
		int size = keywordList.size();
		
		for (int i = 0; i < 5; ++i) {

			if (i < size) {
				returnArray[i] = keywordList.get(i).getWord();
			}
			else if ((i == 0) && (size == 0)) {
				returnArray[i] = string;
			}
			else {
				returnArray[i] = "";
			}
		}
		
		return returnArray;
	}

	
}
