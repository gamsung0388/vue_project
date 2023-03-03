package com.example.demo.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/comment/insert")
	public Map<String, Object> commentInsert(@RequestBody CommentDTO commentDTO){
		
		Map<String, Object> map = new HashMap<>();
		
		//System.out.println("commentDTO: "+commentDTO);
		
		String insertyn = commentService.commentInsert(commentDTO);		
		
		map.put("result", insertyn);
		
		return map;		
	}
	
	@PostMapping("/comment/update")
	public Map<String, Object> commentUpdate(@RequestBody CommentDTO commentDTO){
	
		Map<String, Object> map = new HashMap<>();
		
		System.out.println("commentDTO: "+commentDTO);
		
		String updateyn = commentService.commentUpdate(commentDTO);		
	
		map.put("result", updateyn);
		
		return map;		
	}
	
	@GetMapping("/comment/delete")
	public Map<String, Object> commentDelete(@RequestParam("commentNum") int commentNum){
		
		Map<String, Object> map = new HashMap<>();
		
		String deleteyn = commentService.commentDelete(commentNum);		
	
		map.put("result", deleteyn);
		
		return map;		
	}
	
	@GetMapping("/comment/truedelete")
	public Map<String, Object> commentTrueDelete(@RequestParam("commentNum") int commentNum){
		
		Map<String, Object> map = new HashMap<>();
		
		String deleteyn = commentService.commentTrueDelete(commentNum);		
	
		map.put("result", deleteyn);
		
		return map;		
	}
	
	@GetMapping("/comment/select")
	public Map<String, Object> commentSelect(@RequestParam("boardNum") int boardNum){
		
		System.out.println("boardNum: "+boardNum);
		
		Map<String, Object> map = new HashMap<>();
		
		List<CommentDTO> commentList = commentService.commentSelect(boardNum);		
	
		System.out.println("commentList: "+commentList);
		
		map.put("commentList", commentList);
		
		return map;		
	}
	
}
