<%-- Java를 해석해서 다시 재조정된 글자를 보내는게 WAS의 기능 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 센터</title>
</head>
<body>
	<header>
		<h1>게시글 목록 구현</h1>
	</header>
	<main>
		<div class="panel">
			<div class="searchPanel">
				<div>
					<a href="/qnaWrite">글쓰기</a>
				</div>
				<form id="searchForm" method="GET" action="/searchQna">
					<table>
						<tr>
							<td>
								<select id="searchField" class="searchOption"
										name="searchField">
									<option value="title">제목</option>
									<option value="content">내용</option>
									<option value="tag">태그</option>
								</select>
							</td>
							<td>
								<input type="text" id="searchText" class="searchOption"
									placeholder="검색어 입력" name="searchText">
							</td>
							<td><input type="submit" value="검색"></td>
						</tr>
					</table>
				</form>
			</div>
			<table>
				<thead>
					<tr>
						<th>no</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>댓글수</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty qnaList}">
						<!-- 게시물 목록이 비어있을 경우 -->
						<tr>
							<td colspan="5">게시글이 없습니다.</td>
						</tr>
					</c:if>
					<!-- forEach로 qnaList 순회해서 표에 게시글을 다 나타낼수 있도록 함 -->
					<c:forEach var="qna" items="${qnaList}">
						<tr>
							<td>${qna.articleId}</td>
							<td><a href="/qna/${qna.articleId}">${qna.title}</a></td>
							<td>${qna.username}</td>
							<td>${qna.views}</td>
							<td>${qna.comments}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>
</body>
</html>