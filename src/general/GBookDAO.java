package general;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GBookDAO {
	
	private final String URL = "jdbc:mysql://-.-.-.-:-/booklibrary?serverTimezone=JST";
	private final String USER = "----";
	private final String PASS = "----";
	private String SQL = "";

	public List<GBookDTO> getAllBookList() {
		try (Connection conn = DriverManager.getConnection(URL, ID, PASS)) {
			SQL = "select * from book";
			PreparedStatement psmt = conn.prepareStatement(SQL);
			ResultSet rs = psmt.executeQuery();
			List<GBookDTO> allBookList = new ArrayList<>();

			while (rs.next()) {
				String bookId = String.valueOf(rs.getInt("book_id"));
				String bookName = rs.getString("book_name");
				String writer = rs.getString("writer");
				String publishY = rs.getString("publish_year");
				String buyDay = rs.getString("buy_day");
				String status = rs.getString("status");
				String count = String.valueOf(rs.getInt("count"));
				String bookImg = rs.getString("book_img");

				GBookDTO bookInfo = new GBookDTO(bookId, bookName, writer, publishY, buyDay, status, count, bookImg);
				allBookList.add(bookInfo);
			}

			return allBookList;
		} catch (SQLException e) {
			return null;
		}
	}

	public GBookDTO getBookInfo(String inBookId) {
		GBookDTO bookInfo = null;
		try (Connection conn = DriverManager.getConnection(URL, ID, PASS)) {

			SQL = "select * from book where book_id = ? ";
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

				bookInfo = new GBookDTO(bookId, bookName, writer, publishY, buyDay, status, count, bookImg);
			}
			return bookInfo;

		} catch (SQLException e) {
			return null;
		}
	}

}
