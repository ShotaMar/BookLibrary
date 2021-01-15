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
				<span>検索結果</span>
			</h2>

			<!-- 登録したい本の書籍詳細情報を表示させる -->
			<br>
			<c:choose>
				<c:when test="${action==\"fromTitleSearch\"}">

					<%
						int count = 0;
					%>
					<c:forEach var="date" items="${hitList}">
						<div class="searchResult">
							<div class="searchResultImg">
								<img src="${date.bookImg }" alt="">
							</div>
							<div class="searchResultDoc">
								<table class="searchResultDocTable">
									<tr>
										<th>書籍名</th>
										<td>${date.bookName }</td>
									</tr>
									<tr>
										<th>著者名</th>
										<td>${date.writer }</td>
									</tr>
									<tr>
										<th>ISBNコード</th>
										<td>${date.isbn}</td>
									</tr>
									<tr>
										<th>出版年</th>
										<td>${date.publishYear }</td>
									</tr>
									<tr>
										<th>書籍説明</th>
										<td>${date.introduction }</td>
									</tr>
									<tr>
										<th></th>
										<td><a
											href="AController?action=executeBookAPITitleRegister&count=<%=count%>"
											class="button2">本を登録する</a></td>
									</tr>

								</table>
							</div>
						</div>
						<%
							count++;
						%>
						<br>
						<br>
						<br>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="searchResult">
						<div class="searchResultImg">
							<img src="${hitBook.bookImg }" alt="">
						</div>
						<div class="searchResultDoc">
							<table class="searchResultDocTable">
								<tr>
									<th>書籍名</th>
									<td>${hitBook.bookName }</td>
								</tr>
								<tr>
									<th>著者名</th>
									<td>${hitBook.writer }</td>
								</tr>
								<tr>
									<th>ISBNコード</th>
									<td>${hitBook.isbn}</td>
								</tr>
								<tr>
									<th>出版年</th>
									<td>${hitBook.publishYear }</td>
								</tr>
								<tr>
									<th>書籍説明</th>
									<td>${hitBook.introduction }</td>
								</tr>
							</table>
						</div>
					</div>
				</c:otherwise>
			</c:choose>

			<!-- 登録ボタン表示 -->
			<br>
			<c:if test="${action==\"fromISBNSearch\"}">
				<p>
					<a
						href="AController?action=executeBookAPIRegister&hitBook=${hitBook}"
						class="button2">本を登録する</a>
				</p>
			</c:if>


			<!-- 書籍管理画面に戻るボタン表示 -->
			<br> <br> <br>
			<p>
				<a href="AController?action=fromSearchResultToMngBook"
					class="button1">書籍管理画面へ戻る</a>
			</p>

		</div>
		<!-- main部分 終了 -->

		<!-- footer部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/footer.jsp"></jsp:include>
	</div>
</body>
</html>