package user;

public class UUserDTO {

	private String userId;
	private String name;
	private String authority;
	private String pass;

	UUserDTO() {
	}

	UUserDTO(String userId, String name, String auth, String pass) {
		this.userId = userId;
		this.name = name;
		this.authority = auth;
		this.pass = pass;
	}

	public String getId() {
		return userId;
	}

	public String getName() {
		return this.name;
	}

	public String getAuth() {
		return this.authority;
	}

	public String getPass() {
		return this.pass;
	}

}
