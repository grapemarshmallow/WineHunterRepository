/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             WineReview.java
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
=
  *
  * @author Orbi Ish-Shalom
  */
public class WineReview {
	
	

	//Instance Variables
		int reviewId;
		int points;
		float score;
		String description;
		Wine wine;
		Taster taster;
		
		/**
		 * @param reviewId
		 * @param points
		 * @param score
		 * @param description
		 * @param wine
		 * @param taster
		 */
		public WineReview(int reviewId, int points, String description, Wine wine, Taster taster) {
			super();
			this.reviewId = reviewId;
			this.points = points;
			this.score = -1F;
			this.description = description;
			this.wine = wine;
			this.taster = taster;
		}
		
		public int getReviewId() {
			return reviewId;
		}
		
		public void setReviewId(int reviewId) {
			this.reviewId = reviewId;
		}
		
		public int getPoints() {
			return points;
		}
		
		public void setPoints(int points) {
			this.points = points;
		}
		
		public float getScore() {
			return score;
		}
		
		public void setScore(float score) {
			this.score = score;
		}
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
		
		public Wine getWine() {
			return wine;
		}
		
		public void setWine(Wine wine) {
			this.wine = wine;
		}
		
		public Taster getTaster() {
			return taster;
		}
		
		public void setTaster(Taster taster) {
			this.taster = taster;
		}
	
		
		
}
