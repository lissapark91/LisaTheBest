package kr.or.nextit.login.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.nextit.common.util.CookieBox;
import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.MemberService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping("/login")
	public String login(@RequestParam(value="mem_id") String mem_id, @RequestParam(value="mem_pwd") String mem_pwd,
			HttpSession session, Model model,
			HttpServletResponse response, String remember_me) throws Exception {
		
		Member member = memberService.getMember(mem_id);
		boolean isError = true;
		String message = "";
		if(member != null) {
			if(mem_pwd.equals(member.getMem_pwd())) {
				//로그인 성공
				message = member.getMem_name() + "님, 환영합니다.";
				session.setAttribute("LOGIN_USER", member);
				isError = false;
				
				//아이디저장 (한달동안)
				if("Y".equals(remember_me)) {
					response.addCookie(CookieBox.createCookie("USER_ID", member.getMem_id(), "/", (60 * 60 * 24 * 30)));
					response.addCookie(CookieBox.createCookie("REMEBER_ME", "Y", "/", (60 * 60 * 24 * 30)));
				}else {
					response.addCookie(CookieBox.createCookie("USER_ID", member.getMem_id(), "/", 0));
					response.addCookie(CookieBox.createCookie("REMEBER_ME", "Y", "/", 0));					
				}
				
			}else {
				// 사용자는 있지만 비밀번호 다를 때
				message = "비밀번호가 일치하지 않습니다.";
			}
			
		}else {
			// 해당 사용자 없을 때
			message = "해당 아이디가 존재하지 않습니다.";
			
		}
		
		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
		model.addAttribute("locationURL", "/");
		
		
		return "common/message";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "login/loginForm";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {	
	
		if(session != null) {
			session.invalidate(); // 로그아웃			
		}
		
		return "redirect:/";
		
	}
}
