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
import org.zerock.domain.CommentVO;
import org.zerock.mapper.CommentMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CommentServiceTests {
	@Setter(onMethod_ = @Autowired)
	private CommentService service;
	
	@Setter(onMethod_ = @Autowired)
	private CommentMapper mapper;

	@Test
	public void testExist() {
		assertNotNull(service);
	}
	@Test
	public void testRegister() {
		CommentVO comment = new CommentVO();
		comment.setBno(2L);
		comment.setContent("qquack qquack");
		comment.setWriter("ori");
		
		int before = mapper.getList().size();
		
		service.register(comment);
		
		int after = mapper.getList().size();
		
		assertEquals(before + 1, after);
	}
	
	@Test
	public void testGetList() {
		List<CommentVO> list = service.getList();
		
		assertNotNull(list);
		assertNotEquals(list.size(), 0);
		log.info("list size: " + list.size());
	}
	
	@Test
	public void testGet() {
		CommentVO comment = new CommentVO();
		comment.setBno(2L);
		comment.setContent("qquack qquack");
		comment.setWriter("ori");
		
		service.register(comment);
		
		CommentVO readComment = service.get(comment.getCno());
		
		assertNotNull(readComment);
		assertEquals(readComment.getCno(), comment.getCno());
		assertEquals(readComment.getContent(), comment.getContent());
	}
	
	@Test
	public void testDelete() {
		CommentVO comment = new CommentVO();
		comment.setBno(2L);
		comment.setContent("qquack qquack");
		comment.setWriter("ori");
		
		service.register(comment);
		
		int before = mapper.getList().size();
		
		assertTrue(service.remove(comment.getCno()));
		
		int after = mapper.getList().size();
		
		assertEquals(before - 1, after);
	}
	
	
	@Test
	public void testUpdate() {
		CommentVO comment = new CommentVO();
		comment.setBno(2L);
		comment.setContent("qquack qquack");
		comment.setWriter("ori");
		
		service.register(comment);
		
		CommentVO newComment = new CommentVO();
		newComment.setCno(comment.getCno());
		newComment.setBno(comment.getBno());
		newComment.setContent("newwww Content!");
		newComment.setWriter(comment.getWriter());
		
		assertTrue(service.modify(newComment));
		
		CommentVO updatedComment = service.get(newComment.getCno());
		
		assertEquals(updatedComment.getContent(), newComment.getContent());
		assertEquals(updatedComment.getWriter(), newComment.getWriter());
	}
	
}
