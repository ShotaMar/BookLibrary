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
				<span>書籍新規登録</span>
			</h2>
			<!-- 書籍登録フォーム -->
			<form action="/BookLibrary/MController?action=executeBookRegister "
				method="post">
				<p>書籍ID：</p>
				<span class="input"><input type="text" name="bookId"></span>
				<br>
				<p>ISBNコード：</p>
				<span class="input"><input type="text" name="isbn"></span> <br>
				<p>書籍名：</p>
				<span class="input"><input type="text" name="bookName"></span>
				<br>
				<p>著者名：</p>
				<span class="input"><input type="text" name="writer"></span>
				<p>刊行日：</p>
				<span class="input"><input type="text" name="publishYear"></span>
				<br>
				<p>購入日：</p>
				<span class="input"><input type="text" name="buyDay"></span>
				<br> <br> <br> <input type="submit" value="登録"
					class="button1">
			</form>
			<br> <br>
			<p>
				<a href="MController?action=fromBookRegisterToMngBook"
					class="button2">書籍管理画面へ戻る</a>
			</p>

		</div>
		<!-- main部分 終了 -->

		<!-- footer部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/footer.jsp"></jsp:include>
	</div>
</body>
</html>