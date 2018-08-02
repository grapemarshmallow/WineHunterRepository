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
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;

public class WriteWineReview extends JPanel {
	
	/**
	 * Create the panel to write and edit a wine review
	 * @param wineID
	 * @param userID
	 * @param score - current score
	 * @param wineName
	 * @param notes - current notes
	 * @param invalid - 0 if ok 1 if we got here because of invalid inputs, 2 if updated successfully
	 */
	private static final long serialVersionUID = 462987850014607520L;
	private JTextField newScore;
	private JTextField newNotes; 
	private int updateScore;

	public WriteWineReview(int wineID, int userID, int score, String wineName, String notes, int invalid) {
		//new
		this.setPreferredSize(WineHunterApplication.mainDimension);
		this.setMaximumSize(new Dimension(WineHunterApplication.APPLICATION_WIDTH, WineHunterApplication.APPLICATION_MAIN_HEIGHT - 50));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new MigLayout("fill", "100%" , "100%"));
		
		JLabel lblWineSearch = new JLabel("Currently reviewing " + wineName);
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
		//end
		
		if(invalid ==1) {
			JLabel lblInvalid = new JLabel("Please put valid input before updating");
			lblInvalid.setName("lblInvalid");
			lblInvalid.setHorizontalAlignment(SwingConstants.TRAILING);
			lblInvalid.setFont(WineHunterApplication.format.getSubheadingFont());
			GridBagConstraints gbc_lblInvalid = new GridBagConstraints();
			gbc_lblInvalid.anchor = GridBagConstraints.EAST;
			gbc_lblInvalid.insets = new Insets(5, 5, 5, 5);
			gbc_lblInvalid.gridx = 0;
			gbc_lblInvalid.gridy = 0;
			buttonPanel.add(lblInvalid, gbc_lblInvalid);
		}
		if(invalid == 2) {
			JLabel lblSuccess = new JLabel("Update successful!");
			lblSuccess.setName("gbc_lblSuccess");
			lblSuccess.setHorizontalAlignment(SwingConstants.TRAILING);
			lblSuccess.setFont(WineHunterApplication.format.getSubheadingFont());
			GridBagConstraints gbc_lblSuccess = new GridBagConstraints();
			gbc_lblSuccess.anchor = GridBagConstraints.EAST;
			gbc_lblSuccess.insets = new Insets(5, 5, 5, 5);
			gbc_lblSuccess.gridx = 0;
			gbc_lblSuccess.gridy = 0;
			buttonPanel.add(lblSuccess, gbc_lblSuccess);
		}
		
		JLabel lblScore = new JLabel("Current score: ");
		lblScore.setName("lblScore");
		lblScore.setHorizontalAlignment(SwingConstants.TRAILING);
		lblScore.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblScore = new GridBagConstraints();
		gbc_lblScore.anchor = GridBagConstraints.EAST;
		gbc_lblScore.insets = new Insets(5, 5, 5, 5);
		gbc_lblScore.gridx = 0;
		gbc_lblScore.gridy = 2;
		buttonPanel.add(lblScore, gbc_lblScore);
		
		String userScore = "(not yet rated)";
		if(score != 0) {
			userScore = Integer.toString(score);
		}
		JLabel lblScoreVal = new JLabel(userScore);
		lblScoreVal.setName("lblScoreVal");
		lblScoreVal.setHorizontalAlignment(SwingConstants.TRAILING);
		lblScoreVal.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblScoreVal = new GridBagConstraints();
		gbc_lblScoreVal.anchor = GridBagConstraints.EAST;
		gbc_lblScoreVal.insets = new Insets(5, 5, 5, 5);
		gbc_lblScoreVal.gridx = 1;
		gbc_lblScoreVal.gridy = 2;
		buttonPanel.add(lblScoreVal, gbc_lblScoreVal);
		
		JLabel lblNewScore = new JLabel("New Score: ");
		lblNewScore.setName("lblNewScore");
		lblNewScore.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewScore.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblNewScore = new GridBagConstraints();
		gbc_lblNewScore.anchor = GridBagConstraints.EAST;
		gbc_lblNewScore.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewScore.gridx = 0;
		gbc_lblNewScore.gridy = 3;
		buttonPanel.add(lblNewScore, gbc_lblNewScore);
		
		newScore = new JTextField();
		lblNewScore.setLabelFor(newScore);
		newScore.setColumns(10);
		newScore.setName("newScore");
		newScore.setHorizontalAlignment(SwingConstants.TRAILING);
		newScore.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblNewScoreTxt = new GridBagConstraints();
		gbc_lblNewScoreTxt.anchor = GridBagConstraints.EAST;
		gbc_lblNewScoreTxt.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewScoreTxt.gridx = 1;
		gbc_lblNewScoreTxt.gridy = 3;
		buttonPanel.add(newScore, gbc_lblNewScoreTxt);
		
		JButton btnScore = new JButton("Update Score!");
		btnScore.setName("btnScore");
		btnScore.setFont(WineHunterApplication.format.getBaseFont());
		btnScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int validScore = 1; //0 if invalid, 1 if valid
				if(newScore.getText().isEmpty()) {
					//invalid to have empty score in updating
					validScore = 0; 
					
				}
				else {
					try {
						updateScore = Integer.parseInt(newScore.getText());
					} catch (NumberFormatException e){
						validScore = 0; 
					}
				}
				if(validScore == 0) {
					WineHunterApplication.writeReview(wineID, userID,score, wineName, notes,1);
				}
				else {
					try {
						updateUserWineScore(userID,wineID, updateScore);
						WineHunterApplication.writeReview(wineID, userID,updateScore, wineName, notes,2);
					} catch (SQLException e) {
						e.printStackTrace();
					} 
					
				}
				
			}
		}); 
		GridBagConstraints gbc_btnScore = new GridBagConstraints();
		gbc_btnScore.insets = new Insets(5, 5, 5, 0);
		gbc_btnScore.anchor = GridBagConstraints.WEST;
		gbc_btnScore.gridx = 2;
		gbc_btnScore.gridy = 3;
		buttonPanel.add(btnScore, gbc_btnScore);
		
		JLabel lblNotes = new JLabel("Current notes: ");
		lblNotes.setName("lblNotes");
		lblNotes.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNotes.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblNotes = new GridBagConstraints();
		gbc_lblNotes.anchor = GridBagConstraints.EAST;
		gbc_lblNotes.insets = new Insets(5, 5, 5, 5);
		gbc_lblNotes.gridx = 0;
		gbc_lblNotes.gridy = 4;
		buttonPanel.add(lblNotes, gbc_lblNotes);
		//panel.add(lblScore);
		
		JLabel lblNotesVal = new JLabel(notes);
		lblNotesVal.setName("lblNotesVal");
		lblNotesVal.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNotesVal.setFont(WineHunterApplication.format.getSubheadingFontBase());
		GridBagConstraints gbc_lblNotesVal = new GridBagConstraints();
		gbc_lblNotesVal.anchor = GridBagConstraints.EAST;
		gbc_lblNotesVal.insets = new Insets(5, 5, 5, 5);
		gbc_lblNotesVal.gridx = 1;
		gbc_lblNotesVal.gridy = 4;
		buttonPanel.add(lblNotesVal, gbc_lblNotesVal);
		
		JLabel lblNewNotes = new JLabel("New Notes: ");
		lblNewNotes.setName("lblNewNotes");
		lblNewNotes.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewNotes.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblNewNotes = new GridBagConstraints();
		gbc_lblNewNotes.anchor = GridBagConstraints.EAST;
		gbc_lblNewNotes.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewNotes.gridx = 0;
		gbc_lblNewNotes.gridy = 5;
		buttonPanel.add(lblNewNotes, gbc_lblNewNotes);
		
		newNotes = new JTextField();
		lblNewScore.setLabelFor(newNotes);
		newNotes.setColumns(10);
		newNotes.setName("newNotes");
		newNotes.setHorizontalAlignment(SwingConstants.TRAILING);
		newNotes.setFont(WineHunterApplication.format.getSubheadingFont());
		GridBagConstraints gbc_lblNewNoteTxt = new GridBagConstraints();
		gbc_lblNewNoteTxt.anchor = GridBagConstraints.EAST;
		gbc_lblNewNoteTxt.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewNoteTxt.gridx = 1;
		gbc_lblNewNoteTxt.gridy = 5;
		buttonPanel.add(newNotes, gbc_lblNewNoteTxt);
		
		JButton btnNotes = new JButton("Update Notes!");
		btnNotes.setName("btnNotes");
		btnNotes.setFont(WineHunterApplication.format.getBaseFont());
		btnNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String updateNote = ""; 
				if(!newNotes.getText().isEmpty()) {
					updateNote = newNotes.getText(); 
				}
				try {
					updateUserWineNotes(userID,wineID, updateNote);
					WineHunterApplication.writeReview(wineID, userID,score, wineName, updateNote,2);
				} catch (SQLException e) {
					e.printStackTrace();
				} 
				
			}
		}); 
		GridBagConstraints gbc_btnNotes = new GridBagConstraints();
		gbc_btnNotes.insets = new Insets(5, 5, 5, 0);
		gbc_btnNotes.anchor = GridBagConstraints.WEST;
		gbc_btnNotes.gridx = 2;
		gbc_btnNotes.gridy = 5;
		buttonPanel.add(btnNotes, gbc_btnNotes);

		JButton btnBack = new JButton("Go back to wine page");
		btnBack.setName("btnBack");
		btnBack.setFont(WineHunterApplication.format.getBaseFont());
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WineHunterApplication.viewWine(wineID,userID); 
			}
		}); 
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(5, 5, 5, 0);
		gbc_btnBack.anchor = GridBagConstraints.WEST;
		gbc_btnBack.gridx = 1;
		gbc_btnBack.gridy = 6;
		buttonPanel.add(btnBack, gbc_btnBack);
	}
	/**
	 * Update userwine with the new score
	 * 
	 * @param userID
	 * @param wineID
	 * @param score to update
	 * @return F if fail, T if success
	 * @throws SQLException
	 */
	public boolean updateUserWineScore(int userID, int wineID, int score) throws SQLException {
		boolean result = true;
		
		//update userWine
		try {
			CallableStatement cStmt = WineHunterApplication.connection.getConnection().prepareCall("{call updateUserWineScore(?, ?, ?)}");
			cStmt.setInt(1, userID);
			cStmt.setInt(2, wineID);
			cStmt.setInt(3, score);
			cStmt.execute();
			cStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false; 
		}
		
		//update the user's top varieties
		try {
			CallableStatement cStmt2 = WineHunterApplication.connection.getConnection().prepareCall("{call updateTopVariety(?)}");
			cStmt2.setInt(1, userID);
			cStmt2.execute();
			cStmt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false; 
		}
		
		//update the user's bottom varieties
		
		try {
			CallableStatement cStmt3 = WineHunterApplication.connection.getConnection().prepareCall("{call updateBottomVariety(?)}");
			cStmt3.setInt(1, userID);
			cStmt3.execute(); 
			cStmt3.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false; 
		}
		
		//update the user's top keywords
		
		try {
			CallableStatement cStmt4 = WineHunterApplication.connection.getConnection().prepareCall("{call updateTopKeyword(?)}");
			cStmt4.setInt(1, userID);
			cStmt4.execute(); 
			cStmt4.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false; 
		}
		
		//update the user's bottom keywords
		
		try {
			CallableStatement cStmt5 = WineHunterApplication.connection.getConnection().prepareCall("{call updateBottomKeyword(?)}");
			cStmt5.setInt(1, userID);
			cStmt5.execute(); 
			cStmt5.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false; 
		}
		
		return result;
		
	}
	
	/**
	 * Update userwine with the new notes
	 * 
	 * @param userID
	 * @param wineID
	 * @param notes
	 * @return F if fail, T if success
	 * @throws SQLException
	 */
	public boolean updateUserWineNotes(int userID, int wineID, String notes) throws SQLException {
		boolean result = false;
		
		//update userWine
		try {
			CallableStatement cStmt = WineHunterApplication.connection.getConnection().prepareCall("{call updateUserWineNotes(?, ?, ?)}");
			cStmt.setInt(1, userID);
			cStmt.setInt(2, wineID);
			cStmt.setString(3, notes);
			cStmt.execute();
			cStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false; 
		}
		
		return result;
		
	}
}
