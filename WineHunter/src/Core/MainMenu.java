/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             MainMenu.java
 * Semester:         Summer 2018
 *
 *
 * Author:           Orbi Ish-Shalom (oishshalom@wisc.edu)
 * CS Login:         orbi
 * Lecturer's Name:  Hien Hguyen
 *
 *                    PAIR PROGRAMMERS COMPLETE THIS SECTION
 *  Pair Partner:     Jennifer Shih
 * //////////////////////////// 80 columns wide //////////////////////////////////
 *******************************************************************************/

package Core;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * This class creates the main menu for the application.
 *
 */
public class MainMenu extends JPanel {
	
	//fields
	private static final long serialVersionUID = 8666789356489661025L;

	/**
	 * Create the panel.
	 * @param messages for subsequent visits to this page. 1 if a use was successfully deleted.
	 */
	public MainMenu(int subsequent) {
		
		String labelText = "Welcome, " + WineHunterApplication.userSession.getUser().getFullName() + "!";
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		
		setLayout(gridBagLayout);
		
		if (subsequent == 1) {
			add(new JLabel("User successfully deleted."));
		}
		if (subsequent == 2) {
			add(new JLabel("Error: Try again later."));
		}
	
		
		JLabel lblWelcome = new JLabel(labelText);
		lblWelcome.setFont(WineHunterApplication.format.getHeadingFont());
		lblWelcome.setName("lblWelcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.insets = new Insets(15, 15, 15, 15);
		gbc_lblWelcome.gridx = 0;
		gbc_lblWelcome.gridy = 4;
		gbc_lblWelcome.weightx = 1;
		gbc_lblWelcome.fill = GridBagConstraints.BOTH;
		add(lblWelcome, gbc_lblWelcome);
		
		JLabel lblHelp = new JLabel("Use the toolbar buttons to access the listed functionality.");
		lblHelp.setFont(WineHunterApplication.format.getSubheadingFont2Base());
		lblHelp.setName("lblHelp");
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblHelp = new GridBagConstraints();
		gbc_lblHelp.insets = new Insets(15, 15, 15, 15);
		gbc_lblHelp.gridx = 0;
		gbc_lblHelp.gridy = 6;
		gbc_lblHelp.weightx = 1;
		gbc_lblHelp.fill = GridBagConstraints.BOTH;
		add(lblHelp, gbc_lblHelp);
		
		
		
	}
	
}
