package kr.or.nextit.member.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.nextit.common.exception.NosuchDataException;
import kr.or.nextit.common.exception.ServiceException;
import kr.or.nextit.member.mapper.MemberMapper;
import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.MemberService;
import kr.or.nextit.mybatis.MybatisSqlSessionFactory;

@Service("memberService")
public class MemberServiceImpl  implements MemberService {
	private static Logger logger = Logger.getLogger(MemberServiceImpl.class);
	
	@Autowired // bean으로 등록해야 쓸수 있다.!
	MemberMapper memberMapper;
	//@Autowired
//	MemberDao dao;
	
	@Override
	public List<Member> getMemberList(Map<String, Object> paramMap) throws Exception {

		//AOP적용을 했기 때문에 코어 concern만 남는다.
		List<Member> memberList = memberMapper.selectMemberList(paramMap);
	
		return memberList;
	}

	@Override
	public Member getMember(String mem_id) throws Exception {
		
		Member member = memberMapper.selectMember(mem_id);
						
		return member;
	}

	@Override
	public int insertMember(Member member) throws Exception {

		return memberMapper.insertMember(member);
		
	}

	@Override
	public int updateMember(Member member) throws Exception {
	
		return memberMapper.updateMember(member);
	}

	@Override
	public int deleteMember(String mem_id) throws Exception {
		
		return memberMapper.deleteMember(mem_id);
	}

}
