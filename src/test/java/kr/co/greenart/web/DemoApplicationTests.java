package kr.co.greenart.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.greenart.web.customer.qna.QNA;
import kr.co.greenart.web.customer.qna.QNA_Mapper;

@SpringBootTest // 스트링부트에서의 테스트
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 메소드에 지정한 @Order의 순서를 따르겠다
class DemoApplicationTests {
	
	// 241023 수업
	
	@Autowired
	private QNA_Mapper mapper;
	
	@Test
	@Order(1) // 테스트 순서 정하기 (이 경우 첫번째, 원래는 알파벳 순이라서 만약 다른 메소드도 1로 작성하면 다시 알파벳 순서로 진행됨)
	void testInsert() {
		QNA qna = QNA.builder().title("제목").content("내용").username("유저네임").password("비밀번호").build();
		int rows = mapper.save(qna);
		
		assertEquals(1, rows);
		assertNotNull(qna.getArticleId());
	}
	
	@Test
	@Order(2) // 테스트 순서 정하기 (이 경우 두번째)
	void testSelect() {
		List<QNA> all = mapper.findAll(10, 0);
		
		assertNotEquals(0, all.size());
	}
}