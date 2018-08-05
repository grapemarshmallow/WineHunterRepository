/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             UserReview.java
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

/**
 * class indicates if a user liked or disliked a particular review
 *
 */
public class UserReview {
	
	//private fields
	private User user;
	private WineReview wineReview;
	private int likeDislike;
	
	/**
	 * constructs a user review of a wine review
	 * @param user the user
	 * @param wineReview the review
	 * @param likeDislike 1 for like, 0 for dislike
	 */
	public UserReview(User user, WineReview wineReview, int likeDislike) {
		this.user = user;
		this.wineReview = wineReview;
		this.likeDislike = likeDislike;
	}
	
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public WineReview getWineReview() {
		return this.wineReview;
	}
	public void setWineReview(WineReview wineReview) {
		this.wineReview = wineReview;
	}
	public int getLikeDislike() {
		return this.likeDislike;
	}
	public void setLikeDislike(int likeDislike) {
		this.likeDislike = likeDislike;
	}
}
