package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;

public class ReplyServiceImpl implements ReplyService {
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Override
	public int register(ReplyVO vo) {
		return mapper.insert(vo);
	}
	
	@Override
	public ReplyVO get(Long rno) {
		return mapper.read(rno);
	}
	
	@Override
	public int modify(ReplyVO vo) {
		return mapper.update(vo);
	}
	
	@Override
	public int remove(Long rno) {
		return mapper.delete(rno);
	}
	
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		return mapper.getListWithPaging(cri, bno);
	}
}
