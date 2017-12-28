/*package kr.or.nextit.member.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.nextit.member.service.MemberService;
import kr.or.nextit.member.service.impl.MemberServiceImpl;
import kr.or.nextit.web.servlet.Controller;

public class MemberDeleteController implements Controller{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		String mem_id = request.getParameter("mem_id");
		boolean isError = false;
		String message = "정상 삭제처리 되었습니다.";

		try {
			MemberService memberService = MemberServiceImpl.getInstance();
			int updCnt = memberService.deleteMember(mem_id);
			
			if(updCnt == 0) {
				isError = true;
				message = "회원 삭제에 실패하였습니다.";
			}
			
		}catch (Exception e) {
			isError = true;
			message = "회원 삭제에 실패하였습니다.";
		}
		
		String viewPage = "common/message";
		request.setAttribute("isError", isError);
		request.setAttribute("message", message);
		request.setAttribute("locationURL", "/member/memberList.do");
		
		return viewPage;
	}
}
*/