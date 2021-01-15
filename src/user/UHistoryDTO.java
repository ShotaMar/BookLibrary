package user;

public class UHistoryDTO {

	private String bookId;
	private String userId;
	private String rental;
	private String plansDay;
	private String returnDay;
	private String status;

	UHistoryDTO() {
	}

	UHistoryDTO(String bookId, String userId, String rental, String plansDay, String returnDay, String status) {
		this.bookId = bookId;
		this.userId = userId;
		this.rental = rental;
		this.plansDay = plansDay;
		this.returnDay = returnDay;
		this.status = status;
	}

	public String getBookId() {
		return bookId;
	}

	public String getUserId() {
		return userId;
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

	public void setBookId() {
		this.bookId = bookId;
	}

	public void setUserId() {
		this.userId = userId;
	}

	public void setRental() {
		this.rental = rental;
	}

	public void setPlansDay() {
		this.plansDay = plansDay;
	}

	public void setReturnDay() {
		this.returnDay = returnDay;
	}

	public void setStatus() {
		this.status = status;
	}

}
