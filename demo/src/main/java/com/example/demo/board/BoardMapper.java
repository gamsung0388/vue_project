package com.example.demo.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
	public void boardinsert(BoardDTO boardDTO);
	public void boardupdate(BoardDTO boardDTO);
	public void boarddelete(int boardNum);
	public List<BoardDTO> boardselect();
}
