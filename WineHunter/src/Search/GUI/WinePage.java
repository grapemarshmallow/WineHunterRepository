package Search.GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		this.setMaximumSize(new Dimension(WineHunterApplication.APPLICATION_WIDTH - 100, WineHunterApplication.APPLICATION_HEIGHT - 100));

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[]{0};
		
		
		JLabel lblNewLabel = new JLabel("Wine Details");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		
		this.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(20);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		
		//gather wine data to put in the window
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
		
		
		//actually display everything
		
		//wine info

		JLabel lblWineName = new JLabel("Wine Name: " + results[0]);
		panel.add(lblWineName);
		
		JLabel lblWinery = new JLabel("Winery: " + results[1]);
		panel.add(lblWinery);
		
		JLabel lblVintage = new JLabel("Vintage: " + results[2]);
		panel.add(lblVintage);
		
		JLabel lblCountry = new JLabel("Country: " + results[3]);
		panel.add(lblCountry);
		
		JLabel lblRegion = new JLabel("Region: " + results[4]);
		panel.add(lblRegion);
		
		JLabel lblProvince = new JLabel("Province: " + results[5]);
		panel.add(lblProvince);
		
		JLabel lblPrice = new JLabel("Price: " + results[6]);
		panel.add(lblPrice);
		
		JLabel lblTaster = new JLabel("Taster Name: " + results[7]);
		panel.add(lblTaster);
		
		JLabel lblTasterTwitter = new JLabel("Taster Twitter: " + results[8]);
		panel.add(lblTasterTwitter);
		
		JLabel lblReview = new JLabel("Review: " + results[9]);
		panel.add(lblReview);
		
		JLabel lblPoint = new JLabel("Points: " + results[10]);
		panel.add(lblPoint);
		
		
		JLabel lblScore = new JLabel("Score: " + results[11] + "%");
		panel.add(lblScore);
		
		JLabel lblVarieties = new JLabel("Grape Varieties: " + varieties);
		panel.add(lblVarieties);

		
		//user info
		JLabel lblLikeDislike = new JLabel("You " + userResults[0] + " this review.");
		panel.add(lblLikeDislike);
		
		if(userResults[1] == "favorite") {
			JLabel lblFave = new JLabel("You have favorited this review.");
			panel.add(lblFave); 
		}
		
		JLabel lblWineScore = new JLabel("You gave this wine a score of " + userResults[2]);
		panel.add(lblWineScore); 
	
		JLabel lblNotes = new JLabel("Your notes: " + userResults[3]);
		panel.add(lblNotes); 
		
		
		//user buttons 
		
		//favorite/unfavorite
		String fave = "Favorite";
		if(userResults[1] == "favorite") {
			fave = "Unfavorite";
		}
		JButton btnFavorite = new JButton(fave);
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
		panel.add(btnFavorite);
		
		
		//write/edit wine review
	
		JButton btnWriteReview = new JButton("Write/Edit a review");
		btnWriteReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int scoreInt = 0; 
				if(userResults[2] != "N/A") {
					scoreInt = Integer.parseInt(userResults[2]);
				}
				WineHunterApplication.writeReview(wineID, userID,scoreInt, results[0], userResults[3],0);
			}
		}); 
		panel.add(btnWriteReview);

		
		//like dislike review
		String newlikeDislike = "like";
		if(userResults[0] == "like") {
			newlikeDislike = "dislike";
		}

		JButton btnLikeDislike = new JButton(newlikeDislike);
		reviewID = Integer.parseInt(results[12]);
		btnLikeDislike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//update userreview like/dislike + review score
				try {
					updateUserReview(userID,reviewID,userResults[0]);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				//reload page 
				WineHunterApplication.viewWine(wineID,userID); 
			}
		}); 
		panel.add(btnLikeDislike);
		
		
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
			data[6] = Double.toString(price);
		}
		data[7] = Taster;
		data[8] = Twitter; 
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
		data[2] = "N/A"; 
		data[3] = "N/A"; 
		
		Statement stmt = WineHunterApplication.connection.getConnection().createStatement();
		
		String sql; 
		sql = "SELECT *" + 
				" FROM wine w INNER JOIN UserWine uw ON w.wineID = uw.WineID" +
				" INNER JOIN winereview wr ON wr.wineID = w.wineID" + 
				" INNER JOIN UserReview ur ON ur.ReviewID = wr.ReviewID" + 
				" WHERE w.wineID = "+ wineID + " AND uw.UserID = " + userID; 
		
		ResultSet rs = stmt.executeQuery(sql);
		
		//there should be only one or zero results
		
		boolean hasResults = rs.first();
		if (!hasResults) {
			return data; 
		}
		
		int likeDislike = rs.getInt("LikeDislikeWineReview");
		int favorite = rs.getInt("Favorite");
		int score = rs.getInt("Score");
		String notes = rs.getString("Notes"); 
		
		if (likeDislike == 1) {
			data[0] = "like";
		}
		else if (likeDislike == 2) {
			data[0] = "dislike";
		}
		else {
			data[0] = "have not liked/disliked"; 
		}
		
		if(favorite >0) {
			data[1] = "favorite";
		}
		else {
			data[1] = "not"; 
		}
		data[2] = Integer.toString(score);
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
