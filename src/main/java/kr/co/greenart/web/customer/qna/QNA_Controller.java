package kr.co.greenart.web.customer.qna;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QNA_Controller {
	// MVC : model, view, controller
	// 사용자가 view(UI)를 조작할때 controller에 명령이 내려짐
	// controller는 명령에 따라서 model을 조작함
	// model은 사용자에게 view(UI)의 형태로 제공을 해줌
	
	private QNA_Service service;
	
	@GetMapping("/qna")
	public String qna(Model model) {
		model.addAttribute("qnaList", new ArrayList<QNA>());
		
		return "qna"; // view
	}
}