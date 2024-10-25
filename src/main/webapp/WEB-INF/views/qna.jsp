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
	<!-- 삭제 성공 여부를 알림으로 나타냄 -->
	<c:if test="${not empty message}">
        <script>
            alert("${message}");
        </script>
    </c:if>
	
	<header>
		<h1>게시글 목록</h1>
	</header>
	<main>
		<div class="panel">
			<div class="searchPanel">
				<div>
					<!-- 글쓰기 페이지로 이동 -->
					<a href="/qnaWrite">글쓰기</a>
				</div>
				<!-- 기준별 검색 폼 -->
				<form id="searchForm" method="GET" action="/searchQna">
					<table>
						<tr>
							<td>
								<!-- 드롭박스 이용해서 검색 기준 보여줌 -->
								<select id="searchField" class="searchOption"
										name="searchField">
									<option value="title">제목</option>
									<option value="content">내용</option>
									<option value="tag">태그</option>
								</select>
							</td>
							<td>
								<!-- 검색어 입력칸 -->
								<input type="text" id="searchText" class="searchOption"
									placeholder="검색어 입력" name="searchText">
							</td>
							<!-- 제출 버튼(검색 버튼) -->
							<td><input type="submit" value="검색"></td>
						</tr>
					</table>
				</form>
			</div>
			<table> <!-- 게시글 목록 나타낼 표 -->
				<!-- 표 헤더 -->
				<thead>
					<tr>
						<th>no</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>댓글수</th>
					</tr>
				</thead>
				<!-- 표 바디 -->
				<tbody>
					<!-- 게시물 목록이 비어있을 경우 -->
					<c:if test="${empty qnaList}">
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
			
			<!-- 페이징 컨트롤 -->
			<c:if test="${totalPages > 1}"> <!-- 페이지 수가 1보다 클 때만 표시 -->
				<div class="pagination">
					<c:if test="${currentPage > 0}">
						<a href="/qna?page=${currentPage - 1}">이전</a>
					</c:if>

					<!-- 페이지 블록 크기 설정 -->
					<c:set var="pageBlockSize" value="10" />

					<!-- 시작 페이지와 끝 페이지 계산 -->
					<c:set var="startBlock" value="${(currentPage / pageBlockSize)}" />
					<c:set var="startPage" value="${startBlock * pageBlockSize}" />
					<c:set var="endPage" value="${startPage + pageBlockSize - 1}" />
					
					<c:if test="${endPage >= totalPages}">
						<c:set var="endPage" value="${totalPages - 1}" />
					</c:if>

					<!-- 페이지 번호 출력 -->
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:choose>
							<c:when test="${i == currentPage}">
								<strong>${i + 1}</strong> <!-- 현재 페이지 강조 -->
							</c:when>
							<c:otherwise>
								<a href="/qna?page=${i}">${i + 1}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<c:if test="${currentPage < totalPages - 1}">
						<a href="/qna?page=${currentPage + 1}">다음</a>
					</c:if>
				</div>
			</c:if>
		</div>
	</main>
</body>
</html>