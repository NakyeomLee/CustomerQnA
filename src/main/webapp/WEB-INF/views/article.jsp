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
			<p>작성일 : ${ qna.createdAt }</p><br>
			<a href="/qnaModify/${ qna.articleId }">수정하기</a>
			 <form action="/qna/delete/${qna.articleId}" method="POST" onsubmit="return confirm('이 게시글을 정말 삭제하시겠습니까?');">
        		<input type="submit" value="삭제하기">
    		 </form>
			<a href="/qna">게시글 목록으로 돌아가기</a>
		</footer>
	</article>
</body>
</html>