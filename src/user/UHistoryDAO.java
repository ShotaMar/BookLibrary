package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UHistoryDAO {

	private final String URL = "jdbc:mysql://172.16.71.120:3306/booklibrary?serverTimezone=JST";
	private final String USER = "03user";
	private final String PASS = "03user03pass";

	//*********************************************************************************************
	// ログインしているユーザーの書籍貸出履歴を全件表示させるメソッド
	// 作成者：丸山 / 修正：中島
	//*********************************************************************************************
	public List<UHistoryAllListDTO> getMyHistoryList(String inUserId) {

		List<UHistoryAllListDTO> historyList = new ArrayList<>();

		String SQL = "select background.book_id,book.book_name,book.writer,background.rental,background.plans_day,background.return_day,background.status from background join book on background.book_id=book.book_id where user_id=?";

		// データベースに接続
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstm = conn.prepareStatement(SQL)) {

			// プレースホルダにデータをセットする
			pstm.setString(1, inUserId); //社員ID

			// SQL文の実行
			ResultSet rs = pstm.executeQuery();

			// ResultSetのフェッチ処理
			while (rs.next()) {
				// 取り出したレコードを保存するためのDTOオブジェクトの生成
				UHistoryAllListDTO dto = new UHistoryAllListDTO();

				// 各列のデータをDTOにセッターを使って保存
				dto.setBookId(String.valueOf(rs.getInt(1))); //書籍id
				dto.setBookName(rs.getString(2)); //書籍名
				dto.setWriter(rs.getString(3)); //著者名
				dto.setRental(rs.getString(4)); //貸出日
				dto.setPlansDay(rs.getString(5)); //返却予定日
				dto.setReturnDay(rs.getString(6)); //返却日
				dto.setStatus(rs.getString(7)); //返却日

				// データが格納されたDTOオブジェクトをArrayListに追加保存
				historyList.add(dto);

			}

		} catch (SQLException e) {

		}
		return historyList;
	}

	public int getBookRentalInfo(String inUserId, String inBookId) {
		int status = 0;

		// 実行するSQL文を文字列として事前に設定
		String SQL = "select status from book where book_id=?";

		// データベースに接続
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			PreparedStatement psmt = conn.prepareStatement(SQL);
			psmt.setInt(1, Integer.parseInt(inBookId));

			// SQL文の実行
			ResultSet rs = psmt.executeQuery(SQL);

			// ResultSetのフェッチ処理
			while (rs.next()) {
				status = rs.getInt(1); //貸出状況
			}

		} catch (SQLException e) {
		}

		return status;
	}

	//*********************************************************************************************
	// historyテーブル（貸出履歴）に新規で借りた本を登録するメソッド
	// 作成者：中島
	//*********************************************************************************************
	public boolean rentalInsert(String bookId, String userId, int no) {

		//日付の書式を決定
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");

		//日時を格納するためのDateクラスを宣言(現在時刻)
		Date date = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		//今日の日付（貸出日）を変数に保存
		String rentalDay = sdf.format(date);

		//Date型の持つ日時の14日後を表示(返却予定日)
		calendar.add(Calendar.DAY_OF_MONTH, 14);
		date = calendar.getTime();
		String plansDay = sdf.format(date);

		// 使用するSQL文を変数に保存
		String SQL = "insert into background values(?,?,?,?,?,null,1)";

		// データベースに接続
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstm = conn.prepareStatement(SQL)) {

			// プレースホルダにデータをセットする
			pstm.setInt(1, no + 1); //history_id+1
			pstm.setInt(2, Integer.parseInt(bookId)); //書籍ID
			pstm.setInt(3, Integer.parseInt(userId)); //社員ID
			pstm.setString(4, rentalDay); //貸出日（今日の日付）
			pstm.setString(5, plansDay); //返却予定日（２週間後）

			// SQL文を実行し、更新できたレコード数を変数noに保存
			int result = pstm.executeUpdate();

			// noが1（更新できたレコード数が1）であれば更新完了。戻り値にtrueをセット
			if (result > 0) {
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
	// 本を返却するときに動くメソッド（historyテーブル（貸出履歴）の貸出状況更新）
	// 作成者：中島
	//*********************************************************************************************
	public boolean returnInsert(String bookId, String userId) {

		//日付の書式を決定
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");

		//日時を格納するためのDateクラスを宣言(現在時刻)
		Date date = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		//今日の日付（貸出日）を変数に保存
		String returnDay = sdf.format(new Date());

		// 使用するSQL文を変数に保存
		String SQL = "update background set return_day=?,status=0 where book_id=?";

		// データベースに接続
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstm = conn.prepareStatement(SQL)) {

			// プレースホルダにデータをセットする
			pstm.setString(1, returnDay); //返却日（今日の日付）
			pstm.setInt(2, Integer.parseInt(bookId)); //書籍ID

			// SQL文を実行し、更新できたレコード数を変数noに保存
			int result = pstm.executeUpdate();

			// noが1（更新できたレコード数が1）であれば更新完了。戻り値にtrueをセット
			if (result > 0) {
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
	// historyテーブルのhistory_noが最後何番かをチェックするメソッド
	// 作成者：中島
	//*********************************************************************************************
	public int lastNo() {
		int no = 0;

		//DBに接続
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement stm = conn.createStatement()) {

			//SELECT文の準備
			String SQL = "SELECT max(history_no) FROM background";
			// SQL文の実行
			ResultSet rs = stm.executeQuery(SQL);

			// ResultSetのフェッチ処理
			while (rs.next()) {

				no = rs.getInt(1);
			}

		} catch (SQLException e) {

		}

		return no;

	}

}
