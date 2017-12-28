/*package kr.or.nextit.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.impl.MemberServiceImpl;
import kr.or.nextit.web.servlet.Controller;

public class MemberInsertController implements Controller{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		//redirect 상황 url 앞에 redirect:를 붙여준다.
		//String viewPage = "redirect:/memberDBCP/memberList.do"; 
		String viewPage = "/common/message";
		
		Member member = new Member();
		//jsp:bean이랑 같다
		BeanUtils.populate(member, request.getParameterMap());
		boolean isError = false;
		String message = "정상적으로 회원 가입 되었습니다.";
		if (member.getMem_id() == null && member.getMem_id().isEmpty() ) {
			isError = true;
			message = "아이디를 입력하세요.";
		}
		if(StringUtils.isBlank(member.getMem_name())) { //한번에 확인 가능
			isError = true;
			message="이름을 입력하세요.";
		}
		
		
	try {
		int updCnt = 0;
		if(!isError) {
			MemberServiceImpl memService = MemberServiceImpl.getInstance();
			updCnt = memService.insertMember(member);			
		}
		if(updCnt == 0){
			isError = true;
			message = "회원 가입이 정상처리되지 않았습니다.";
		}
		
	}catch (Exception e) {
		isError= true;
	}
		
		request.setAttribute("isError", isError);
		request.setAttribute("message", message);
		request.setAttribute("locationURL", "/member/memberList.do");
		//response.sendRedirect(request.getContextPath()+viewPage);
		return viewPage;
	}
}
*/