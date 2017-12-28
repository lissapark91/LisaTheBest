package kr.or.nextit.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


import kr.or.nextit.notice.bean.Notice;

public class NoticeDao {
	
	//singleton
	private static NoticeDao instance = new NoticeDao();
	
	private NoticeDao() {}
	
	public static NoticeDao getInstance() {
		if(instance==null) {
			instance = new NoticeDao();
		}
		return instance;
	}
	// end singleton
	
	//검색조회 및 전체조회
	public ArrayList<Notice> getNoticeList(Connection conn ,HashMap<String, Object> paramMap) throws SQLException{
		ArrayList<Notice> noticeList = new ArrayList<>();
		
		StringBuffer query = new StringBuffer();
		
		query.append("	SELECT                "    )      ;
		query.append("		noti_seq_no,      "    )      ;
		query.append("		noti_mem_id,      "    )      ;
		query.append("		noti_title,       "    )      ;
		query.append("		noti_content,     "    )      ;
		query.append("		noti_reg_date    "    )      ;
		//query.append("		noti_upd_date     "    )      ;
		query.append("  FROM                  "    )      ;
		query.append("  TB_NOTICE             "    )      ;
		query.append("  WHERE 1=1             "    )      ;
		
		//검색조건부분
		if("id".equals(paramMap.get("searchType"))) {
			query.append("  AND noti_mem_id = ? "   )   ;
		}else if("title".equals(paramMap.get("searchType"))) {
			query.append("  AND noti_title LIKE '%' || ? || '%' "    )   ;
		}else if("content".equals(paramMap.get("searchType"))) {
			query.append("  AND noti_content LIKE '%' || ? || '%' "    )   ;
		}
		
		//정렬
		query.append(" ORDER BY NOTI_SEQ_NO ");
		
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		
		//검색어넣기
		int i = 1;
		if(paramMap.size() != 0 && !paramMap.isEmpty()) {

			if("id".equals(paramMap.get("searchType"))) {
				pstmt.setString(i++, (String)paramMap.get("searchWord"));
			}else if("title".equals(paramMap.get("searchType"))) {
				pstmt.setString(i++, (String)paramMap.get("searchWord"));		
			}else if("content".equals(paramMap.get("searchType"))) {
				pstmt.setString(i++, (String)paramMap.get("searchWord"));
			}
			
		}
		
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Notice notice = new Notice();
			
			notice.setNoti_seq_no(rs.getInt("noti_seq_no"));
			notice.setNoti_mem_id(rs.getString("noti_mem_id"));
			notice.setNoti_title(rs.getString("noti_title"));
			notice.setNoti_content(rs.getString("noti_content"));
			notice.setNoti_reg_date(rs.getString("noti_reg_date"));
			
			noticeList.add(notice);
		}
		
		return noticeList;
	}// getNoticeList end
	
	public Notice getNotice(Connection conn, int noti_seq_no) throws SQLException {
		
		StringBuffer query = new StringBuffer();
		
		query.append("	SELECT                "    )      ;
		query.append("		noti_seq_no,      "    )      ;
		query.append("		noti_mem_id,      "    )      ;
		query.append("		noti_title,       "    )      ;
		query.append("		noti_content,     "    )      ;
		query.append("		noti_reg_date,   "    )      ;
		query.append("		noti_upd_date     "    )      ;
		query.append("  FROM                  "    )      ;
		query.append("  TB_NOTICE             "    )      ;
		query.append("  WHERE noti_seq_no = ? ");
		
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		
		pstmt.setInt(1, noti_seq_no);
		
		ResultSet rs = pstmt.executeQuery();
		Notice notice = null;
		while(rs.next()) {
			
			notice = new Notice();
			
			notice.setNoti_seq_no(rs.getInt("noti_seq_no"));
			notice.setNoti_mem_id(rs.getString("noti_mem_id"));
			notice.setNoti_title(rs.getString("noti_title"));
			
			notice.setNoti_content(rs.getString("noti_content"));
			notice.setNoti_reg_date(rs.getString("noti_reg_date"));
			notice.setNoti_upd_date(rs.getString("noti_upd_date"));
			
		}
		
		
		return notice;
	}
	
	public int insertNotice(Connection conn, Notice notice) throws SQLException {
		
		StringBuffer query = new StringBuffer();
		
		query.append("	INSERT INTO TB_NOTICE (       "  )     ;
		query.append("		    NOTI_SEQ_NO,          "  )     ;
		query.append("		    NOTI_MEM_ID,          "  )     ;
		query.append("		    NOTI_TITLE,           "  )     ;
		query.append("		    NOTI_CONTENT,         "  )     ;
		query.append("		    NOTI_REG_DATE,        "  )     ;
		query.append("		    NOTI_UPD_DATE         "  )     ;
		query.append("		) VALUES (                "  )     ;
		query.append("		    SEQ_NOTI_SEQ_NO.NEXTVAL, "  )     ;
		query.append("		    ?,                  "  )     ;
		query.append("		    ?,                  "  )     ;
		query.append("		    ?,                  "  )     ;
		query.append("		    sysdate,                  "  )     ;
		query.append("		    sysdate                   "  )     ;
		query.append("		)                        "  )     ;
		
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		
		int i = 1;
		pstmt.setString(i++, notice.getNoti_mem_id());
		pstmt.setString(i++, notice.getNoti_title());
		pstmt.setString(i++, notice.getNoti_content());
		
		int updCnt = pstmt.executeUpdate();
		
		return updCnt;
	}
	
	public int updateNotice(Connection conn, Notice notice) throws SQLException {
		
		StringBuffer query = new StringBuffer();
		
		query.append("	UPDATE TB_NOTICE           ");
		query.append("    SET                      ");
		query.append("        noti_title = ?                ");
		query.append("        ,noti_content = ?                ");
		query.append("        ,noti_upd_date = sysdate           ");
		query.append("    WHERE                    ");
		query.append("        NOTI_SEQ_NO = ?     ");
		
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		
		int i = 1;
		pstmt.setString(i++, notice.getNoti_title());
		pstmt.setString(i++, notice.getNoti_content());
		pstmt.setInt(i++, notice.getNoti_seq_no());
		
		int updCnt = pstmt.executeUpdate();
		
		return updCnt;
	}
	
	public int deleteNotice(Connection conn, int noti_seq_no) throws SQLException {
		
		String query = " DELETE FROM TB_NOTICE WHERE " + 
				"        NOTI_SEQ_NO =? ";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		pstmt.setInt(1, noti_seq_no);
		
		int updCnt = pstmt.executeUpdate();
		return updCnt;
	}
	
	
	
	

}
