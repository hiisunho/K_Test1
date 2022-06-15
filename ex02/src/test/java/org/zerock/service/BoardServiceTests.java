package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Autowired
	private BoardService service;
	//목록보기
	@Test
	public void testGetList() {
		Criteria cri = new Criteria();
		service.getList(cri).forEach(vo -> log.info(vo));
	}
	
	//글등록
	@Test
	public void testRegister() {
		BoardVO vo = new BoardVO();
		vo.setTitle("서비스제목입니다");
		vo.setContent("서비스내용입니다");
		vo.setWriter("서비스작성자입니다");
		service.register(vo);
	}
	//글삭제
	@Test
	public void testRemove() {
		log.info("삭제된 글 수 : " + service.remove(2L));
	}

	
	//글수정
	@Test
	public void testModify() {
		BoardVO vo = new BoardVO();
		vo.setBno(6L);
		vo.setTitle("바뀐서비스제목입니다");
		vo.setContent("바뀐서비스내용입니다");
		vo.setWriter("바뀐서비스작성자입니다");
		log.info("수정된 글 수 : " + service.modify(vo));
	}
	
	//글상세보기
	@Test
	public void testGet() {
		log.info(service.get(6L));
	}
}
