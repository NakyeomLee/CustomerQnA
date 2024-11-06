<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제용 비밀번호 확인 페이지</title>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center; /* 세로 정중앙 정렬 */
	width: 100%;
	height: 100vh; /* 화면 전체 높이 */
	background-color: #f9f9f9; /* 배경색을 동일하게 */
}

.contentPanel {
	display: flex;
	flex-direction: column; /* 세로 정렬 */
	align-items: center;
	width: 80%; /* 화면 너비 */
	max-width: 500px; /* 최대 너비 설정 */
	border: 1px solid #ddd; /* 테두리 */
	padding: 20px;
	border-radius: 10px;
	background-color: #ffffff; /* 배경색을 흰색으로 설정 */
}

a {
	color: black;
	text-decoration: none; /* 하이퍼링크 밑줄 없애기 */
}

.writePassword {
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 90%; /* 전체 너비를 차지하도록 */
	gap: 10px; /* 요소 간의 간격 */
	margin: 15px;
}

input[type="submit"] {
	background-color: #f0f0f0; /* 버튼 색상 */
	color: black;
	border: none;
	padding: 10px 15px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
}

.returnPost {
	text-align: center;
	background-color: #f0f0f0; /* 버튼 색상 */
	color: #ffffff;
	border: none;
	padding: 10px 15px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
	width: 200px;
	margin-top: 15px; /* 위쪽 여백 추가 */
}
</style>
</head>
<body>
	<div class="contentPanel">
		<div class="title">
			<h3>게시글 삭제를 위해 비밀번호를 입력하세요.</h3>
		</div>

		<div class="writePassword">
			<!-- 비밀번호 입력 폼 -->
			<form action="/qnaDelete/${article_id}" method="POST">
				<label for="password">비밀번호: </label> <input type="password"
					id="password" name="password" required> <input
					type="submit" value="삭제하기">
			</form>
		</div>

		<div class="returnPost">
			<!-- 게시글 상세 페이지로 이동 -->
			<a href="/qna/${article_id}">게시글로 돌아가기</a>
		</div>
	</div>
</body>
</html>