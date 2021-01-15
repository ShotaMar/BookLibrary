package user;

public class UHistoryAllListDTO {

	private String bookId;
	private String bookName;
	private String writer;
	private String rental;
	private String plansDay;
	private String returnDay;
	private String status;

	UHistoryAllListDTO() {
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

	public String getStatus() {
		return status;
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

	public void setStatus(String status) {
		this.status = status;
	}

}

//	private String bookId;
//	private String userId;
//	private String rental;
//	private String plansDay;
//	private String returnDay;
//	private String status;
//
//	private String bookName;
//	private String writer;
//
//	UHistoryAllListDTO() {
//	}
//
//	UHistoryAllListDTO(String bookId, String userId, String rental, String plansDay, String returnDay, String status,
//			String bookName, String writer) {
//		this.bookId = bookId;
//		this.userId = userId;
//		this.rental = rental;
//		this.plansDay = plansDay;
//		this.returnDay = returnDay;
//		this.status = status;
//		this.bookName = bookName;
//		this.writer = writer;
//
//	}
//
//	public String getBookId() {
//		return bookId;
//	}
//
//	public String getUserId() {
//		return userId;
//	}
//
//	public String getRental() {
//		return rental;
//	}
//
//	public String getPlansDay() {
//		return plansDay;
//	}
//
//	public String getReturnDay() {
//		return returnDay;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public String bookName() {
//		return bookName;
//	}
//
//	public String writer() {
//		return writer;
//	}
//
//	public void setBookId(String bookId) {
//		this.bookId = bookId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//
//	public void setRental(String rental) {
//		this.rental = rental;
//	}
//
//	public void setPlansDay(String plansDay) {
//		this.plansDay = plansDay;
//	}
//
//	public void setReturnDay(String returnDay) {
//		this.returnDay = returnDay;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public void setBookName(String bookName) {
//		this.bookName = bookName;
//	}
//
//	public void setWriter(String writer) {
//		this.writer = writer;
//	}
//
//}
