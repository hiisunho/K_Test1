package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.CountVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
//	전체목록/list?pageNum=2&amount=10 (get) -> /board/list.jsp
	@GetMapping("/list")
	public void list(Model model, Criteria cri) {
		log.info("list 요청");
		model.addAttribute("list", service.getList(cri));
		//model.addAttribute("count", service.count());
		PageDTO pageDTO = new PageDTO(cri, 123L);
		model.addAttribute("pageMaker", new PageDTO(cri, 123L));
		log.info("확인용:" + pageDTO);
	}
	
	//등록하기위한 화면요청
	@GetMapping("/register")
	public void register() {
		
	}
	
	//등록/register(post) -> 요청/board/list
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		log.info("글등록 요청");
		service.register(vo);
		rttr.addFlashAttribute("bno", vo.getBno());	//입력된 글번호 전송 addFlashAttribute로 전송하면 내부적으로 세션으로 처리됨 (새로고침했을때는 값이 안넘어감)
		return "redirect:/board/list";	//주의/board/list.jsp 가 아님 새로운 url 요청이다.
		//redirect하는 이유가 값을 안지우면 값을 계속 가지고 있는데 여기서 새로고침으로 무한생성이 가능하므로
	}
	
	//조회/get?bno=13(get) -> /board/get.jsp, 수정화면 열기 /modify(get) -> board/modify.jsp
	//변경 -> /get?bno=13&pageNum=2&amount=10
	@GetMapping({"/get", "/modify"})
	public void get(Long bno, Criteria cri, Model model) {
		model.addAttribute("board", service.get(bno));
	}
	
	//삭제/remove(post) -> 요청/board/list
	@PostMapping("/remove")
	public String remove(Long bno, Criteria cri, RedirectAttributes rttr) {
		if(service.remove(bno))
			rttr.addFlashAttribute("state", "remove");
		return "redirect:/board/list?pageNum="+cri.getPageNum()+"&amount="+cri.getAmount();
	}

	//수정/modify(post) -> 요청/board/list (수정처리 하는부분)
	@PostMapping("/modify")		
	public String modify(BoardVO vo, Criteria cri, RedirectAttributes rttr) {
		if(service.modify(vo))
			rttr.addFlashAttribute("state", "modify");
		return "redirect:/board/list?pageNum="+cri.getPageNum()+"&amount="+cri.getAmount();
	}
	
	//퀴즈 /총글자개수 가져오는 서비스
	@GetMapping("/count")
	public void count(Model model) {
		model.addAttribute("count", service.count());
	}
	
	//퀴즈 /랭킹 매기기
	@GetMapping("/rank")
	public void rank(Criteria cri, Model model) {
		log.info("랭킹데이터:" + service.rank());
		model.addAttribute("rank", service.rank());
	}
}
