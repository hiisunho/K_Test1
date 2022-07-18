package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/front")
@Log4j
public class FrontController {

	// /front/count  요청이면  /front/conut.jsp  열어주는거
	@GetMapping("/count")
	public void count() {
		
	}
	
	// 게시판에서 글상세보기용 화면 요청 -> 화면을 보여주고-> 거기서 자바스크립트로 데이터 요청해서 출력
	// /front/get/123 -> 3번글 요청하는거라 
	@GetMapping("/get/{num}")
	public String get(@PathVariable("num") Long num, Model model){
		log.info("요청글번호:"+num);
		model.addAttribute("num", num);
		return "/front/frontGet";   
	}
	

}





