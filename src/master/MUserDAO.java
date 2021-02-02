package master;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MUserDAO {

	private final String URL = "jdbc:mysql://-.-.-.-:-/booklibrary?serverTimezone=JST";
	private final String USER = "----";
	private final String PASS = "----";
	private String SQL = "";

	public List<MUserDTO> getUserList() {

		List<MUserDTO> userList = new ArrayList<>();

		//DBに接続
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement stm = conn.createStatement()) {

			//SELECT文の準備
			SQL = "SELECT user_id,name,department,authority,pass FROM USER;";
			// SQL文の実行
			ResultSet rs = stm.executeQuery(SQL);

			// ResultSetのフェッチ処理
			while (rs.next()) {
				// 取り出したレコードを保存するためのDTOオブジェクトの生成
				MUserDTO dto = new MUserDTO();

				// 各列のデータをDTOにセッターを使って保存
				dto.setUserId(String.valueOf(rs.getInt(1))); //社員id
				dto.setName(rs.getString(2)); //社員id
				dto.setDepartment(rs.getString(3)); //部署名
				dto.setAuthority(rs.getString(4)); //権限
				dto.setPass(rs.getString(5)); //パスワード

				// データが格納されたDTOオブジェクトをArrayListに追加保存
				userList.add(dto);
			}

		} catch (SQLException e) {

		}

		// DTOが格納されたArrayListを返す
		return userList;
	}

	// 社員IDが重複しているかチェックするメソッド
	public boolean isNotDuplicate(String inUserId) {
		int no = 0;

		SQL = "SELECT USER_ID FROM USER WHERE USER_ID=?";//              (井上修正箇所)

		// データベースに接続
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstm = conn.prepareStatement(SQL)) {

			// プレースホルダにデータをセットする
			pstm.setString(1, inUserId); //社員ID(井上修正箇所)

			// SQL文の実行
			ResultSet rs = pstm.executeQuery();//  (井上修正箇所)

			// ResultSetのフェッチ処理
			while (rs.next()) {
				no += rs.getInt(1);
			}

			if (no != 0) {//      (井上修正箇所)
				// データあり
				return false;//     (井上修正箇所)
			} else {
				// データなし
				return true;//     (井上修正箇所)
			}

		} catch (SQLException e) {
			//
			return false;
		}

	}

	// ユーザーを新規登録するメソッド
	public boolean executeUserRegister(MUserDTO userInfo) {

		SQL = "INSERT INTO USER VALUES (?,?,?,?,?);";

		// データベースに接続
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstm = conn.prepareStatement(SQL)) {

			// プレースホルダにデータをセットする
			pstm.setInt(1, Integer.parseInt(userInfo.getUserId())); //社員ID
			pstm.setString(2, userInfo.getName());
			pstm.setString(3, userInfo.getDepartment());
			pstm.setString(4, userInfo.getAuthority());
			pstm.setString(5, userInfo.getPass());
			// SQL文の実行
			int flag = pstm.executeUpdate();//(井上修正箇所)

			// 指定した社員IDが見つからなければtrueを返す
			if (flag == 1) {
				return true;

			} else { //指定した社員IDが既に存在するならばfalseを返す
				return false;
			}

		} catch (SQLException e) {
			//
			return false;
		}

	}

}
