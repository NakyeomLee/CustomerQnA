package kr.co.greenart.web.customer.qna;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import kr.co.greenart.web.util.QNA_NotFoundException;

// @ControllerAdvice : 특정 컨트롤러 클래스를 보조함
@ControllerAdvice(assignableTypes = QNA_Controller.class)
public class QNA_ControllerAdvice {
	
	@ExceptionHandler(QNA_NotFoundException.class)
	public ModelAndView notFound(QNA_NotFoundException e) {
		ModelAndView mv = new ModelAndView("notFound");
		mv.setStatus(HttpStatusCode.valueOf(404));
		
		return mv; 
	}
}