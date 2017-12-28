package kr.or.nextit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.nextit.board.model.Board;
import kr.or.nextit.board.service.BoardService;
import kr.or.nextit.common.file.model.FileItem;
import kr.or.nextit.common.file.service.FileItemService;
import kr.or.nextit.common.util.PagingUtil;
import kr.or.nextit.member.model.Member;
  
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	@Autowired
	FileItemService fileItemService;
	
	@RequestMapping("/boardList")
	public String boardList(@RequestParam(value="searchType", required=false, defaultValue="") String searchType, 
			@RequestParam(value="searchWord", required=false, defaultValue="") String searchWord,
			@RequestParam(value="currentPage", defaultValue="1") int currentPage,
			@RequestParam(value="pageSize", defaultValue="10") int pageSize,
			Model model) throws Exception {

		//페이지 기능 추가...페이징 처리
		
		int pageCount = 5; //기본값
		int totalCnt = 0;
		
		//검색기능
		Map<String, Object> paramMap = new HashMap<>();
		if(StringUtils.isNotBlank(searchType) && StringUtils.isNotBlank(searchWord)) {
			paramMap.put("searchType", searchType);
			paramMap.put("searchWord", searchWord);
		}
		
		totalCnt = boardService.getBoardCount(paramMap);
		
		PagingUtil pagingUtil = new PagingUtil(currentPage, totalCnt, pageSize, pageCount);
		
		paramMap.put("startRow", pagingUtil.getStartRow());
		paramMap.put("endRow", pagingUtil.getEndRow());
		
		List<Board> boardList = boardService.getBoardList(paramMap);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pagingUtil", pagingUtil);
		
		return "board/boardList";
	}
	
	@RequestMapping("/boardView/{bo_seq_no}")
	public String boardView(
			//@RequestParam(value="bo_seq_no") int bo_seq_no
			@PathVariable("bo_seq_no") int bo_seq_no
			, Model model) throws Exception {
		
		Board board = null;
		if(bo_seq_no != 0) {
			board = boardService.getBoard(bo_seq_no);
			int updCnt = boardService.updateHitCnt(bo_seq_no);			
		}
			
		model.addAttribute("board", board);
		
		return "board/boardView";
		
		
	}
	
	@RequestMapping("/boardForm")
	public String boardForm(HttpSession session, 
			@RequestParam(value="bo_seq_no", required=false, defaultValue="0") int bo_seq_no,
			Model model) throws Exception {
		
		//로그인 여부 확인
		Member member = (Member) session.getAttribute("LOGIN_USER");
		
		if(member == null) {
			return "redirect:/login/loginForm";
		}
				
		Board board = new Board();
		
		if(bo_seq_no != 0) {			
			board = boardService.getBoard(bo_seq_no);
			
		}else {
			//로그인 사용자 정보로
			board.setBo_writer(member.getMem_id());
			board.setBo_writer_name(member.getMem_name());
		}
		
		model.addAttribute("board", board);
		
		return "board/boardForm";
	}
	
	@RequestMapping(value="/boardInsert", method=RequestMethod.POST)
	public String boardInsert(Board board, HttpServletRequest request, Model model) throws Exception {
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("LOGIN_USER");
	
		board.setBo_writer(member.getMem_id());

		String viewPage ="common/message";
		boolean isError = false;
		String message = "게시글이 정상적으로 등록되었습니다.";
		String locationURL ="/board/boardList.do";
		
		try {
			
			//파일 목록 생성
			List<FileItem> fileItemList = fileItemService.uploadFiles(request, board.getBo_type());
			board.setFileItemList(fileItemList);
			
			System.out.println("#1" + board.getBo_writer());
			int updCnt = boardService.insertBoard(board);
			
			if(updCnt == 0) {
				isError = true;
				message = "게시글 등록에 실패하였습니다.";
			}
		}catch(Exception e) {
			e.printStackTrace();
			isError = true;
			message = "게시글 등록에 실패하였습니다.";
			throw e;
		}
		
		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
		model.addAttribute("locationURL", locationURL);
		
		return viewPage;
	}

	@RequestMapping("/boardUpdate")
	public String boardUpdate(Board board, HttpServletRequest request, Model model) {
	
		//로그인 정보(세션에서 가져옴)
		//로그인 여부 확인
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("LOGIN_USER");
		
		board.setUpd_user(member.getMem_id());
		board.setBo_writer(member.getMem_id());
		
		
		String viewPage ="common/message";
		boolean isError = false;
		String message = "게시글이 정상적으로 수정되었습니다.";
		String locationURL ="/board/boardView/" + board.getBo_seq_no();
		
		try {
			
			List<FileItem> fileItemList =  fileItemService.uploadFiles(request, board.getBo_type());
			board.setFileItemList(fileItemList);
			
			int updCnt = boardService.updateBoard(board);
			
			
			if(updCnt == 0) {
				isError = true;
				message = "게시글 수정을 실패하였습니다.";
			}
		}catch(Exception e) {
			isError = true;
			message = "게시글 수정을 실패하였습니다.";
		}
		
		request.setAttribute("isError", isError);
		request.setAttribute("message", message);
		request.setAttribute("locationURL", locationURL);
		
		
		return viewPage;
	}
	
	@RequestMapping("/boardDelete")
	public String boardDelete(HttpSession session, 
			@RequestParam(value="bo_seq_no", required=false, defaultValue="0") int bo_seq_no,
			Model model) throws Exception {
		
		String viewPage = "common/message";
		boolean isError = false;
		String message = "정상적으로 삭제되었습니다.";
		String locationURL = "/board/boardList.do";
				
		Map<String, Object> paramMap = new HashMap<>();
		
		//로그인 정보
		//로그인 여부 확인
		Member member = (Member) session.getAttribute("LOGIN_USER");
		paramMap.put("upd_user", member.getMem_id());
		paramMap.put("bo_seq_no", bo_seq_no);
		if(bo_seq_no != 0) {
			
			try {
				int updCnt = boardService.deleteBoard(paramMap);		
				
				if(updCnt == 0) {
					isError = true;
					message = "해당 글 삭제를 실패하였습니다.";
				}
				
			}catch(Exception e) {
				isError = true;
				message = "해당 글 삭제를 실패하였습니다.";
				e.printStackTrace();
				
			}
		}
		
		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
		model.addAttribute("locationURL", locationURL);
		
		return viewPage;
		
	}
	
}
