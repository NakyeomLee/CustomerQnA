<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 조회 페이지</title>
</head>
<body>
	<!-- 삭제 성공 여부를 알림으로 나타냄 -->
	<c:if test="${not empty message}">
        <script>
            alert("${message}");
        </script>
    </c:if>
    
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
    		<a href="/qnaDelete/${ qna.articleId }">삭제하기</a>
			<a href="/qna">게시글 목록으로 돌아가기</a>
		</footer>
	</article>
</body>
</html>