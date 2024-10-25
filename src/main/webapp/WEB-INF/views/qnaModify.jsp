<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정 페이지</title>
</head>
<body>
	<!-- 수정 성공 여부를 알림으로 나타냄 -->
	<c:if test="${not empty message}">
        <script>
            alert("${message}");
        </script>
    </c:if>
    
	<form action="/qnaModify" method="POST">
		<input type="hidden" name="articleId" value="${qna.articleId}"> <!-- articleId를 hidden input으로 전달 -->
		
		<label>제목 <input type="text" name="title" value="${ qna.title }"></label><br> <!-- br : 개행 -->
		<label>작성자 <input type="text" name="username" value="${ qna.username }"></label><br>
		<label>내용</label><br>
		<textarea rows="5" cols="30" name="content">${qna.content}</textarea><br>
		<label>비밀번호 <input type="password" name="password"></label><br>
		<input type="submit" value="수정">
	</form>
	<a href="/qna/${qna.articleId}">게시글로 돌아가기</a>
</body>
</html>