package kr.or.nextit.member.service;

import java.util.List;
import java.util.Map;
import kr.or.nextit.member.model.Member;

public interface MemberService {
	//메서드의 프로토 타입을 만들 때는 신중하게 하자! 정확하게 분석해서 정확하게~~ 
	
	//회원 목록 조회 
	public List<Member> getMemberList(Map<String, Object> paramMap) throws Exception;
	
	//회원 정보 조회
	public Member getMember(String mem_id) throws Exception;
	
	//회원 등록
	public int insertMember(Member member) throws Exception;
	
	//회원 수정
	public int updateMember(Member member) throws Exception;
	
	//회원 삭제
	public int deleteMember(String mem_id) throws Exception;

	//회원 삭제
	//public int deleteMember(String[] mem_ids) throws Exception;
	
	
}
