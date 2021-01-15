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

			<table>
				<tr>
					<th></th>
					<th>書籍ID</th>
					<th>書籍名</th>
					<th>著者名</th>
					<th>貸出日</th>
					<th>返却予定日</th>
					<th>返却日</th>
					<th>貸出状況</th>
					<th></th>
				</tr>
				<c:forEach var="date" items="${myHistoryList}">
					<tr>
						<td></td>
						<td><c:out value="${date.bookId }" /></td>
						<td><span class="fo"><c:out value="${date.bookName }" /></span></td>
						<td><span class="fo"><c:out value="${date.writer }" /></span></td>
						<td><span class="fo"><c:out value="${date.rental }" /></span></td>
						<td><span class="fo"><c:out value="${date.plansDay }" /></span></td>
						<td><span class="fo"><c:out value="${date.returnDay }" /></span></td>
						<td><c:choose>
								<c:when test="${date.status==\"1\" }">
									<a
										href="UController?action=fromMyHistoryToReturn&bookId=${date.bookId }"
										class="button3">返却</a>
								</c:when>
								<c:otherwise>
									<c:out value="" />
								</c:otherwise>
							</c:choose></td>
						<td></td>
					</tr>
				</c:forEach>
			</table>

			<!-- 戻るボタン表示（マイページに遷移） -->
			<p>
				<a href="UController?action=fromMyHistoryToMyPage" class="button1">マイページへ戻る</a>
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