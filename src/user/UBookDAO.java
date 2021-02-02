package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UBookDAO {

	private final String URL = "jdbc:mysql://-.-.-.-:-/booklibrary?serverTimezone=JST";
	private final String USER = "----";
	private final String PASS = "----";

	//*********************************************************************************************
	// 書籍を借りるとき動くメソッド（書籍の貸出状況を更新する）
	// 作成者：中島
	//*********************************************************************************************
	public boolean rentalUpdate(String bookId, String userId) {

		//日付の書式を決定
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");

		//日時を格納するためのDateクラスを宣言(現在時刻)
		Date date = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		//今日の日付（貸出日）を変数に保存
		String rentalDay = sdf.format(new Date());

		//Date型の持つ日時の14日後を表示(返却予定日)
		calendar.add(Calendar.DAY_OF_MONTH, 14);
		date = calendar.getTime();
		String plansDay = sdf.format(date);

		// 使用するSQL文を変数に保存
		String SQL = "update book set status=1,borrower_id=?,rental=?,plans_day=? where book_id=?";

		// データベースに接続
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstm = conn.prepareStatement(SQL)) {

			// プレースホルダにデータをセットする
			pstm.setInt(1, Integer.parseInt(userId)); //社員ID
			pstm.setString(2, rentalDay); //最終貸し出し日（今日の日付）
			pstm.setString(3, plansDay); //返却予定日（２週間後）
			pstm.setInt(4, Integer.parseInt(bookId)); //書籍ID

			// SQL文を実行し、更新できたレコード数を変数noに保存
			int no = pstm.executeUpdate();

			// noが1（更新できたレコード数が1）であれば更新完了。戻り値にtrueをセット
			if (no == 1) {
				return true;
			} else { //それ以外であれば更新失敗。戻り値にfalseをセット
				return false;
			}

		} catch (SQLException e) {
			//更新失敗
			return false;
		}

	}

	//*********************************************************************************************
	// 書籍を返却するときに動くメソッド（書籍の貸出状況を更新する）
	// 作成者：中島
	//*********************************************************************************************
	public boolean returnUpdate(String bookId, String userId) {

		//日付の書式を決定
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");

		//日時を格納するためのDateクラスを宣言(現在時刻)
		Date date = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		//今日の日付（貸出日）を変数に保存
		String returnDay = sdf.format(new Date());

		// 使用するSQL文を変数に保存
		String SQL = "update book set status=0,borrower_id=null,return_day=? where book_id=?";

		// データベースに接続
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstm = conn.prepareStatement(SQL)) {

			// プレースホルダにデータをセットする
			pstm.setString(1, returnDay); //返却日（今日の日付）
			pstm.setInt(2, Integer.parseInt(bookId)); //書籍ID

			// SQL文を実行し、更新できたレコード数を変数noに保存
			int no = pstm.executeUpdate();

			// noが1（更新できたレコード数が1）であれば更新完了。戻り値にtrueをセット
			if (no == 1) {
				return true;
			} else { //それ以外であれば更新失敗。戻り値にfalseをセット
				return false;
			}

		} catch (SQLException e) {
			//更新失敗
			return false;
		}
	}

	public UBookDTO getBookInfo(String inBookId) {
		UBookDTO bookInfo = null;
		String SQL = "select * from book where book_id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

			PreparedStatement psmt = conn.prepareStatement(SQL);
			psmt.setString(1, inBookId);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {

				String bookId = String.valueOf(rs.getInt("book_id"));
				String bookName = rs.getString("book_name");
				String writer = rs.getString("writer");
				String publishY = rs.getString("publish_year");
				String buyDay = rs.getString("buy_day");
				String status = rs.getString("status");
				String count = String.valueOf(rs.getInt("count"));
				String bookImg = rs.getString("book_img");

				bookInfo = new UBookDTO(bookId, bookName, writer, publishY, buyDay, status, count, bookImg);
			}
			return bookInfo;

		} catch (SQLException e) {
			return null;
		}
	}

	public List<UBookDTO> getAllBookList() {
		String SQL = "select * from book ";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			PreparedStatement psmt = conn.prepareStatement(SQL);
			ResultSet rs = psmt.executeQuery();
			List<UBookDTO> allBookList = new ArrayList<>();

			while (rs.next()) {
				String bookId = String.valueOf(rs.getInt("book_id"));
				String bookName = rs.getString("book_name");
				String writer = rs.getString("writer");
				String publishY = rs.getString("publish_year");
				String buyDay = rs.getString("buy_day");
				String status = rs.getString("status");
				String count = String.valueOf(rs.getInt("count"));
				String bookImg = rs.getString("book_img");

				UBookDTO bookInfo = new UBookDTO(bookId, bookName, writer, publishY, buyDay, status, count, bookImg);
				allBookList.add(bookInfo);
			}
			return allBookList;
		} catch (SQLException e) {
			return null;
		}
	}
}
