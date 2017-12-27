package com.bimils.myapp.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bimils.myapp.member.model.Member;
import com.bimils.myapp.member.service.MemberService;


@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	@RequestMapping("/form")
	public String memberForm() throws Exception {
		return "member/memberForm";
	}
	
	@RequestMapping("/exists")
	@ResponseBody
	public Map<String,Object> memberExists(@RequestParam("type") String type
			, @RequestParam("info") String info) throws Exception{
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("searchType", type);
		paramMap.put("searchWord", info);
		
		Member member = memberService.getMember(paramMap);
		
		Map<String, Object> resultMap = new HashMap<>();

		if(member != null) {
			resultMap.put("result", "true");
		}else {
			resultMap.put("result", "false");
		}
		
		return resultMap;
	}
	
	
	
	
}
