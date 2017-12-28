/*package kr.or.nextit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.nextit.board.model.Board;
import kr.or.nextit.board.service.BoardService;
import kr.or.nextit.board.service.impl.BoardServiceImpl;
import kr.or.nextit.common.util.PagingUtil;
import kr.or.nextit.web.servlet.Controller;

public class BoardListController implements Controller{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();

//		request.setCharacterEncoding("utf-8");
		
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		
		
		//페이지 기능 추가...페이징 처리
		int currentPage = 1;
		int pageSize = 10;
		int pageCount = 5; //기본값
		int totalCnt = 0;
		
		if(StringUtils.isNotBlank(request.getParameter("currentPage"))) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		if(StringUtils.isNotBlank(request.getParameter("pageSize"))) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		
//		String page = request.getParameter("page");
//		if(page == null) {
//			page = "1";
//			paramMap.put("page", Integer.parseInt(page));
//		}else {
//			paramMap.put("page", Integer.parseInt(page));			
//		}
		
		
		
		///////
		
		
		//검색기능
		
		if(searchType != null && searchWord != null) {
			paramMap.put("searchType", searchType);
			paramMap.put("searchWord", searchWord);
		}
		
		BoardService boardService = BoardServiceImpl.getInstance();
		
		totalCnt = boardService.getBoardCount(paramMap);
		
		PagingUtil pagingUtil = new PagingUtil(currentPage, totalCnt, pageSize, pageCount);
		
		paramMap.put("startRow", pagingUtil.getStartRow());
		paramMap.put("endRow", pagingUtil.getEndRow());
		
		List<Board> boardList = boardService.getBoardList(paramMap);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("pagingUtil", pagingUtil);
		
		String viewPage = "/board/boardList";
		
		return viewPage;
	}
}
*/