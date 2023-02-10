package com.example.demo.board;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardService {
	
	private BoardMapper boardMapper;
	
	//게시물 등록
	public String boardInsert(BoardDTO boardDTO) {
		
		System.out.println("boardDTO: "+boardDTO);
		
		boardMapper.boardinsert(boardDTO);
		
		return "Y";
	}
	
	//게시물 수정
	public String boardUpdate(BoardDTO boardDTO) {
		
		boardMapper.boardupdate(boardDTO);
		
		return "Y";
	}
	
	//게시물 삭제
	public String boardDelete(int bnum) {
		
		boardMapper.boarddelete(bnum);
		
		return "Y";
	}	

	//게시물 목록
	public List<BoardDTO> boardSelect() {
		
		List<BoardDTO> list = boardMapper.boardselect();
		
		return list;
	}

}
