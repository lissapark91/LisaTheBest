package com.bimils.myapp.member.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bimils.myapp.member.mapper.MemberMapper;
import com.bimils.myapp.member.model.Member;
import com.bimils.myapp.member.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper memberMapper;

	@Override
	public int getMemberCnt(Map<String, Object> paramMap) throws Exception {
		
		return memberMapper.selectMemberCnt(paramMap);
	}

	@Override
	public List<Member> getMemberList(Map<String, Object> paramMap) throws Exception {
		List<Member> memberList = memberMapper.selectMemberList(paramMap);
		return memberList;
	}

	@Override
	public Member getMember(Map<String, Object> paramMap) throws Exception {
		return memberMapper.selectMember(paramMap);
	}

	@Override
	public int insertMember(Member member) throws Exception {
		return memberMapper.insertMember(member);
	}

	@Override
	public int updateMemberInfo(Member member) throws Exception {
		return memberMapper.updateMemberInfo(member);
	}

	@Override
	public int updateMemberPoint(Member member) throws Exception {
		return memberMapper.updateMemberPoint(member);
	}

	@Override
	public int deleteMember(String mem_id) throws Exception {
		return memberMapper.deleteMember(mem_id);
	}

}
