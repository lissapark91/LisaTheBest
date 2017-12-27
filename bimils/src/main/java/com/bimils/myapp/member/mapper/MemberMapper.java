package com.bimils.myapp.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bimils.myapp.member.model.Member;

@Mapper
public interface MemberMapper {
	
	public int selectMemberCnt(Map<String, Object> paramMap) throws Exception;
	
	public List<Member> selectMemberList(Map<String, Object> paramMap) throws Exception;
	
	public Member selectMember(Map<String, Object> paramMap) throws Exception;
	
	public int insertMember(Member member) throws Exception;
	
	public int updateMemberInfo(Member member) throws Exception;

	public int updateMemberPoint(Member member) throws Exception;
	
	public int deleteMember(String mem_id) throws Exception;

}
