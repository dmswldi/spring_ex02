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

@Controller // @Component ����, spring�� �����ϴ� bean
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
		// handler method�� return type�� void�� ��û ��ΰ� �� view(jsp) ���!!
		// /board/list.jsp
		log.info("************** list **************");
		List<BoardVO> list = service.getList();
		model.addAttribute("list", list);// disaptcherServlet�� �� ����, jsp���� �Ѱ���
	}
	
	@GetMapping("/register")
	public void register() {// /board/register.jsp
		
	}
	
	//@RequestMapping(value = "register", method = RequestMethod.POST)
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		// �� �� ����ϰ� ������� RedirectAttributes
		/*
		 BoardVO board = new BoardVO();
		 board.setTitle(request.getParameter("title"); 
		 board.setContent(request.getParameter("content"); 
		 board.setWriter(request.getParameter("writer"); 
		 -> �������� ��� ���ش�
		 */
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(Long bno, Model model) {// @RequestParam("bno") ����
		// log.info("*************** get ***********");
		// log.info("**************** bno: " + bno);
		BoardVO vo = service.get(bno);
		model.addAttribute("board", vo);
	}
	
	/* 하는 일 get()과 중복
	@GetMapping("/modify")
	public void modify(Long bno, Model model) {// return 값 다르게 overload
		BoardVO vo = service.get(bno);
		model.addAttribute("board", vo);
	}*/

	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {// �Ķ���� ���: dispatcherServlet�� �Ķ���� �޾Ƽ� set ó��
		if(service.modify(board)) {
			rttr.addFlashAttribute("result" ,"success");
		}
		return "redirect:/board/list";// model�� �ƴ� redirectAttribute�� �ٿ��� �Ѱܾ� �� ������! (????)
	}
	
	@PostMapping("/modify2")
	public String modify2(BoardVO board, RedirectAttributes rttr) {
		if(service.modify(board)) {
			rttr.addFlashAttribute("result" ,"success");// �� ���� -> �׷� �� ��𿡼� ���� �ž�? jsp �ƴϾ�??
			rttr.addAttribute("bno", board.getBno());// addAttribute()�� �͸� query string���� �Ѿ
			//rttr.addAttribute("a", "aa");
			//rttr.addFlashAttribute("b", "bb");// get���� �긦 �� �г�,,,
			
			// addFlashAttribute�� map�� ��Ƽ� �� ���� �����ٸ�?
		}
		return "redirect:/board/get";// model�� �ƴ� redirectAttribute�� �ٿ��� �Ѱܾ� �� ������! (????)
		// �Ѱ���� �ϴ� bno�� dispatcherServlet�� ó�����ֳ�? -> rttr�� ��ƾ� �Ѵ�!
	}

	@GetMapping("/remove")
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {// @RequestParam("bno") ���� = ���� �̸�
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}
	
}
