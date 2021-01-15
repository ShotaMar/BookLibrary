package general;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GUserDAO {

	private final String URL = "jdbc:mysql://172.16.71.120:3306/booklibrary?serverTimezone=JST";
	private final String ID = "03user";
	private final String PASS = "03user03pass";
	private String SQL = "";

	//	public GUserDTO getUserInfo(String inId,String inPass){
	//		GUserDTO userInfo = null;
	//
	//		try(Connection conn = DriverManager.getConnection(URL,ID,PASS)){
	//
	//			SQL = "select user_id,name,authority,pass from sampleDB.user where user_id = ? and pass = ?;";
	//			PreparedStatement psmt = conn.prepareStatement(SQL);
	//			psmt.setString(1,inId);
	//			psmt.setString(2,inPass);
	//			ResultSet rs = psmt.executeQuery();
	//
	//			while(rs.next()) {
	//				String userId = String.valueOf(rs.getInt("user_id"));
	//				String name = rs.getString("name");
	//				String auth = rs.getString("authority");
	//				String pass = rs.getNString("pass");
	//
	//				userInfo = new GUserDTO(userId,name,auth,pass);
	//			}
	//			return userInfo;
	//
	//		}catch(SQLException e) {
	//			System.out.println(1);
	//			return null;
	//		}
	//	}

	public GUserDTO getUserInfo(String inId, String inPass) {
		GUserDTO userInfo = null;

		try (Connection conn = DriverManager.getConnection(URL, ID, PASS)) {

			SQL = "select user_id,name,authority,pass from user where user_id = ? and pass = ?;";
			PreparedStatement psmt = conn.prepareStatement(SQL);
			psmt.setString(1, inId);
			psmt.setString(2, inPass);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				String userId = String.valueOf(rs.getInt("user_id"));
				String name = rs.getString("name");
				String auth = rs.getString("authority");
				String pass = rs.getNString("pass");

				userInfo = new GUserDTO(userId, name, auth, pass);
			}
			return userInfo;

		} catch (SQLException e) {
			System.out.println(1);
			return null;
		}
	}
}
