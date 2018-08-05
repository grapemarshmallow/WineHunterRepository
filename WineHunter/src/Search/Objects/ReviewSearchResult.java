/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             ReviewSearchResult.java
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

import WineObjects.UserReview;
import WineObjects.UserWine;

/** 
 * this class holds a user review
 *
 */
public class ReviewSearchResult {
	
	private UserWine userWineReview;
	private UserReview userReview;
	
	
	/**
	 * @param userWineReview
	 * @param userReview
	 */
	public ReviewSearchResult(UserWine userWineReview, UserReview userReview) {
		this.userWineReview = userWineReview;
		this.userReview = userReview;
	}
	
	public UserWine getUserWineReview() {
		return this.userWineReview;
	}
	public void setUserWineReview(UserWine userWineReview) {
		this.userWineReview = userWineReview;
	}
	public UserReview getUserReview() {
		return this.userReview;
	}
	public void setUserReview(UserReview userReview) {
		this.userReview = userReview;
	}
	
	
}
