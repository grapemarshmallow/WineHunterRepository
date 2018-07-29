package Core;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class MainMenu extends JPanel {
	
	/**
	 * 
	 */
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
	
		
		JLabel lblWelcome = new JLabel(labelText);
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.insets = new Insets(0, 0, 5, 0);
		gbc_lblWelcome.gridx = 0;
		gbc_lblWelcome.gridy = 4;
		add(lblWelcome, gbc_lblWelcome);
		
		JLabel lblNewLabel = new JLabel("Use the toolbar buttons to access the listed functionality.");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 6;
		add(lblNewLabel, gbc_lblNewLabel);
		
		
		
	}
	
}
