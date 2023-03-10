package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.board.BoardDTO;
import com.example.demo.board.BoardService;
import com.example.demo.paging.SearchDTO;

@SpringBootTest
public class BoardTest {

	@Autowired
	private BoardService boardService;
	
	@Test
	public void boardInsert() {
		
		BoardDTO boardData = new BoardDTO();
		
		boardData.setBoardTitle("테스트 제목");
		boardData.setBoardTxt("테스트 내용");
		boardData.setBoardTag("테스트 태그");
		boardData.setUserId("test1234");
		
		boardService.boardInsert(boardData);
			
	}
	
	@Test
	public void boardUpdate() {
		
		BoardDTO boardData = new BoardDTO();
		
		boardData.setBoardTitle("테스트 제목");
		boardData.setBoardTxt("테스트 내용");
		boardData.setBoardTag("테스트 태그");
		
		
		boardService.boardUpdate(boardData);
	}
	
	@Test
	public void boardDelete() {
		
		int bnum = 1;
		
		//boardService.boardDelete(bnum);
		
	}
	@Test
	public void list(SearchDTO searchDTO){
		
		//List<BoardDTO> list = boardService.boardSelect(searchDTO);
			
		//System.out.println("list: "+list);
	
	}
	
	
}
