package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private int startPage;
	private int endPage;
	
	private boolean prev;
	private boolean next;
	
	private int total;
	
	private Criteria criteria;
	
	public PageDTO(Criteria criteria, int total) {
		this.criteria = criteria;
		this.total = total;

		/* pagination 1-5 (5�� �������� ���̰�) */
		this.endPage = (int) Math.ceil(criteria.getPageNum() / 5.0) * 5;
		this.startPage = endPage - 4;
		
		int realEnd = (int) Math.ceil(total * 1.0 / criteria.getAmount());// �� �� ������
		
		this.endPage = Math.min(realEnd, endPage);
		
		this.prev = this.startPage > 1;
		this.next = endPage < realEnd;
		
		
		/* pagination 1-10 (10�� �������� ���̰�) 
		this.endPage = (int) Math.ceil(criteria.getPageNum() / 10.0) * 10;
		this.startPage = endPage - 9;
		
		int realEnd = (int) Math.ceil(total * 1.0 / criteria.getAmount());
		
		endPage = Math.min(realEnd, endPage);
		
		this.prev = this.startPage > 1;
		this.next = endPage < realEnd;
		*/
	}
}
