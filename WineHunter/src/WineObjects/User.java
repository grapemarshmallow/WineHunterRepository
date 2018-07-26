package WineObjects;

public class User {
	private String username;
	private String fullName;
	private int id;
	private int admin;
	private int superAdmin;
	
	/**
	 * @param username
	 * @param fullName
	 * @param id
	 * @param admin
	 * @param superAdmin
	 */
	public User(String username, String fullName, int id, int admin, int superAdmin) {
		this.username = username;
		this.fullName = fullName;
		this.id = id;
		this.admin = admin;
		this.superAdmin = superAdmin;
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
	
	
}
