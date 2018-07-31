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
		
		this.setMaximumSize(new Dimension(WineHunterApplication.APPLICATION_WIDTH - 100, WineHunterApplication.APPLICATION_HEIGHT - 100));

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[]{0};
		
		
		JLabel lblNewLabel = new JLabel("Write/Edit Wine Review");
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
		
		JLabel lblInvalid = new JLabel("Please put valid input before updating");
		if(invalid == 1) {
			panel.add(lblInvalid);
		}
		
		JLabel lblSuccess = new JLabel("Update successful!");
		if(invalid == 2) {
			panel.add(lblSuccess);
		}
		
		JLabel lblWineName = new JLabel("Currently reviewing " + wineName);
		panel.add(lblWineName);
		
		JLabel lblScore = new JLabel("Current score is " + score);
		panel.add(lblScore);
		
		JLabel lblNewScore = new JLabel("New Score: ");
		panel.add(lblNewScore);
		
		newScore = new JTextField();
		lblNewScore.setLabelFor(newScore);
		panel.add(newScore);
		newScore.setColumns(10);
		
		JLabel lblNotes = new JLabel("Current notes: " + notes);
		panel.add(lblNotes);
		
		JLabel lblNewNotes = new JLabel("New Notes: ");
		panel.add(lblNewNotes);
		
		newNotes = new JTextField();
		lblNewNotes.setLabelFor(newNotes);
		panel.add(newNotes);
		newNotes.setColumns(10);

		JButton btnScore = new JButton("Update Score!");
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
		panel.add(btnScore);
		
		JButton btnNotes = new JButton("Update Notes!");
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
		panel.add(btnNotes);
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
