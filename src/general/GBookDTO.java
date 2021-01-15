package general;

import java.io.Serializable;

public class GBookDTO implements Serializable {
	private String bookId;
	private String bookName;
	private String writer;
	private String publishYear;
	private String buyDay;
	private String status;
	private String count;
	private String bookImg;

	public GBookDTO(String bookId, String bookName, String writer, String publishYear, String buyDay, String status,
			String count, String bookImg) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.writer = writer;
		this.publishYear = publishYear;
		this.buyDay = buyDay;
		this.status = status;
		this.count = count;
		this.bookImg = bookImg;
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

	public String getStatus() {
		return status;
	}

	public String getCount() {
		return count;
	}

	public String getBookImg() {
		return bookImg;
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

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}

}
