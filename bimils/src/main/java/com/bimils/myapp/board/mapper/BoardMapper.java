package com.bimils.myapp.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bimils.myapp.board.model.Board;

@Mapper
public interface BoardMapper {
	
	public int selectBoardCnt(Map<String, Object> paramMap) throws Exception;

	public List<Board> selectBoardList(Map<String, Object> paramMap) throws Exception;

	public List<Board> selectHotBoardList() throws Exception;
	
	public int selectBoard(int bo_seq_no) throws Exception;

	public int insertBoard(Board board) throws Exception;
	
	public int updateBoardContent(Board board) throws Exception;

	public int updateBoardHitCnt(int bo_seq_no) throws Exception;
		
	public int selectUpdownSave(Map<String, Object> paramMap) throws Exception;
	
	public int updateUpdownSave(Map<String, Object> paramMap) throws Exception;
	
	public int updateBoardUp(int bo_seq_no) throws Exception;
	
	public int updateBoardDown(int bo_seq_no) throws Exception;

	public int deleteBoard(Map<String, Object> paramMap) throws Exception;
	
}
