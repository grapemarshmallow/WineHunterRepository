package Search.GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


import javax.swing.JLabel;

import Core.WineHunterApplication;


import javax.swing.SwingConstants;

import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

import javax.swing.JTable;


public class ViewWineResults extends JPanel {

	private static final long serialVersionUID = 2747473463278030742L;
	private JTable table;
	
	/**
	 * Create the panel to view results
	 * @param data
	 * @param columnNames
	 * @param empty
	 */
	public ViewWineResults(String[][] data, String[] columnNames) {
		
		this.setMaximumSize(new Dimension(WineHunterApplication.APPLICATION_WIDTH - 150, WineHunterApplication.APPLICATION_HEIGHT - 150));

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[]{0};
		
		
		JLabel lblNewLabel = new JLabel("Your results");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		
		this.add(lblNewLabel, gbc_lblNewLabel);
		
		table = new JTable(data,columnNames);
		table.setMaximumSize(new Dimension(WineHunterApplication.APPLICATION_WIDTH - 300, WineHunterApplication.APPLICATION_HEIGHT - 300));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setMaximumSize(new Dimension(WineHunterApplication.APPLICATION_WIDTH - 200, WineHunterApplication.APPLICATION_HEIGHT - 200));
		this.add(scrollPane);
		
		
	}

}
