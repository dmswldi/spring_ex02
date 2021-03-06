package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private Long bno;// long의 wrapper class
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
	private int replyCnt;
	
	private String filename;
}
