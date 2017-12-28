package kr.or.nextit.board.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.nextit.board.mapper.BoardMapper;
import kr.or.nextit.board.model.Board;
import kr.or.nextit.board.service.BoardService;
import kr.or.nextit.common.file.mapper.FileItemMapper;
import kr.or.nextit.common.file.model.FileItem;
import kr.or.nextit.mybatis.MybatisSqlSessionFactory;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	FileItemMapper fileItemMapper;
	
	
	
	@Override
	public int getBoardCount(Map<String, Object> paramMap) throws SQLException {
		
			return boardMapper.selectBoardCount(paramMap);
		
	}
	
	@Override
	public List<Board> getBoardList(Map<String, Object> paramMap) throws Exception {
		
			List<Board> boardList = boardMapper.selectBoardList(paramMap);
			return boardList;
		
	}
 
	@Override
	public Board getBoard(int bo_seq_no) throws Exception {
		
			
			Board board = boardMapper.selectBoard(bo_seq_no);
			
			//파일 리스트 가져오기..
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("ref_seq_no", bo_seq_no);
			paramMap.put("biz_type", board.getBo_type());
			List<FileItem> fileItemList = fileItemMapper.selectFileItemList(paramMap);
			board.setFileItemList(fileItemList); //-> 여기에 넣어주면 됐다.


			return board;			
		
	}

	@Override
	public int insertBoard(Board board) throws Exception {
		
		int updCnt= boardMapper.insertBoard(board); //setBo_seq_no한 상태
		//파일 정보 저장
		List<FileItem> fileItemList = board.getFileItemList();
		if(fileItemList != null && !fileItemList.isEmpty()) {
			for(FileItem fileItem : fileItemList) {
				fileItem.setRef_seq_no(board.getBo_seq_no()+"");//String
				fileItem.setReg_user(board.getBo_writer());
				fileItem.setUpd_user(board.getBo_writer());
				fileItemMapper.insertFileItems(fileItem);
			}
		}
		
		return updCnt;
			
		
	}

	@Override
	public int updateBoard(Board board) throws Exception {
		
		int updCnt = 0;
		String[] delFileSeqNo = board.getDelFileSeqNo();
		if(delFileSeqNo != null && delFileSeqNo.length > 0) {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("delFileSeqNo", delFileSeqNo);
			fileItemMapper.deleteFileItems(paramMap);
		}
			
		//게시판 업데이트
		updCnt = boardMapper.updateBoard(board);
			
		//신규 파일 등록
		List<FileItem> fileItemList = board.getFileItemList();
		if(fileItemList != null && !fileItemList.isEmpty()) {
			for(FileItem fileItem : fileItemList) {
				fileItem.setRef_seq_no(board.getBo_seq_no()+"");
				fileItem.setReg_user(board.getBo_writer());
				fileItem.setUpd_user(board.getBo_writer());
				fileItemMapper.insertFileItems(fileItem);
			
			}
		}
			
			
		return updCnt;
			
	}

	@Override
	public int deleteBoard(Map<String, Object> paramMap) throws Exception {
		int updCnt = boardMapper.deleteBoard(paramMap);
		return updCnt;
	}
	
	@Override
	public int updateHitCnt(int bo_seq_no) throws Exception {
		int updCnt = 0;
		updCnt = boardMapper.updateHitCnt(bo_seq_no);
		return updCnt;
			
	}
	
}
