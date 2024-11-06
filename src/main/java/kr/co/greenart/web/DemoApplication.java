package kr.co.greenart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kr.co.greenart.web.customer.qna.QNA;
import kr.co.greenart.web.customer.qna.QNA_Mapper;

@SpringBootApplication // @componentscan도 포함되어있음
public class DemoApplication implements CommandLineRunner {

	@Autowired // QNA_Mapper bean 주입
	private QNA_Mapper mapper;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// 241024 수업
	// 애플리케이션 실행 시 호출되는 메소드
	// 실행하면 QNA 객체가 게시글 목록 표에 나타날 것
	@Override
	public void run(String... args) throws Exception {
		// 반복문 활용해서 QNA 데이터 저장 (이 경우 5번 저장)
		for (int i = 1; i < 35; i++) {
			// QNA 객체를 빌더 패턴으로 생성
			mapper.save(QNA.builder().title("제목" + i).content("내용" + i).username("작성자" + i)
					.password("password" + i).build());
		}
	}
}