package org.zerock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Rest1;
import org.zerock.domain.Rest2;

import lombok.extern.log4j.Log4j;

//@Controller
@RestController // @Controller + @ResponseBody
@RequestMapping("/rest2")
@Log4j
public class RestControllerEx2 {
	
	@RequestMapping("/ex1")
	//@ResponseBody
	public String method1() {
		return "hello";
	}
	
	@RequestMapping("/ex02")
	public String method02() {
		log.info("method02");
		
		Rest1 r = new Rest1();
		r.setName("donald");
		r.setAge(20);
		
		return "ex02";
	}
	
	@RequestMapping("/ex2")
	public Rest1 method2() {// 객체가 responseBody가 됨
		log.info("method2");
		
		Rest1 r = new Rest1();
		r.setName("donald");
		r.setAge(20);
		
		return r;
	}
	
	@RequestMapping("/ex3")
	public String method3() {// 객체가 responseBody가 됨
		log.info("method3");
		
		Rest1 r = new Rest1();
		r.setName("donald");
		r.setAge(20);
		
		// 객체를 text로 -> json 표기법
		//String res = "이름: " + r.getName() + ", 나이: " + r.getAge();
		String res = "{\"name\":\"" + r.getName() + "\", \"age\":" + r.getAge() + "}";// java에서 따옴표 \"
		
		return res;
	}
	
	@RequestMapping("/ex4")// /rest2/ex4.json -> 객체를 json으로 표기해줌
	public Rest1 method4() {
		log.info("method4");
		
		Rest1 r = new Rest1();
		r.setName("donald");
		r.setAge(20);
		
		// String 변환 코드 생략, xml 표기 방법으로 표현됨
		return r;
	}
	
	@RequestMapping("/ex5")
	public Rest2 method5() {
		log.info("method5");
				
		Rest1 r1 = new Rest1();
		r1.setName("donald");
		r1.setAge(28);
		r1.setVote(true);
		
		Rest2 r2 = new Rest2();
		r2.setAddress("seoul");
		r2.setRest1(r1);
		
		// String 변환 코드 생략, xml 표기 방법으로 표현됨
		return r2;
	}
}
