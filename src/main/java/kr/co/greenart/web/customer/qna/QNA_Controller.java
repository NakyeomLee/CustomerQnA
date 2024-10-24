package kr.co.greenart.web.customer.qna;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.greenart.web.util.QNA_NotFoundException;

// 241023 수업
// 241023 Q

@Controller
public class QNA_Controller {
	// MVC : model, view, controller
	// 사용자가 view(UI)를 조작할때 controller에 명령이 내려짐
	// controller는 명령에 따라서 model을 조작함
	// model은 사용자에게 view(UI)의 형태로 제공을 해줌
	
	@Autowired
	private QNA_Service service;
	
	@GetMapping("/qna")
	public String qna(Model model) {
		List<QNA> qnaList = service.findAll();
		
		model.addAttribute("qnaList", qnaList);
		
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
}