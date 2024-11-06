<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정 페이지</title>
<style type="text/css">

body {
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center; /* 세로 정중앙 정렬 */
    width: 100%;
    height: 100vh; /* 화면 전체 높이 */
    background-color: #f9f9f9; /* 배경색을 동일하게 */
}

.contentPanel {
    display: flex;
    flex-direction: column; /* 세로 정렬 */
    align-items: center;
    width: 80%; /* 화면 너비 */
    max-width: 800px; /* 최대 너비 설정 */
    border: 1px solid #ddd; /* 테두리 */
    padding: 20px;
    border-radius: 10px;
    background-color: #ffffff; /* 배경색을 흰색으로 설정 */
}

a {
    color: black;
    text-decoration: none; /* 하이퍼링크 밑줄 없애기 */
}

.writeForm {
    display: flex;
    flex-direction: column;
    width: 90%; /* 전체 너비를 차지하도록 */
    gap: 10px; /* 요소 간의 간격 */
}

textarea, input[type="text"], input[type="password"] {
    width: 100%; /* 입력 필드 너비 조정 */
    padding: 10px; /* 패딩 추가 */
    border: 1px solid #ccc; /* 테두리 */
    border-radius: 5px; /* 모서리 둥글게 */
    margin-bottom: 10px;
}

input[type="submit"] {
    background-color: #f0f0f0; /* 버튼 색상 */
    color: black;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 14px;
}

.returnPost {
    text-align: center;
    background-color: #f0f0f0; /* 버튼 색상 */
    color: #ffffff;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 14px;
    width: 200px;
    margin-top: 15px; /* 위쪽 여백 추가 */
}
</style>
</head>
<body>
	<!-- 수정 성공 여부를 알림으로 나타냄 -->
	<c:if test="${not empty message}">
        <script>
            alert("${message}");
        </script>
    </c:if>
    
	<div class="contentPanel">
		<div class="writeForm">
			<!-- 게시글 수정 폼 --> 
			<!-- 각 input에 해당 게시글의 내용이 작성되어있고, 각 input을 수정하면됨 -->
			<form action="/qnaModify" method="POST">
				<input type="hidden" name="articleId" value="${qna.articleId}"> <!-- articleId를 hidden input으로 전달 -->
				
				<label>제목 <input type="text" name="title" value="${ qna.title }"></label><br> <!-- br : 개행 -->
				<label>작성자 <input type="text" name="username" value="${ qna.username }"></label><br>
				<label>내용</label><br>
				<textarea rows="5" cols="30" name="content">${qna.content}</textarea><br>
				<label>비밀번호 <input type="password" name="password" required></label><br>
				<input type="submit" value="수정"> <!-- 제출 버튼 (수정 버튼) -->
			</form>
		</div>
		
		<div class="returnPost">
			<!-- 게시글 상세 페이지로 이동 -->
			<a href="/qna/${qna.articleId}">게시글로 돌아가기</a>
		</div>
	</div>
</body>
</html>