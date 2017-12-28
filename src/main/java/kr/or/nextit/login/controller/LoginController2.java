package kr.or.nextit.login.controller;
/*package kr.or.nextit.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import kr.or.nextit.common.util.CookieBox;
import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.MemberService;
import kr.or.nextit.member.service.impl.MemberServiceImpl;
import kr.or.nextit.web.servlet.Controller;

public class LoginController implements Controller{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String mem_id = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");
		String remember_me = request.getParameter("remember_me");

		//세션은 리퀘스트 객체에서 가져온다.
		HttpSession session = request.getSession();
		
		boolean isError = true;
		String message = "로그인 실패";
		
		MemberService memberService = MemberServiceImpl.getInstance();
		Member member = memberService.getMember(mem_id);
		
		if(member != null) {
			if(!mem_pwd.equals(member.getMem_pwd())) {
				message = "비밀번호를 확인하세요.";
			}else {
				//로그인 성
				isError = false;
				message = member.getMem_name()+"님이 로그인 하셨습니다.";
				System.out.println(message);
				session.setAttribute("LOGIN_USER", member);
				
				//아이디저장 (한달동안)
				if("Y".equals(remember_me)) {
					response.addCookie(CookieBox.createCookie("USER_ID", member.getMem_id(), "/", (60 * 60 * 24 * 30)));
					response.addCookie(CookieBox.createCookie("REMEBER_ME", "Y", "/", (60 * 60 * 24 * 30)));
				}else {
					response.addCookie(CookieBox.createCookie("USER_ID", member.getMem_id(), "/", 0));
					response.addCookie(CookieBox.createCookie("REMEBER_ME", "Y", "/", 0));					
				}
			}
		}else {
			message = "해당 사용자를 찾을 수 없습니다.";
		}
		if(isError) {
			request.setAttribute("isError", isError);
			request.setAttribute("message", message);
		}else{
			request.setAttribute("isError", isError);
			request.setAttribute("message", message);
			if(session.getAttribute("previewPage") != null) {
				String previewPage = (String) session.getAttribute("previewPage");
				request.setAttribute("locationURL", previewPage);
				session.removeAttribute("previewPage");
			}else {
				request.setAttribute("locationURL", "/");								
			}
		}
		
		return "common/message";
	}
}
*/