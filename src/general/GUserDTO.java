package general;

public class GUserDTO {

	private String user_id;
	private String name;
	private String authority;
	private String pass;

	GUserDTO() {
		//do nothing
	}

	GUserDTO(String id, String name, String auth, String pass) {
		this.user_id = id;
		this.name = name;
		this.authority = auth;
		this.pass = pass;
	}

	public String getId() {
		return user_id;
	}

	public String getName() {
		return this.name;
	}

	public String getAuth() {
		if (authority == null) {
			return "null";
		}
		return this.authority;
	}

	public String getPass() {
		return this.pass;
	}

}
