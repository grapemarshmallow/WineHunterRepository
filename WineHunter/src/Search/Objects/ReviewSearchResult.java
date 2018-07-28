package Search.Objects;

import WineObjects.UserReview;
import WineObjects.UserWine;

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
