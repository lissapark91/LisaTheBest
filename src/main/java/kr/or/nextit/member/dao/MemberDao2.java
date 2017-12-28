package kr.or.nextit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.or.nextit.member.model.Member;

public class MemberDao2 {
	
	private static MemberDao2 instance = new MemberDao2();
	
	private MemberDao2() {
		
	}
	
	public static MemberDao2 getInstance() {
		if(instance == null) {
			instance = new MemberDao2();
		}
		return instance;
	}
	
	
	
	//회원목록 조회	
	public List<Member> selectMemberList(Connection conn, HashMap<String, Object> paramMap) throws Exception{
		
		//회원 담을 리스트
		ArrayList<Member> memberList = new ArrayList<>();
		
		StringBuffer query = new StringBuffer();
	
		//select 쿼리
		query.append(" SELECT ");
		query.append("    mem_id            ");
		query.append("   ,mem_name          ");
		query.append("   ,mem_pwd           ");
		query.append("   ,mem_birth         ");
		query.append("   ,mem_phone         ");
		query.append("   ,mem_email         ");
		query.append("   ,mem_zipcode       ");
		query.append("   ,mem_addr_master   ");
		query.append("   ,mem_addr_detail   ");
		query.append("   ,reg_user          ");
		query.append("   ,reg_date          ");
		query.append("   ,upd_user          ");
		query.append("   ,upd_date          ");
		query.append(" FROM TB_MEMBER       ");
		query.append(" WHERE 1=1");
		
		//where문 조건 추가 HashMap id - 검색값, name - 검색값
		if(paramMap.size() != 0 && !paramMap.isEmpty()){
			if(paramMap.containsKey("id")){
				query.append(" AND mem_id = ?");
			}else if(paramMap.containsKey("name")){
				query.append(" AND mem_name like '%' || ? || '%' ");			
			}		
		}
		query.append(" ORDER BY REG_DATE DESC    ");
		
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		
		int i =1;
		if(paramMap.size() != 0 && !paramMap.isEmpty()){
			if(paramMap.containsKey("id")){
				pstmt.setString(i++, (String)paramMap.get("id"));
				
			}else if(paramMap.containsKey("name")){
				pstmt.setString(i++, (String)paramMap.get("name"));
				
			}		
		}		
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()){
			
			Member member = new Member();
			member.setMem_id(rs.getString("mem_id"));
			member.setMem_name(rs.getString("mem_name"));
			member.setMem_pwd(rs.getString("mem_pwd"));
			member.setMem_birth(rs.getString("mem_birth"));
			member.setMem_phone(rs.getString("mem_phone"));
			member.setMem_email(rs.getString("mem_email"));
			member.setMem_zipcode(rs.getString("mem_zipcode"));
			member.setMem_addr_master(rs.getString("mem_addr_master"));
			member.setMem_addr_detail(rs.getString("mem_addr_detail"));
			member.setReg_date(rs.getString("reg_date"));
			
			memberList.add(member);
		}
	
		
		return memberList;
	}
	
	//회원 정보 조회
	public Member selectMember(Connection conn, String mem_id) throws Exception{
		
		StringBuffer query = new StringBuffer();
		
		query.append(" SELECT ");
		query.append("    mem_id            ");
		query.append("   ,mem_name          ");
		query.append("   ,mem_pwd           ");
		query.append("   ,mem_birth         ");
		query.append("   ,mem_phone         ");
		query.append("   ,mem_email         ");
		query.append("   ,mem_zipcode       ");
		query.append("   ,mem_addr_master   ");
		query.append("   ,mem_addr_detail   ");
		query.append("   ,reg_user          ");
		query.append("   ,reg_date          ");
		query.append("   ,upd_user          ");
		query.append("   ,upd_date          ");
		query.append(" FROM TB_MEMBER       ");
		query.append(" WHERE MEM_ID = ?     ");
	
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		pstmt.setString(1, mem_id);
		
		ResultSet rs = pstmt.executeQuery();
		Member member = null;
		if(rs.next()){
			member = new Member();
			member.setMem_id(rs.getString("mem_id"));
			member.setMem_name(rs.getString("mem_name"));
			member.setMem_pwd(rs.getString("mem_pwd"));
			member.setMem_birth(rs.getString("mem_birth"));
			member.setMem_phone(rs.getString("mem_phone"));
			member.setMem_email(rs.getString("mem_email"));
			member.setMem_zipcode(rs.getString("mem_zipcode"));
			member.setMem_addr_master(rs.getString("mem_addr_master"));
			member.setMem_addr_detail(rs.getString("mem_addr_detail"));
			member.setReg_date(rs.getString("reg_date"));
			
		}
		
		
		return member;
	}
	
	
	//회원 등록
	public int insertMember(Connection conn, Member member) throws Exception{
		
		StringBuffer query = new StringBuffer();
		
		query.append("	INSERT INTO TB_MEMBER (   ");
		query.append("		    MEM_ID,           ");
		query.append("		    MEM_NAME,         ");
		query.append("		    MEM_PWD,          ");
		query.append("		    MEM_BIRTH,        ");
		query.append("		    MEM_PHONE,        ");
		query.append("		    MEM_EMAIL,        ");
		query.append("		    MEM_ZIPCODE,      ");
		query.append("		    MEM_ADDR_MASTER,  ");
		query.append("		    MEM_ADDR_DETAIL,  ");
		query.append("		    REG_USER,         ");
		query.append("		    REG_DATE,         ");
		query.append("		    UPD_USER,         ");
		query.append("		    UPD_DATE          ");
		query.append("		) VALUES (            ");
		query.append("		    ?,              ");
		query.append("		    ?,              ");
		query.append("		    ?,              ");
		query.append("		    ?,              ");
		query.append("		    ?,              ");
		query.append("		    ?,              ");
		query.append("		    ?,              ");
		query.append("		    ?,              ");
		query.append("		    ?,              ");
		query.append("		    ?,              ");
		query.append("		    SYSDATE,             ");
		query.append("		    ?,             ");
		query.append("		    SYSDATE              ");
		query.append("		)                    ");
	
		
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		
		int i = 1;
		
		pstmt.setString(i++, member.getMem_id());
		pstmt.setString(i++, member.getMem_name());
		pstmt.setString(i++, member.getMem_pwd());
		pstmt.setString(i++, member.getMem_birth());
		pstmt.setString(i++, member.getMem_phone());
		
		pstmt.setString(i++, member.getMem_email());
		pstmt.setString(i++, member.getMem_zipcode());
		pstmt.setString(i++, member.getMem_addr_master());
		pstmt.setString(i++, member.getMem_addr_detail());
		pstmt.setString(i++, member.getMem_id());
		
		pstmt.setString(i++, member.getMem_id());
	
		int updCnt = pstmt.executeUpdate();
		
		return updCnt;
	}
	
	
	//회원 수정
	public int updateMember(Connection conn, Member member) throws Exception{
		
		StringBuffer query = new StringBuffer();
        
		query.append("	UPDATE TB_MEMBER                       "                  )      ;
		query.append("    SET                                  "                  )      ;
		query.append("	     MEM_NAME = ?                       "                  )      ;
		query.append("	    ,MEM_PWD = ?                        "                  )      ;
		query.append("	    ,MEM_BIRTH = ?                      "                  )      ;
		query.append("	    ,MEM_PHONE = ?                      "                  )      ;
		query.append("	    ,MEM_EMAIL = ?                      "                  )      ;
		query.append("	    ,MEM_ZIPCODE = ?                    "                  )      ;
		query.append("	    ,MEM_ADDR_MASTER = ?                "                  )      ;
		query.append("	    ,MEM_ADDR_DETAIL = ?                "                  )      ;
		query.append("	    ,UPD_DATE = SYSDATE                 "                  )      ;
		query.append("	WHERE                                  "                  )      ;
		query.append("        MEM_ID = ? ")      ;
		
			
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		
		int i = 1;
		
		pstmt.setString(i++, member.getMem_name());
		pstmt.setString(i++, member.getMem_pwd());
		pstmt.setString(i++, member.getMem_birth());
		pstmt.setString(i++, member.getMem_phone());
		
		pstmt.setString(i++, member.getMem_email());
		pstmt.setString(i++, member.getMem_zipcode());
		pstmt.setString(i++, member.getMem_addr_master());
		pstmt.setString(i++, member.getMem_addr_detail());
		
		pstmt.setString(i++, member.getMem_id());
	
		int updCnt = pstmt.executeUpdate();
		
		
		return updCnt;
	}
	
	
	
	//회원삭제
	public int deleteMember(Connection conn, String mem_id) throws Exception{
		
		StringBuffer query = new StringBuffer();
		query.append(" DELETE FROM TB_MEMBER WHERE ");
		query.append(" MEM_ID = ? ");
		
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		pstmt.setString(1, mem_id);
		
		int udpCnt = pstmt.executeUpdate();
		
		return udpCnt;
	}
	
	
	
}
