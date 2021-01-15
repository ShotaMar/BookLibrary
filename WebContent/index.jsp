<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String str = "http";
%>
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
				<span>蔵書一覧</span>
			</h2>

			<div class="box_area">
				<c:forEach var="date" items="${allBookList}">

					<div class="box_item">
						<figure>
							<c:choose>
								<c:when test="${date.bookImg!=null }">
									<a
										href="GController?action=fromTopToBookInfo&bookId=${date.bookId }">
										<img src="${date.bookImg }" alt="">
									</a>
								</c:when>
								<c:otherwise>
									<a
										href="GController?action=fromTopToBookInfo&bookId=${date.bookId }">
										<img src="img/noImage.png" alt="">
									</a>
								</c:otherwise>
							</c:choose>
						</figure>
						<div>
							<p>
								<span>${date.bookName }</span>
							</p>
							<p>${date.writer }</p>
						</div>
					</div>


				</c:forEach>
			</div>


		</div>
		<!-- main部分 終了 -->

		<!-- footer部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/footer.jsp"></jsp:include>
	</div>
</body>
</html>