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
		<c:choose>
			<c:when test="${action==\"fromMyPage\"}">
				<jsp:include page="/WEB-INF/includeTemplate/headerMypage.jsp"></jsp:include>
			</c:when>
			<c:otherwise><jsp:include
					page="/WEB-INF/includeTemplate/headerIndex.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>


		<!-- main部分 開始 -->
		<div class="main">

			<h2>
				<span>書籍詳細情報</span>
			</h2>
			<br>

			<div class="searchResult">
				<div class="searchResultImg">
					<c:choose>
						<c:when test="${bookInfo.bookImg!=null }">
							<img src="${bookInfo.bookImg }" alt="">
						</c:when>
						<c:otherwise>
							<img src="img/noImage.png" alt="">
						</c:otherwise>
					</c:choose>
				</div>

				<div class="searchResultDoc">
					<table class="searchResultDocTable">
						<tr>
							<th>書籍ID</th>
							<td>${bookInfo.bookId}</td>
						</tr>
						<tr>
							<th>書籍名</th>
							<td>${bookInfo.bookName}</td>
						</tr>
						<tr>
							<th>著者名</th>
							<td>${bookInfo.writer}</td>
						</tr>
						<tr>
							<th>出版日</th>
							<td>${bookInfo. publishYear}</td>
						</tr>
						<tr>
							<th>購入日</th>
							<td>${bookInfo.buyDay}</td>
						</tr>
						<tr>
							<th>貸出状況</th>
							<td><c:choose>
									<c:when test="${bookInfo.status==\"0\"}">
												貸出可
											</c:when>
									<c:otherwise>貸出中
											</c:otherwise>
								</c:choose></td>
						</tr>
					</table>
				</div>
			</div>
			<br>
			<!-- マイページからこのページに遷移してきた場合、貸出ボタンor返却予定日を表示させる -->
			<c:if test="${action==\"fromMyPage\"}">
				<c:choose>
					<c:when test="${bookInfo.status==\"0\"}">
						<a
							href="UController?action=fromBookInfoToRental&bookId=${bookInfo.bookId}"
							class="button1"> この本を借りる </a>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</c:if>

			<br>
			<!-- 戻るボタンの表示 -->
			<!-- ※どのページから来たかによって、戻り先を変える -->
			<c:choose>
				<c:when test="${action==\"fromMyPage\"}">
					<a href="UController?action=fromBookInfoToMyPage" class="button2">
						マイページへ戻る </a>
				</c:when>
				<c:otherwise>
					<a href="GController?action=fromBookInfoToTop" class="button1">
						TOPへ戻る </a>
				</c:otherwise>
			</c:choose>

		</div>
		<!-- main部分 終了 -->

		<!-- footer部分 -->
		<jsp:include page="/WEB-INF/includeTemplate/footer.jsp"></jsp:include>
	</div>
</body>
</html>