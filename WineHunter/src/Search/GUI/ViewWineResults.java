package Search.GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


import javax.swing.JLabel;

import Core.WineHunterApplication;
import net.miginfocom.swing.MigLayout;

import javax.swing.SwingConstants;

import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;


public class ViewWineResults extends JPanel {

	private static final long serialVersionUID = 2747473463278030742L;
	private JTable wineInfoScroll;
	
	/**
	 * Create the panel to view results
	 * @param data
	 * @param columnNames
	 * @param empty
	 */
	public ViewWineResults(String[][] data, String[] columnNames) {
		
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
		
		this.add(wineScroll, "width 100%, height 90%");

		
		
	}

}
