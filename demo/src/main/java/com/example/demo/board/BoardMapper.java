package com.example.demo.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.paging.SearchDTO;

@Mapper
public interface BoardMapper {
	public void boardinsert(BoardDTO boardDTO);
	public void boardupdate(BoardDTO boardDTO);
	public void boarddelete(int boardNum);
	public void boardtruedelete(int boardNum);
	public void readCnt(int boardNum);
	
	public int boardCnt();
	
	public BoardDTO boardOne(int boardNum);
	public List<BoardDTO> boardselect(SearchDTO searchDTO);
}
