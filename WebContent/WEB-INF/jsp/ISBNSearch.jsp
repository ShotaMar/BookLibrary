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
				<span>Google Books APIからISBNコード検索</span>
			</h2>
			<br>
			<!-- ISBN検索フォーム -->
			<form action="/BookLibrary/AController?action=executeISBNSearch "
				method="post">
				<p class="inputForm2">
					ISBNコード： <span class="input2"><input type="text" name="isbn"></span>
				</p>
				<input type="submit" value="検索" class="button2">
			</form>

			<!-- エラーメッセージの表示 -->
			<br> <br>
			<c:if test="${eMsg!=null}">
				<h4>
					<span>${eMsg}</span>
				</h4>
				<br>
				<br>
			</c:if>

			<!-- 書籍管理画面に戻るボタン表示 -->
			<br> <br> <br>
			<p>
				<a href="AController?action=fromISBNSearchToMngBook" class="button1">書籍管理画面へ戻る</a>
			</p>

		</div>
		<!-- main部分 終了 -->

		<!-- footer部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/footer.jsp"></jsp:include>
	</div>
</body>
</html>