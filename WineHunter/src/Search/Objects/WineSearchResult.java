/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             WineSearchResult.java
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

package Search.Objects;
import WineObjects.*;

/**
 * This class creates a panel with wine search results
 *
 */
public class WineSearchResult {
	private Wine wine;
	private WineReview wineReview;
	
	/**
	 * @param wine
	 * @param wineReview
	 * @param winery
	 */
	public WineSearchResult(Wine wine, WineReview wineReview, Winery winery) {
		this.wine = wine;
		this.wineReview = wineReview;
	}
	
	public Wine getWine() {
		return this.wine;
	}
	public WineReview getWineReview() {
		return this.wineReview;
	}
	
	public void setWine(Wine wine) {
		this.wine = wine;
	}
	public void setWineReview(WineReview wineReview) {
		this.wineReview = wineReview;
	}

	
	
}
