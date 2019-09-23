package kr.or.ddit.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	/**
	* Method : main
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @return
	* Method 설명 : 메인화면 출력
	*/
	@RequestMapping("/main")
	public String main() {
		
		return "main";
	}
}
