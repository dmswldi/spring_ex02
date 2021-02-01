package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
	
	public int getTotalCount(Criteria criteria);
	
	//@Select("SELECT * FROM tbl_board WHERE bno > 0") // or BoardMapper.xml
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria criteria	);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);// @Param("amount") 
}