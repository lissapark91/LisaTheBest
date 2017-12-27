package com.bimils.myapp.member.service;

import java.util.List;
import java.util.Map;


import com.bimils.myapp.member.model.Member;

public interface MemberService {
	
	public int getMemberCnt(Map<String, Object> paramMap) throws Exception;
	
	public List<Member> getMemberList(Map<String, Object> paramMap) throws Exception;
	
	public Member getMember(Map<String, Object> paramMap) throws Exception;
	
	public int insertMember(Member member) throws Exception;
	
	public int updateMemberInfo(Member member) throws Exception;

	public int updateMemberPoint(Member member) throws Exception;
	
	public int deleteMember(String mem_id) throws Exception;

}
