package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
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
/*	@GetMapping("/list")
	public void list(Model model) {
		// handler method�� return type�� void�� ��û ��ΰ� �� view(jsp) ���!!
		// /board/list.jsp
		log.info("************** list **************");
		List<BoardVO> list = service.getList();
		model.addAttribute("list", list);// disaptcherServlet�� �� ����, jsp���� �Ѱ���
	}
*/
	@GetMapping("/list")
	public void list(Criteria criteria, Model model) {		
		List<BoardVO> list = service.getList(criteria);
		
		int total = service.getTotal(criteria);
		
		PageDTO dto = new PageDTO(criteria, total);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", dto);
	}
	
	@GetMapping("/register")
	public void register(Criteria criteria) {// /board/register.jsp
		// �� criteria ���ִ� �͸����� �۾���-> ����� �Ѿ��?
		// model.addAttribute("criteria", crieteria) �ڵ�
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
	@PostMapping("/get")
	public void get(Long bno, Criteria criteria, Model model) {// @RequestParam("bno"), @ModelAttribute("criteria") ����
		// log.info("*************** get ***********");
		// log.info("**************** bno: " + bno);
		// log.info(criteria);
		BoardVO vo = service.get(bno);
		model.addAttribute("board", vo);
		// model.addAttribute("criteria", criteria); -> annotation���� ó��
		// --> param @ModelAttribute("criteria") Ŭ������ ���� �ڵ� model.add ��
	}
	
	/*
	@GetMapping("/modify")
	public void modify(Long bno, Model model) {
		BoardVO vo = service.get(bno);
		model.addAttribute("board", vo);
	}
	*/
	
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria criteria, RedirectAttributes rttr) {// �Ķ���� ���: dispatcherServlet�� �Ķ���� �޾Ƽ� set ó��
		if(service.modify(board)) {
			rttr.addFlashAttribute("result" ,"modSuccess");		
		}
		// redirect �� rttr�� Criteria �־��ֱ�
		rttr.addAttribute("pageNum", criteria.getPageNum());
		rttr.addAttribute("amount", criteria.getAmount());
		
		return "redirect:/board/list";// model�� �ƴ� redirectAttribute�� �ٿ��� �Ѱܾ� �� ������! (????)
	}
	
	@PostMapping("/modify2")
	public String modify2(BoardVO board, RedirectAttributes rttr) {
		if(service.modify(board)) {
			rttr.addFlashAttribute("result" ,"success");// �� ���� -> �׷� �� ��𿡼� ���� �ž�? jsp �ƴϾ�??
			rttr.addAttribute("bno", board.getBno());// addAttribute()�� �͸� query string���� �Ѿ
			rttr.addAttribute("a", "aa");
			rttr.addFlashAttribute("b", "bb");// get���� �긦 �� �г�,,,
			
			// addFlashAttribute�� map�� ��Ƽ� �� ���� �����ٸ�?
		}
		
		return "redirect:/board/get";// model�� �ƴ� redirectAttribute�� �ٿ��� �Ѱܾ� �� ������! (????)
		// �Ѱ���� �ϴ� bno�� dispatcherServlet�� ó�����ֳ�? -> rttr�� ��ƾ� �Ѵ�!
	}

	@GetMapping("/remove")
	@PostMapping("/remove")// ���� ���� �͸� test �ǳ�...?
	public String remove(Long bno, Criteria criteria, RedirectAttributes rttr) {// @RequestParam("bno")
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "delSuccess");
		}
		
		// redirect �� rttr�� Criteria �־��ֱ�
		rttr.addAttribute("pageNum", criteria.getPageNum());
		rttr.addAttribute("amount", criteria.getAmount());
			
		return "redirect:/board/list";
	}
	
}
