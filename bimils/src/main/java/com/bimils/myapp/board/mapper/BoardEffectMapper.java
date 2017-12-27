package com.bimils.myapp.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bimils.myapp.board.model.BoardEffect;

@Mapper
public interface BoardEffectMapper {
	
	public List<BoardEffect> selectBoardEffectList(int bo_seq_no) throws Exception;

	public int insertBoardEffect(BoardEffect boardEffect) throws Exception;

	public int deleteBoardEffect(Map<String, Object> paramMap) throws Exception;
	
}
