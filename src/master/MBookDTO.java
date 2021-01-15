package master;

import java.io.Serializable;

public class MBookDTO implements Serializable {
	private String status;
	private String bookId;
	private String bookName;
	private String writer;
	private String publishYear;
	private String buyDay;
	private String buyAuthority;
	private String count;
	private String bookImg;
	private String isbn;
	private String borrowerId;
	private String rental;
	private String plansDay;
	private String returnDay;

	public MBookDTO() {
	}
	//		public MBookDTO(String bookId,String bookName,String writer,String publishYear,String buyDay,String status,String count) {
	//			this.bookId = bookId;
	//			this.bookName = bookName;
	//			this.writer = writer;
	//			this.publishYear = publishYear;
	//			this.buyDay = buyDay;
	//			this.writer = status;
	//			this.count = count;
	//		}

	public MBookDTO(String bookId, String bookName, String writer, String publishYear, String buyDay, String isbn,
			String status,
			String count) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.writer = writer;
		this.publishYear = publishYear;
		this.buyDay = buyDay;
		this.isbn = isbn; //（吉岡追記）
		this.status = status;
		this.count = count;
	}

	// ゲッター
	public String getStatus() {
		return status;
	}

	public String getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public String getWriter() {
		return writer;
	}

	public String getPublishYear() {
		return publishYear;
	}

	public String getBuyDay() {
		return buyDay;
	}

	public String getBuyAuthority() {
		return buyAuthority;
	}

	public String getCount() {
		return count;
	}

	public String getBookImg() {
		return bookImg;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getBorrowerId() {
		return borrowerId;
	}

	public String getRental() {
		return rental;
	}

	public String getPlansDay() {
		return plansDay;
	}

	public String getReturnDay() {
		return returnDay;
	}

	// セッター
	public void setStatus(String status) {
		this.status = status;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setPublishYear(String publishYear) {
		this.publishYear = publishYear;
	}

	public void setBuyDay(String buyDay) {
		this.buyDay = buyDay;
	}

	public void setBuyAuthority(String buyAuthority) {
		this.buyAuthority = buyAuthority;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setBorrowerId(String borrowerId) {
		this.borrowerId = borrowerId;
	}

	public void setRental(String rental) {
		this.rental = rental;
	}

	public void setPlansDay(String plansDay) {
		this.plansDay = plansDay;
	}

	public void setReturnDay(String returnDay) {
		this.returnDay = returnDay;
	}

}
