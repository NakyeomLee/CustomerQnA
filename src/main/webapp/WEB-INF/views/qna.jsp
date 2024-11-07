<%-- Java를 해석해서 다시 재조정된 글자를 보내는게 WAS의 기능 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 센터</title>
<style>
body {
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start; /* 상단 중앙에 맞춤 */
    width: 100%; /* 전체 화면 너비 사용 */
}

header {
	margin: 10px;
}

main {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
    margin: 0 auto;
    text-align: center;
}

.contentpanel {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 95%; 
    margin: auto;
}

.panel1 {
    width: 95%;
    display: flex;
    justify-content: space-between; /* 요소들을 양 끝으로 배치 */
    align-items: center;
    margin-bottom: 10px;
}

.tablePanel {
	width: 100%;
	margin: auto;
	border: 1px solid #cccccc;
	border-radius: 8px;
	background-color: #ffffff;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.tablePanel table {
	width: 100%;
	border-collapse: collapse;
}

.tablePanel th, .tablePanel td {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: center;
}

.tablePanel th {
	background-color: #f0f0f0;
	color: #333;
}

.write {
	background-color: #f0f0f0;
	color: #ffffff;
	border: 1px solid #ddd;
	padding: 10px 15px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
	width: 50px;
}

.write:hover {
	background-color: #f4f4f4;
}

a {
	text-decoration: none; /* 하이퍼링크 밑줄 없애기 */
	color: black;
}

.pagination {
	margin: 15px;
	font-size: 18px;
}

.alert {
	color: #d9534f;
	font-weight: bold;
	margin-top: 15px;
}
</style>
</head>
<body>
	<!-- 삭제 성공 여부를 알림으로 나타냄 -->
	<c:if test="${not empty message}">
		<script>
			alert("${message}");
		</script>
	</c:if>

	<header>
		<h1><a href="/qna">게시글 목록</a></h1>
	</header>
	<main>
		<div class="contentpanel">
			<div class="panel1">
				<!-- 글쓰기 페이지로 이동 -->
				<div class="write">
					<a href="/qnaWrite">글쓰기</a>
				</div>
				<div class="searchFormPanel">
					<!-- 기준별 검색 폼 -->
					<form id="searchForm" method="GET" action="/qna/search">
						<table>
							<tr>
								<td>
									<!-- 드롭박스 이용해서 검색 기준 보여줌 --> <select id="searchField"
									class="searchOption" name="searchField">
										<option value="title">제목</option>
										<option value="username">작성자</option>
								</select>
								</td>
								<td>
									<!-- 검색어 입력칸 --> <input type="text" id="searchText"
									class="searchOption" placeholder="검색어 입력" name="searchText">
								</td>
								<!-- 제출 버튼(검색 버튼) -->
								<td><input type="submit" value="검색"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="tablePanel">
				<table>
					<!-- 게시글 목록 나타낼 표 -->
					<!-- 표 헤더 -->
					<thead>
						<tr>
							<th>글 번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>작성일자</th>
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
								<td>${qna.formatCreatedAt}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- 페이징 컨트롤 -->
			<!-- 페이지 수가 1보다 클 때만 표시 -->
			<c:if test="${totalPages > 1}">
				<div class="pagination">
					<c:if test="${currentPage > 0}">
						<a href="/qna?page=${currentPage - 1}
			                <c:if test="${not empty searchField}">&searchField=${searchField}</c:if>
			                <c:if test="${not empty searchText}">&searchText=${searchText}</c:if>">이전
			            </a>
					</c:if>

					<!-- 한 번에 표시할 페이지 숫자 수 -->
					<c:set var="pageBlockSize" value="10" />
					<!-- 시작 페이지와 끝 페이지 계산 -->
					<c:set var="startPage" value="${(currentPage / pageBlockSize) * pageBlockSize}" />
					<c:set var="endPage" value="${startPage + pageBlockSize - 1}" />

					<c:if test="${endPage >= totalPages}">
						<c:set var="endPage" value="${totalPages - 1}" />
					</c:if>

					<!-- 페이지 번호 출력 -->
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:choose>
							<c:when test="${i == currentPage}">
								<!-- 현재 페이지 강조 -->
								<strong>${i + 1}</strong>
							</c:when>
							<c:otherwise>
								 <a href="/qna?page=${i}<c:if test="${not empty searchField}">&searchField=${searchField}</c:if>
								 <c:if test="${not empty searchText}">&searchText=${searchText}</c:if>">${i + 1}
			                     </a>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<c:if test="${currentPage < totalPages - 1}">
			            <a href="/qna?page=${currentPage + 1}
			                <c:if test="${not empty searchField}">&searchField=${searchField}</c:if>
			                <c:if test="${not empty searchText}">&searchText=${searchText}</c:if>">다음
			            </a>
			        </c:if>
				</div>
			</c:if>
		</div>
	</main>
</body>
</html>