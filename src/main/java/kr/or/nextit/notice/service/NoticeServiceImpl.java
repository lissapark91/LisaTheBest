package kr.or.nextit.notice.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import kr.or.nextit.common.exception.NosuchDataException;
import kr.or.nextit.common.exception.ServiceException;
import kr.or.nextit.common.jdbc.ConnectionProvider;
import kr.or.nextit.notice.bean.Notice;
import kr.or.nextit.notice.dao.NoticeDao;

public class NoticeServiceImpl implements NoticeService{
	
	private static NoticeServiceImpl instance = new NoticeServiceImpl();
	private NoticeServiceImpl() {}
	public static NoticeServiceImpl getInstance() {
		if(instance == null) {
			instance = new NoticeServiceImpl();
		}
		return instance;
	}
	
	NoticeDao dao = NoticeDao.getInstance();
	
	//noticeList 목록조회
	@Override
	public ArrayList<Notice> getNoticeList(HashMap<String, Object> paramMap) throws Exception {
		ArrayList<Notice> noticeList = null;
		Connection conn = null;
		
		try {
			
			conn = ConnectionProvider.getConnection();
			noticeList = dao.getNoticeList(conn, paramMap);
			
			
		} catch(ServiceException e){
			throw new NosuchDataException("해당 데이터가 존재하지 않습니다.", e);
		} finally {
			conn.close();
		}
		
		return noticeList;
	}

	//Notice 단건조회
	@Override
	public Notice getNotice(int noti_seq_no) throws Exception{
		Notice notice  = null;
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			notice = dao.getNotice(conn, noti_seq_no);
			
		}catch (ServiceException e) {
			throw new NosuchDataException("해당 데이터가 존재하지 않습니다.",e);
		}
		
		return notice;
	}

	@Override
	public int setNotice(Notice notice) throws Exception{
		
		Connection conn = null;
		int updCnt = 0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			updCnt = dao.insertNotice(conn, notice);
			if(updCnt == 0 && conn != null) {
				conn.rollback();
				throw new ServiceException("insert 0건");
			}
			conn.commit();
		}catch (ServiceException e) {
			if(conn != null) {
				conn.rollback();				
			}
			throw new NosuchDataException("insert 실패.",e);
		}finally {
			conn.close();
		}
		
		return updCnt;		
	}

	@Override
	public int updateNotice(Notice notice) throws Exception{
		
		Connection conn = null;
		int updCnt = 0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			updCnt = dao.updateNotice(conn, notice);
			if(updCnt == 0 && conn != null) {
				conn.rollback();
				throw new ServiceException("update 0건");
			}
			conn.commit();
		}catch (ServiceException e) {
			if(conn != null) {
				conn.rollback();				
			}
			throw new NosuchDataException("update 실패.",e);
		}finally {
			conn.close();
		}
		
		return updCnt;	
	}

	@Override
	public int deleteNotice(int noti_seq_no) throws Exception{
		Connection conn = null;
		int updCnt = 0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			updCnt = dao.deleteNotice(conn, noti_seq_no);
			if(updCnt == 0 && conn != null) {
				conn.rollback();
				throw new ServiceException("delete 0건");
			}
			conn.commit();
		}catch (ServiceException e) {
			if(conn != null) {
				conn.rollback();				
			}
			throw new NosuchDataException("delete 실패.",e);
		}finally {
			conn.close();
		}
		
		return updCnt;	
	}
	
}
