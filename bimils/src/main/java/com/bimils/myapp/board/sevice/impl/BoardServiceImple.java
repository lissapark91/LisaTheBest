package com.bimils.myapp.board.sevice.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bimils.myapp.board.mapper.BoardMapper;
import com.bimils.myapp.board.model.Board;
import com.bimils.myapp.board.sevice.BoardService;

public class BoardServiceImple implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	

	@Override
	public int getBoardCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Board> getBoardList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Board> getHotBoardList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBoard(int bo_seq_no) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertBoard(Board board) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoardContent(Board board) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoardHitCnt(int bo_seq_no) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getUpdownSave(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUpdownSave(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoardUp(int bo_seq_no) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoardDown(int bo_seq_no) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
