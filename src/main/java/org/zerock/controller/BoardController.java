package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller // @Component 포함, spring이 관리하는 bean
@AllArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	private BoardService service;

	/*
	public BoardController(BoardService service) {
		this.service = service;
	}*/
	
	//@RequestMapping(value = "/list", method = RequestMethod.GET)
	@GetMapping("/list")
	public void list(Model model) {
		// handler method의 return type이 void면 요청 경로가 곧 view(jsp) 경로!!
		// /board/list.jsp
		log.info("************** list **************");
		//List<BoardVO> list = service.getList();
		//model.addAttribute("list", list);// disaptcherServlet이 모델 관리, jsp한테 넘겨줌
	}
	
	@GetMapping("/register")
	public void register() {// /board/register.jsp
		
	}
	
	//@RequestMapping(value = "register", method = RequestMethod.POST)
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		// 한 번 사용하고 사라지는 RedirectAttributes
		/*
		 BoardVO board = new BoardVO();
		 board.setTitle(request.getParameter("title"); 
		 board.setContent(request.getParameter("content"); 
		 board.setWriter(request.getParameter("writer"); 
		 -> 스프링이 대신 해준다
		 */
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/get")
	public void get(Long bno, Model model) {// @RequestParam("bno") 생략
		log.info("*************** get ***********");
		log.info("**************** bno: " + bno);
		BoardVO vo = service.get(bno);
		model.addAttribute("board", vo);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {// 파라미터 명시: dispatcherServlet이 파라미터 받아서 set 처리
		if(service.modify(board)) {
			rttr.addFlashAttribute("result" ,"success");		
		}
		return "redirect:/board/list";// model이 아닌 redirectAttribute에 붙여서 넘겨야 안 없어짐! (????)
	}
	
	@PostMapping("/modify2")
	public String modify2(BoardVO board, RedirectAttributes rttr) {
		if(service.modify(board)) {
			rttr.addFlashAttribute("result" ,"success");// 얘 말고 -> 그럼 얜 어디에서 쓰는 거야? jsp 아니야??
			rttr.addAttribute("bno", board.getBno());// addAttribute()한 것만 query string으로 넘어감
			rttr.addAttribute("a", "aa");
			rttr.addFlashAttribute("b", "bb");// get에서 얘를 못 읽네,,,
			
			// addFlashAttribute를 map에 모아서 한 번에 보낸다면?
		}
		return "redirect:/board/get";// model이 아닌 redirectAttribute에 붙여서 넘겨야 안 없어짐! (????)
		// 넘겨줘야 하는 bno도 dispatcherServlet이 처리해주나? -> rttr에 담아야 한다!
	}

	@GetMapping("/remove")
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {// @RequestParam("bno") 占쏙옙占쏙옙 = 占쏙옙占쏙옙 占싱몌옙
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}
	
}
