package master;

public class MHistoryDTO {

	private String bookId;
	private String bookName;
	private String writer;
	private String rental;
	private String plansDay;
	private String returnDay;

	MHistoryDTO() {
	}

	//	MHistoryDTO(String bookId, String userId, String rental, String plansDay, String returnDay, String status) {
	//		this.bookId = bookId;
	//		this.userId = userId;
	//		this.rental = rental;
	//		this.plansDay = plansDay;
	//		this.returnDay = returnDay;
	//		this.status = status;
	//	}

	// ゲッター
	public String getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public String getWriter() {
		return writer;
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
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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
