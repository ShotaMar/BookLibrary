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
			<p>
				<a href="MController?action=fromMngUserToUserRegister"
					class="button4">新しくユーザーを登録する</a>
			</p>
			<br>
			<h2>
				<span>登録済みユーザー一覧</span>
			</h2>
			<!-- 登録されている全ユーザーが一覧で表示される -->
			<br>
			<table>
				<tr>
					<th>user ID</th>
					<th>user name</th>
					<th>authority</th>
					<th>貸出履歴</th>
				</tr>
				<c:forEach var="date" items="${userList}">
					<tr>
						<th><c:out value="${date.userId }" /></th>
						<td><c:out value="${date.name }" /></td>
						<td><c:choose>
								<c:when test="${date.authority==\"true\"}">
									管理者
								</c:when>
								<c:otherwise>
								一般ユーザー
								</c:otherwise>
							</c:choose></td>
						<td><a
							href="MController?action=fromMngUserToMngUserDetails&userId=${date.userId }"><button
									class="button3">詳細</button> </a></td>
					</tr>
				</c:forEach>

			</table>

			<!-- 戻るボタン表示（マイページに遷移） -->
			<br>
			<p>
				<a href="MController?action=fromMngUserToMaster" class="button1">管理者画面へ戻る</a>
			</p>
		</div>
		<!-- main部分 終了 -->

		<!-- footer部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/footer.jsp"></jsp:include>
	</div>
</body>
</html>