package master;

public class MUserDTO {

	private String userId;
	private String name;
	private String department;
	private String authority;
	private String pass;

	MUserDTO() {
	}

	MUserDTO(String id, String name, String department, String auth, String pass) {
		this.userId = id;
		this.name = name;
		this.department = department;
		this.authority = auth;
		this.pass = pass;
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return this.name;
	}

	public String getDepartment() {
		return this.department;
	}

	public String getAuthority() {
		return this.authority;
	}

	public String getPass() {
		return this.pass;
	}

	// セッターメソッド
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setAuthority(String auth) {
		this.authority = auth;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}