package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.RankVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/api")
@Log4j
@AllArgsConstructor
public class ApiController {

	private BoardService service;
	
	@GetMapping("/rank")
	public List<RankVO> rank() {
		return service.rank();
	}
	
	
	//목록보기
	//  /list?pageNum=2&amount=10 
	@GetMapping("/list")
	public List<BoardVO> list(Criteria cri) {
		return service.getList(cri);
	
	}
	
	//글 상세보기
	// /get/23   -> 23번글 글 정보를 다주도록
	@GetMapping("/get/{num}")
	public BoardVO get(@PathVariable("num") Long num) {
		return service.get(num);
	}
	
	//글 수정
	// /modify   -> put으로 요청  (data는 json으로보내주는거)
	// 응답은 문자로  ok
	@PutMapping("/modify")
	public String modify(@RequestBody BoardVO vo) {
		 service.modify(vo); //수정끝
		 return "ok";
	}
	
	//글 등록
	// /register  -> post (data는 json)
	// 응답데이터는 없음
	@PostMapping("/register")
	public void register(@RequestBody BoardVO vo) {
		service.register(vo);
	}
	
	//글 삭제
	// /remove/123  ->delete (123번글 삭제)
	
	// 삭제성공시 success 문자를 돌려주고 상태코드는 200으로 
	// 삭제실패시 아무것도 안돌려주고 500(INTERNAL_SERVER_ERROR)으로
	@DeleteMapping("/remove/{num}")
	public ResponseEntity<String> remove(@PathVariable("num") Long num) {
		if(service.remove(num)==true) {
			return ResponseEntity.status(HttpStatus.OK).body("success");
		}else {
			return ResponseEntity.status(HttpStatus.
					INTERNAL_SERVER_ERROR).body("");
		}
	}
	
	//글개수 가져오기 (검색된 글개수도 지원) GET 요청
	// /count  (전체글개수)
	// /count?type=T&keyword=aa  (제목이aa인 글개수)
	@GetMapping("count")
	public String count(Criteria cri) {
		//총글개수 가져오는 서비스
		Long count = service.count(cri);
		return count + "";
	}
	
	
}


























