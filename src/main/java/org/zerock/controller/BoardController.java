package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;
import org.zerock.service.FileUpService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller // @Component 포함, spring이 관리하는 bean
@AllArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	private BoardService service;
	private FileUpService fileUpSvc;

	/*
	public BoardController(BoardService service) {
		this.service = service;
	}*/
	
	//@RequestMapping(value = "/list", method = RequestMethod.GET)
/*	@GetMapping("/list")
	public void list(Model model) {
		// handler method의 return type이 void면 요청 경로가 곧 view(jsp) 경로!!
		// /board/list.jsp
		log.info("************** list **************");
		List<BoardVO> list = service.getList();
		model.addAttribute("list", list);// disaptcherServlet이 모델 관리, jsp한테 넘겨줌
	}
*/
	@GetMapping("/list")
	public void list(Criteria criteria, Model model) {		
		List<BoardVO> list = service.getList(criteria);// 최대 10개 return : 해당 pagination의 게시물만 가져옴
		
		int total = service.getTotal(criteria);
		
		PageDTO dto = new PageDTO(criteria, total);
		
		model.addAttribute("list", list);// model로 넣은 값을 jsp에서 el로 꺼내쓸 수 있음
		model.addAttribute("pageMaker", dto);
	}
	
	@GetMapping("/register")
	public void register(Criteria criteria) {// /board/register.jsp
		// 왜 criteria 써주는 것만으로 글쓰기-> 목록이 넘어가지?
		// model.addAttribute("criteria", crieteria) 자동
	}
	
	//@RequestMapping(value = "register", method = RequestMethod.POST)
	@PostMapping("/register")
	public String register(BoardVO board, MultipartFile file, RedirectAttributes rttr) {
		// 한 번 사용하고 사라지는 RedirectAttributes
		/*
		 BoardVO board = new BoardVO();
		 board.setTitle(request.getParameter("title"); 
		 board.setContent(request.getParameter("content"); 
		 board.setWriter(request.getParameter("writer"); 
		 -> 스프링이 대신 해준다
		 */
		board.setFilename("");// null 값 못 넣는대 왜지
		service.register(board);

		if(file != null) {
			board.setFilename(board.getBno() + "_" + file.getOriginalFilename());			
			service.modify(board);

			//fileUpSvc.write(file, board.getFilename());
			try {
				fileUpSvc.transfer(file, board.getFilename());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify", "/modify2"})
	@PostMapping("/get")
	public void get(Long bno, Criteria criteria, Model model) {// @RequestParam("bno"), @ModelAttribute("criteria") 생략
		// log.info("*************** get ***********");
		// log.info("**************** bno: " + bno);
		// log.info(criteria);
		BoardVO vo = service.get(bno);
		model.addAttribute("board", vo);
		// model.addAttribute("criteria", criteria); -> annotation으로 처리
		// --> param @ModelAttribute("criteria") 클래스명 따라 자동 model.add 됨
	}
	
	/*
	@GetMapping("/modify")
	public void modify(Long bno, Model model) {
		BoardVO vo = service.get(bno);
		model.addAttribute("board", vo);
	}
	*/
	
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria criteria, RedirectAttributes rttr) {// 파라미터 명시: dispatcherServlet이 파라미터 받아서 set 처리
		if(service.modify(board)) {
			rttr.addFlashAttribute("result" ,"modSuccess");		
		}
		// redirect 시 rttr에 Criteria 넣어주기
		rttr.addAttribute("pageNum", criteria.getPageNum());
		rttr.addAttribute("amount", criteria.getAmount());
		
		return "redirect:/board/list";// model이 아닌 redirectAttribute에 붙여서 넘겨야 안 없어짐!
	}
	
	@PostMapping("/modify2")
	public String modify2(BoardVO board, MultipartFile file, Criteria criteria, RedirectAttributes rttr) {
		if(file != null) {
			board.setFilename(board.getBno() + "_" + file.getOriginalFilename());			
		} else {
			board.setFilename("");
		}
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result" ,"modSuccess");
			rttr.addAttribute("bno", board.getBno());// addAttribute()한 것만 query string으로 넘어감
			//rttr.addAttribute("a", "aa");
			//rttr.addFlashAttribute("b", "bb");// get에서 얘를 못 읽네,,,
			
			// addFlashAttribute를 map에 모아서 한 번에 보낸다면?
			
			rttr.addAttribute("type", criteria.getType());
			rttr.addAttribute("keyword", criteria.getKeyword());
			rttr.addAttribute("pageNum", criteria.getPageNum());
			rttr.addAttribute("amount", criteria.getAmount());
			
			// 이전 이미지 삭제 필요
			//fileUpSvc.write(file, board.getFilename());
			try {
				fileUpSvc.transfer(file, board.getFilename());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/board/get";// model이 아닌 redirectAttribute에 붙여서 넘겨야 안 없어짐! (????)
		// 넘겨줘야 하는 bno도 dispatcherServlet이 처리해주나? -> rttr에 담아야 한다!
	}

//	@GetMapping("/remove")
//	@PostMapping("/remove")
	@RequestMapping(path = "/remove", method = {RequestMethod.GET, RequestMethod.POST})
	public String remove(Long bno, Criteria criteria, RedirectAttributes rttr) {// @RequestParam("bno")
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "delSuccess");
		}
		
		// redirect 시 rttr에 Criteria 넣어주기
		rttr.addAttribute("type", criteria.getType());
		rttr.addAttribute("keyword", criteria.getKeyword());
		rttr.addAttribute("pageNum", criteria.getPageNum());
		rttr.addAttribute("amount", criteria.getAmount());
			
		return "redirect:/board/list";
	}
	
}
