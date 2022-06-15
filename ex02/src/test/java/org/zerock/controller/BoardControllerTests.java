package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.service.BoardServiceTests;

import lombok.extern.log4j.Log4j;

@WebAppConfiguration															//이렇게
@RunWith(SpringJUnit4ClassRunner.class)											//3개
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",	//컨트롤러 테스트할 떄
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})		//필요한 애들임
@Log4j
public class BoardControllerTests {

	@Autowired
	private WebApplicationContext ctx;
	
	//컨트롤러를 테스트하기위해서는 MockMvc 객체를 이용한다.
	private MockMvc mockMvc;
	
	//테스트전에 mockMvc 객체 생성하기
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap());
	}
	
	@Test
	public void testRegister() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "1111").param("content", "냉무").param("writer", "접니당"))
				.andReturn().getModelAndView().getViewName());
	}
	
	@Test
	public void testGet() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno", "13"))
				.andReturn().getModelAndView().getModelMap());
	}
	
	@Test
	public void testRemove() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/remove").param("bno", "100"))
				.andReturn().getModelAndView().getViewName());
	}
	
	@Test
	public void testModify() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "13").param("title", "수정1").param("content", "수정내용1").param("writer", "수정자1"))
				.andReturn().getModelAndView().getModelMap());
	}
	
	@Test
	// /board/count(get) - /board/count (총 글개수를 보내준다)
	public void testCount() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/count"))
				.andReturn().getModelAndView().getModelMap());
	}
}
