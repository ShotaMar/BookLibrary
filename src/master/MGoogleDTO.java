package master;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MGoogleDTO implements Serializable {
	private String status;
	private String bookId;
	private String bookName;
	private String writer;
	private String publishYear;
	private String introduction;
	private String buyDay;
	private String buyAuthority;
	private String count;
	private String bookImg;
	private String isbn;
	private String borrowerId;
	private String rental;
	private String plansDay;
	private String returnDay;

	public MGoogleDTO() {
	}

	//API用コンストラクタ
	public MGoogleDTO( String booktitle, String firstAuthor, String publishDate,
			String description,String thumbnail, String isbn) {
		this.bookName = booktitle;
		this.writer = firstAuthor;
		this.publishYear = publishDate;
		this.introduction = description;
		this.buyDay = getTime();
		this.bookImg = thumbnail;
		System.out.println(bookImg);
		this.isbn = isbn;
		this.status = "0";
		this.count = "0";
	}

	public MGoogleDTO(String bookId, String bookName, String writer, String publishYear, String buyDay, String isbn,String status,String count) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.writer = writer;
		this.publishYear = publishYear;
		this.buyDay = buyDay;
		this.status = status;
		this.count = count;
	}

	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String now = sdf.format(new Date());
		return now;
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

	public String getIntroduction() {
		return introduction;
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
		return this.bookImg;
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

	public void setBookName(String title) {
		this.bookName=title;

	}

	public void setWriter(String author) {
		this.writer=author;
	}

	public void setPublishYear(String publishedDate) {
		this.publishYear=publishedDate;
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

	public void setIsbn(String type) {
		this.isbn=type;
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
