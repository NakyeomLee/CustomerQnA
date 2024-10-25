package kr.co.greenart.web.customer.qna;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

// 241023 수업
// 241023 Q

@Controller
@Slf4j
public class QNA_Controller {
	// MVC : model, view, controller
	// 사용자가 view(UI)를 조작할때 controller에 명령이 내려짐
	// controller는 명령에 따라서 model을 조작함
	// model은 사용자에게 view(UI)의 형태로 제공을 해줌
	
	@Autowired
	private QNA_Service service;
	
	@GetMapping("/qna")
	// @PageableDefault(size = 10) => pageable의 기본값을 10으로 설정
	public String qna(@PageableDefault(size = 10) Pageable page, Model model) {
		List<QNA> qnaList = service.findAll();
		
		model.addAttribute("qnaList", qnaList);
		
		log.info("size = " + page.getPageSize());
		log.info("page = " + page.getPageNumber());
		
		log.info("offset : " + page.getOffset());
		
		log.info("sort = " + page.getSort());
		
		return "qna"; // view
	}
	
	@GetMapping("/qna/{id}")
	public String readArticle(@PathVariable Integer id, Model model) {
		QNA qna = service.findById(id);
		
		model.addAttribute("qna", qna);
		
		return "article";
	}
	
	@GetMapping("/qnaWrite")
	public String writeQnaForm() {
		return "qnaWrite";
	}
	
	@PostMapping("/qnaWrite")
	public String writeQna(QNA qna) {
		service.save(qna);
		
		return "redirect:/qna";
	}
	
	@GetMapping("/qnaModify/{id}")
	public String modifyQnaForm(@PathVariable Integer id, Model model) {
		QNA qna = service.findById(id);
		
		model.addAttribute("qna", qna);
		
		return "qnaModify";
	}
	
	@PostMapping("/qnaModify")
	public String modifyQna(QNA qna) {
		service.update(qna);
		
		return "redirect:/qna/" + qna.getArticleId(); // 업데이트 후 해당 게시글 페이지로 리다이렉트
	}
	
	@PostMapping("/qna/delete/{id}")
	public String deleteArticle(@PathVariable Integer id, QNA qna) {
	    qna.setArticleId(id);
	    qna.setDeleted(true);  // 논리적 삭제 (is_deleted 플래그를 true로 설정)
	    service.makeDelete(qna);  // 삭제 처리

	    return "redirect:/qna";  // 삭제 후 게시글 목록으로 리다이렉트
	}
	
}