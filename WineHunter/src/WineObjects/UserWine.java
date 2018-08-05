/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             UserWine.java
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
 * User wine review object
 *
 */
public class UserWine {
	
	//fields
	private User user;
	private Wine wine;
	private int score;
	private int favorite;
	private String notes;
	
	
	/**
	 * constructor
	 * @param user who reviewed the wine
	 * @param wine the wine reviewed
	 * @param score the score for the wine
	 * @param favorite if the wine is a favorite
	 * @param notes the user-written notes
	 */
	public UserWine(User user, Wine wine, int score, int favorite, String notes) {
		this.user = user;
		this.wine = wine;
		this.score = score;
		this.favorite = favorite;
		this.notes = notes;
	}
	
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Wine getWine() {
		return this.wine;
	}
	public void setWine(Wine wine) {
		this.wine = wine;
	}
	public int getScore() {
		return this.score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getFavorite() {
		return this.favorite;
	}
	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}
	public String getNotes() {
		return this.notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
