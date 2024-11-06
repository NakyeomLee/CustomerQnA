<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성 페이지</title>
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
    max-width: 800px; /* 최대 너비 설정 */
    border: 1px solid #ddd; /* 테두리 */
    padding: 20px;
    border-radius: 10px;
    background-color: #ffffff; /* 배경색을 흰색으로 설정 */
}

a {
    color: black;
    text-decoration: none; /* 하이퍼링크 밑줄 없애기 */
}

.writeForm {
    display: flex;
    flex-direction: column;
    width: 90%; /* 전체 너비를 차지하도록 */
    gap: 10px; /* 요소 간의 간격 */
}

textarea, input[type="text"], input[type="password"] {
    width: 100%; /* 입력 필드 너비 조정 */
    padding: 10px; /* 패딩 추가 */
    border: 1px solid #ccc; /* 테두리 */
    border-radius: 5px; /* 모서리 둥글게 */
    margin-bottom: 10px;
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

.returnMain {
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
		<div class="writeForm">
			<!-- 게시글 작성 폼 -->
			<form class="writeContent" action="/qnaWrite" method="POST">
				<label>제목 <input type="text" name="title" required></label><br>
				<!-- br : 개행 -->
				<label>작성자 <input type="text" name="username" required></label><br>
				<label>내용</label><br>
				<textarea rows="5" cols="30" name="content" required></textarea><br> 
				<label>비밀번호 <input type="password" name="password" required></label><br> 
				<input type="submit" value="등록">
				<!-- 제출 버튼 (등록 버튼) -->
			</form>
		</div>

		<div class="returnMain">
			<!-- 게시글 목록 페이지로 이동 -->
			<a href="/qna">메인으로 가기</a>
		</div>
	</div>
</body>
</html>