<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제용 비밀번호 확인 페이지</title>
</head>
<body>
	<h3>게시글 삭제를 위해 비밀번호를 입력하세요.</h3>
	<form action="/qnaDelete/${article_id}" method="POST">
		<label for="password">비밀번호: </label>
		<input type="password" id="password" name="password" required>
		<input type="submit" value="삭제하기">
	</form>
	<a href="/qna/${article_id}">게시글로 돌아가기</a>
</body>
</html>