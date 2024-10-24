<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 조회 페이지</title>
</head>
<body>
	<article>
		<header>
			<h3>제목 : ${ qna.title }</h3>
			<h4>작성자 : ${ qna.username }</h4>
			<p>조회수 : ${ qna.views }</p>
		</header>
		<p>내용</p>
		<p>${ qna.content }</p>
		<footer>
			<p>작성일 : ${ qna.createdAt }</p>
		</footer>
	</article>
</body>
</html>