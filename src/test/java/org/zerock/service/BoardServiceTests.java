package org.zerock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	@Setter(onMethod_ = @Autowired)
	private BoardService service;// dependency(BoardServiceImpl class) 주입
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(service);
	}

	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("title");
		board.setContent("content");
		board.setWriter("eunzzzzzi");
		
		int before = mapper.getList().size();
		
		service.register(board);
		
		int after = mapper.getList().size();
		
		log.info("생성된 게시물 번호: " + board.getBno());// <selectKey>에서 bno에 값 넣음
		assertEquals(before + 1, after);
	}
	
	@Test
	public void testGetList() {
		List<BoardVO> list = service.getList();
		
		assertNotNull(list);
		assertNotEquals(list.size(), 0);
		log.info("list size: " + list.size());//
	}
	
	@Test
	public void testGet() {
		BoardVO board = new BoardVO();
		board.setTitle("titleeeee");
		board.setContent("contenttttt");
		board.setWriter("eunzzzzziiiii");
		
		service.register(board);
		
		BoardVO readBoard = service.get(board.getBno());
		
		assertNotNull(readBoard);
		assertEquals(readBoard.getBno(), board.getBno());
		assertEquals(readBoard.getTitle(), board.getTitle());
	}
	
	@Test
	public void testDelete() {
		BoardVO board = new BoardVO();
		board.setTitle("titleeeee");
		board.setContent("contenttttt");
		board.setWriter("eunzzzzziiiii");
		
		service.register(board);
		
		assertTrue(service.remove(board.getBno()));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setTitle("titleeeee");
		board.setContent("contenttttt");
		board.setWriter("eunzzzzziiiii");
		
		service.register(board);
		
		BoardVO newBoard = new BoardVO();
		newBoard.setBno(board.getBno());
		newBoard.setTitle("title22222");
		newBoard.setContent("content222");
		newBoard.setWriter(board.getWriter());
		
		assertTrue(service.modify(newBoard));
		
		BoardVO updatedBoard = service.get(newBoard.getBno());
		
		assertEquals("title22222", updatedBoard.getTitle());
		assertEquals("content222", updatedBoard.getContent());
		
	}
}
