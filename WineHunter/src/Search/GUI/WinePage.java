package Search.GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JButton;

import Core.WineHunterApplication;
import net.miginfocom.swing.MigLayout;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;

public class WinePage extends JPanel {

	private static final long serialVersionUID = 443684491838793168L;
	private String[] userResults; 
	private String[] results; 
	private String varieties; 
	private int reviewID; 
	/**
	 * Create the panel with a specific wine page 
	 * @param wineID
	 * @param userID
	 */
	public WinePage(int wineID, int userID) {
		
		/**
		 * Gather info to display from wines, reviews, etc.
		 */
		results = new String[13]; //see findValues specs below for details about this array
		try {
			results = findValues(wineID);
		} catch (SQLException e) {
			// shouldn't get here...
			e.printStackTrace();
			WineHunterApplication.searchWines(2);
		}
		
		varieties = ""; 
		try {
			varieties = getVariety(wineID);
		} catch (SQLException e) {
			// shouldn't get here...
			e.printStackTrace();
			WineHunterApplication.searchWines(2);
		}
		
		//gather user info on the wine
		//need like/dislike review, favorite, wine review (score, notes)
		userResults = new String[4];
		try {
			userResults = findUserValues(wineID, userID);
		} catch (SQLException e) {
			// shouldn't get here...
			e.printStackTrace();
			WineHunterApplication.searchWines(2);
		}
		
		
		/**
		 * Display everything
		 */
		
		this.setPreferredSize(WineHunterApplication.mainDimension);
		this.setMaximumSize(new Dimension(WineHunterApplication.APPLICATION_WIDTH, WineHunterApplication.APPLICATION_MAIN_HEIGHT - 50));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new MigLayout("fill", "100%" , "100%"));
		
		JLabel lblWineSearch = new JLabel("Wine Details");
		lblWineSearch.setFont(WineHunterApplication.format.getHeadingFont());
		lblWineSearch.setVerticalAlignment(SwingConstants.TOP);
		lblWineSearch.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.add(lblWineSearch, "width 100%, dock north");
		
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
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setName("buttonPanel");
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 0;
		gbc_buttonPanel.weightx = 1;
		userInfoScroll.add(buttonPanel, gbc_buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0};
		gbl_buttonPanel.rowHeights = new int[]{0};

		buttonPanel.setLayout(gbl_buttonPanel);
		
		if(userResults[1] == "favorite") {
			JLabel lblFave = new JLabel("You have favorited this wine!");
			lblFave.setName("lblWine");
			lblFave.setHorizontalAlignment(SwingConstants.TRAILING);
			lblFave.setFont(WineHunterApplication.format.getSubheadingFont());
			GridBagConstraints gbc_lblFave = new GridBagConstraints();
			gbc_lblFave.anchor = GridBagConstraints.EAST;
			gbc_lblFave.insets = new Insets(5, 5, 5, 5);
			gbc_lblFave.gridx = 0;
			gbc_lblFave.gridy = 0;
			buttonPanel.add(lblFave, gbc_lblFave);
		}
		
		//favorite/unfavorite
		String fave = "Favorite";
		if(userResults[1] == "favorite") {
			fave = "Unfavorite";
		}
		JButton btnFavorite = new JButton(fave);
		btnFavorite.setName("btnFavorite");
		btnFavorite.setFont(WineHunterApplication.format.getBaseFont());
		btnFavorite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//update userwine favorite
				try {
					updateFavorite(userID,wineID,userResults[1]);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				//reload page 
				WineHunterApplication.viewWine(wineID,userID); 
			}
		}); 
		GridBagConstraints gbc_btnFavorite = new GridBagConstraints();
		gbc_btnFavorite.insets = new Insets(5, 5, 5, 0);
		gbc_btnFavorite.anchor = GridBagConstraints.WEST;
		gbc_btnFavorite.gridx = 1;
		gbc_btnFavorite.gridy = 0;
		buttonPanel.add(btnFavorite, gbc_btnFavorite);
		
		String userScore = "You have not given this wine a score yet.";
		if(userResults[2] != "N/A") {
			userScore = "You gave this wine a score of " + userResults[2];
		}
		JLabel lblWineScore = new JLabel(userScore);
		lblWineScore.setName("lblWineScore");
		lblWineScore.setHorizontalAlignment(SwingConstants.TRAILING);
		lblWineScore.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblWineScore = new GridBagConstraints();
		gbc_lblWineScore.anchor = GridBagConstraints.EAST;
		gbc_lblWineScore.insets = new Insets(5, 5, 5, 5);
		gbc_lblWineScore.gridx = 0;
		gbc_lblWineScore.gridy = 1;
		buttonPanel.add(lblWineScore, gbc_lblWineScore);
		
		JButton btnWriteReview = new JButton("Write/Edit a review");
		btnWriteReview.setName("btnWriteReview");
		btnWriteReview.setFont(WineHunterApplication.format.getBaseFont());
		btnWriteReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				int scoreInt = 0; 
				if(userResults[2] != "N/A") {
					scoreInt = Integer.parseInt(userResults[2]);
				}
				WineHunterApplication.writeReview(wineID, userID,scoreInt, results[0], userResults[3],0);
			}
		}); 
		GridBagConstraints gbc_btnWriteReview = new GridBagConstraints();
		gbc_btnWriteReview.insets = new Insets(5, 5, 5, 0);
		gbc_btnWriteReview.anchor = GridBagConstraints.WEST;
		gbc_btnWriteReview.gridx = 1;
		gbc_btnWriteReview.gridy = 1;
		buttonPanel.add(btnWriteReview, gbc_btnWriteReview);
		
		
		String userNotes = "You have not written any notes for this wine yet.";
		if(!((userResults[3] != "N/A") || (userResults[3] != "null"))) {
			userNotes = "Your notes: " + userResults[3];
		}
		JLabel lblNotes = new JLabel(userNotes);
		lblNotes.setName("lblWineScore");
		lblNotes.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNotes.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblNotes = new GridBagConstraints();
		gbc_lblNotes.anchor = GridBagConstraints.EAST;
		gbc_lblNotes.insets = new Insets(5, 5, 5, 5);
		gbc_lblNotes.gridx = 0;
		gbc_lblNotes.gridy = 2;
		buttonPanel.add(lblNotes, gbc_lblNotes);
		
		
		//actually display everything
		
		JPanel profileBucket = new JPanel();
		profileBucket.setName("profileBucket");
		GridBagConstraints gbc_profileBucket = new GridBagConstraints();
		gbc_profileBucket.fill = GridBagConstraints.BOTH;
		gbc_profileBucket.anchor = GridBagConstraints.CENTER;
		gbc_profileBucket.insets = new Insets(5, 5, 5, 5);
		gbc_profileBucket.gridx = 0;
		gbc_profileBucket.gridy = 2; //4?
		gbc_profileBucket.weightx = 1;
		userInfoScroll.add(profileBucket, gbc_profileBucket);
		GridBagLayout gbl_profileBucket = new GridBagLayout();
		gbl_profileBucket.columnWidths = new int[]{0};
		gbl_profileBucket.rowHeights = new int[] {0};
		profileBucket.setLayout(gbl_profileBucket);
		
		JLabel lblWine = new JLabel("Wine Name: ");
		lblWine.setName("lblWine");
		lblWine.setHorizontalAlignment(SwingConstants.TRAILING);
		lblWine.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblWine = new GridBagConstraints();
		gbc_lblWine.anchor = GridBagConstraints.EAST;
		gbc_lblWine.insets = new Insets(5, 5, 5, 5);
		gbc_lblWine.gridx = 0;
		gbc_lblWine.gridy = 0;
		profileBucket.add(lblWine, gbc_lblWine);
		
		JLabel lblWineName = new JLabel(results[0]);
		lblWineName.setHorizontalAlignment(SwingConstants.LEADING);
		lblWineName.setName("lblWineName");
		lblWineName.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblWineName = new GridBagConstraints();
		gbc_lblWineName.anchor = GridBagConstraints.WEST;
		gbc_lblWineName.insets = new Insets(5, 5, 5, 5);
		gbc_lblWineName.gridx = 1;
		gbc_lblWineName.gridy = 0;
		profileBucket.add(lblWineName, gbc_lblWineName);
		
		JLabel lblWinery = new JLabel("Winery:");
		lblWinery.setName("lblWinery");
		lblWinery.setHorizontalAlignment(SwingConstants.TRAILING);
		lblWinery.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblWinery = new GridBagConstraints();
		gbc_lblWinery.insets = new Insets(5, 5, 5, 5);
		gbc_lblWinery.anchor = GridBagConstraints.EAST;
		gbc_lblWinery.gridx = 0;
		gbc_lblWinery.gridy = 1;
		profileBucket.add(lblWinery, gbc_lblWinery);
		
		JLabel lblWineryName = new JLabel(results[1]);
		lblWineryName.setHorizontalAlignment(SwingConstants.LEADING);
		lblWineryName.setName("lblWineryName");
		lblWineryName.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblWineryName = new GridBagConstraints();
		gbc_lblWineryName.anchor = GridBagConstraints.WEST;
		gbc_lblWineryName.insets = new Insets(5, 5, 5, 5);
		gbc_lblWineryName.gridx = 1;
		gbc_lblWineryName.gridy = 1;
		profileBucket.add(lblWineryName, gbc_lblWineryName);
		
		JLabel lblVintage = new JLabel("Vintage:");
		lblVintage.setName("lblVintage");
		lblVintage.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVintage.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblVintage = new GridBagConstraints();
		gbc_lblVintage.insets = new Insets(5, 5, 5, 5);
		gbc_lblVintage.anchor = GridBagConstraints.EAST;
		gbc_lblVintage.gridx = 0;
		gbc_lblVintage.gridy = 2;
		profileBucket.add(lblVintage, gbc_lblVintage);
		
		JLabel lblVintageYr = new JLabel(results[2]);
		lblVintageYr.setHorizontalAlignment(SwingConstants.LEADING);
		lblVintageYr.setName("lblVintageYr");
		lblVintageYr.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblVintageYr = new GridBagConstraints();
		gbc_lblVintageYr.anchor = GridBagConstraints.WEST;
		gbc_lblVintageYr.insets = new Insets(5, 5, 5, 5);
		gbc_lblVintageYr.gridx = 1;
		gbc_lblVintageYr.gridy = 2;
		profileBucket.add(lblVintageYr, gbc_lblVintageYr);
		
		JLabel lblVariety = new JLabel("Varieties:");
		lblVariety.setName("lblVariety");
		lblVariety.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVariety.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblVariety = new GridBagConstraints();
		gbc_lblVariety.insets = new Insets(5, 5, 5, 5);
		gbc_lblVariety.anchor = GridBagConstraints.EAST;
		gbc_lblVariety.gridx = 0;
		gbc_lblVariety.gridy = 3;
		profileBucket.add(lblVariety, gbc_lblVariety);
		
		JLabel lblVarietyVal = new JLabel(varieties);
		lblVarietyVal.setHorizontalAlignment(SwingConstants.LEADING);
		lblVarietyVal.setName("lblVarietyVal");
		lblVarietyVal.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblVarietyVal = new GridBagConstraints();
		gbc_lblVarietyVal.anchor = GridBagConstraints.WEST;
		gbc_lblVarietyVal.insets = new Insets(5, 5, 5, 5);
		gbc_lblVarietyVal.gridx = 1;
		gbc_lblVarietyVal.gridy = 3;
		profileBucket.add(lblVarietyVal, gbc_lblVarietyVal);
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setName("lblCountry");
		lblCountry.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCountry.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.insets = new Insets(5, 5, 5, 5);
		gbc_lblCountry.anchor = GridBagConstraints.EAST;
		gbc_lblCountry.gridx = 0;
		gbc_lblCountry.gridy = 4;
		profileBucket.add(lblCountry, gbc_lblCountry);
		
		JLabel lblCountryName = new JLabel(results[3]);
		lblCountryName.setHorizontalAlignment(SwingConstants.LEADING);
		lblCountryName.setName("lblCountryName");
		lblCountryName.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblCountryName = new GridBagConstraints();
		gbc_lblCountryName.anchor = GridBagConstraints.WEST;
		gbc_lblCountryName.insets = new Insets(5, 5, 5, 5);
		gbc_lblCountryName.gridx = 1;
		gbc_lblCountryName.gridy = 4;
		profileBucket.add(lblCountryName, gbc_lblCountryName);
		
		JLabel lblRegion = new JLabel("Region:");
		lblRegion.setName("lblRegion");
		lblRegion.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRegion.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblRegion = new GridBagConstraints();
		gbc_lblRegion.insets = new Insets(5, 5, 5, 5);
		gbc_lblRegion.anchor = GridBagConstraints.EAST;
		gbc_lblRegion.gridx = 0;
		gbc_lblRegion.gridy = 5;
		profileBucket.add(lblRegion, gbc_lblRegion);
		
		JLabel lblRegionName = new JLabel(results[4]);
		lblRegionName.setHorizontalAlignment(SwingConstants.LEADING);
		lblRegionName.setName("lblRegionName");
		lblRegionName.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblRegionName = new GridBagConstraints();
		gbc_lblRegionName.anchor = GridBagConstraints.WEST;
		gbc_lblRegionName.insets = new Insets(5, 5, 5, 5);
		gbc_lblRegionName.gridx = 1;
		gbc_lblRegionName.gridy = 5;
		profileBucket.add(lblRegionName, gbc_lblRegionName);
		
		JLabel lblProvince = new JLabel("Province:");
		lblProvince.setName("lblProvince");
		lblProvince.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProvince.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblProvince = new GridBagConstraints();
		gbc_lblProvince.insets = new Insets(5, 5, 5, 5);
		gbc_lblProvince.anchor = GridBagConstraints.EAST;
		gbc_lblProvince.gridx = 0;
		gbc_lblProvince.gridy = 6;
		profileBucket.add(lblProvince, gbc_lblProvince);
		
		JLabel lblProvinceName = new JLabel(results[5]);
		lblProvinceName.setHorizontalAlignment(SwingConstants.LEADING);
		lblProvinceName.setName("lblProvinceName");
		lblProvinceName.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblProvinceName = new GridBagConstraints();
		gbc_lblProvinceName.anchor = GridBagConstraints.WEST;
		gbc_lblProvinceName.insets = new Insets(5, 5, 5, 5);
		gbc_lblProvinceName.gridx = 1;
		gbc_lblProvinceName.gridy = 6;
		profileBucket.add(lblProvinceName, gbc_lblProvinceName);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setName("lblPrice");
		lblPrice.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrice.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.insets = new Insets(5, 5, 5, 5);
		gbc_lblPrice.anchor = GridBagConstraints.EAST;
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 7;
		profileBucket.add(lblPrice, gbc_lblPrice);
		
		JLabel lblPriceVal = new JLabel(results[6]);
		lblPriceVal.setHorizontalAlignment(SwingConstants.LEADING);
		lblPriceVal.setName("lblPriceVal");
		lblPriceVal.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblPriceVal = new GridBagConstraints();
		gbc_lblPriceVal.anchor = GridBagConstraints.WEST;
		gbc_lblPriceVal.insets = new Insets(5, 5, 5, 5);
		gbc_lblPriceVal.gridx = 1;
		gbc_lblPriceVal.gridy = 7;
		profileBucket.add(lblPriceVal, gbc_lblPriceVal);
		
		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		sep.setName("Wine Review Separator");
	    GridBagConstraints gbc_sep = new GridBagConstraints();
	    sep.setPreferredSize(new Dimension(scrollPort.getWidth(),5));
	    gbc_sep.fill = GridBagConstraints.HORIZONTAL;
	    gbc_sep.gridx = 0;
	    gbc_sep.gridy = 3;
	    gbc_sep.weightx = 1;
		userInfoScroll.add(sep, gbc_sep);
		
		//panel for review-user
		JPanel reviewUser = new JPanel();
		reviewUser.setName("reviewUser");
		GridBagConstraints gbc_reviewUser = new GridBagConstraints();
		gbc_reviewUser.fill = GridBagConstraints.BOTH;
		gbc_reviewUser.anchor = GridBagConstraints.CENTER;
		gbc_reviewUser.insets = new Insets(5, 5, 5, 5);
		gbc_reviewUser.gridx = 0;
		gbc_reviewUser.gridy = 4; 
		gbc_reviewUser.weightx = 1;
		userInfoScroll.add(reviewUser, gbc_reviewUser);
		GridBagLayout gbl_reviewUser = new GridBagLayout();
		gbl_reviewUser.columnWidths = new int[]{0};
		gbl_reviewUser.rowHeights = new int[] {0};
		reviewUser.setLayout(gbl_reviewUser);
		
		JLabel lblUserLike = new JLabel("You " + userResults[0] + " this review.");
		lblUserLike.setName("lblUserLike");
		lblUserLike.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserLike.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblUserLike = new GridBagConstraints();
		gbc_lblUserLike.anchor = GridBagConstraints.EAST;
		gbc_lblUserLike.insets = new Insets(5, 5, 5, 5);
		gbc_lblUserLike.gridx = 0;
		gbc_lblUserLike.gridy = 0;
		reviewUser.add(lblUserLike, gbc_lblUserLike);
		
		//like dislike review
		String newlikeDislike = "like";
		if(userResults[0] == "like") {
			newlikeDislike = "dislike";
		}
		
		//new button
		JButton btnLike = new JButton(newlikeDislike);
		reviewID = Integer.parseInt(results[12]); 
		btnLike.setName("btnLike");
		btnLike.setFont(WineHunterApplication.format.getBaseFont());
		btnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update userreview like/dislike + review score
				try {
					updateUserReview(userID,reviewID,userResults[0]);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				//reload page 
				WineHunterApplication.viewWine(wineID,userID); 
			}
		});
		GridBagConstraints gbc_btnLike = new GridBagConstraints();
		gbc_btnLike.insets = new Insets(5, 5, 5, 0);
		gbc_btnLike.anchor = GridBagConstraints.WEST;
		gbc_btnLike.gridx = 1;
		gbc_btnLike.gridy = 0;
		reviewUser.add(btnLike, gbc_btnLike);
		//end
		
		String noscore = "No users have liked/disliked this review";
		int hasScore = 0; 
		if(Double.parseDouble(results[11])> -1) {
			noscore = "% of users who like this review: ";
			hasScore = 1; 
		}
		JLabel lblRScoreTitle = new JLabel(noscore);
		lblRScoreTitle.setName("lblRScoreTitle");
		lblRScoreTitle.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRScoreTitle.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblRScoreTitle = new GridBagConstraints();
		gbc_lblRScoreTitle.anchor = GridBagConstraints.EAST;
		gbc_lblRScoreTitle.insets = new Insets(5, 5, 5, 5);
		gbc_lblRScoreTitle.gridx = 0;
		gbc_lblRScoreTitle.gridy = 1;
		reviewUser.add(lblRScoreTitle, gbc_lblRScoreTitle);
		
		if(hasScore == 1) {
			JLabel lblReviewScore = new JLabel(results[11]);
			lblReviewScore.setName("lblReviewScore");
			lblReviewScore.setHorizontalAlignment(SwingConstants.TRAILING);
			lblReviewScore.setFont(WineHunterApplication.format.getSubheadingFont());
			GridBagConstraints gbc_lblReviewScore = new GridBagConstraints();
			gbc_lblReviewScore.anchor = GridBagConstraints.EAST;
			gbc_lblReviewScore.insets = new Insets(5, 5, 5, 5);
			gbc_lblReviewScore.gridx = 1;
			gbc_lblReviewScore.gridy = 1;
			reviewUser.add(lblReviewScore, gbc_lblReviewScore);
		}
		
		//new panel for review: 
		JPanel reviewBucket = new JPanel();
		reviewBucket.setName("reviewBucket");
		GridBagConstraints gbc_reviewBucket = new GridBagConstraints();
		gbc_reviewBucket.fill = GridBagConstraints.BOTH;
		gbc_reviewBucket.anchor = GridBagConstraints.CENTER;
		gbc_reviewBucket.insets = new Insets(5, 5, 5, 5);
		gbc_reviewBucket.gridx = 0;
		gbc_reviewBucket.gridy = 8; //4?
		gbc_reviewBucket.weightx = 1;
		userInfoScroll.add(reviewBucket, gbc_reviewBucket);
		GridBagLayout gbl_reviewBucket = new GridBagLayout();
		gbl_reviewBucket.columnWidths = new int[]{0};
		gbl_reviewBucket.rowHeights = new int[] {0};
		reviewBucket.setLayout(gbl_reviewBucket);
		
		JLabel lblTaster = new JLabel(results[7] + " gave this: ");
		lblTaster.setName("lblTaster");
		lblTaster.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTaster.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblTaster = new GridBagConstraints();
		gbc_lblTaster.anchor = GridBagConstraints.EAST;
		gbc_lblTaster.insets = new Insets(5, 5, 5, 5);
		gbc_lblTaster.gridx = 0;
		gbc_lblTaster.gridy = 0;
		reviewBucket.add(lblTaster, gbc_lblTaster);
		
		JLabel lblScore = new JLabel(results[10] + " points");
		lblScore.setName("lblScore");
		lblScore.setHorizontalAlignment(SwingConstants.TRAILING);
		lblScore.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblScore = new GridBagConstraints();
		gbc_lblScore.anchor = GridBagConstraints.EAST;
		gbc_lblScore.insets = new Insets(5, 5, 5, 5);
		gbc_lblScore.gridx = 1;
		gbc_lblScore.gridy = 0;
		reviewBucket.add(lblScore, gbc_lblScore);
		
		JLabel lblTwitter = new JLabel("Twitter: ");
		lblTwitter.setName("lblTwitter");
		lblTwitter.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTwitter.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblTwitter = new GridBagConstraints();
		gbc_lblTwitter.anchor = GridBagConstraints.EAST;
		gbc_lblTwitter.insets = new Insets(5, 5, 5, 5);
		gbc_lblTwitter.gridx = 0;
		gbc_lblTwitter.gridy = 1;
		reviewBucket.add(lblTwitter, gbc_lblTwitter);
		
		JLabel lblTwitterHandle = new JLabel(results[8]);
		lblTwitterHandle.setName("lblTwitterHandle");
		lblTwitterHandle.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTwitterHandle.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblTwitterHandle = new GridBagConstraints();
		gbc_lblTwitterHandle.anchor = GridBagConstraints.EAST;
		gbc_lblTwitterHandle.insets = new Insets(5, 5, 5, 5);
		gbc_lblTwitterHandle.gridx = 1;
		gbc_lblTwitterHandle.gridy = 1;
		reviewBucket.add(lblTwitterHandle, gbc_lblTwitterHandle);
		
		
		//new panel to show review text
		JPanel reviewText = new JPanel();
		reviewText.setName("reviewText");
		GridBagConstraints gbc_reviewText = new GridBagConstraints();
		gbc_reviewText.fill = GridBagConstraints.BOTH;
		gbc_reviewText.anchor = GridBagConstraints.CENTER;
		gbc_reviewText.insets = new Insets(5, 5, 5, 5);
		gbc_reviewText.gridx = 0;
		gbc_reviewText.gridy = 12; 
		gbc_reviewText.weightx = 1;
		userInfoScroll.add(reviewText, gbc_reviewText);
		GridBagLayout gbl_reviewText = new GridBagLayout();
		gbl_reviewText.columnWidths = new int[]{0};
		gbl_reviewText.rowHeights = new int[] {0};
		reviewText.setLayout(gbl_reviewText);
		
		JLabel lblText = new JLabel("Review: ");
		lblText.setName("lblText");
		lblText.setHorizontalAlignment(SwingConstants.TRAILING);
		lblText.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblText = new GridBagConstraints();
		gbc_lblText.anchor = GridBagConstraints.EAST;
		gbc_lblText.insets = new Insets(5, 5, 5, 5);
		gbc_lblText.gridx = 0;
		gbc_lblText.gridy = 0;
		reviewText.add(lblText, gbc_lblText);
		
		JLabel lblTextVal = new JLabel(results[9]);
		lblTextVal.setName("lblTextVal");
		lblTextVal.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTextVal.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblTextVal = new GridBagConstraints();
		gbc_lblTextVal.anchor = GridBagConstraints.EAST;
		gbc_lblTextVal.insets = new Insets(5, 5, 5, 5);
		gbc_lblTextVal.gridx = 1;
		gbc_lblTextVal.gridy = 0;
		reviewText.add(lblTextVal, gbc_lblTextVal);
	}
	
	/**
	 * Get all the attributes for the wine
	 * @param wineID
	 * @return String [] with the following features - index
	 * 0 - Wine Name 
	 * 1 - Winery
	 * 2 - Vintage
	 * 3 - Country 
	 * 4 - Region
	 * 5 - Province
	 * 6 - Price
	 * 7 - Taster Name
	 * 8 - Taster Twitter 
	 * 9 - Review text 
	 * 10 - Points
	 * 11 - Score 
	 * 12 - ReviewID
	 */
	public String[] findValues(int wineID) throws SQLException {
		String[] data = new String[13]; 
		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
		String sql; 
		sql = "SELECT *" + 
				" FROM wine w INNER JOIN wineries wy ON w.wineryID=wy.wineryID" +
				" INNER JOIN winereview wr ON wr.wineID = w.wineID" + 
				" INNER JOIN region r ON r.RegionID = wy.RegionID" + 
				" INNER JOIN province p ON p.ProvinceID = wy.ProvinceID" + 
				" INNER JOIN country c ON c.CountryID = p.CountryID"+
				" INNER JOIN taster t ON t.TasterID = wr.TasterID"+
				" WHERE w.wineID = "+ wineID; 
		
		ResultSet rs = stmt.executeQuery(sql);
		
		//there should be only one result
		
		rs.first();

		String wineName = rs.getString("wineName");
		int vintageval = rs.getInt("vintage");
		double price = rs.getDouble("price");
		String wineryName = rs.getString("wineryName");
		String countryName = rs.getString("countryName");
		String provinceName = rs.getString("provinceName");
		String regionName = rs.getString("regionName");
		String Taster = rs.getString("TasterName");
		String Twitter = rs.getString("TasterTwitter"); 
		String ReviewText = rs.getString("Text");
		double Score = rs.getDouble("Score")*100;
		int Points = rs.getInt("Points");
		int reviewID = rs.getInt("ReviewID");
		
		
		data[0] = wineName;
		data[1] = wineryName;
		if(vintageval == 0) {
			data[2] = "UNKNOWN";
		}
		else {
			data[2] = Integer.toString(vintageval);
		}
		data[3] = countryName;
		data[4] = regionName;
		data[5] = provinceName;
		if(price == -1) {
			data[6] = "UNKNOWN";
		}
		else {
			data[6] = "$" + Double.toString(price) + "0";
		}
		data[7] = Taster;
		if(Twitter.isEmpty()) {
			data[8]= "N/A";
		}
		else {
			data[8] = Twitter; 
		}
		
		ReviewText = String.format("<html><div WIDTH=%d>%s</div><html>", 500, ReviewText);
		data[9] = ReviewText; 
		data[10] = Integer.toString(Points);
		data[11] = Double.toString(Score);
		data[12] = Integer.toString(reviewID); 
		
		rs.close();

		stmt.close();
		
		return data; 
		
	}
	
	/**
	 * 
	 * @param wineID
	 * @return
	 * @throws SQLException
	 */
	public String getVariety(int wineID) throws SQLException {
		String variety = ""; 
		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
		String sql; 
		
		sql = "SELECT *" + 
				" FROM wine w INNER JOIN WineVariety v ON w.wineID = v.wineID" +
				" INNER JOIN varieties vs ON vs.varietyID = v.varietyID" + 
				" WHERE w.wineID = "+ wineID; 
		
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.last(); 
		
		int size = rs.getRow();
		
		rs.beforeFirst();
		
		
		if (size <= 0) {
			rs.close();
			
			stmt.close();
			return variety;
		}
		
		int first = 1; //set to 0 when not first anymore 
		
		while(rs.next()){
		
			String varietyName = rs.getString("varietyName");
			if (first == 1) {
				first = 0; 
			}
			else {
				variety = variety + ", ";
			}
			variety = variety + varietyName; 
		}
		
		rs.close();

		stmt.close();
		
		return variety; 
	}
	/**
	 * Get all the attributes for the user for the wine
	 * @param wineID
	 * @param userID
	 * @return String [] with the following features - index
	 * 0 - like (1)/dislike (2) review
	 * 1 - Favorite (1 if yes)
	 * 2 - Score
	 * 3 - Notes  
	 */
	public String[] findUserValues(int wineID, int userID) throws SQLException {
		String[] data = new String[4]; 
		data[0] = "have not liked/disliked";
		data[1] = "not"; 
		data[2] = "N/A"; 
		data[3] = "N/A"; 
		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
		String sql; 
		sql = "SELECT *" + 
				" FROM wine w INNER JOIN winereview wr ON wr.wineID = w.wineID" +
				" LEFT JOIN UserWine uw ON w.wineID = uw.WineID" + 
				" LEFT JOIN UserReview ur ON ur.ReviewID = wr.ReviewID" + 
				" WHERE w.wineID = "+ wineID + " AND (uw.UserID = " + userID + " OR ur.UserID = " + userID + ")"; 
		ResultSet rs = stmt.executeQuery(sql);

		
		//there should be only one or zero results
		
		boolean hasResults = rs.first();

		if (!hasResults) {
			return data; 
		}
		
		int likeDislike = rs.getInt("LikeDislikeWineReview");
		int favorite = rs.getInt("Favorite");
		int score = rs.getInt("UserScore");
		String notes = rs.getString("Notes"); 
		
		if (likeDislike == 1) {
			data[0] = "like";
		}
		else if (likeDislike == 2) {
			data[0] = "dislike";
		}
		
		if(favorite >0) {
			data[1] = "favorite";
		}
		if(score != 0) {
			data[2] = Integer.toString(score);
		}
		data[3] = notes; 
		
		rs.close();

		stmt.close();
	
		
		return data; 
		
	}
	
	
	/**
	 * Update a favorite (favorite if not yet favorited, unfavorite if currently favorited) 
	 * 
	 * @param userID
	 * @param wineID
	 * @param favorite - the current favorite (this function will swap it)
	 * @return F if fail, T if success
	 * @throws SQLException
	 */
	public boolean updateFavorite(int userID, int wineID, String favorite) throws SQLException {
		boolean result = false;
		int newFavorite = 1; 
		
		if (favorite == "favorite") {
			newFavorite = 0; 
		}
		
		try {
			CallableStatement cStmt = WineHunterApplication.connection.getConnection().prepareCall("{call updateFavorite(?, ?, ?)}");
			cStmt.setInt(1, userID);
			cStmt.setInt(2, wineID);
			cStmt.setInt(3, newFavorite);
			cStmt.execute(); 
			cStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false; 
		}
		
		
		return result;
	}
	
	
	/**
	 * Update userReview and review score when user likes/dislikes a review 
	 * 
	 * @param userID
	 * @param reviewID
	 * @param likedislike - the current like/dislike state (this function will swap it)
	 * @return F if fail, T if success
	 * @throws SQLException
	 */
	public boolean updateUserReview(int userID, int reviewID, String likedislike) throws SQLException {
		boolean result = true;
		int newLikeDislike = 1; 
		
		if (likedislike == "like") { //currently like, need to dislike it
			newLikeDislike = 2; 
		}
		
		//update userReview
		try {
			CallableStatement cStmt = WineHunterApplication.connection.getConnection().prepareCall("{call updateUserReview(?, ?, ?)}");
			cStmt.setInt(1, userID);
			cStmt.setInt(2, reviewID);
			cStmt.setInt(3, newLikeDislike);
			cStmt.execute(); 
			cStmt.close();
		} catch (SQLException e) {
			result = false; 
			e.printStackTrace();
		}
		
		//update the review's score
		try {
			CallableStatement cStmt2 = WineHunterApplication.connection.getConnection().prepareCall("{call updateReviewScore(?)}");
			cStmt2.setInt(1, reviewID);
			cStmt2.execute(); 
			cStmt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false; 
		}
		
		return result;
		
	}


}
