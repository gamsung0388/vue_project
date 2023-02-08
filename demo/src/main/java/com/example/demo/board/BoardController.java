package com.example.demo.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	public Map<String, Object> boardInsert(BoardDTO boardDTO){
		
		Map<String, Object> map = new HashMap<>();
		
		String board = boardService.boardInsert(boardDTO);
		
		map.put("YN", board);
				
		return map;
	}
}
