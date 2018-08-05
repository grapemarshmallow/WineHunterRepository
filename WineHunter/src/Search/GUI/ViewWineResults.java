/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             ViewWineResults.java
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

package Search.GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import Core.WineHunterApplication;
import Search.Logic.WineSearch;
import WineObjects.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.SwingConstants;

import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

/**
 * This class allows a panel to be created to show results for a wine search.
 *
 */
public class ViewWineResults extends JPanel {
	
	// fields
	private static final long serialVersionUID = 2747473463278030742L;
	private JTable wineInfoScroll;
	
	/**
	 * Create the panel to view results
	 * @param data the wine data to be displayed
	 * @param columnNames names of the table columns
	 * @param empty set if there are no results, otherwise 0
	 * @param user the results are for
	 */
	public ViewWineResults(WineSearch wineSearch, User user) {
		
		String[][] data = wineSearch.getResults();
		String[] columnNames = wineSearch.getColumns();
		int[] wineIDs = wineSearch.getWineIDs();
		this.setPreferredSize(WineHunterApplication.mainDimension);
		this.setMaximumSize(new Dimension(WineHunterApplication.APPLICATION_WIDTH, WineHunterApplication.APPLICATION_MAIN_HEIGHT - 50));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new MigLayout("fill", "100%" , "100%"));
		
		JLabel lblWineSearch = new JLabel("Wine Search Results");
		lblWineSearch.setFont(WineHunterApplication.format.getHeadingFont());
		lblWineSearch.setVerticalAlignment(SwingConstants.TOP);
		lblWineSearch.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.add(lblWineSearch, "width 100%, dock north");
		
		JScrollPane wineScroll = new JScrollPane();
		wineScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		wineScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		wineScroll.setViewportBorder(null);
		wineScroll.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		wineScroll.setName("userScroll");
		
		JViewport scrollPort = new JViewport();
		scrollPort.setName("scrollPort");
		scrollPort.setPreferredSize(new Dimension(wineScroll.getWidth(), wineScroll.getHeight()));
		scrollPort.setMaximumSize(new Dimension(WineHunterApplication.APPLICATION_WIDTH, WineHunterApplication.APPLICATION_MAIN_HEIGHT - 100));

		wineInfoScroll = new JTable(data,columnNames);
		wineInfoScroll.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		wineInfoScroll.setAutoCreateRowSorter(true);

		wineInfoScroll.setName("wineInfoScroll");
		
		wineScroll.setViewport(scrollPort);
		wineScroll.setViewportView(wineInfoScroll);
		wineInfoScroll.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				int row = wineInfoScroll.getSelectedRow();
				
				int wineID = wineIDs[row]; 
				WineHunterApplication.viewWine(wineID, user); 
			}
		});
		
		this.add(wineScroll, "width 100%, height 90%");
		
		
	}

}
