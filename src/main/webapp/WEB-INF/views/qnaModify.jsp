<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정 페이지</title>
</head>
<body>
	<form action="/qnaModify" method="POST">
		<input type="hidden" name="articleId" value="${qna.articleId}"> <!-- articleId를 hidden input으로 전달 -->
		<label>제목 <input type="text" name="title" value="${ qna.title }"></label><br> <!-- br : 개행 -->
		<label>작성자 <input type="text" name="username" value="${ qna.username }"></label><br>
		<label>내용</label><br>
		<textarea rows="5" cols="30" name="content">${qna.content}</textarea><br>
		<label>비밀번호 <input type="text" name="password"></label><br>
		<input type="submit" value="수정">
	</form>
</body>
</html>