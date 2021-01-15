<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- title、css読込み -->
<jsp:include page="/WEB-INF/includeTemplate/head.jsp"></jsp:include>
<link rel='stylesheet' type='text/css' href='css/bookLibrary.css'>
</head>
<body>
	<div class="container">
		<!-- header部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/headerMypage.jsp"></jsp:include>

		<!-- ******************************************************************************************** -->
		<!-- main部分 開始（この中にプログラム記述しています） -->
		<!-- ******************************************************************************************** -->

		<div class="main">

			<h2>
				<span>貸出履歴一覧</span>
			</h2>
			<br>
			<!-- ログイン中のユーザーが借りた本履歴が一覧で表示される -->
			<!-- プログラムがうまく動作していれば情報が表示される -->
			<br> <br>
			<table>
				<tr>
					<th>書籍ID</th>
					<th>書籍名</th>
					<th>著者名</th>
					<th>貸出日</th>
					<th>返却予定日</th>
					<th>返却日</th>
				</tr>
				<c:forEach var="date" items="${historyList}">
					<tr>
						<td><c:out value="${date.bookId }" /></td>
						<td><c:out value="${date.bookName }" /></td>
						<td><c:out value="${date.writer }" /></td>
						<td><c:out value="${date.rental }" /></td>
						<td><c:out value="${date.plansDay }" /></td>
						<td><c:out value="${date.returnDay }" /></td>
					</tr>
				</c:forEach>
			</table>
			<br> <br>

			<!-- 戻るボタン表示（マイページに遷移） -->
			<p>
				<a href="MController?action=fromMngUserDetailsToMngUser"
					class="button2">ユーザー管理画面へ戻る</a>
			</p>

		</div>
		<!-- ******************************************************************************************** -->
		<!-- main部分 終了 -->
		<!-- ******************************************************************************************** -->

		<!-- footer部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/footer.jsp"></jsp:include>
	</div>
</body>
</html>