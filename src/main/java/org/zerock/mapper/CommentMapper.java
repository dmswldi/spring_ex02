package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.CommentVO;

public interface CommentMapper {// mybatis ��¼��...

	public List<CommentVO> getList();

	public void insert(CommentVO board);
	
	public void insertSelectKey(CommentVO comment);
	
	public CommentVO read(Long cno);
	
	public int delete(Long cno);
	
	public int update(CommentVO comment);
}
