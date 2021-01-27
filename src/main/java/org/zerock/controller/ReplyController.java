package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies")
@Log4j
@AllArgsConstructor
public class ReplyController {
	// consumes: 제출된 컨텐트의 타입 명시
	// produces: 이 메소드가 리턴할 컨텐트 타입 명시, req의 Accept에 포함된 타입일 때만 return

	// ex) 클라이언트가 text/html; application/json 타입을 받을 수 있다면, 서버는 처리된 데이터를 브라우저에 이러한 포맷으로 캡슐화해서 보낸다
	
	private ReplyService service;
	// cilient <-> server, <server 입장에서의 consumes, produces!>
	// @RequestBody : HTTP 요청 몸체를 자바 객체로 변환
	// @ResponseBody : 자바 객체를 HTTP 응답 몸체로 변환
	@RequestMapping(value = "/new",
					consumes = MediaType.APPLICATION_JSON_VALUE,// client가 요청은 json으로 server에 보내고 (req의 Content-Type) / type mapping
					produces = MediaType.TEXT_PLAIN_VALUE)// client가 응답은 text만 받겠다, res의 content-type과 안 맞으면 서버가 일 안함 (req의 Accept)
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){// 요청 본문이 내용이 되도록 / body 쓰려면 post로 보내야 함, 몸통이 데이터:raw
		
		log.info("vo: " + vo);
		
		int insertCnt = service.register(vo);
		
		log.info("count: " + insertCnt);
		
		if(insertCnt == 1) {
			return new ResponseEntity<>("success!", HttpStatus.OK);// 200, $.ajax(): jqXHR에서 꺼낼 수 있음 => jqXHR.responseText
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);// 500
		}
		
		// HttpEntity = HttpHeader +  HttpBody
		// RequestEntity, ResponseEntity = HttpStatus +  HttpHeaders + HttpBody
	}
	
	@GetMapping(value = "/pages/{bno}/{page}", // {} pathVariable
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)// consumes은 Header에서 체크, produces는 X ???
	public ResponseEntity<List<ReplyVO>> getList(
			@PathVariable int page, @PathVariable Long bno) {
		
		Criteria cri = new Criteria(page, 10);
		
		List<ReplyVO> list = service.getList(cri, bno);
		
		log.info(list);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{rno}",
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)// server가 json으로 응답한 것만 받기
	public ResponseEntity<ReplyVO> get(@PathVariable Long rno){
		ReplyVO vo = service.get(rno);
		
		log.info(vo);
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{rno}",// method = RequestMethod.DELETE
				   produces = MediaType.TEXT_PLAIN_VALUE)// 이 메소드가 리턴할 컨텐트 타입
	public ResponseEntity<String> remove(@PathVariable Long rno){// 본문이 String, text/plain으로 넘어갈 예정
		
		log.info("rno: " + rno);
		/*
		int deleteCnt = service.remove(rno);
		
		if (deleteCnt == 1) {
			return new ResponseEntity<>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
		return service.remove(rno) == 1
			? new ResponseEntity<>("success", HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/{rno}",
					method = {RequestMethod.PUT, RequestMethod.PATCH},
					consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
					produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo,// 요청 몸체가 파라미터가 됨 = HTTP 요청 몸체를 자바 객체로 변환
										 @PathVariable Long rno){
		
		vo.setRno(rno);
		log.info("modified vo: "+ vo);

		return service.modify(vo) == 1
			? new ResponseEntity<>("success", HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
