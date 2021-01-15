package user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import general.GUserDTO;

@WebServlet("/UController")
public class UController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String action = (String) request.getParameter("action");
		String bookId = (String) request.getParameter("bookId");
		String fp = "";
		int status = 0;
		String statusMsg = "";

		List<UBookDTO> allBookList;
		UBookDTO bookInfo;
		UBookDAO bdao = new UBookDAO();

		List<UHistoryAllListDTO> myHistoryList;
		UHistoryDAO hdao;
		UHistoryAllListDTO bookRentalInfo;

		GUserDTO userInfo;
		String userId;

		switch (action) {
		//↓書籍詳細へのルーティング
		case "fromMyPageToBookInfo":
			hdao = new UHistoryDAO();
			userInfo = (GUserDTO) session.getAttribute("userInfo");
			userId = userInfo.getId();
			status = hdao.getBookRentalInfo(userId, bookId);

			if (status == 0) {
				statusMsg = "貸出可";
			} else {
				statusMsg = "他のユーザーに貸出中";
			}

			bookInfo = bdao.getBookInfo(bookId);
			request.setAttribute("bookInfo", bookInfo);
			request.setAttribute("bookRentalInfo", statusMsg);
			request.setAttribute("action", "fromMyPage");
			fp = "/WEB-INF/jsp/bookInfo.jsp";
			break;

		case "fromBookInfoToMyPage":
			allBookList = bdao.getAllBookList();
			request.setAttribute("allBookList", allBookList);
			request.setAttribute("action", "fromBookInfo");
			fp = "/WEB-INF/jsp/mypage.jsp";
			break;

		//↓レンタルのルーティング
		case "fromBookInfoToRental":
			request.setAttribute("bookId", bookId);
			fp = "/WEB-INF/jsp/rental.jsp";
			break;

		case "fromRentalToRentalDone":
			String rentalMsg = "貸出に失敗しました…";
			//ユーザーIDをセッションスコープから取得
			userInfo = (GUserDTO) session.getAttribute("userInfo");
			userId = userInfo.getId();
			//Historyテーブルのhistory_noが最後何番か取得する
			hdao = new UHistoryDAO();
			int no = hdao.lastNo();
			//historyテーブルに行を挿入する
			boolean hInsertFlag = hdao.rentalInsert(bookId, userId, no);
			//bookテーブルの貸出状況を更新する
			bdao = new UBookDAO();
			boolean bUpdateFlag = bdao.rentalUpdate(bookId, userId);

			if (hInsertFlag && bUpdateFlag) {
				rentalMsg = "レンタルしました！";
			}
			request.setAttribute("rentalMsg", rentalMsg);
			fp = "/WEB-INF/jsp/rentalDone.jsp";
			break;

		case "fromRentalToBookInfo":
			hdao = new UHistoryDAO();
			userInfo = (GUserDTO) session.getAttribute("userInfo");
			userId = userInfo.getId();
			status = hdao.getBookRentalInfo(userId, bookId);

			if (status == 0) {
				statusMsg = "貸出可";
			} else {
				statusMsg = "他のユーザーに貸出中";
			}

			bookInfo = bdao.getBookInfo(bookId);
			request.setAttribute("bookInfo", bookInfo);
			request.setAttribute("bookRentalInfo", statusMsg);
			request.setAttribute("action", "fromMyPage");
			fp = "/WEB-INF/jsp/bookInfo.jsp";
			break;

		case "fromRentalDoneToBookInfo":
			hdao = new UHistoryDAO();
			userInfo = (GUserDTO) session.getAttribute("userInfo");
			userId = userInfo.getId();
			status = hdao.getBookRentalInfo(userId, bookId);

			if (status == 0) {
				statusMsg = "貸出可";
			} else {
				statusMsg = "他のユーザーに貸出中";
			}

			bookInfo = bdao.getBookInfo(bookId);
			request.setAttribute("bookInfo", bookInfo);
			request.setAttribute("bookRentalInfo", statusMsg);
			request.setAttribute("action", "fromMyPage");
			fp = "/WEB-INF/jsp/bookInfo.jsp";
			break;

		case "fromRentalDoneToMyPage":
			// マイページ一覧表示処理
			allBookList = bdao.getAllBookList();
			request.setAttribute("allBookList", allBookList);
			request.setAttribute("action", "fromBookInfo");
			fp = "/WEB-INF/jsp/mypage.jsp";
			break;

		//↓履歴ページのルーティング
		case "fromMyPageToMyHistory":
			hdao = new UHistoryDAO();
			userInfo = (GUserDTO) session.getAttribute("userInfo");
			userId = userInfo.getId();
			myHistoryList = hdao.getMyHistoryList(userId);
			request.setAttribute("myHistoryList", myHistoryList);
			request.setAttribute("action", "fromMyPage");
			fp = "/WEB-INF/jsp/myHistory.jsp";
			break;

		case "fromMyHistoryToMyPage":
			allBookList = bdao.getAllBookList();
			request.setAttribute("allBookList", allBookList);
			request.setAttribute("action", "fromMyHistory");
			fp = "/WEB-INF/jsp/mypage.jsp";
			break;

		//↓返却に関するルーティング
		case "fromMyHistoryToReturn":
			request.setAttribute("bookId", bookId);
			fp = "/WEB-INF/jsp/return.jsp";
			break;

		case "fromReturnToMyHistory":
			hdao = new UHistoryDAO();
			userInfo = (GUserDTO) session.getAttribute("userInfo");
			userId = userInfo.getId();
			myHistoryList = hdao.getMyHistoryList(userId);
			request.setAttribute("myHistoryList", myHistoryList);
			request.setAttribute("action", "fromMyPage");
			fp = "/WEB-INF/jsp/myHistory.jsp";
			break;

		case "fromReturnToReturnDone":
			String returnMsg = "返却しました！";
			userInfo = (GUserDTO) session.getAttribute("userInfo");
			userId = userInfo.getId();
			//Historyテーブルの貸出状況を更新
			hdao = new UHistoryDAO();
			boolean hFlag = hdao.returnInsert(bookId, userId);
			System.out.print("historyは" + hFlag);
			//Bookテーブルの貸出状況を更新
			bdao = new UBookDAO();
			boolean bFlag = bdao.returnUpdate(bookId, userId);
			System.out.print("bookは" + bFlag);

			if (hFlag == true && bFlag == true) {
				returnMsg = "返却しました！";
			} else {
				returnMsg = "返却失敗です…";
			}
			request.setAttribute("returnMsg", returnMsg);
			fp = "/WEB-INF/jsp/returnDone.jsp";
			break;

		case "fromReturnDoneToMyHistory":
			hdao = new UHistoryDAO();
			userInfo = (GUserDTO) session.getAttribute("userInfo");
			userId = userInfo.getId();
			myHistoryList = hdao.getMyHistoryList(userId);
			request.setAttribute("myHistoryList", myHistoryList);
			request.setAttribute("action", "fromMyPage");
			fp = "/WEB-INF/jsp/myHistory.jsp";
			break;

		}
		RequestDispatcher disp = request.getRequestDispatcher(fp);
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String action = (String) request.getParameter("action");
		String bookId = (String) request.getParameter("bookId");
		GUserDTO userInfo = (GUserDTO) session.getAttribute("userInfo");
		String userId = userInfo.getId();

		String fp = "";

		switch (action) {
		case "executeRental":
			UBookDAO bdao = new UBookDAO();
			;
			boolean isRental = bdao.rentalUpdate(bookId, userId);

			request.setAttribute("rentalResult", isRental);
			fp = "/WEB-INF/jsp/reantalDone.jsp";
			break;

		case "executeReturn":
			//			UHistoryDAO hdao = new UHistoryDAO();
			//			boolean isReturn = hdao.returnInsert(bookId, userId);
			//			request.setAttribute("returnResult", isReturn);
			//			fp = "/WEB-INF/jsp/returnDone.jsp";
		}

		RequestDispatcher disp = request.getRequestDispatcher(fp);
		disp.forward(request, response);
	}
}
