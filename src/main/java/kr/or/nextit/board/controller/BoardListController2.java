/*package kr.or.nextit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.nextit.board.model.Board;
import kr.or.nextit.board.service.BoardService;
import kr.or.nextit.board.service.impl.BoardServiceImpl;
import kr.or.nextit.web.servlet.Controller;

public class BoardListController2 implements Controller{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();

//		request.setCharacterEncoding("utf-8");
		
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		System.out.println(searchType);
		System.out.println(searchWord);
		if(searchType != null && searchWord != null) {
			paramMap.put("searchType", searchType);
			paramMap.put("searchWord", searchWord);
		}
		
		BoardService boardService = BoardServiceImpl.getInstance();
		
		
		List<Board> boardList = boardService.getBoardList(paramMap);
		
		request.setAttribute("boardList", boardList);
		
		String viewPage = "/board/boardList";
		
		return viewPage;
	}
}
*/