package master;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MGoogleDAO implements EnvSet {
	URL url = null;
	HttpURLConnection con = null;

	private final String URL = "jdbc:mysql://172.16.71.120:3306/booklibrary?serverTimezone=JST";
	private final String USER = "03user";
	private final String PASS = "03user03pass";
	private String SQL = "";

	public boolean getConnectGoogle(String requestURL) {

		try {
			//URLConnectionの作成
			url = new URL(requestURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");// GETリクエスト
			con.setReadTimeout(10000); // 10秒
			con.setConnectTimeout(10000);// 10秒

			//レスポンスコードの確認
			int responseCode = con.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				//接続を切断する
				con.disconnect();
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getStringJson() {
		//検索結果データ(レスポンス)の取得
		//ストリーム作成用
		BufferedReader responseReader = null;
		InputStreamReader isr = null;

		//レスポンス全データ取得用
		StringBuilder builder = new StringBuilder();

		//レスポンスを取得するためのストリームの作成
		try {
			isr = new InputStreamReader(con.getInputStream());
			responseReader = new BufferedReader(isr);

			//レスポンスデータを1行取得する
			String line = responseReader.readLine();

			//nullになるまでデータを１行取り出し、builderへ追加する
			while (line != null) {
				builder.append(line);
				line = responseReader.readLine();
			}

			//取得したデータを文字列へ変換する
			String responseJsonString = builder.toString();

			//接続を切断する
			con.disconnect();

			return responseJsonString;

		} catch (IOException e) {
			e.printStackTrace();
			con.disconnect();
			return null;
		}
	}

	public List<MGoogleDTO> convertHitList(String responseJsonString) {
		JSONObject jsonObject = null;

		try {
			//取得した文字列からJSONオブジェクトを作成
			jsonObject = new JSONObject(responseJsonString);

			//検索データ数 totalItemsを検索結果数としています
			//実際に検索して得られるデータは最大10個のようです
			int totalItems = Integer.parseInt(jsonObject.getString("totalItems"));

			//requestへcountを格納
			//		        	request.setAttribute("count", count);

			//検索結果0の場合、no_result.jspへフォワードする
			if (totalItems == 0) {
				//		        		rd = request.getRequestDispatcher("/no_result.jsp");
				//		        		rd.forward(request, response);
				return null;
			}
			//JSON配列itemsの取得
			JSONArray jsonArray = jsonObject.getJSONArray("items");

			//検索結果データの格納
			List<MGoogleDTO> hitList = new ArrayList<>();

			//実際に得られるデータ数
			totalItems = jsonArray.length();

			for (int i = 0; i < totalItems; i++) {
				//各検索結果
				JSONObject item = jsonArray.getJSONObject(i);

				//volumeInfoに関するデータの取得
				JSONObject volumeInfo = item.getJSONObject("volumeInfo");

				//titleの取得
				String booktitle = volumeInfo.getString("title");

				//authorsの取得
				JSONArray authors = null;
				String firstAuthor = null;

				try {
					authors = volumeInfo.getJSONArray("authors");
					firstAuthor = authors.getString(0);
				} catch (JSONException e) {
					firstAuthor = "未登録";
				}

				//publishedDateの取得
				String publishedDate = null;
				try {
					publishedDate = volumeInfo.getString("publishedDate");

				} catch (JSONException e) {
					publishedDate = "未登録";
				}

				//descriptionの取得
				String description = null;
				try {
					description = volumeInfo.getString("description");

				} catch (JSONException e) {
					description = "未登録";
				}

				//thumbnailの取得
				JSONObject imageLinks = null;
				String thumbnail = null;

				try {
					imageLinks = volumeInfo.getJSONObject("imageLinks");
					thumbnail = imageLinks.getString("thumbnail");
				} catch (JSONException e) {
					thumbnail = "未登録";
				}

				//isbnの取得
				JSONArray industryIdentifiers = null;
				String isbn = null;

				try {
					industryIdentifiers = volumeInfo.getJSONArray("industryIdentifiers");
					isbn = industryIdentifiers.getJSONObject(0).getString("identifier");
				} catch (JSONException e) {
					firstAuthor = "未登録";
				}

				//検索結果データの追加
				hitList.add(new MGoogleDTO(booktitle, firstAuthor, publishedDate, description, thumbnail, isbn));
			}
			return hitList;

		} catch (Exception e) {
			return null;
			//例外発生時、error.jspへフォワードする
			//		        	request.setAttribute("error", e.toString());
			//		        	rd = request.getRequestDispatcher("/error.jsp");
			//		        	rd.forward(request, response);
			//		        	return;
		}
	}

	//    	//レスポンスコードが200以外の場合は、error.jspへフォワードする
	//    	request.setAttribute("error", "Google Books API　へのリクエストが失敗しました。レスポンスコード：" + responseCode);
	//    	rd = request.getRequestDispatcher("/error.jsp");
	//    	rd.forward(request, response);
	//    	return;

	public MGoogleDTO convertHitBook(String responseJsonString) {
		JSONObject jsonObject = null;
		MGoogleDTO hitBook = null;

		try {
			//取得した文字列からJSONオブジェクトを作成
			jsonObject = new JSONObject(responseJsonString);

			//検索データ数 totalItemsを検索結果数としています
			//実際に検索して得られるデータは最大10個のようです
			int totalItems = Integer.parseInt(jsonObject.getString("totalItems"));
			//*************************

			//requestへcountを格納
			//		        	request.setAttribute("count", count);

			//検索結果0の場合、no_result.jspへフォワードする
			if (totalItems == 0) { //****************************
				//		        		rd = request.getRequestDispatcher("/no_result.jsp");
				//		        		rd.forward(request, response);
				return null;
			}
			System.out.println("ヒットしたデータ数：" + totalItems);
			//JSON配列itemsの取得
			JSONArray jsonArray = jsonObject.getJSONArray("items");

			//実際に得られるデータ数
			totalItems = jsonArray.length();

			for (int i = 0; i < totalItems; i++) {
				//各検索結果
				JSONObject item = jsonArray.getJSONObject(i);

				//volumeInfoに関するデータの取得
				JSONObject volumeInfo = item.getJSONObject("volumeInfo");

				//titleの取得
				String booktitle = volumeInfo.getString("title");

				//authorsの取得
				JSONArray authors = null;
				String firstAuthor = null;

				try {
					authors = volumeInfo.getJSONArray("authors");
					firstAuthor = authors.getString(0);
				} catch (JSONException e) {
					firstAuthor = "未登録";
				}

				//publishedDateの取得
				String publishedDate = null;
				try {
					publishedDate = volumeInfo.getString("publishedDate");

				} catch (JSONException e) {
					publishedDate = "未登録";
				}

				//descriptionの取得
				String description = null;
				try {
					description = volumeInfo.getString("description");

				} catch (JSONException e) {
					description = "未登録";
				}

				//thumbnailの取得
				JSONObject imageLinks = null;
				String thumbnail = null;

				try {
					imageLinks = volumeInfo.getJSONObject("imageLinks");
					thumbnail = imageLinks.getString("thumbnail");
				} catch (JSONException e) {
					thumbnail = "未登録";
				}

				//isbnの取得
				JSONArray industryIdentifiers = null;
				String isbn = null;

				try {
					industryIdentifiers = volumeInfo.getJSONArray("industryIdentifiers");
					isbn = industryIdentifiers.getJSONObject(0).getString("identifier");
				} catch (JSONException e) {
					firstAuthor = "未登録";
				}

				//検索結果データの追加
				hitBook = new MGoogleDTO(booktitle, firstAuthor, publishedDate, description, thumbnail, isbn);

			}
			return hitBook;

		} catch (Exception e) {
			return null;
			//例外発生時、error.jspへフォワードする
			//		        	request.setAttribute("error", e.toString());
			//		        	rd = request.getRequestDispatcher("/error.jsp");
			//		        	rd.forward(request, response);
			//		        	return;
		}
	}

	public boolean executeBookRegister(MGoogleDTO bookInfo) {

		int no = 0;

		SQL = "INSERT INTO BOOK (status,book_name,writer,publish_year,new_intro,buy_day,book_img,varchar_isbn) VALUES (?,?,?,?,?,?,?,?)"; //テーブルは8項目

		// データベースに接続
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstm = conn.prepareStatement(SQL)) {

			System.out.println(bookInfo.getStatus());
			System.out.println(bookInfo.getBookName());
			System.out.println(bookInfo.getWriter());
			System.out.println(bookInfo.getPublishYear());
			System.out.println(bookInfo.getIntroduction());
			System.out.println(bookInfo.getBuyDay());
			System.out.println(bookInfo.getBookImg());
			System.out.println(bookInfo.getIsbn());

			// プレースホルダにデータをセットする
			pstm.setInt(1, Integer.parseInt(bookInfo.getStatus())); //貸出状況
			//pstm.setInt(2, null); System.out.println("omg1");//書籍ID
			pstm.setString(2, bookInfo.getBookName());//書籍名
			pstm.setString(3, bookInfo.getWriter());//著者名
			pstm.setString(4, bookInfo.getPublishYear()); //刊行日
			pstm.setString(5, bookInfo.getIntroduction()); //intoroduction 説明文
			pstm.setString(6, bookInfo.getBuyDay()); //	購入日
			//				pstm.setString(8, null); //buy_authority購入部署
			//				pstm.setInt(9, Integer.parseInt(bookInfo.getCount()));System.out.println("omg4");  //貸出回数
			pstm.setString(7, bookInfo.getBookImg()); //画像パス
			pstm.setString(8, bookInfo.getIsbn()); //ISBN
			//				pstm.setString(12, null); //借りている人（吉岡追記）
			//				pstm.setString(13, null); //貸し出し日（吉岡修正）
			//				pstm.setString(14, null); //貸出予定日（吉岡修正）
			//				pstm.setString(15, null); //返却日（吉岡修正）

			// SQL文の実行

			no = pstm.executeUpdate();
			System.out.println(no);
			// 登録できたレコード数が1なら成功
			if (no == 1) {
				return true;

			} else { //失敗
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("omg");
			//
			return false;
		}
	}
}
