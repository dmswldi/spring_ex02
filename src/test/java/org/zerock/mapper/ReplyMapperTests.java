package org.zerock.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	private Long[] bnoArr = {186L, 187L, 188L, 199L, 205L};// 존재하는 게시물 번호
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void testList() {
		List<ReplyVO> list = mapper.getListWithPaging(null, 205L);
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void testUpdate() {
		ReplyVO vo = new ReplyVO();
		vo.setRno(26L);
		vo.setReply("updated reply !");
		
		mapper.update(vo);
		assertEquals("updated reply !", vo.getReply());
	}
	
	@Test
	public void tesetDelete() {
		Long rno = 27L;
		int cnt = mapper.delete(rno);
		assertEquals(1, cnt);
	}
	
	@Test
	public void testRead() {
		Long rno = 26L;
		
		ReplyVO vo = mapper.read(rno);
		assertEquals("reply test 1", vo.getReply());
	}
	
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			// .range() -> 1~10 가진 stream 리턴
			log.info(i + ", " + i % 5);// 1 2 3 4 5
		
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("reply test " + i);
			vo.setReplyer("replyer" + i);
			
			mapper.insert(vo);
		});
		
	}
	
	@Test
	public void testCreate2() {
		ReplyVO vo = new ReplyVO();
		vo.setBno(205L);
		vo.setReply("Hello today is 25 Jan");
		vo.setReplyer("newEun");
		
		mapper.insert(vo);
		
		try {
			vo.setBno(204L);
			mapper.insert(vo);
			fail();// 여기까지 오면 fail
		} catch(Exception e) {
			
		}
	}
}
