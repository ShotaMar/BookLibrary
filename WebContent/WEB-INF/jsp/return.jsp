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
			<!-- 確認メッセージ表示 -->
			<h3>この本を返しますか？</h3>

			<br> <br>
			<!-- 返すボタン表示（返却処理に進む） -->
			<p>
				<a href="UController?action=fromReturnToReturnDone&bookId=${bookId}"
					class="button1">返す</a>
			</p>
			<br>
			<!-- 戻るボタン表示（履歴一覧ページに遷移） -->
			<p>
				<a href="UController?action=fromReturnToMyHistory" class="button2">履歴一覧に戻る
				</a>
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