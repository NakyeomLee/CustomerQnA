package kr.co.greenart.web.customer.qna;

import java.util.List;

// 요구사항 도출 / 검증
public interface QNA_Service {
	// article_id로 게시글 조회
	// 조회수 자동 증가
	QNA findById(Integer articleId);
	
	// 게시글 작성
	int save(QNA qna);
	
	// 게시글 목록 조회
	List<QNA> findAll(int pageSize, int offset);
	
	// 전체 게시글 수 조회
	int getTotalCount();
		
	// 제목으로 게시글 검색
	List<QNA> findByTitle(String title, int pageSize, int offset);
	
	// 작성자로 게시글 검색
	List<QNA> findByUsername(String username, int pageSize, int offset);
	
	// 제목으로 검색한 게시글 수 조회
	int getCountByTitle(String title);
	
	// 작성자로 검색한 게시글 수 조회
	int getCountByUsername(String username);
	
	// 게시글 수정
	int update(QNA qna);
	
	// 게시글 논리 삭제
	int makeDelete(QNA qna);	
/*
1. 게시글 작성

- [V] 필수 입력 항목: 제목, 내용, 유저이름, 비밀번호
	  비밀번호는 게시글 수정/삭제 시 필요

2. 게시글 조회

모든 사용자가 게시글 열람 가능 (비밀글은 비밀번호 일치시)
- [V] 조회수 자동 증가
최신순/조회수순 정렬 가능
- [V] 페이지당 20개 게시글 표시 (일단 10개로 해놓음)
페이지 표시하는 숫자가 10을 넘을 경우 다음을 눌러야 11부터 또 나오도록 하기

3. 게시글 수정/삭제

- [V] 작성 시 입력한 비밀번호로 인증
수정 이력 저장 (해당 게시물에 수정일자 남기기)
- [V] 삭제 시 실제 삭제가 아닌 논리 삭제 처리

4. 검색 기능

검색 대상: 제목, 내용, 태그

5. 관리자 기능

비밀 게시글 조회
게시글 강제 삭제

6. 공유

게시글 공유 링크 생성
 */
}