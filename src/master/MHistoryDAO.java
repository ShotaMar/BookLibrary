package master;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MHistoryDAO {

	private final String URL = "jdbc:mysql://-.-.-.-:-/booklibrary?serverTimezone=JST";
	private final String USER = "----";
	private final String PASS = "----";

	// 各ユーザの履歴一覧を全表示するメソッド
	public List<MHistoryDTO> getHistoryList(String userId) {

		List<MHistoryDTO> historyList = new ArrayList<>();

		String SQL = "select background.book_id,book.book_name,book.writer,background.rental,background.plans_day,background.return_day,background.status from background join book on background.book_id=book.book_id where user_id=?";

		// データベースに接続
		try (Connection conn = DriverManager.getConnection(URL, ID, PASS);
				PreparedStatement pstm = conn.prepareStatement(SQL)) {

			// プレースホルダにデータをセットする
			pstm.setString(1, userId); //社員ID

			// SQL文の実行
			ResultSet rs = pstm.executeQuery();

			// ResultSetのフェッチ処理
			while (rs.next()) {
				// 取り出したレコードを保存するためのDTOオブジェクトの生成
				MHistoryDTO dto = new MHistoryDTO();

				// 各列のデータをDTOにセッターを使って保存
				dto.setBookId(String.valueOf(rs.getInt(1))); //書籍id
				dto.setBookName(rs.getString(2)); //書籍名
				dto.setWriter(rs.getString(3)); //著者名
				dto.setRental(rs.getString(4)); //貸出日
				dto.setPlansDay(rs.getString(5)); //返却予定日
				dto.setReturnDay(rs.getString(5)); //返却日

				// データが格納されたDTOオブジェクトをArrayListに追加保存
				historyList.add(dto);
			}

		} catch (SQLException e) {

		}

		return historyList;

	}

}
