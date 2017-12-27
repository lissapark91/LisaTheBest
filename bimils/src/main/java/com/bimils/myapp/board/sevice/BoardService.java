package com.bimils.myapp.board.sevice;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bimils.myapp.board.model.Board;

public interface BoardService {
	
	public int getBoardCnt(Map<String, Object> paramMap) throws Exception;

	public List<Board> getBoardList(Map<String, Object> paramMap) throws Exception;

	public List<Board> getHotBoardList() throws Exception;
	
	public int getBoard(int bo_seq_no) throws Exception;

	public int insertBoard(Board board) throws Exception;
	
	public int updateBoardContent(Board board) throws Exception;

	public int updateBoardHitCnt(int bo_seq_no) throws Exception;
		
	public int getUpdownSave(Map<String, Object> paramMap) throws Exception;
	
	public int updateUpdownSave(Map<String, Object> paramMap) throws Exception;
	
	public int updateBoardUp(int bo_seq_no) throws Exception;
	
	public int updateBoardDown(int bo_seq_no) throws Exception;

	public int deleteBoard(Map<String, Object> paramMap) throws Exception;
	

}
