/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             Wine.java
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

package WineObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * defines a wine object
 *
 */
public class Wine {
	
	//Instance Variables
		int wineId;
		int vintage;
		float price;
		String name;
		Winery winery;
		List<Integer> keywords = new ArrayList<Integer>();
		List<Variety> varieties = new ArrayList<Variety>();
	
	/**
	 * Wine constructor
	 * @param wineId ID for the wine
	 * @param vintage vintage for the wine
	 * @param price price for the wine
	 * @param name name of the wine
	 * @param winery where the wine was made
	 */
	public Wine(int wineId, int vintage, float price, String name, Winery winery) {
		this.wineId = wineId;
		this.vintage = vintage;
		this.price = price;
		this.name = name;
		this.winery = winery;
	}

	/**
	 * @return the wineId
	 */
	public int getWineId() {
		return wineId;
	}
	
	@Override
	public String toString() {
			
			return this.name;
	}

	/**
	 * @param wineId the wineId to set
	 */
	public void setWineId(int wineId) {
		this.wineId = wineId;
	}

	/**
	 * @return the vintage
	 */
	public int getVintage() {
		return vintage;
	}

	/**
	 * @param vintage the vintage to set
	 */
	public void setVintage(int vintage) {
		this.vintage = vintage;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the winery
	 */
	public Winery getWinery() {
		return winery;
	}

	/**
	 * @param winery the winery to set
	 */
	public void setWinery(Winery winery) {
		this.winery = winery;
	}

	/**
	 * @return the keywords
	 */
	public List<Integer> getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(List<Integer> keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the varieties
	 */
	public List<Variety> getVarieties() {
		return varieties;
	}

	/**
	 * @param varieties the varieties to set
	 */
	public void setVarieties(List<Variety> varieties) {
		this.varieties = varieties;
	}
	
	
	
	
}
