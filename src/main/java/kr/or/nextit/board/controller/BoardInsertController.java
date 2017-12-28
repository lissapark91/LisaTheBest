/*package kr.or.nextit.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.nextit.board.model.Board;
import kr.or.nextit.board.service.BoardService;
import kr.or.nextit.board.service.impl.BoardServiceImpl;
import kr.or.nextit.common.file.model.FileItem;
import kr.or.nextit.common.file.service.FileItemService;
import kr.or.nextit.common.file.service.impl.FileItemServiceImpl;
import kr.or.nextit.member.model.Member;
import kr.or.nextit.web.servlet.Controller;

public class BoardInsertController implements Controller{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		request.setCharacterEncoding("utf-8");
		
		Board board = new Board();
		BeanUtils.populate(board, request.getParameterMap());
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("LOGIN_USER");
		
		board.setBo_writer(member.getMem_id());

		String viewPage ="common/message";
		boolean isError = false;
		String message = "게시글이 정상적으로 등록되었습니다.";
		String locationURL ="/board/boardList.do";
		
		try {
			
			BoardService boardService = BoardServiceImpl.getInstance();
			
			//파일 목록 생성
			FileItemService fileItemService = FileItemServiceImpl.getInstance();
			List<FileItem> fileItemList = fileItemService.uploadFiles(request, board.getBo_type());
			board.setFileItemList(fileItemList);
			
			int updCnt = boardService.insertBoard(board);
			
			if(updCnt == 0) {
				isError = true;
				message = "게시글 등록에 실패하였습니다.";
			}
		}catch(Exception e) {
			e.printStackTrace();
			isError = true;
			message = "게시글 등록에 실패하였습니다.";
		}
		
		request.setAttribute("isError", isError);
		request.setAttribute("message", message);
		request.setAttribute("locationURL", locationURL);
		
		
		return viewPage;
	}
}
*/