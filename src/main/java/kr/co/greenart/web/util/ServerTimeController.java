package kr.co.greenart.web.util;

import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 241022 수업

@Controller // @component의 다른 이름
public class ServerTimeController {
	@GetMapping("/server/time")
	public String serverTime(Model model) {
		model.addAttribute("serverTime", LocalDateTime.now());
		
		return "server-time"; // view
	}
}