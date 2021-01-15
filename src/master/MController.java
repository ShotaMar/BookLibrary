package master;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MController")
public class MController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session;
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String fp = "";

		List<MUserDTO> userList;
		MUserDAO udao;

		List<MBookDTO> allBookList;
		List<MBookDTO> overdueList;
		MBookDAO bdao;

		List<MHistoryDTO> historyList;
		MHistoryDAO hdao;

		switch (action) {
		//↓ユーザ管理ルーティング
		case "fromMasterToMngUser":
			udao = new MUserDAO();
			userList = udao.getUserList();
			request.setAttribute("userList", userList);
			fp = "/WEB-INF/jsp/mngUser.jsp";
			break;

		case "fromMngUserToMaster":
			fp = "/WEB-INF/jsp/master.jsp";
			break;

		case "fromMngUserToUserRegister":
			fp = "/WEB-INF/jsp/userRegister.jsp";
			break;

		case "fromUserRegisterToMngUser":
			udao = new MUserDAO();
			userList = udao.getUserList();
			request.setAttribute("userList", userList);
			fp = "/WEB-INF/jsp/mngUser.jsp";
			break;
		case "fromUserRegisterResultToMngUser":
			udao = new MUserDAO();
			userList = udao.getUserList();
			request.setAttribute("userList", userList);
			fp = "/WEB-INF/jsp/mngUser.jsp";
			break;
		case "fromMngUserToMngUserDetails":
			String userId = (String) request.getParameter("userId");
			hdao = new MHistoryDAO();
			historyList = hdao.getHistoryList(userId);
			request.setAttribute("userId", userId);
			request.setAttribute("historyList", historyList);
			fp = "/WEB-INF/jsp/mngUserDetails.jsp";
			break;
		case "fromMngUserDetailsToMngUser":
			udao = new MUserDAO();
			userList = udao.getUserList();
			request.setAttribute("userList", userList);
			fp = "/WEB-INF/jsp/mngUser.jsp";
			break;

		//↓書籍管理ルーティング
		case "fromMasterToMngBook":
			bdao = new MBookDAO();
			allBookList = bdao.getAllBookList();
			request.setAttribute("allBookList", allBookList);
			fp = "/WEB-INF/jsp/mngBook.jsp";
			break;

		case "fromMngBookToMaster":
			fp = "/WEB-INF/jsp/master.jsp";
			break;

		case "fromMngBookToBookRegister":
			fp = "/WEB-INF/jsp/bookRegister.jsp";
			break;

		case "fromBookRegisterToMngBook":
			bdao = new MBookDAO();
			allBookList = bdao.getAllBookList();
			request.setAttribute("allBookList", allBookList);
			fp = "/WEB-INF/jsp/mngBook.jsp";
			break;
		case "fromUserRegisterResultToMngBook":
			bdao = new MBookDAO();
			allBookList = bdao.getAllBookList();
			request.setAttribute("allBookList", allBookList);
			fp = "/WEB-INF/jsp/mngBook.jsp";
			break;

		//↓２W延滞情報
		case "fromMasterToOverDueInfo":
			bdao = new MBookDAO();
			overdueList = bdao.getOverdueList();
			request.setAttribute("overdueList", overdueList);
			fp = "/WEB-INF/jsp/overDueInfo.jsp";
			break;

		case "fromOverDueInfoToMaster":
			fp = "/WEB-INF/jsp/master.jsp";
			break;

		//コピペ用
		case "":
			fp = "/WEB-INF/jsp/";
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
		String fp = "";

		switch (action) {
		//↓ユーザ登録ルーティング
		case "executeUserRegister":
			String userId = (String) request.getParameter("userId");
			String userName = (String) request.getParameter("userName");
			String department = (String) request.getParameter("department");
			String auth = (String) request.getParameter("authority");
			String pass = (String) request.getParameter("pass");

			// 入力された社員IDが重複していないかチェック
			MUserDAO udao = new MUserDAO();
			boolean isNotDuplicate = udao.isNotDuplicate(userId);

			// 社員IDが重複していなければ
			if (isNotDuplicate == true) {
				MUserDTO userInfo = new MUserDTO(userId, userName, department, auth, pass);
				boolean registerResult = udao.executeUserRegister(userInfo);

				if (registerResult) { //登録成功
					request.setAttribute("registerResult", registerResult);
					request.setAttribute("userInfo", userInfo);

				} else { //登録失敗
					request.setAttribute("registerResult", registerResult);
					request.setAttribute("eMsg", "登録に失敗しました。もう一度やり直してください");
				}

			} else {
				request.setAttribute("eMsg", "ユーザIDが重複しています");
			}

			fp = "/WEB-INF/jsp/userRegisterResult.jsp";
			break;

		//↓本登録ルーティング
		case "executeBookRegister":
			String bookId = (String) request.getParameter("bookId");
			String bookName = (String) request.getParameter("bookName");
			String writer = (String) request.getParameter("writer");
			String publishYear = (String) request.getParameter("publishYear");
			String buyDay = (String) request.getParameter("buyDay");
			String isbn = (String) request.getParameter("isbn");
			String status = "0";
			String count = "0";

			// 書籍を登録する
			MBookDTO bookInfo = new MBookDTO(bookId, bookName, writer, publishYear, buyDay, isbn, status, count);
			MBookDAO bdao = new MBookDAO();
			boolean registerResult = bdao.executeBookRegister(bookInfo);

			if (registerResult) {
				request.setAttribute("bookInfo", bookInfo);

			} else {
				request.setAttribute("eMsg", "登録に失敗しました/nもう一度やり直してください");
			}

			request.setAttribute("registerResult", registerResult);
			fp = "/WEB-INF/jsp/bookRegisterResult.jsp";
			break;

		}
		RequestDispatcher disp = request.getRequestDispatcher(fp);
		disp.forward(request, response);
	}
}
