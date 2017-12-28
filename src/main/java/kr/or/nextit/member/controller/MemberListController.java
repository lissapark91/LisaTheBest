/*package kr.or.nextit.member.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.impl.MemberServiceImpl;



public class MemberListController implements Controller{
	
	//return : string -> viewPage
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		HashMap<String,Object> paramMap = new HashMap<>();
		paramMap.put("searchType", searchType);
		paramMap.put("searchWord", searchWord);
		
		ArrayList<Member> memberList = null;
		
		MemberServiceImpl memService = MemberServiceImpl.getInstance();
		memberList = (ArrayList<Member>)memService.getMemberList(paramMap);
		
//		request.setAttribute("memberList", memberList);
//		request.setAttribute("size", memberList.size());
		
		ModelAndView mav = new ModelAndView("/member/memberList");
		mav.addObject("memberList", memberList);
		mav.addObject("size", memberList.size());
		
		
		return mav;
	}

	

}
*/