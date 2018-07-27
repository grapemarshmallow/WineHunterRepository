package WineObjects;

import java.util.ArrayList;

public class User {
	private String username;
	private String fullName;
	private int id;
	private int admin;
	private int superAdmin;
	private String email;
	private ArrayList<Keyword> userLikeKeywordList = new ArrayList<Keyword>();
	private ArrayList<Keyword> userDislikeKeywordList = new ArrayList<Keyword>();
	private ArrayList<Keyword> sysLikeKeywordList = new ArrayList<Keyword>();
	private ArrayList<Keyword> sysDislikeKeywordList = new ArrayList<Keyword>();
	private ArrayList<Variety> userLikeVarietyList = new ArrayList<Variety>();
	private ArrayList<Variety> userDislikeVarietyList = new ArrayList<Variety>();
	private ArrayList<Variety> sysLikeVarietyList = new ArrayList<Variety>();
	private ArrayList<Variety> sysDislikeVarietyList = new ArrayList<Variety>();
	
	/**
	 * @param username
	 * @param fullName
	 * @param id
	 * @param admin
	 * @param superAdmin
	 */
	public User(String username, String fullName, int id, int admin, int superAdmin, String email) {
		this.username = username;
		this.fullName = fullName;
		this.id = id;
		this.admin = admin;
		this.superAdmin = superAdmin;
		this.setEmail(email);
	}
	
	public ArrayList<Keyword> getUserLikeKeywordList() {
		return this.userLikeKeywordList;
	}

	public ArrayList<Keyword> getUserDislikeKeywordList() {
		return this.userDislikeKeywordList;
	}

	public ArrayList<Keyword> getSysLikeKeywordList() {
		return this.sysLikeKeywordList;
	}

	public ArrayList<Keyword> getSysDislikeKeywordList() {
		return this.sysDislikeKeywordList;
	}

	public ArrayList<Variety> getUserLikeVarietyList() {
		return this.userLikeVarietyList;
	}

	public ArrayList<Variety> getUserDislikeVarietyList() {
		return this.userDislikeVarietyList;
	}

	public ArrayList<Variety> getSysLikeVarietyList() {
		return this.sysLikeVarietyList;
	}

	public ArrayList<Variety> getSysDislikeVarietyList() {
		return this.sysDislikeVarietyList;
	}

	public void setUserLikeKeywordList(ArrayList<Keyword> userLikeKeywordList) {
		this.userLikeKeywordList = userLikeKeywordList;
	}

	public void setUserDislikeKeywordList(ArrayList<Keyword> userDisikeKeywordList) {
		this.userDislikeKeywordList = userDisikeKeywordList;
	}

	public void setUserLikeVarietyList(ArrayList<Variety> userLikeVarietyList) {
		this.userLikeVarietyList = userLikeVarietyList;
	}

	public void setUserDislikeVarietyList(ArrayList<Variety> userDisikeVarietyList) {
		this.userDislikeVarietyList = userDisikeVarietyList;
	}

	public String getUsername() {
		return this.username;
	}
	public String getFullName() {
		return this.fullName;
	}
	public int getId() {
		return this.id;
	}
	public int getAdmin() {
		return this.admin;
	}
	public int getSuperAdmin() {
		return this.superAdmin;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public void setSuperAdmin(int superAdmin) {
		this.superAdmin = superAdmin;
	}
	
	/**
	 * refreshes our users lists for filling
	 */
	public void cleanLists() {
		
		
		userLikeKeywordList = new ArrayList<Keyword>();
		userDislikeKeywordList = new ArrayList<Keyword>();
		sysLikeKeywordList = new ArrayList<Keyword>();
		sysDislikeKeywordList = new ArrayList<Keyword>();
		userLikeVarietyList = new ArrayList<Variety>();
		userDislikeVarietyList = new ArrayList<Variety>();
		sysLikeVarietyList = new ArrayList<Variety>();
		sysDislikeVarietyList = new ArrayList<Variety>();
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
