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
				<span>延滞されている書籍一覧</span>
			</h2>
			<br> <br>

			<table>
				<tr>
					<th>書籍ID</th>
					<th>書籍名</th>
					<th>著者名</th>
					<th>貸出日</th>
					<th>返却予定日</th>
					<th>貸出者ID</th>
				</tr>
				<c:forEach var="date" items="${overdueList}">
					<tr>
						<td>${date.bookId }</td>
						<td>${date.bookName }</td>
						<td>${date.writer }</td>
						<td>${date.rental }</td>
						<td>${date.plansDay }</td>
						<td>${date.borrowerId }</td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<!-- 戻るボタン表示（マイページに遷移） -->
			<p>
				<a href="MController?action=fromOverDueInfoToMaster" class="button2">管理者画面へ戻る</a>
			</p>

		</div>
		<!-- main部分 終了 -->

		<!-- footer部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/footer.jsp"></jsp:include>
	</div>
</body>
</html>