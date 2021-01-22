package org.zerock.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration // disaptcherServlet이 일하도록 명시 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})	
@Log4j
public class BoardControllerTests {

	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	private MockMvc mockMvc;// 가짜 mvc, 서버 실행하지 않고 !
	
	@org.junit.Before // 다른 메소드 실행 이전에 실행됨 
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testExist() {
		assertNotNull(ctx);
		assertNotNull(mockMvc);
	}
	
	@Test
	public void testList() throws Exception {
		// 주소 요청해서 테스트하는 작업 대신함
		// url 요청 시 request Http 어쩌고를 만드는데 그 작업을 해줘야 함
		// .perform : dispatcherServlet한테 일 시키기
		/*
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"));
		MvcResult rs = result.andReturn();
		ModelAndView mv = rs.getModelAndView();
		log.info(mv.getView());// void
		log.info(mv.getModel().get("list"));
		*/
		Object o = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
		.andReturn()
		.getModelAndView()
		.getModel()
		.get("list");
		
		assertNotNull(o);
		assertTrue(o instanceof List);
		assertNotEquals(((List) o).size(), 0);
	}
	/*
	@Test
	public void testRegister() throws Exception {
		int before = mapper.getList().size();
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "테스트 제목")
				.param("content", "테스트 내용")
				.param("writer", "user0"))
		.andReturn();
		
		ModelAndView mv = result.getModelAndView();
		FlashMap map = result.getFlashMap();
		
		int after = mapper.getList().size();
		
		assertEquals(before + 1, after);
		assertEquals("redirect:/board/list", mv.getViewName());
		assertNotNull(map.get("result"));// getBno()
		
		log.info(map.get("result") + "************");
	}
	
	@Test
	public void testGet() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
				.param("bno", "1"))
		.andReturn();
		
		String viewName = result.getModelAndView().getViewName();
		Map<String, Object> modelMap = result.getModelAndView().getModelMap();
		
		assertEquals("board/get", viewName);// 왜 슬래시 없지..!!
		assertNotNull(modelMap.get("board"));
		assertEquals(new Long(1), ((BoardVO) modelMap.get("board")).getBno());
	}
	
	@Test
	public void testModify() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("new title");
		board.setContent("new content");
		board.setWriter("new writer");
		
		mapper.insertSelectKey(board);
		
		Long key = board.getBno();// controller가 dispatcherServlet한테 model에 값 담아서 넘겨준다
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", key + "")
				.param("title", "ttt")
				.param("content", "ccc")
				.param("writer", "www"))
		.andReturn();
		
		FlashMap map = result.getFlashMap();
		String viewName = result.getModelAndView().getViewName();
		BoardVO mod = mapper.read(key);
		
		assertEquals("ttt", mod.getTitle());
		assertEquals("ccc", mod.getContent());
		assertEquals("modSuccess", map.get("result"));
		assertEquals("redirect:/board/list", viewName);
	}
*/
	@Test
	public void testRemove() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("new title");
		board.setContent("new content");
		board.setWriter("new writer");
		
		mapper.insertSelectKey(board);
		
		Long key = board.getBno();
		
		int before = mapper.getList().size();
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", key + ""))
		.andReturn();
		/*
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/board/remove")
				.param("bno", key + ""))
		.andReturn();
		*/
		int after= mapper.getList().size();
		
		log.warn("before: " + before + " / after: " + after + "*******");
		
		assertEquals(before - 1, after);
		
		String viewName = result.getModelAndView().getViewName();
		
		assertEquals("redirect:/board/list", viewName);
		
		assertEquals("delSuccess", result.getFlashMap().get("result"));
		
	}
	
	@Test
	public void testListPaging() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "2")
				.param("amount", "10"))
		.andReturn();
		
		Map<String, Object> model = result.getModelAndView().getModel();
		List list = (List) model.get("list");
		
		assertEquals(10, list.size());
	}
}
