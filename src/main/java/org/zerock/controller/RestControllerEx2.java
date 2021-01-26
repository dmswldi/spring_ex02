package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
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
	public Rest2 method5() {// object {}
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
	
	@RequestMapping("/ex6")
	public String[] method6() {// ["", "", ""]
		String[] arr = {"java", "json", "xml"};
		
		return arr;
	}
	
	@RequestMapping("/ex7")
	public List<String> method7() {// ["", "", ""]
		List<String> list = new ArrayList<>();
		list.add("donald");
		list.add("duck");
		list.add("hello");
		
		return list;
	}
	
	@RequestMapping("/ex8")
	public Map<String, String> method8() {// {"":"", "":"", "":""}
		Map<String, String> map = new HashMap<>();
		map.put("java", "script");
		map.put("hello", "world");
		map.put("spring", "boot");

		return map;
	}
	
	@RequestMapping("/ex9")
	public List<Rest1> method9() {// [{object}. {object}]
		List<Rest1> list = new ArrayList<>();
		
		Rest1 r1 = new Rest1();
		r1.setName("eun");
		r1.setAge(20);
		r1.setVote(false);
		
		list.add(r1);
		
		Rest1 r2 = new Rest1();
		r1.setName("yun");
		r1.setAge(23);
		r1.setVote(true);
		
		list.add(r2);
		
		return list;
	}
	
	// 응답 : status code, response header, body
	
	// 특정 status code로 응답 시
	@RequestMapping("/ex10")
	public ResponseEntity<String> method10() {// body 타입: String
		return ResponseEntity.status(200)// BodyBuilder 타입 리턴
			.body("hello");// String 리턴
	}
	
	@RequestMapping("/ex11")
	public ResponseEntity<String> method11(int num) {
		if (num > 0) {
			return ResponseEntity.status(200).body("spring");
		} else {
			return ResponseEntity.status(404).body("");
		}
	}
}
