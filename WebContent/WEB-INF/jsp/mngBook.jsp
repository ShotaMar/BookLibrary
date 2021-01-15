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

		<!-- main部分 開始 -->
		<div class="main">

			<h2>
				<span>書籍の新規登録</span>
			</h2>
			<p>
				<a href="MController?action=fromMngBookToBookRegister"
					class="button4">手動登録</a>
			</p>
			<p>
				<a href="AController?action=fromMngBookToTitleSearch"
					class="button4">Google Books APIからタイトル検索</a>
			</p>
			<p>
				<a href="AController?action=fromMngBookToISBNSearch" class="button4">Google
					Books APIからISBN検索</a>
			</p>
			<br>
			<h2>
				<span>蔵書一覧</span>
			</h2>
			<br>
			<!-- 登録されている全書籍が一覧で表示される -->
			<table>
				<tr>
					<th>書籍ID</th>
					<th>書籍名</th>
					<th>著者名</th>
					<th>購入日</th>
				</tr>
				<c:forEach var="date" items="${allBookList}">
					<tr>
						<td>${date.bookId }</td>
						<td>${date.bookName }</td>
						<td>${date.writer }</td>
						<td>${date.buyDay }</td>
					</tr>
				</c:forEach>
			</table>

			<!-- 戻るボタン表示（マイページに遷移） -->
			<br>
			<p>
				<a href="MController?action=fromMngBookToMaster" class="button1">管理者画面へ戻る</a>
			</p>
		</div>
		<!-- main部分 終了 -->

		<!-- footer部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/footer.jsp"></jsp:include>
	</div>
</body>
</html>