<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성 페이지</title>
</head>
<body>
	<!-- 게시글 작성 폼 -->
	<form action="/qnaWrite" method="POST">
		<label>제목 <input type="text" name="title"></label><br> <!-- br : 개행 -->
		<label>작성자 <input type="text" name="username"></label><br>
		<label>내용</label><br>
		<textarea rows="5" cols="30" name="content"></textarea><br>
		<label>비밀번호 <input type="password" name="password"></label><br>
		<input type="submit" value="등록"> <!-- 제출 버튼 (등록 버튼) -->
	</form>
	
	<!-- 게시글 목록 페이지로 이동 -->
	<a href="/qna">게시글 목록으로 돌아가기</a>
</body>
</html>