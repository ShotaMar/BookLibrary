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

			<!-- 返却処理が完了したかを通知するページ -->
			<!-- ※コントローラから来たリクエストスコープ("returnFlag")の値を取り出し、 -->
			<!--   値が「true」なら「処理成功」 、そうでなければ「処理失敗」の表示を行う-->

			<h3>
				<span><c:out value="${returnMsg }"></c:out></span>
			</h3>
			<br> <br>

			<!--<c:choose>
				<c:when test="${returnFlag}">
					<h3>返却完了！</h3>
				</c:when>
				<c:otherwise>
					<h3>ERROR</h3>
					<p>処理に失敗しました…</p>
				</c:otherwise>
			</c:choose>-->

			<!-- レンタル履歴表示ページに戻るボタンを表示 -->
			<a href="UController?action=fromReturnDoneToMyHistory"><button
					class="button2">履歴一覧に戻る</button></a>


		</div>
		<!-- ******************************************************************************************** -->
		<!-- main部分 終了 -->
		<!-- ******************************************************************************************** -->

		<!-- footer部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/footer.jsp"></jsp:include>
	</div>
</body>
</html>