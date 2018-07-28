package WineObjects;

public class UserWine {
	
	//fields
	private User user;
	private Wine wine;
	private int score;
	private int favorite;
	private String notes;
	
	
	/**
	 * @param user
	 * @param wine
	 * @param score
	 * @param favorite
	 * @param notes
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
