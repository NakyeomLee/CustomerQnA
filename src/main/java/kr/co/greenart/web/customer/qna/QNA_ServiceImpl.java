package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.greenart.web.util.QNA_IsSecure;
import kr.co.greenart.web.util.QNA_NotFoundException;

@Service
public class QNA_ServiceImpl implements QNA_Service {
	@Autowired // QNA_Mapper bean 주입
	private QNA_Mapper mapper;

	// article_id에 해당하는 게시글 조회
	// @Transactional : 해당 메소드가 트랜잭션 안에서 실행되고,
	// 예외 발생하면 자동 롤백, 예외 발생하지않으면 커밋됨
	// 데이터베이스 상태를 변경하는 메서드들에 필요(새 글 작성, 수정, 삭제 등등)
	@Override
	@Transactional
	public QNA findById(Integer articleId) {
		QNA qna = mapper.findById(articleId);

		if (qna == null) {
			throw new QNA_NotFoundException(articleId);
		}

		if (qna.getSecure()) {
			throw new QNA_IsSecure(articleId);
		}

		int rows = mapper.updateCount(articleId);
		if (rows == 1) {
			qna.setViews(qna.getViews() + 1);
		}

		return qna;
	}

	// 게시글 작성
	@Override
	@Transactional
	public int save(QNA qna) {
		int rows = mapper.save(qna);

		return rows;
	}

	// 게시글 목록 조회
	@Override
	public List<QNA> findAll(int pageSize, int offset) {

		// int limit : 몇 개의 데이터를 가지고 올 지
		// int offset : 시작할 위치 설정 (0부터 시작)
		// (이 경우 페이지당 5개 게시글 표시)
		List<QNA> qnaList = mapper.findAll(pageSize, offset);

		// LocalDateTime(등록일자, 수정일자) 포맷 설정
		for (QNA qna : qnaList) {
			qna.formatDateTime(qna.getCreatedAt(), qna.getUpdatedAt());
		}

		return qnaList;
	}
	
	// 제목으로 게시글 검색
	@Override
	public List<QNA> findByTitle(String title, int pageSize, int offset) {
		
		List<QNA> searchByTitleList = mapper.findByTitle(title, pageSize, offset);
	    
		// LocalDateTime(등록일자, 수정일자) 포맷 설정
		for (QNA qna : searchByTitleList) {
	        qna.formatDateTime(qna.getCreatedAt(), qna.getUpdatedAt());
	    }
		
	    return searchByTitleList;
	}

	// 작성자로 게시글 검색
	@Override
	public List<QNA> findByUsername(String username, int pageSize, int offset) {
		
		List<QNA> searchByUsernameList = mapper.findByUsername(username, pageSize, offset);
	    
		// LocalDateTime(등록일자, 수정일자) 포맷 설정
		for (QNA qna : searchByUsernameList) {
	        qna.formatDateTime(qna.getCreatedAt(), qna.getUpdatedAt());
	    }
		
	    return searchByUsernameList;
	}

	// 게시글 전체 수 조회
	@Override
	public int getTotalCount() {
	    return mapper.getTotalCount();
	}
	
	// 제목으로 검색한 게시글 수 조회
	@Override
	public int getCountByTitle(String title) {
		return mapper.getCountByTitle(title);
	}

	// 작성자로 검색한 게시글 수 조회
	@Override
	public int getCountByUsername(String username) {
		return mapper.getCountByUsername(username);
	}

	// 게시글 수정
	@Override
	@Transactional
	public int update(QNA qna) {

		return mapper.update(qna);
	}

	// 게시글 삭제
	@Override
	@Transactional
	public int makeDelete(QNA qna) {

		return mapper.updateDelete(qna);
	}
}