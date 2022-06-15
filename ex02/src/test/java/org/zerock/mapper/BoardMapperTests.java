package org.zerock.mapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Mapper;
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
public class BoardMapperTests {

	@Autowired	//필드 주입
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
//		log.info(mapper.getList());
/*		for (BoardVO vo : mapper.getList()) {
			log.info(vo);
		} */
		
		mapper.getList().forEach(vo -> log.info(vo));
	}
	
	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria();
		mapper.getListWithPaging(cri).forEach(vo -> log.info(vo));
	}
	
	@Test
	public void testInsert() {
		BoardVO vo = new BoardVO();
		vo.setTitle("제목입니다");
		vo.setContent("내용입니다");
		vo.setWriter("작성자입니다");
		mapper.insert(vo);
	}
	
	@Test
	public void testRead() {
		log.info("상세보기:" + mapper.read(3L));	//Long타입이 1이다.
	}
	
	@Test
	public void testDelete() {
		log.info("삭제된 글의 개수는:" + mapper.delete(1L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		vo.setBno(4L);
		vo.setTitle("바뀐제목입니다");
		vo.setContent("바뀐내용입니다");
		vo.setWriter("바뀐작성자입니다");
		
		log.info("수정된 글의 개수는:" + mapper.update(vo));
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO vo = new BoardVO();
		vo.setTitle("제목입니다");
		vo.setContent("내용입니다");
		vo.setWriter("작성자입니다");
		
		mapper.insertSelectKey(vo);
		log.info("등록된 글번호는:" + vo.getBno());
	}
	
	
	@Test
	public void testCount() {
		log.info("글개수는:" + mapper.count());
	}
	
	@Test
	public void testWriteCount() {
		BoardVO vo = new BoardVO();
		vo.setWriter("user00");
		log.info("유저글개수:" + mapper.writeCount(vo));
	}
	
	@Test
	public void testQuiz1() {
		log.info("유저별 글 개수:" + mapper.quiz1());
	}
	
	@Test
	public void testQuiz2() {
//		log.info("제목,작성자:" + mapper.quiz2());
		for (BoardVO vo : mapper.quiz2()) {
			log.info(vo.getTitle() + "   " + vo.getWriter());
		}
	}
}
