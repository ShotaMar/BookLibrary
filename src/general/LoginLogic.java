package general;

public class LoginLogic {

	boolean isLogin(String inId, String inPass, GUserDTO userInfo) {

		if (userInfo == null) {
			return false;
		} else {

			String id = userInfo.getId();
			String pass = userInfo.getPass();

			if (id.equals(inId) && pass.equals(inPass)) {

				return true;
			} else {
				System.out.println(inId);
				return false;
			}
		}
	}

	boolean test(String inId, String inPass) {
		GUserDAO udao = new GUserDAO();
		GUserDTO userInfo = udao.getUserInfo(inId, inPass);

		if (userInfo == null) {
			return false;
		} else {
			return true;
		}
	}

	GUserDTO getUserInfo(String inId, String inPass) {
		GUserDAO udao = new GUserDAO();
		GUserDTO userInfo = udao.getUserInfo(inId, inPass);

		return userInfo;
	}
}
