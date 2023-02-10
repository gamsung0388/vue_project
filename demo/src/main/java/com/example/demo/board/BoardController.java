package com.example.demo.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin(origins="http://localhost:3030")
public class BoardController {

	private BoardService boardService;
	
	@PostMapping("/board/insert")
	public Map<String, Object> boardInsert(@RequestBody BoardDTO boardDTO){
		
		Map<String, Object> map = new HashMap<>();
		
		System.out.println("boardDTO: "+boardDTO);
		
		String board = boardService.boardInsert(boardDTO);
		
		System.out.println("등록 성공");
		
		map.put("YN", board);
				
		return map;
	}
	
	@GetMapping("/board/list")
	public List<BoardDTO> boardList(){
		
		List<BoardDTO> list = boardService.boardSelect();
		
		System.out.println("selectList: "+list);
		
		return list;
		
	}
	
	
}
