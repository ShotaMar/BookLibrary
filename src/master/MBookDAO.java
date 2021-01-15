package master;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MBookDAO {

	private final String URL = "jdbc:mysql://172.16.71.120:3306/booklibrary?serverTimezone=JST";
	private final String USER = "03user";
	private final String PASS = "03user03pass";
	private String SQL = "";

	// 蔵書を全件表示させるメソッド
	public List<MBookDTO> getAllBookList() {
		List<MBookDTO> allBookList = new ArrayList<>();

		//DBに接続
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement stm = conn.createStatement()) {

			//SELECT文の準備
			SQL = "select book_id,book_name,writer,rental,plans_day,return_day,buy_day,count from book;";
			// SQL文の実行
			ResultSet rs = stm.executeQuery(SQL);

			// ResultSetのフェッチ処理
			while (rs.next()) {
				// 取り出したレコードを保存するためのDTOオブジェクトの生成
				MBookDTO dto = new MBookDTO();

				// 各列のデータをDTOにセッターを使って保存
				dto.setBookId(String.valueOf(rs.getInt(1))); //書籍id
				dto.setBookName(rs.getString(2)); //書籍名
				dto.setWriter(rs.getString(3)); //著者名
				dto.setRental(rs.getString(4)); //貸し出し日
				dto.setPlansDay(rs.getString(5)); //返却予定日
				dto.setReturnDay(rs.getString(6)); //返却日
				dto.setBuyDay(rs.getString(7)); //購入日
				dto.setCount(String.valueOf(rs.getInt(8))); //貸出回数

				// データが格納されたDTOオブジェクトをArrayListに追加保存
				allBookList.add(dto);
			}

		} catch (SQLException e) {

		}

		// DTOが格納されたArrayListを返す
		return allBookList;

	}

	// 書籍を新規登録する
	public boolean executeBookRegister(MBookDTO bookInfo) {

		int no = 0;

		SQL = "INSERT INTO BOOK VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";	//テーブルは15項目（吉岡追記）

		// データベースに接続
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstm = conn.prepareStatement(SQL)) {

			// プレースホルダにデータをセットする
			pstm.setInt(1, Integer.parseInt(bookInfo.getStatus())); //貸出状況
			pstm.setInt(2, Integer.parseInt(bookInfo.getBookId())); //書籍ID
			pstm.setString(3, bookInfo.getBookName()); //書籍名
			pstm.setString(4, bookInfo.getWriter()); //著者名
			pstm.setString(5, bookInfo.getPublishYear()); //刊行日
			pstm.setString(6, null); //intoroduction 説明文
			pstm.setString(7, bookInfo.getBuyDay()); //	購入日
			pstm.setString(8, null); //buy_authority購入部署
			pstm.setInt(9, Integer.parseInt(bookInfo.getCount())); //貸出回数
			pstm.setString(10, null); //画像パス
			pstm.setString(11, bookInfo.getIsbn()); //ISBN
			pstm.setString(12, null); //借りている人（吉岡追記）
			pstm.setString(13, null); //貸し出し日（吉岡修正）
			pstm.setString(14, null); //貸出予定日（吉岡修正）
			pstm.setString(15, null); //返却日（吉岡修正）

			// SQL文の実行
			no = pstm.executeUpdate();

			// 登録できたレコード数が1なら成功
			if (no == 1) {
				return true;

			} else { //失敗
				return false;
			}

		} catch (SQLException e) {
			//
			return false;
		}

	}

	// 2週間以上貸出している本を全件表示させるメソッド
	public List<MBookDTO> getOverdueList() {
		// 今日の日付を算出
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int now = Integer.parseInt(sdf.format(new Date()));

		List<MBookDTO> overdueList = new ArrayList<>();

		//DBに接続
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement stm = conn.createStatement()) {

			//SELECT文の準備
			SQL = "select book_id,book_name,writer,rental,plans_day,borrower_id from book where status=1;";
			// SQL文の実行
			ResultSet rs = stm.executeQuery(SQL);

			// ResultSetのフェッチ処理
			while (rs.next()) {

				int plans = Integer.parseInt(rs.getString(5).replace("/", ""));
				if (plans < now) {
					// 取り出したレコードを保存するためのDTOオブジェクトの生成
					MBookDTO dto = new MBookDTO();

					// 各列のデータをDTOにセッターを使って保存
					dto.setBookId(String.valueOf(rs.getInt(1))); //書籍id
					dto.setBookName(rs.getString(2)); //書籍名
					dto.setWriter(rs.getString(3)); //著者名
					dto.setRental(rs.getString(4)); //貸し出し日
					dto.setPlansDay(rs.getString(5)); //返却予定日
					dto.setBorrowerId(rs.getString(6)); //貸出人のID

					// データが格納されたDTOオブジェクトをArrayListに追加保存
					overdueList.add(dto);
				}

			}

		} catch (SQLException e) {

		}

		// DTOが格納されたArrayListを返す
		return overdueList;

	}

}
