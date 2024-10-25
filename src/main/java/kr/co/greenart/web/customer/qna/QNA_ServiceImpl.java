package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.greenart.web.util.QNA_IsSecure;
import kr.co.greenart.web.util.QNA_NotFoundException;

@Service
public class QNA_ServiceImpl implements QNA_Service {
	@Autowired
	private QNA_Mapper mapper;
	
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

	@Override
	@Transactional
	public int save(QNA qna) {
		int rows = mapper.save(qna);
		
		return rows;
	}

	@Override
	public List<QNA> findAll(int pageSize, int offset) {
		
		// int limit : 몇 개의 데이터를 가지고 올 지
		// int offset : 시작할 위치 설정 (0부터 시작)
		// (이 경우 페이지당 5개 게시글 표시)
		return mapper.findAll(pageSize, offset);
	}
	
	@Override
	public int getTotalCount() {
	    return mapper.getTotalCount();
	}

	@Override
	@Transactional
	public int update(QNA qna) {
		
		return mapper.update(qna);
	}

	@Override
	@Transactional
	public int makeDelete(QNA qna) {
		
		return mapper.updateDelete(qna);
	}

}