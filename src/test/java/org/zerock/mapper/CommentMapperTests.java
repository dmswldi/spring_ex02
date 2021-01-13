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
import org.zerock.domain.CommentVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CommentMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private CommentMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}

	@Test
	public void testGetList() {
		List<CommentVO> list = mapper.getList();
		assertNotEquals(list.size(), 0);
	}
	
	@Test
	public void testInsert() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("hihihello");
		comment.setWriter("yun2");
		
		int before = mapper.getList().size();
		
		mapper.insertSelectKey(comment);
		
		int after = mapper.getList().size();
		
		assertEquals(before + 1, after);
	}
	
	@Test
	public void testInsertSelectKey() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("hihihello");
		comment.setWriter("yun2");
		
		int before = mapper.getList().size();
		
		mapper.insertSelectKey(comment);
		
		int after = mapper.getList().size();
		
		assertEquals(before + 1, after);
	}
	
	@Test
	public void testRead() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("hihihello");
		comment.setWriter("yun2");
		
		mapper.insertSelectKey(comment);
		
		CommentVO readComment = mapper.read(comment.getCno());
		
		assertNotNull(readComment);
		assertEquals(readComment.getCno(), comment.getCno());
	}
	
	@Test
	public void testDelete() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("hihihello");
		comment.setWriter("yun2");
		
		mapper.insertSelectKey(comment);
		
		int before = mapper.getList().size();
		
		mapper.delete(comment.getCno());
		
		int after = mapper.getList().size();
		
		assertEquals(before - 1, after);
	}
	
	@Test
	public void testUpdate() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("hihihello");
		comment.setWriter("yun2");
	
		mapper.insertSelectKey(comment);
		
		CommentVO newComment = new CommentVO();
		newComment.setCno(comment.getCno());
		newComment.setBno(comment.getBno());
		newComment.setContent("hihihellooooo");
		newComment.setWriter("yun22222");
		mapper.update(newComment);
		
		CommentVO updatedComment = mapper.read(newComment.getCno());
		assertEquals(updatedComment.getContent(), "hihihellooooo");
		assertEquals(updatedComment.getWriter(), "yun22222");
		
	}
}