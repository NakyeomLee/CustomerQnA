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
	public int save(QNA qna) {
		int rows = mapper.save(qna);
		
		return rows;
	}

	@Override
	public List<QNA> findAll() {
		
		
		return mapper.findAll(20, 0);
	}
}