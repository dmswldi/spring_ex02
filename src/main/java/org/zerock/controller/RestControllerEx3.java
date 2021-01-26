package org.zerock.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Rest1;

import lombok.extern.log4j.Log4j;

@RestController // @Controller + @ResponseBody
@RequestMapping("/rest3")
@Log4j
public class RestControllerEx3 {

	@RequestMapping("/ex1")
	public String method1(String name) {
		log.info("name: " + name);
		
		return "spring";
	}
	
	@RequestMapping("/ex2/{val}")// path variable, 이 val이
	public String method2(@PathVariable("val") String value) {// 여기로 들어감
		log.info("method2");
		
		return "method2";
	}
	
	@RequestMapping("/ex3/{val}")// 이 val이
	public String methodd3(@PathVariable String val) {// 여기로!
		log.info("method3");
		
		return val;
	}
	
	@RequestMapping("/ex4/{val}/other/{age}")
	public String methodd4(@PathVariable String val, @PathVariable int age) {
		return val + age;
	}
	
	@RequestMapping("/ex5")
	public String method5(@RequestBody String b) {// body 쓰려면 post로 보내야 함, 몸통이 데이터:raw
		log.info(b);
		
		return "method5";
	}
	
	// Header: Content-Type: json (서버->client)
	@RequestMapping("/ex6")
	public String method6(@RequestBody Rest1 body) {
		log.info(body);
		
		return "method6";
	}
	
	// 특정 midia type만 골라서 일함, request header의 Content-Type
	@RequestMapping(path = "/ex7", consumes = "text/plain")// http media type (mime 타입)
	public String method7(@RequestBody String body) {
		log.info(body);
		
		return "method7";
	}
	
	@RequestMapping(path = "/ex8", consumes = MediaType.APPLICATION_JSON_VALUE)// http media type (mime 타입)
	public String method8(@RequestBody String body) {
		log.info(body);
		
		return "method8";
	}
	
	@RequestMapping(path = "/ex9", 
					consumes = {MediaType.APPLICATION_JSON_VALUE, 
								MediaType.TEXT_PLAIN_VALUE})// http media type (mime 타입)
	public String method9(@RequestBody String body) {
		log.info(body);
		
		return "method9";
	}
	
	// client -> server : request의 Content-Type (mime의 consumes)
	// jsp : text/html
	// server -> client : request의 Accept (mime의 produces)
}
