package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum;
	private int amount;
	
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {// type to array
		if(this.type == null) {
			return new String[] {};// 빈 string 배열  vs.  new String[0]
		} 
		/*else if(this.type.length() == 1) {
			return new String[] {type};
		} */
		else {
			System.out.println(type.split(""));
			return type.split("");
		}
	}
}
