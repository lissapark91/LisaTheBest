package kr.or.nextit.member.controller;

import java.lang.Exception;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.MemberService;

@Controller
@RequestMapping("/member") //member 밑으로 들어오는 모든 요청
//@SessionAttributes(value="member")
public class MemberController {
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	//공통코드 .. 검색
	@ModelAttribute("searchTypeMap")
	public Map<String, String> getsearchType(){
		//맵에 순서를 입히고 싶으면 링크드해쉬맵~~
		Map<String, String> searchTypeMap = new LinkedHashMap<>();
		searchTypeMap.put("id", "아이디");
		searchTypeMap.put("name", "이름");
		return searchTypeMap;
	}
	
	
	//설정 정보를 어노테이션으로 할 수 있다.
	@RequestMapping("/memberList") //member로 들어온 요청중에 memberList
	public String memberList(
			@RequestParam(value="searchType", required=false, defaultValue="") String search, 
			String searchWord, @RequestParam(value="currentPage", defaultValue="1") int currentPage,
			@RequestParam(value="pageSize", defaultValue="10") int pageSize
			, Model model //ModelAndView의 Model, 화면에 뿌려주는 데이타를 옮김
			) throws Exception {

		
//		String searchType = request.getParameter("searchType");
//		String searchWord = request.getParameter("searchWord");
		
		Map<String,Object> paramMap = new HashMap<>();
		
		System.out.println("currentPage : " + currentPage);
		System.out.println("pageSize : " + pageSize);
		
		if(StringUtils.isNotBlank(searchWord)) {
			
			paramMap.put("searchType", search);
			paramMap.put("searchWord", searchWord);
			
		}
		//회원 목록 조회

		List<Member> memberList = memberService.getMemberList(paramMap);
	
		model.addAttribute("memberList", memberList);
		
		//view의 이름 리턴(return type이 String일 경우에 view name으로 인지)
		return "member/memberList";
	}
	
	//단건조회
	@RequestMapping("/memberView")
	public String memberView(@RequestParam("mem_id") String mem_id,
			Model model ) throws Exception{
		
		Member member = memberService.getMember(mem_id);
		
		model.addAttribute("member", member);
		
		
		return "member/memberView";
	}
	
	//가입양식
	@RequestMapping("/memberForm")
	public String memberForm(String mem_id, @RequestParam(value="isInsert", defaultValue="true") boolean isInsert
			, Model model, HttpSession session) throws Exception {
		
		//isInsert로 보내는 방식도 있고
		//뒤에 GET방식 파라미터로 보내도 된다.
		Member member = null;
		if(StringUtils.isNotBlank(mem_id)) { //requestParam을 쓰면 이부분을 체크 안해도 된다.
			isInsert = false;
			member = memberService.getMember(mem_id);
			model.addAttribute("member", member);			
		}
//		Member member = (Member)session.getAttribute("member");
//		model.addAttribute("member", member);
		model.addAttribute("isInsert",isInsert);
		
		return "member/memberForm";
	}

	//회원추가 
	
	@RequestMapping(value="/memberInsert", method=RequestMethod.POST)
	public String memberInsert(Member member, Model model, SessionStatus sessionStatus) { //member : command객체
		//@ModelAttribute("member") 자동으로 담아감
		//BeanUtils.populate(member, request.getParameterMap()); 자동으로 맵핑 
		boolean isError = false;
		boolean isInsert = true;
		model.addAttribute("member", member);
		String message = "정상적으로 회원 가입 되었습니다.";
		if (member.getMem_id() == null && member.getMem_id().isEmpty() ) {
			isError = true;
			message = "아이디를 입력하세요.";
		}
		if(StringUtils.isBlank(member.getMem_name())) { //한번에 확인 가능
			isError = true;
			message="이름을 입력하세요.";
			
			model.addAttribute("isError", isError);
			model.addAttribute("isInsert", isInsert);
			model.addAttribute("message", message);
			
			//그냥 리턴하면 포워드(주소가 안바뀐다.)
//			return "redirect:member/memberForm";
			return "member/memberForm";
		}
		
		
		try {
			int updCnt = 0;
			if(!isError) {
				updCnt = memberService.insertMember(member);			
			}
			if(updCnt == 0){
				isError = true;
				message = "회원 가입이 정상처리되지 않았습니다.";
			}
			//정상 완료시 세션 애트리부트를 제거
//			sessionStatus.setComplete();
			
		}catch (Exception e) {
			isError= true;
		}
		
		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
		model.addAttribute("locationURL", "/member/memberList.do");
	
		return "common/message";
	}
	
	
	//수정
	@RequestMapping("/memberUpdate")
	public String memberUpdate(@ModelAttribute("member") Member member, Model model) {
		
		boolean isError = false;
		boolean isInsert = false;
		String message = "정상적으로 수정 되었습니다.";
		if (member.getMem_id() == null && member.getMem_id().isEmpty() ) {
			isError = true;
			message = "아이디를 입력하세요.";
		}
		if(StringUtils.isBlank(member.getMem_name())) { //한번에 확인 가능
			isError = true;
			message="이름을 입력하세요.";
			model.addAttribute("isError", isError);
			model.addAttribute("isInsert", isInsert);
			model.addAttribute("message", message);

			return "member/memberForm";
		}
		
		
		try {
			int updCnt = 0;
			if(!isError) {
				updCnt = memberService.updateMember(member);			
			}
			if(updCnt == 0){
				isError = true;
				message = "회원 수정이 정상처리되지 않았습니다.";
			}
			
		}catch (Exception e) {
			isError= true;
		}
		
		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
		model.addAttribute("locationURL", "/member/memberView.do?mem_id="+member.getMem_id());
		
		
		return "common/message";
	}
	
	@RequestMapping("/memberDelete")
	public String memberDelete(@RequestParam String mem_id, Model model) throws Exception {
		
		boolean isError = false;
		String message = "정상 삭제처리 되었습니다.";

		try {
			int updCnt = memberService.deleteMember(mem_id);
			
			if(updCnt == 0) {
				isError = true;
				message = "회원 삭제에 실패하였습니다.";
			}
			
		}catch (Exception e) {
			isError = true;
			message = "회원 삭제에 실패하였습니다.";
		}
		
		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
		model.addAttribute("locationURL", "/member/memberList.do");
		
		return "common/message";
	}
	
	//ajax return이 뷰 이름이 아니라 리스폰스 바디로(컨텐트)로 들어갈 데이터임을 알려주어야 한다. (@ResponseBody)
	@RequestMapping("/memberExists")
	@ResponseBody //중요!
	public Map<String,Object> memberExists(@RequestParam("mem_id") String mem_id) throws Exception{
		
		Member member = memberService.getMember(mem_id);
		
		Map<String, Object> resultMap = new HashMap<>();
		
//		resultMap.put("member", member);
//		resultMap.put("msg", "성공");
//		
//	
//		return resultMap;
		if(member != null) {
			resultMap.put("result", "true");
		}else {
			resultMap.put("result", "false");
		}
		
		return resultMap;
	}
	//ajax로 string이 아닌 다른 클래스들을 던질 수 있다.(map, member 포함) -> 추가 라이브러리가 더 필요하다!
	
	
}
