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
				<span>ユーザー新規登録</span>
			</h2>
			<!-- ユーザー登録フォーム -->
			<form action="/BookLibrary/MController?action=executeUserRegister "
				method="post">
				<p>社員ID：</p>
				<span class="input"><input type="text" name="userId"></span>
				<br>
				<p>社員名：</p>
				<span class="input"><input type="text" name="userName"></span>
				<br>
				<p>部署名：</p>
				<span class="input"><input type="text" name="department"></span>
				<br>
				<p>権限：</p>
				<span class="input"><input type="text" name="authority"></span>
				<br>
				<p>パスワード：</p>
				<span class="input"><input type="password" name="pass"></span>
				<br> <br> <input type="submit" value="登録確認画面へ"
					class="button1">
			</form>
			<br> <br>
			<p>
				<a href="MController?action=fromUserRegisterToMngUser"
					class="button2">ユーザー管理画面へ戻る</a>
			</p>

		</div>
		<!-- main部分 終了 -->

		<!-- footer部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/footer.jsp"></jsp:include>
	</div>
</body>
</html>