package kr.co.greenart.web.customer.qna;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customerqna {
	private int article_id; // pk
	private String title; // 제목
	private String content; // 내용
	private String username; // 작성자 이름
	private String password; // 비밀번호
	private int is_secure; // 비밀글 여부 (bit 0, 1)
	private int is_deleted; // 논리 삭제 (bit 0, 1)
}