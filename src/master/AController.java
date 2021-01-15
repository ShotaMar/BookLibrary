package master;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AController")
public class AController extends HttpServlet implements EnvSet {
	private static final long serialVersionUID = 1L;

	public AController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		//proxyの設定
		System.setProperty("https.proxyHost", J701_HTTPS_PROXY_ADDRESS);
		System.setProperty("https.proxyPort", J701_HTTPS_PROXY_PORT);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session;
		String fp = "";

		List<MBookDTO> allBookList;
		MBookDAO bdao;

		switch (action) {
		case "fromMngBookToTitleSearch":
			request.setAttribute("action", "fromMngBook");
			fp = "/WEB-INF/jsp/titleSearch.jsp";
			break;

		case "fromTitleSearchToMngBook":
			bdao = new MBookDAO();
			allBookList = bdao.getAllBookList();
			request.setAttribute("allBookList", allBookList);
			request.setAttribute("action", "fromTitleSearch");
			fp = "/WEB-INF/jsp/mngBook.jsp";
			break;

		case "fromMngBookToISBNSearch":
			request.setAttribute("action", "fromMngBook");
			fp = "/WEB-INF/jsp/ISBNSearch.jsp";
			break;
		case "fromISBNSearchToMngBook":
			bdao = new MBookDAO();
			allBookList = bdao.getAllBookList();
			request.setAttribute("allBookList", allBookList);
			request.setAttribute("action", "fromISBNSearch");
			fp = "/WEB-INF/jsp/mngBook.jsp";
			break;

		case "executeBookAPIRegister":
			session = request.getSession();
			MGoogleDTO hitBook = (MGoogleDTO) session.getAttribute("hitBook");
			session.invalidate();
			MGoogleDAO gdao = new MGoogleDAO();

			boolean isRegisterResult = gdao.executeBookRegister(hitBook);
			System.out.println("omg");
			request.setAttribute("registerResult", isRegisterResult);
			request.setAttribute("action", "fromSearchResult");
			fp = "/WEB-INF/jsp/bookRegisterResult.jsp";
			break;

		case "executeBookAPITitleRegister":
			int count = Integer.parseInt(request.getParameter("count"));
			session = request.getSession();
			List<MGoogleDTO> hitList = (List<MGoogleDTO>) session.getAttribute("hitList");
			MGoogleDTO hit = hitList.get(count);
			session.invalidate();
			gdao = new MGoogleDAO();
			boolean isRegisterResultTitle = gdao.executeBookRegister(hit);
			System.out.println("omg");
			request.setAttribute("registerResult", isRegisterResultTitle);
			request.setAttribute("action", "fromSearchResult");
			fp = "/WEB-INF/jsp/bookRegisterResult.jsp";
			break;

		case "fromBookRegisterResultToMaster":
			request.setAttribute("action", "fromBookRegisterResult");
			fp = "/WEB-INF/jsp/master.jsp";
			break;

		case "fromSearchResultToMngBook":
			bdao = new MBookDAO();
			allBookList = bdao.getAllBookList();
			request.setAttribute("allBookList", allBookList);
			request.setAttribute("action", "fromMngBook");
			fp = "/WEB-INF/jsp/mngBook.jsp";
			break;

		}
		RequestDispatcher disp = request.getRequestDispatcher(fp);
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String requestURL;
		MGoogleDAO gdao;
		boolean getConnect;
		HttpSession session;
		String fp = "";

		switch (action) {
		case "executeTitleSearch":
			String inTitle = request.getParameter("title");
			System.out.println(inTitle);
			requestURL = GOOGLE_BOOKS_API_TITLE + inTitle;
			System.out.println(requestURL);
			gdao = new MGoogleDAO();
			getConnect = gdao.getConnectGoogle(requestURL);

			if (getConnect) {
				String stringJson = gdao.getStringJson();
				if (stringJson != null) {
					List<MGoogleDTO> hitList = gdao.convertHitList(stringJson);
					if (hitList != null) {
						//検索結果データのソート
						//Collections.sort(APIsList, new SortList()); ************
						session = request.getSession();
						session.setAttribute("hitList", hitList);
						fp = "/WEB-INF/jsp/searchResult.jsp"; //成功

					} else {
						request.setAttribute("eMsg", "JSONファイルの変換に失敗しました");
						fp = "/WEB-INF/jsp/titleSearch.jsp";
					}
				} else {
					request.setAttribute("eMsg", "検索データを取得できませんでした");
					fp = "/WEB-INF/jsp/titleSearch.jsp";
				}
			} else {
				request.setAttribute("eMsg", "Googleへの接続に失敗しました");
				fp = "/WEB-INF/jsp/titleSearch.jsp";
			}
			request.setAttribute("action", "fromTitleSearch");
			break;

		case "executeISBNSearch":
			String inIsbn = request.getParameter("isbn");
			System.out.println(inIsbn);
			requestURL = GOOGLE_BOOKS_API_ISBN + inIsbn;
			gdao = new MGoogleDAO();
			getConnect = gdao.getConnectGoogle(requestURL);

			if (getConnect) {
				String stringJson = gdao.getStringJson();
				System.out.println(stringJson);
				if (stringJson != null) {
					MGoogleDTO hitBook = gdao.convertHitBook(stringJson);
					if (hitBook != null) {
						//検索結果データのソート
						//Collections.sort(APIsList, new SortList()); ************
						session = request.getSession();
						session.setAttribute("hitBook", hitBook);
						fp = "/WEB-INF/jsp/searchResult.jsp"; //成功

					} else {
						request.setAttribute("eMsg", "JSONファイルの変換に失敗しました");
						fp = "/WEB-INF/jsp/titleSearch.jsp";
					}
				} else {
					request.setAttribute("eMsg", "検索データを取得できませんでした");
					fp = "/WEB-INF/jsp/titleSearch.jsp";
				}
			} else {

				request.setAttribute("eMsg", "Googleへの接続に失敗しました");
				fp = "/WEB-INF/jsp/titleSearch.jsp";
			}
			request.setAttribute("action", "fromISBNSearch");
			break;
		}

		RequestDispatcher disp = request.getRequestDispatcher(fp);
		disp.forward(request, response);
	}

}
