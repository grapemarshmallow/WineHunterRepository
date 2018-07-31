package UserFunctions.GUI;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;


import com.jgoodies.forms.factories.DefaultComponentFactory;

import Core.WineHunterApplication;
import Search.Logic.WineSearch;
import UserFunctions.Logic.UserProfile;
import UserFunctions.Logic.UserUpdate;
import WineObjects.Keyword;
import WineObjects.User;
import WineObjects.Variety;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;

public class ViewUserProfile extends JPanel {
	
	//fields
	private static final long serialVersionUID = -4768934525868550643L;
	

	/**
	 * Create the panel with a specific user object.
	 * @param user the user object to generate the profile for
	 * @param subsequent 0 for the first visit, various flags otherwise
	 * @param editMode 0 for view, 1 for password, 2 for full name
	 */
	public ViewUserProfile(User user, int subsequent, int editMode) {
		this.setPreferredSize(WineHunterApplication.mainDimension);
		this.setMaximumSize(new Dimension(WineHunterApplication.APPLICATION_WIDTH, WineHunterApplication.APPLICATION_MAIN_HEIGHT - 50));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new MigLayout("fill", "100%" , "100%"));
		
		JScrollPane userScroll = new JScrollPane();
		userScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		userScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		userScroll.setViewportBorder(null);
		userScroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		userScroll.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		userScroll.setName("userScroll");
		
		JViewport scrollPort = new JViewport();
		scrollPort.setName("scrollPort");
		
		scrollPort.setPreferredSize(new Dimension(userScroll.getWidth(), userScroll.getHeight()));
		scrollPort.setMaximumSize(new Dimension(WineHunterApplication.APPLICATION_WIDTH, WineHunterApplication.APPLICATION_MAIN_HEIGHT - 100));
		
		JPanel userInfoScroll = new JPanel();
		userInfoScroll.setName("userInfoScroll");
		
		GridBagLayout gbl_userInfoScroll = new GridBagLayout();
		gbl_userInfoScroll.columnWidths = new int[]{0};
		gbl_userInfoScroll.rowHeights = new int[]{0};
		userInfoScroll.setLayout(gbl_userInfoScroll);
		
		userScroll.setViewport(scrollPort);
		userScroll.setViewportView(userInfoScroll);
		
		this.add(userScroll, "width 100%, height 90%");
		
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

	    
		String profileString = "Your User Profile";
		
		
		if (subsequent == 0) {
			if (other == 1) {
				profileString = "User Profile for user "+ user.getId() + " (" + user.getUsername() + ")";
			}
		}
		else {
			if (subsequent == 1) {
				profileString = "User deletion failed.";
				
			}
			else if (subsequent == 2) {
				profileString = "User update failed.";
				
			}
			else if (subsequent == 3) {
				profileString = "Update successful!";
				
			}
			else if (subsequent == 4) {
				profileString = "Insufficient security for update.";
				
			}
			else if (subsequent == 5) {
				profileString = "Could not search for user reviews.";
			}
			else if (subsequent == 6) {
				profileString = "Could not search for user favorites.";
			}
			else if (subsequent == 7) {
				profileString = "Could not search for wines using user taster profile.";
			}
			else if (subsequent == 8) {
				profileString = "No wines found.";
			}
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
		profileTitle.setName("profileTitle");
		profileTitle.setPreferredSize(new Dimension(scrollPort.getWidth(),20));
		GridBagConstraints gbc_profileTitle = new GridBagConstraints();
		gbc_profileTitle.fill = GridBagConstraints.BOTH;
		gbc_profileTitle.insets = new Insets(5, 5, 5, 5);
		gbc_profileTitle.anchor = GridBagConstraints.CENTER;
		gbc_profileTitle.gridx = 0;
		gbc_profileTitle.gridy = 0;
		gbc_profileTitle.weightx = 1;
		
		userInfoScroll.add(profileTitle, gbc_profileTitle);
		GridBagLayout gbl_profileTitle = new GridBagLayout();
		gbl_profileTitle.columnWidths = new int[]{0};
		gbl_profileTitle.rowHeights = new int[] {0};
		profileTitle.setLayout(gbl_profileTitle);
		
		
		JLabel userProfileTitle = new JLabel(profileString);
		userProfileTitle.setPreferredSize(new Dimension(scrollPort.getWidth(),20));
		userProfileTitle.setName("user profile title");
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
		
		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		sep.setName("Profile Separator");
	    GridBagConstraints gbc_sep = new GridBagConstraints();
	    sep.setPreferredSize(new Dimension(scrollPort.getWidth(),5));
	    gbc_sep.fill = GridBagConstraints.HORIZONTAL;
	    gbc_sep.gridx = 0;
	    gbc_sep.gridy = 0;
	    gbc_sep.weightx = 1;
		userInfoScroll.add(sep, gbc_sep);
		
		buildButtonPanel(user, userInfoScroll, isSuperAdmin, userUpdate);
		
		JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
		sep2.setName("Profile Separator2");
	    GridBagConstraints gbc_sep2 = new GridBagConstraints();
	    sep2.setPreferredSize(new Dimension(scrollPort.getWidth(),5));
	    gbc_sep2.fill = GridBagConstraints.HORIZONTAL;
	    gbc_sep2.weightx = 1;
		gbc_sep2.gridy = 3;
		gbc_sep2.gridx = 0;
		userInfoScroll.add(sep2, gbc_sep2);
		
		buildProfileBucket(user, userInfoScroll, editMode, userUpdate);
		
		JSeparator sep3 = new JSeparator(SwingConstants.HORIZONTAL);
		sep3.setName("Profile Separator3");
	    GridBagConstraints gbc_sep3 = new GridBagConstraints();
	    sep3.setPreferredSize(new Dimension(scrollPort.getWidth(),5));
	    gbc_sep3.fill = GridBagConstraints.HORIZONTAL;
	    gbc_sep3.weightx = 1;
		gbc_sep3.gridy = 5;
		gbc_sep3.gridx = 0;
		userInfoScroll.add(sep3, gbc_sep3);
		
		//our taster profile panel
		buildTasterProfile(user, userInfoScroll);
		

	}

	/**
	 * Modularizes building our profile bucket
	 * @param user the profile is for
	 * @param userInfoScroll parent panel
	 * @param editMode 0 for view, 1 for password, 2 for full name
	 * @param the userupdate object
	 */
	public static void buildProfileBucket(User user, JPanel userInfoScroll, int editMode, UserUpdate userUpdate) {
		JPanel profileBucket = new JPanel();
		profileBucket.setName("profileBucket");
		GridBagConstraints gbc_profileBucket = new GridBagConstraints();
		gbc_profileBucket.fill = GridBagConstraints.BOTH;
		gbc_profileBucket.anchor = GridBagConstraints.CENTER;
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
		lblUsername.setName("lblUsername");
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(5, 5, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		profileBucket.add(lblUsername, gbc_lblUsername);
		
		JLabel lblNameText = new JLabel(user.getUsername());
		lblNameText.setHorizontalAlignment(SwingConstants.LEADING);
		lblNameText.setName("lblNameText");
		lblNameText.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblNameText = new GridBagConstraints();
		gbc_lblNameText.anchor = GridBagConstraints.WEST;
		gbc_lblNameText.insets = new Insets(5, 5, 5, 5);
		gbc_lblNameText.gridx = 1;
		gbc_lblNameText.gridy = 0;
		profileBucket.add(lblNameText, gbc_lblNameText);
		
		JLabel lblFullName = new JLabel("Full Name:");
		lblFullName.setName("lblFullName");
		lblFullName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFullName.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblFullName = new GridBagConstraints();
		gbc_lblFullName.insets = new Insets(5, 5, 5, 5);
		gbc_lblFullName.anchor = GridBagConstraints.EAST;
		gbc_lblFullName.gridx = 0;
		gbc_lblFullName.gridy = 1;
		profileBucket.add(lblFullName, gbc_lblFullName);
		
		if (editMode == 2) {
			JTextField usernameField = new JTextField();
			usernameField.setName("usernameField");
			usernameField.setText(user.getFullName());
			GridBagConstraints gbc_usernameField = new GridBagConstraints();
			gbc_usernameField.insets = new Insets(5, 5, 5, 5);
			gbc_usernameField.fill = GridBagConstraints.WEST;
			gbc_usernameField.gridx = 1;
			gbc_usernameField.gridy = 1;
			profileBucket.add(usernameField, gbc_usernameField);
			usernameField.setColumns(10);
			
			JButton btnEditName = new JButton("Save Name");
			btnEditName.setName("btnEditName");
			btnEditName.setFont(WineHunterApplication.format.getBaseFont());
			btnEditName.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = 0;
					try {
						result = userUpdate.setUserInfoString(user, 2, usernameField.getText());
						
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
					if (result == 1) {
						WineHunterApplication.viewUserProfile(user, 3, 0);
					}
					else if (result == -1) {
						WineHunterApplication.viewUserProfile(user, 4, 0);
					}
					else {
						WineHunterApplication.viewUserProfile(user, 2, 0);
					}
				}
			});
			GridBagConstraints gbc_btnEditName = new GridBagConstraints();
			gbc_btnEditName.insets = new Insets(5, 5, 5, 0);
			gbc_btnEditName.anchor = GridBagConstraints.WEST;
			gbc_btnEditName.gridx = 2;
			gbc_btnEditName.gridy = 1;
			profileBucket.add(btnEditName, gbc_btnEditName);
		}
		else {
			
			JLabel lblUsernameText = new JLabel(user.getFullName());
			lblUsernameText.setName("lblUsernameText");
			lblUsernameText.setHorizontalAlignment(SwingConstants.LEADING);
			lblUsernameText.setFont(WineHunterApplication.format.getSubheadingFontBase());
			GridBagConstraints gbc_lblUsernameText = new GridBagConstraints();
			gbc_lblUsernameText.insets = new Insets(5, 5, 5, 5);
			gbc_lblUsernameText.anchor = GridBagConstraints.WEST;
			gbc_lblUsernameText.gridx = 1;
			gbc_lblUsernameText.gridy = 1;
			profileBucket.add(lblUsernameText, gbc_lblUsernameText);
			
			JButton btnEditName = new JButton("Edit Name");
			btnEditName.setName("btnEditName");
			btnEditName.setFont(WineHunterApplication.format.getBaseFont());
			btnEditName.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WineHunterApplication.viewUserProfile(user, 0, 2);
				}
			});
			GridBagConstraints gbc_btnEditName = new GridBagConstraints();
			gbc_btnEditName.insets = new Insets(5, 5, 5, 5);
			gbc_btnEditName.anchor = GridBagConstraints.WEST;
			gbc_btnEditName.gridx = 2;
			gbc_btnEditName.gridy = 1;
			profileBucket.add(btnEditName, gbc_btnEditName);
		}
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setName("lblEmail");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(5, 5, 5, 5);
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 2;
		profileBucket.add(lblEmail, gbc_lblEmail);
		
		JLabel lblEmailtext = new JLabel(user.getEmail());
		lblEmailtext.setName("lblEmailtext");
		lblEmailtext.setHorizontalAlignment(SwingConstants.LEADING);
		lblEmailtext.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblEmailtext = new GridBagConstraints();
		gbc_lblEmailtext.insets = new Insets(5, 5, 5, 5);
		gbc_lblEmailtext.anchor = GridBagConstraints.WEST;
		gbc_lblEmailtext.gridx = 1;
		gbc_lblEmailtext.gridy = 2;
		profileBucket.add(lblEmailtext, gbc_lblEmailtext);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setName("lblPassword");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(5, 5, 5, 5);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 3;
		profileBucket.add(lblPassword, gbc_lblPassword);
		
		if (editMode == 1) { // updating password
		
			JPasswordField passwordField = new JPasswordField();
			passwordField.setName("passwordField");
			passwordField.setToolTipText("Enter your password.");
			passwordField.setActionCommand("password");
			GridBagConstraints gbc_passwordField = new GridBagConstraints();
			gbc_passwordField.insets = new Insets(0, 0, 5, 5);
			gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_passwordField.gridx = 1;
			gbc_passwordField.gridy = 3;
			profileBucket.add(passwordField, gbc_passwordField);
			
			JButton btnEditPassword = new JButton("Save Password");
			btnEditPassword.setName("btnEditPassword");
			btnEditPassword.setFont(WineHunterApplication.format.getBaseFont());
			btnEditPassword.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = 0;
					try {
						result = userUpdate.setUserInfoString(user, 1, new String(passwordField.getPassword()));
						
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
					if (result == 1) {
						WineHunterApplication.viewUserProfile(user, 3, 0);
					}
					else if (result == -1) {
						WineHunterApplication.viewUserProfile(user, 4, 0);
					}
					else {
						WineHunterApplication.viewUserProfile(user, 2, 0);
					}
				}
			});
			GridBagConstraints gbc_btnEditPassword = new GridBagConstraints();
			gbc_btnEditPassword.insets = new Insets(5, 5, 5, 0);
			gbc_btnEditPassword.anchor = GridBagConstraints.WEST;
			gbc_btnEditPassword.gridx = 2;
			gbc_btnEditPassword.gridy = 3;
			profileBucket.add(btnEditPassword, gbc_btnEditPassword);
		}
		else {
			
			JLabel lblPasswordText = new JLabel("(protected)");
			lblPasswordText.setName("lblPasswordText");
			lblPasswordText.setHorizontalAlignment(SwingConstants.LEADING);
			lblPasswordText.setFont(WineHunterApplication.format.getSubheadingFontBase());
			GridBagConstraints gbc_lblPasswordText = new GridBagConstraints();
			gbc_lblPasswordText.insets = new Insets(5, 5, 5, 5);
			gbc_lblPasswordText.anchor = GridBagConstraints.WEST;
			gbc_lblPasswordText.gridx = 1;
			gbc_lblPasswordText.gridy = 3;
			profileBucket.add(lblPasswordText, gbc_lblPasswordText);
			
			JButton btnEditPassword = new JButton("Edit Password");
			btnEditPassword.setName("btnEditPassword");
			btnEditPassword.setFont(WineHunterApplication.format.getBaseFont());
			btnEditPassword.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WineHunterApplication.viewUserProfile(user, 0, 1);
				}
			});
			GridBagConstraints gbc_btnEditPassword = new GridBagConstraints();
			gbc_btnEditPassword.insets = new Insets(5, 5, 5, 5);
			gbc_btnEditPassword.anchor = GridBagConstraints.WEST;
			gbc_btnEditPassword.gridx = 2;
			gbc_btnEditPassword.gridy = 3;
			profileBucket.add(btnEditPassword, gbc_btnEditPassword);
		}
		
		JLabel lblAdmin = new JLabel("User Type:");
		lblAdmin.setName("lblAdmin");
		lblAdmin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAdmin.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblAdmin = new GridBagConstraints();
		gbc_lblAdmin.insets = new Insets(5, 5, 5, 5);
		gbc_lblAdmin.anchor = GridBagConstraints.EAST;
		gbc_lblAdmin.gridx = 0;
		gbc_lblAdmin.gridy = 4;
		profileBucket.add(lblAdmin, gbc_lblAdmin);
		
		JLabel lblAdminText = new JLabel("Member");
		lblAdminText.setName("lblAdminText");
		lblAdminText.setHorizontalAlignment(SwingConstants.TRAILING);
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
	}

	/**
	 * Modularizes building the taster profile panel for use everywhere.
	 * @param user we are building the taster profile for
	 * @param userInfoScroll parent panel
	 */
	public static void buildTasterProfile(User user, JPanel userInfoScroll) {
		JPanel tasterProfile = new JPanel();
		tasterProfile.setName("tasterProfile");
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
	 * Modularizes building the button panel for reuse everywhere
	 * @param user we are building the panel for
	 * @param userInfoScroll parent panel
	 * @param isSuperAdmin if viewing user is a super admin
	 * @param userUpdate a userUpdate object
	 */
	public static void buildButtonPanel(User user, JPanel userInfoScroll, boolean isSuperAdmin, UserUpdate userUpdate) {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setName("buttonPanel");
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
		
		JButton btnViewUserReviews = new JButton("View User-Reviewed Wines");
		btnViewUserReviews.setName("btnViewUserReviews");
		btnViewUserReviews.setFont(WineHunterApplication.format.getBaseFont());
		btnViewUserReviews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				WineHunterApplication.wineSearch = new WineSearch();
				try {
					result = WineHunterApplication.wineSearch.wineSearchUserReviewed(user);
				} catch (SQLException e2) {
					WineHunterApplication.viewUserProfile(user, 5, 0);
					e2.printStackTrace();
				}
				
				if(result == 0) {
					WineHunterApplication.viewUserProfile(user, 8, 0);
				}
				else {
					String[][] data = WineHunterApplication.wineSearch.getResults();
					String[] columnNames = WineHunterApplication.wineSearch.getColumns();
					int [] wineIDs = WineHunterApplication.wineSearch.getWineIDs();
					WineHunterApplication.showWines(data,columnNames,wineIDs); 
				}
			}
		});
		GridBagConstraints gbc_btnViewUserReviews = new GridBagConstraints();
		gbc_btnViewUserReviews.insets = new Insets(5, 5, 5, 5);
		gbc_btnViewUserReviews.gridx = 0;
		gbc_btnViewUserReviews.gridy = 0;
		buttonPanel.add(btnViewUserReviews, gbc_btnViewUserReviews);
		
		JButton btnViewUserFavorites = new JButton("View User Favorites");
		btnViewUserFavorites.setName("btnViewUserFavorites");
		btnViewUserFavorites.setFont(WineHunterApplication.format.getBaseFont());
		btnViewUserFavorites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				WineHunterApplication.wineSearch = new WineSearch();
				try {
					result = WineHunterApplication.wineSearch.wineSearchUserFavorites(user);
				} catch (SQLException e2) {
					WineHunterApplication.viewUserProfile(user, 6, 0);
					e2.printStackTrace();
				}
				
				if(result == 0) {
					WineHunterApplication.viewUserProfile(user, 8, 0);
				}
				else {
					String[][] data = WineHunterApplication.wineSearch.getResults();
					String[] columnNames = WineHunterApplication.wineSearch.getColumns();
					int [] wineIDs = WineHunterApplication.wineSearch.getWineIDs();
					WineHunterApplication.showWines(data,columnNames, wineIDs); 
				}
			}
		});
		GridBagConstraints gbc_btnViewUserFavorites = new GridBagConstraints();
		gbc_btnViewUserFavorites.insets = new Insets(5, 5, 5, 5);
		gbc_btnViewUserFavorites.gridx = 1;
		gbc_btnViewUserFavorites.gridy = 0;
		buttonPanel.add(btnViewUserFavorites, gbc_btnViewUserFavorites);
		
		JButton btnEditTasterProfile = new JButton("Edit Taster Profile");
		btnEditTasterProfile.setName("btnEditTasterProfile");
		btnEditTasterProfile.setFont(WineHunterApplication.format.getBaseFont());
		btnEditTasterProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WineHunterApplication.editTasterProfile(user);
			}
		});
		GridBagConstraints gbc_btnEditTasterProfile = new GridBagConstraints();
		gbc_btnEditTasterProfile.insets = new Insets(5, 5, 5, 5);
		gbc_btnEditTasterProfile.gridx = 2;
		gbc_btnEditTasterProfile.gridy = 0;
		buttonPanel.add(btnEditTasterProfile, gbc_btnEditTasterProfile);
		
		JButton btnSearchTasterProfile = new JButton("Search With Taster Profile");
		btnSearchTasterProfile.setName("btnSearchTasterProfile");
		btnSearchTasterProfile.setFont(WineHunterApplication.format.getBaseFont());
		btnSearchTasterProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				WineHunterApplication.wineSearch = new WineSearch();
				try {
					result = WineHunterApplication.wineSearch.wineSearchTasterProfile(user);
					
				} catch (SQLException e2) {
					WineHunterApplication.viewUserProfile(user, 7, 0);
					e2.printStackTrace();
				}
				
				if (result == 0) {
					WineHunterApplication.viewUserProfile(user, 8, 0);
				}
				else {
					String[][] data = WineHunterApplication.wineSearch.getResults();
					String[] columnNames = WineHunterApplication.wineSearch.getColumns();
					int [] wineIDs = WineHunterApplication.wineSearch.getWineIDs();
					WineHunterApplication.showWines(data,columnNames, wineIDs); 
				}
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
			btnToggleAdmin.setName("btnToggleAdmin");
			btnToggleAdmin.setFont(WineHunterApplication.format.getBaseFont());
			btnToggleAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int res = userUpdate.setAdminSecurity(user);
						if (res == 1) {
							WineHunterApplication.viewUserProfile(user, 3, 0);
						}
						else if (res == 0) {
							WineHunterApplication.viewUserProfile(user, 4, 0);
						}
						else {
							WineHunterApplication.viewUserProfile(user, 2, 0);
						}
					} catch (SQLException e1) {
						WineHunterApplication.viewUserProfile(user, 2, 0);
						e1.printStackTrace();
					}
				}
			});
			GridBagConstraints gbc_btnToggleAdmin = new GridBagConstraints();
			gbc_btnToggleAdmin.insets = new Insets(5, 5, 5, 5);
			gbc_btnToggleAdmin.gridx = 1;
			gbc_btnToggleAdmin.gridy = 2;
			buttonPanel.add(btnToggleAdmin, gbc_btnToggleAdmin);
			
			JButton btnDeleteUser = new JButton("Delete User");
			btnDeleteUser.setName("btnDeleteUser");
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
								WineHunterApplication.viewUserProfile(user, 2, 0);
							}
							else {
								WineHunterApplication.splash(1);
							}
						} catch (SQLException e1) {
							WineHunterApplication.viewUserProfile(user, 2, 0);
							e1.printStackTrace();
						}
					}
					else {
						WineHunterApplication.viewUserProfile(user, 0, 0);
					}
				}
			});
			GridBagConstraints gbc_btnDeleteUser = new GridBagConstraints();
			gbc_btnDeleteUser.insets = new Insets(5, 5, 5, 5);
			gbc_btnDeleteUser.gridx = 2;
			gbc_btnDeleteUser.gridy = 2;
			buttonPanel.add(btnDeleteUser, gbc_btnDeleteUser);
		}
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
	public static void buildCategoryPanel(JPanel parentPanel, String[] sysLikeLabels, String[] sysDislikeLabels,
			String[] userLikeLabels, String[] userDislikeLabels, String title, int row) {
		JPanel panel = new JPanel();
		panel.setName(title + " panel");
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 0;
		gbc_panel.gridy = row;
		gbc_panel.weightx = 1;
		gbc_panel.fill = GridBagConstraints.BOTH;
		parentPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.rowHeights = new int[]{0};
		panel.setLayout(gbl_panel);
		
		JLabel lblTitlePreferences = DefaultComponentFactory.getInstance().createTitle(title);
		lblTitlePreferences.setName("label " + title);
		lblTitlePreferences.setFont(WineHunterApplication.format.getHeadingFont());
		lblTitlePreferences.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTitlePreferences = new GridBagConstraints();
		gbc_lblTitlePreferences.insets = new Insets(15, 15, 15, 15);
		gbc_lblTitlePreferences.gridx = 0;
		gbc_lblTitlePreferences.gridy = 0;
		gbc_lblTitlePreferences.weightx = 1;
		gbc_lblTitlePreferences.fill = GridBagConstraints.BOTH;
		panel.add(lblTitlePreferences, gbc_lblTitlePreferences);
		
		//build out our liked panel
		buildListPanel(panel, sysLikeLabels, userLikeLabels, "Likes", 1);
		
		// build out our disliked panel
		buildListPanel(panel, sysDislikeLabels, userDislikeLabels, "Dislikes", 3);
		
	}

	/**
	 * Builds a panel with the user and system lists for a category in the taster profile
	 * @param panelParent parent panel this panel will be added to
	 * @param sysLabels array of labels for the system column
	 * @param userLabels array of labels for the user column
	 * @param row where to add the list
	 * @param title Like or dislike
	 */
	public static void buildListPanel(JPanel panelParent, String[] sysLabels, String[] userLabels, String title, int row) {
		JPanel panel = new JPanel();
		panel.setName(title + " panel");
		
		JLabel lblPanels = new JLabel(title);
		lblPanels.setName(title + " label");
		lblPanels.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanels.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblPanels = new GridBagConstraints();
		gbc_lblPanels.insets = new Insets(15, 15, 15, 15);
		gbc_lblPanels.gridx = 0;
		gbc_lblPanels.gridy = row;
		gbc_lblPanels.weightx = 1;
		gbc_lblPanels.fill = GridBagConstraints.BOTH;
		panelParent.add(lblPanels, gbc_lblPanels);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.CENTER;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = row + 1;
		gbc_panel.weightx = 1;
		gbc_panel.fill = GridBagConstraints.BOTH;
		panelParent.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel sysPanel = new JPanel();
		sysPanel.setName(title + " sysPanel");
		panel.add(sysPanel);
		
		sysPanel.setLayout(new GridLayout(6, 1, 0, 0));
		
		JPanel userPanel = new JPanel();
		userPanel.setName(title + " userPanel");

		panel.add(userPanel);
		
		userPanel.setLayout(new GridLayout(6, 1, 0, 0));
		
		JLabel lblSysGenPanel = new JLabel("System-Generated");
		lblSysGenPanel.setName(title + " lblSysGenPanel");
		lblSysGenPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblSysGenPanel.setFont(WineHunterApplication.format.getSubheadingFont2());
		GridBagConstraints gbc_lblSysGenPanel = new GridBagConstraints();
		gbc_lblSysGenPanel.insets = new Insets(5, 5, 5, 5);
		gbc_lblSysGenPanel.gridx = 0;
		gbc_lblSysGenPanel.gridy = 0;
		gbc_lblSysGenPanel.weightx = 1;
		gbc_lblSysGenPanel.anchor = GridBagConstraints.CENTER;
		gbc_lblSysGenPanel.fill = GridBagConstraints.BOTH;
		sysPanel.add(lblSysGenPanel, gbc_lblSysGenPanel);
		
		JLabel lblUserGenPanel = new JLabel("User-Generated");
		lblUserGenPanel.setName(title + " lblUserGenPanel");
		lblUserGenPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserGenPanel.setFont(WineHunterApplication.format.getSubheadingFont2());
		GridBagConstraints gbc_lblUserGenPanel = new GridBagConstraints();
		gbc_lblUserGenPanel.insets = new Insets(5, 5, 5, 5);
		gbc_lblUserGenPanel.gridx = 0;
		gbc_lblUserGenPanel.gridy = 0;
		gbc_lblUserGenPanel.weightx = 1;
		gbc_lblUserGenPanel.anchor = GridBagConstraints.CENTER;
		gbc_lblUserGenPanel.fill = GridBagConstraints.BOTH;
		userPanel.add(lblUserGenPanel, gbc_lblUserGenPanel);
		
		buildList(sysPanel, sysLabels, userLabels);
		buildList(userPanel, userLabels, sysLabels);

	}

	/**
	 * builds a list of up to 5 varieties or keywords depending on the context
	 * @param panel panel this is being added to
	 * @param labels array of labels to be added
	 */
	public static void buildList(JPanel panel, String[] labels, String[] otherLabels) {
	
	for (int i = 0; i < 5; ++i) {
		String text = labels[i];
		String otherText = otherLabels[i];
		if (text.equals("") && otherText.equals("")) {
			return;
		}
		buildListMemberPanel(panel, text, i + 1);
	}
		
	}

	/**
	 * Builds a specific list entry with the provided text and panel information
	 * @param row where the value will be added
	 * @param panel panel this is being added to
	 * @param text label to be added
	 * 
	 */
	public static void buildListMemberPanel(JPanel panel, String text, int row) {
		JLabel rowLabel = new JLabel(text);
		rowLabel.setName("rowLabel r: " + row + " text: " + text);
		rowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rowLabel.setFont(WineHunterApplication.format.getSubheadingFont3Base());
		rowLabel.setMinimumSize(new Dimension(panel.getWidth(), 15));
		GridBagConstraints gbc_rowLabel = new GridBagConstraints();
		gbc_rowLabel.insets = new Insets(1, 5, 1, 5);
		gbc_rowLabel.gridx = 0;
		gbc_rowLabel.gridy = row;
		gbc_rowLabel.weightx = 1;
		gbc_rowLabel.fill = GridBagConstraints.BOTH;
		gbc_rowLabel.anchor = GridBagConstraints.CENTER;
		panel.add(rowLabel, gbc_rowLabel);
	}

	private static String[] createVarietyLabelArray(ArrayList<Variety> varietyList, String string) {
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
	
	private static String[] createKeywordLabelArray(ArrayList<Keyword> keywordList, String string) {
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
				returnArray[i] = " ";
			}
		}
		
		return returnArray;
	}

	
}
