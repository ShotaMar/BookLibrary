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
				<span>書籍登録処理</span>
			</h2>
			<p>
				<!-- 書籍登録処理が完了したかを通知するページ -->
				<!-- ※コントローラから来たリクエストスコープ("registerResult")の値を取り出し、 -->
				<!--   値が「true」なら「処理成功」と表示 、そうでなければeMsgの表示を行う-->
				<br>
				<c:choose>
					<c:when test="${registerResult==\"true\"}">
						<h3>登録完了！</h3>
					</c:when>
					<c:otherwise>
						<h3>ERROR</h3>
						<p>${eMsg}</p>
					</c:otherwise>
				</c:choose>
				<br> <br>

				<!-- 戻るボタンを表示 -->
				<a href="MController?action=fromUserRegisterResultToMngBook"
					class="button2">ユーザー管理画面に戻る</a>
		</div>

		<!-- main部分 終了 -->

		<!-- footer部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/footer.jsp"></jsp:include>
	</div>
</body>
</html>