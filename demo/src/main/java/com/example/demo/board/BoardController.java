package com.example.demo.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.file.BoardFileTotal;
import com.example.demo.file.FileService;
import com.example.demo.paging.SearchDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin(origins="http://localhost:3030")
public class BoardController {

	private BoardService boardService;
	private FileService fileService;
	
	//등록
	@PostMapping("/board/insert")
	public Map<String, Object> boardInsert(@RequestBody BoardDTO boardDTO){
		
		Map<String, Object> map = new HashMap<>();
		
		//System.out.println("boardDTO: "+boardDTO);
		
		String board = "N";
		
		board = boardService.boardInsert(boardDTO);
		
		//System.out.println("boardDTO.getFileIdxs(): "+boardDTO.getFileIdxs());
		
		if(!boardDTO.getFileIdxs().isEmpty()) {
			 fileService.insertBoardFile(boardDTO);
		}		
				
		if(board=="Y") {
			System.out.println("등록 성공");
			map.put("YN", "Y");
		}else {
			map.put("YN", "N");
		}
		
		return map;
	}
	//수정
	@PostMapping("/board/update")
	public Map<String, Object> boardUpdate(@RequestBody BoardDTO boardDTO){
		
		Map<String, Object> map = new HashMap<>();
		
		System.out.println("boardDTO: "+boardDTO);
		
		String delFile = boardDTO.getDelete_files().toString();
	
		fileService.deleteBoardFile(delFile);
		
		String board = boardService.boardUpdate(boardDTO);
		
		if(boardDTO.getFileIdxs()!=null) {
			 fileService.insertBoardFile(boardDTO);
		}
		
		if(board=="Y") {
			System.out.println("수정 성공");
			map.put("YN", "Y");
		}else {
			map.put("YN", "N");
		}
				
		return map;
		
	}
	//삭제
	@GetMapping("/board/delete")
	public Map<String,Object> boardDelete(@RequestParam("checkList") List<String> checkList){
		
		Map<String, Object> map = new HashMap<>();
		
		System.out.println("checkList"+checkList);
		
		String board = boardService.boardDelete(checkList);		
		
		map.put("YN", board);
		
		return map;
	}
	//진짜 삭제
	@GetMapping("/board/truedelete")
	public Map<String,Object> boardtrueDelete(@RequestParam("checkList") List<String> checkList){
		
		Map<String, Object> map = new HashMap<>();
		
		System.out.println("checkList"+checkList);
		
		String board = boardService.boardTrueDelete(checkList);		
		
		map.put("YN", board);
		
		return map;
	} 
	
	//목록
	@GetMapping("/board/list")
	public Map<String, Object> boardList(@RequestParam("pageNum") int pageNum, SearchDTO searchDTO){
		
		searchDTO.setPage(pageNum);
		searchDTO.setPageSize(5);
		
		Map<String, Object> map = boardService.boardSelect(searchDTO);
		
		return map;
		
	}
	
	@GetMapping("/board/updateForm")
	public Map<String, Object> updateForm(@RequestParam("boardNum") String boardNum){
		
		Map<String, Object> map = new HashMap<>();
				
		BoardDTO boardDTO = boardService.boardOne(Integer.parseInt(boardNum));
		List<BoardFileTotal> list = fileService.selectBoardFile(Integer.parseInt(boardNum));
		
		map.put("boardDTO", boardDTO);
		map.put("fileList", list);
		
		return map;
		
	}
	//단일
	@GetMapping("/board/one")
	public Map<String, Object> boardList(@RequestParam("boardNum") String boardNum){
		
		Map<String, Object> map = new HashMap<>();
		
		boardService.readcnt(Integer.parseInt(boardNum));
		
		BoardDTO boardDTO = boardService.boardOne(Integer.parseInt(boardNum));
		List<BoardFileTotal> list = fileService.selectBoardFile(Integer.parseInt(boardNum));
		
		map.put("boardDTO", boardDTO);
		map.put("fileList", list);
		
		return map;
		
	}
	
}
