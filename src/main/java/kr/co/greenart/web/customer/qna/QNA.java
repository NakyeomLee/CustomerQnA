package kr.co.greenart.web.customer.qna;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 241023 수업

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QNA {
	private Integer articleId; // pk
	private String title; // 제목
	private String content; // 내용
	private String username; // 작성자 이름
	private String password; // 비밀번호
	private Integer views; // 조회수
	private Integer comments; // 댓글수
	private LocalDateTime createdAt; // 작성 일자
	private LocalDateTime updatedAt; // 수정 일자
	private Boolean secure; // 비밀글 여부 (bit 0, 1)
	private Boolean deleted; // 논리 삭제 (bit 0, 1)
	
	private String formatCreatedAt; // 포맷(형태) 변경한 작성 일자
	private String formatUpdatedAt; // 포맷(형태) 변경한 수정 일자
	
	// LocalDateTime 포맷 변경 메소드
	public void formatDateTime(LocalDateTime createdAt, LocalDateTime updatedAt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		if (createdAt != null) {
			formatCreatedAt = createdAt.format(formatter);
		}
		
		if (updatedAt != null) {
			formatUpdatedAt = updatedAt.format(formatter);
		}
	}
}