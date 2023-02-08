package com.example.demo.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	public String boardInsert(BoardDTO boardDTO) {
		
		boardMapper.insert(boardDTO);
		
		return "Y";
	}
	
}
