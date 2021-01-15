<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<span>管理者画面</span>
			</h2>
			<br>
			<p>
				<a href="MController?action=fromMasterToMngUser" class="button4">ユーザー管理画面</a>
			</p>
			<p>
				<a href="MController?action=fromMasterToMngBook" class="button4">書籍管理画面</a>
			</p>
			<p>
				<a href="MController?action=fromMasterToOverDueInfo" class="button4">延滞書籍一覧を見る</a>
			</p>

		</div>
		<!-- main部分 終了 -->

		<!-- footer部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/footer.jsp"></jsp:include>
	</div>
</body>
</html>