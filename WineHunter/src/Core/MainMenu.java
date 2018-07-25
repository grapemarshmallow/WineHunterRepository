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
	 */
	public MainMenu() {
		
		String labelText = "Welcome, " + WineHunterApplication.userSession.getUserInfo().get("USERNAME") + "!";
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {WineHunterApplication.APPLICATION_WIDTH};
		gridBagLayout.rowHeights = new int[]{26, 26, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
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
