package org.zerock.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testSearch1() {// WHERE절 붙을 때 (조건에 따른 paging)
		Criteria criteria = new Criteria();
		criteria.setType("T");
		criteria.setKeyword("test");
		
		mapper.getListWithPaging(criteria);
	}
	
	@Test
	public void testSearch2() {// WHERE절 붙을 때 (조건에 따른 paging)
		Criteria criteria = new Criteria();
		criteria.setType("C");
		criteria.setKeyword("content");
		
		mapper.getListWithPaging(criteria);
	}
	
	@Test
	public void testSearch3() {// WHERE절 붙을 때 (조건에 따른 paging)
		Criteria criteria = new Criteria();
		criteria.setType("TW");
		criteria.setKeyword("what");
		// T or W가 what인 것 검색
		
		mapper.getListWithPaging(criteria);
	}
	
	@Test
	public void testGetList() {
		List<BoardVO> list = mapper.getList();
		
		//assertEquals(list.size(), 5);
		assertNotEquals(list.size(), 0);
	}
	
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("its me");
		board.setContent("hello");
		board.setWriter("ji");
		
		int before = mapper.getList().size();
		
		mapper.insert(board);
		
		int after = mapper.getList().size();
		
		assertEquals(before + 1, after);
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("its me22");
		board.setContent("hello22");
		board.setWriter("jiiiii");
		
		int before = mapper.getList().size();
		
		mapper.insertSelectKey(board);
		
		int after = mapper.getList().size();
		
		assertEquals(before + 1, after);
		assertNotEquals(board.getBno(), new Long(0));
	}
	
	@Test
	public void testRead() {
		BoardVO board = new BoardVO();
		board.setTitle("its me3");
		board.setContent("hello3");
		board.setWriter("eunji3");
		
		mapper.insertSelectKey(board);
		
		BoardVO readBoard = mapper.read(board.getBno());
		
		assertNotNull(readBoard);
		assertEquals(readBoard.getBno(), board.getBno());
		log.info("readBoard is : " + readBoard);//????
	}
	
	@Test
	public void testDelete() {
		BoardVO board = new BoardVO();
		board.setTitle("its me333");
		board.setContent("hello333");
		board.setWriter("eunji333");
		
		mapper.insertSelectKey(board);
		
		int before = mapper.getList().size();
		
		int cnt = mapper.delete(board.getBno());
		
		int after = mapper.getList().size();
		
		assertEquals(before - 1, after);
		assertEquals(cnt, 1);
		
		log.info("########################################");
		System.out.println("????????????????????????");
		log.info("delete count: " + cnt);
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setTitle("its me");
		board.setContent("hello");
		board.setWriter("eunji");
		
		mapper.insertSelectKey(board);
		
		board.setTitle("new title");
		board.setContent("new content");
		int cnt = mapper.update(board);
		
		BoardVO updateVO = mapper.read(board.getBno());
		
		assertEquals(updateVO.getTitle(), "new title");
		assertEquals(updateVO.getContent(), "new content");
		assertEquals(cnt, 1);
		log.info("cnt: "+cnt);
	}
	
	@Test
	public void testPaging() {// WHERE절 안 붙을 때
		Criteria criteria = new Criteria(1, 5);// 1페이지, 페이지당 5 게시물
		List<BoardVO> list = mapper.getListWithPaging(criteria);
		
		assertEquals(5, list.size());
		
		criteria = new Criteria(2, 5);
		list = mapper.getListWithPaging(criteria);
		
		list.forEach(board -> log.info("번호: " + board.getBno()));
	}
}
