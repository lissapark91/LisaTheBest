package kr.or.nextit.notice.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.or.nextit.notice.bean.Notice;

public interface NoticeService {
	
	//전체, 검색 목록 조회
	public ArrayList<Notice> getNoticeList(HashMap<String, Object> paramMap) throws Exception;
	
	//단건 조회
	public Notice getNotice(int noti_seq_no) throws Exception;
	
	//글 작성(insert)
	public int setNotice(Notice notice) throws Exception;
	
	//글 수정(update)
	public int updateNotice(Notice notice) throws Exception;
	
	//글 삭제
	public int deleteNotice(int noti_seq_no) throws Exception;  

	
}
