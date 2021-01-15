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
		<jsp:include page="/WEB-INF/includeTemplate/headerIndex.jsp"></jsp:include>

		<!-- main部分 開始 -->
		<div class="main">

			<h2>
				<span>ログイン</span>
			</h2>

			<p>
				<c:out value="${eMsg }" />
			</p>
			<form action="GController?action=executeLogin " method="post">
				<br>
				<p>社員ID：</p>
				<span class="input"><input type="text" name="user_id"></span>
				<br>
				<p>パスワード：</p>
				<span class="input"><input type="password" name="pass"></span>
				<br> <br> <input type="submit" value="ログイン"
					class="button2">
			</form>
			<p>
				<a href="GController?action=fromLoginToTop"><button
						class="button2">TOPへ戻る</button></a>
			</p>

		</div>
		<!-- main部分 終了 -->

		<!-- footer部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/footer.jsp"></jsp:include>
	</div>
</body>
</html>