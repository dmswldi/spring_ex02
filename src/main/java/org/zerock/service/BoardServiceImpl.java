package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

//@Component // spring이 관리하는 bean
@Service // @Component 포함
//@AllArgsConstructor // Generates an all-args constructor
@Log4j
public class BoardServiceImpl implements BoardService {
	private BoardMapper mapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper mapper) {// @AllArgsConstructor랑 같음
		this.mapper = mapper;
	}
	
	@Override
	public void register(BoardVO board) {
		mapper.insertSelectKey(board);
	}
	/*
	@Override
	public List<BoardVO> getList() {
		return mapper.getList();
	}*/
	
	@Override
	public List<BoardVO> getList(Criteria criteria) {
		return mapper.getListWithPaging(criteria);
	}
	
	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}
	
	@Override
	public boolean remove(Long bno) {
		return mapper.delete(bno) == 1;
	}
	
	@Override
	public boolean modify(BoardVO board) {
		return mapper.update(board) == 1;
	}
	
	@Override
	public int getTotal(Criteria criteria) {
		return mapper.getTotalCount(criteria);
	}
}
