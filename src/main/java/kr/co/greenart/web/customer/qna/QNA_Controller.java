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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	// @PageableDefault(size = 10) => pageable의 기본값을 15로 설정 => 한 페이지에 표시될 게시글의 수
	public String qna(@PageableDefault(size = 15) Pageable pageable, Model model) {
		int pageSize = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		int offset = (int) pageable.getOffset();

	    List<QNA> qnaList = service.findAll(pageSize, offset);

		// 모델에 데이터 추가
	    model.addAttribute("qnaList", qnaList);
        model.addAttribute("currentPage", pageNumber);
        // 총 페이지 수 계산
        model.addAttribute("totalPages", (service.getTotalCount() + pageSize - 1) / pageSize); 

		// 로그 정보 (실행 시 콘솔에 출력됨)
		log.info("size = " + pageable.getPageSize());
		log.info("page = " + pageable.getPageNumber());
		log.info("offset : " + pageable.getOffset());
		log.info("sort = " + pageable.getSort());

		return "qna"; // view
	}
	
	@GetMapping(value="/qna/search", params = "searchField=title")
	public String searchByTitle(
			@RequestParam("searchField") String searchField,
			@RequestParam("searchText") String searchText,
			@PageableDefault(size = 15) Pageable pageable, Model model) {
		
		int pageSize = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		int offset = (int) pageable.getOffset();
		
		// 제목으로 검색한 게시글 가져오기
		List<QNA> qnaList = service.findByTitle(searchText, pageSize, offset);
		
		// 모델에 데이터 추가
	    model.addAttribute("qnaList", qnaList);
        model.addAttribute("currentPage", pageNumber);
        // 총 페이지 수 계산
        model.addAttribute("totalPages", (service.getCountByTitle(searchText) + pageSize - 1) / pageSize);
		// 검색 조건과 입력된 검색어 가져오기
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchText", searchText);
        
		return "qna"; // view
	}
	
	@GetMapping(value="/qna/search", params = "searchField=username")
	public String searchByUsername(
			@RequestParam("searchField") String searchField,
			@RequestParam("searchText") String searchText,
			@PageableDefault(size = 15) Pageable pageable, Model model) {
		
		int pageSize = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		int offset = (int) pageable.getOffset();
		
		// 작성자로 검색한 게시글 가져오기
		List<QNA> qnaList = service.findByUsername(searchText, pageSize, offset);
		
		// 모델에 데이터 추가
	    model.addAttribute("qnaList", qnaList);
        model.addAttribute("currentPage", pageNumber);
        // 총 페이지 수 계산
        model.addAttribute("totalPages", (service.getCountByUsername(searchText) + pageSize - 1) / pageSize);
        // 검색 조건과 입력된 검색어 가져오기
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchText", searchText);
		
		return "qna"; // view
	}
	
	@GetMapping("/qna/{id}")
	public String readArticle(@PathVariable Integer id, Model model) {
		QNA qna = service.findById(id);

		// 모델에 데이터 추가
		model.addAttribute("qna", qna);

		return "article";
	}

	@GetMapping("/qnaWrite")
	public String writeQnaForm() {
		return "qnaWrite";
	}

	@PostMapping("/qnaWrite")
	public String writeQna(QNA qna, RedirectAttributes redirectAttributes) {
		// 필수 입력 검증
		if (qna.getTitle() == null || qna.getTitle().isEmpty() || 
			qna.getUsername() == null || qna.getUsername().isEmpty() ||
			qna.getContent() == null || qna.getContent().isEmpty() ||
			qna.getPassword() == null || qna.getPassword().isEmpty()) {
			
			// 오류 메세지 리다이렉트로 전달
			redirectAttributes.addFlashAttribute("errorMessage", "모든 칸의 내용을 입력해주세요.");
			return "redirect:/qnaWrite"; // 작성 페이지로 리다이렉트
		}
		
		// 게시글 등록(저장)
		service.save(qna);

		// 게시글 등록 후 게시글 목록으로 리다이렉트
		return "redirect:/qna";
	}

	@GetMapping("/qnaModify/{id}")
	public String modifyQnaForm(@PathVariable Integer id, Model model) {
		QNA qna = service.findById(id);

		model.addAttribute("qna", qna);

		return "qnaModify"; // view
	}

	@PostMapping("/qnaModify")
	public String modifyQna(QNA qna, RedirectAttributes redirectAttributes) {
		QNA originalQna = service.findById(qna.getArticleId()); // 기존 게시글 조회

		// 비밀번호가 일치하지 않는 경우
		if (!originalQna.getPassword().equals(qna.getPassword())) {

			redirectAttributes.addFlashAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "redirect:/qna/" + qna.getArticleId(); // 게시글 페이지로 리다이렉트
		}

		// 비밀번호가 일치하는 경우
		service.update(qna);
		redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");

		return "redirect:/qna/" + qna.getArticleId(); // 업데이트 후 해당 게시글 페이지로 리다이렉트
	}

	@GetMapping("/qnaDelete/{id}")
	public String deleteQnaForm(@PathVariable Integer id, Model model) {
		model.addAttribute("article_id", id); // 삭제할 게시글 id 전달

		return "qnaDelete"; // view
	}

	@PostMapping("/qnaDelete/{id}")
	public String deleteArticle(@PathVariable Integer id, @RequestParam("password") String password, QNA qna,
			RedirectAttributes redirectAttributes) {
		QNA originalQna = service.findById(id); // 기존 게시글 조회

		// 비밀번호가 일치하지 않은 경우 => 에러 메세지 전달
		if (!originalQna.getPassword().equals(password)) {

			redirectAttributes.addFlashAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "redirect:/qna/" + id;
		}

		// 비밀번호가 일치하는 경우 삭제 처리
		qna.setArticleId(id);
		qna.setDeleted(true); // 논리적 삭제 (is_deleted 플래그를 true로 설정)
		service.makeDelete(qna); // 삭제 처리

		redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");
		return "redirect:/qna"; // 삭제 후 게시글 목록으로 리다이렉트
	}
}