package general;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GControllre
 */
@WebServlet("/GController")
public class GController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GController() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String action = (String) request.getParameter("action");
		String bookId = (String) request.getParameter("bookId");
		String fp = "";

		if (action == null) {
			action = "first";
		}

		List<GBookDTO> allBookList;
		GBookDTO bookInfo;
		GBookDAO bdao = new GBookDAO();

		switch (action) {
		case "first":
			allBookList = bdao.getAllBookList();
			request.setAttribute("allBookList", allBookList);
			fp = "/index.jsp";
			break;

		case "fromTopToLogin":
			fp = "/login.jsp";
			request.setAttribute("action", "fromTop");
			break;

		case "fromLoginToTop":
			allBookList = bdao.getAllBookList();
			request.setAttribute("allBookList", allBookList);
			request.setAttribute("action", "fromLogin");
			fp = "/index.jsp";
			break;

		case "fromTopToBookInfo":
			bookInfo = bdao.getBookInfo(bookId);
			request.setAttribute("bookInfo", bookInfo);
			request.setAttribute("action", "fromTop");
			fp = "/WEB-INF/jsp/bookInfo.jsp";
			break;

		case "fromBookInfoToTop":
			allBookList = bdao.getAllBookList();
			request.setAttribute("allBookList", allBookList);
			request.setAttribute("action", "fromBook");
			fp = "/index.jsp";
			break;

		default:
			allBookList = bdao.getAllBookList();
			request.setAttribute("allBookList", allBookList);
			fp = "/index.jsp";
			break;
		}

		RequestDispatcher disp = request.getRequestDispatcher(fp);
		disp.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = (String) request.getParameter("action");
		String inId = (String) request.getParameter("user_id");
		String inPass = (String) request.getParameter("pass");
		String fp = "";
		HttpSession session;
		GBookDAO bdao;
		switch (action) {

		case "executeLogin":
			LoginLogic ll = new LoginLogic();
			GUserDTO userInfo = ll.getUserInfo(inId, inPass);
			boolean isLogin = ll.isLogin(inId, inPass, userInfo);

			if (isLogin) {
				String auth = userInfo.getAuth();

				if (auth.equals("true")) { //管理者用のページへ
					request.setAttribute("action", "fromLogin");
					fp = "/WEB-INF/jsp/master.jsp";

				} else { //マイページ（一般）へ
					bdao = new GBookDAO();
					List<GBookDTO> allBookList = bdao.getAllBookList();
					request.setAttribute("allBookList", allBookList);
					request.setAttribute("action", "fromLogin");
					fp = "/WEB-INF/jsp/mypage.jsp";
				}
				session = request.getSession();
				session.setAttribute("userInfo", userInfo);

			} else {
				fp = "/login.jsp";
				request.setAttribute("eMsg", "ログイン情報が正しくありません");
			}
			break;

		default:
			List<GBookDTO> allBookList;
			bdao = new GBookDAO();
			allBookList = bdao.getAllBookList();
			request.setAttribute("allBookList", allBookList);
			request.setAttribute("action", "fromLogin");
			fp = "/index.jsp";
			break;
		}

		RequestDispatcher disp = request.getRequestDispatcher(fp);
		disp.forward(request, response);
	}
}
