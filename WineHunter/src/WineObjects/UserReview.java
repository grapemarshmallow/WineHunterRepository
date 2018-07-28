package WineObjects;

public class UserReview {
	
	//private fields
	private User user;
	private WineReview wineReview;
	private int likeDislike;
	
	/**
	 * @param user
	 * @param wineReview
	 * @param likeDislike
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
