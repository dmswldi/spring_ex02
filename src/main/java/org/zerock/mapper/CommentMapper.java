package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.CommentVO;

public interface CommentMapper {// mybatis가 구현 클래스 만들고 xml 읽어서 쿼리 실행해줌 

	public List<CommentVO> getList();

	public void insert(CommentVO board);
	
	public void insertSelectKey(CommentVO comment);
	
	public CommentVO read(Long cno);
	
	public int delete(Long cno);
	
	public int update(CommentVO comment);
}
