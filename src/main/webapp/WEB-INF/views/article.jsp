<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 조회 페이지</title>
<style>
body {
	margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center; /* 세로 정중앙 정렬 */
    width: 100%;
    height: 100vh; /* 화면 전체 높이 */
}

.article {
	display: flex;
    flex-direction: column; /* 세로 정렬 */
    align-items: center;
    width: 80%; /* 화면 너비를 조정할 수 있습니다 */
    border: 1px solid #ddd;
    padding: 20px;
    border-radius: 10px;
    background-color: #f9f9f9;
}

a {
	color: black;
	text-decoration: none; /* 하이퍼링크 밑줄 없애기 */
}

.title {
	border: 1px solid #ddd;
	display: flex;
	justify-content: space-between; /* 옆으로 정렬 */
	align-items: center;
	width: 97%;
	margin: 5px;
	padding: 10px;
	margin-bottom: 20px;
	gap: 15px; /* 요소 간의 간격 */
}

.title h4 {
	margin: 0;
}

.clicks {
	display: flex;
	width: 250px;
}

.modify, .delete, .goMain {
	background-color: #f0f0f0; /* 색상 조정 */
	color: #ffffff;
	border: none;
	padding: 5px 10px;
	margin: 5px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
	text-align: center;
}

.content {
	width: 95%;
	margin: 10px;
	margin-bottom: 20px;
	text-align: left;
}

.createdDate {
	width: 100%;
	text-align: right;
	font-size: 12px;
	color: #777;
}
</style>
</head>
<body>
	<!-- 삭제 성공 여부를 알림으로 나타냄 -->
	<c:if test="${not empty message}">
		<script>
			alert("${message}");
		</script>
	</c:if>

	<div class="article">

		<div class="title">
			<h4>제목 : ${ qna.title }</h4>
			<h4>작성자 : ${ qna.username }</h4>
			<h4>조회수 : ${ qna.views }</h4>

			<div class="clicks">
				<!-- 게시글 수정 페이지로 이동 -->
				<div class="modify">
					<a href="/qnaModify/${ qna.articleId }">수정</a>
				</div>
				<!-- 게시글 삭제 페이지(비밀번호 입력)로 이동 -->
				<div class="delete">
					<a href="/qnaDelete/${ qna.articleId }">삭제</a>
				</div>
				<!-- 게시글 목록 페이지로 이동 -->
				<div class="goMain">
					<a href="/qna">메인으로 가기</a>
				</div>
			</div>
		</div>
		<div class="content">${ qna.content }</div>
		<div class="createdDate">
			<p>작성일 : ${qna.createdAt}</p>
		</div>
	</div>
</body>
</html>