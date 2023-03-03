package com.example.demo.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	public String commentInsert(CommentDTO commentDTO) {
	
		System.out.println(commentDTO);
		
		commentMapper.commentInsert(commentDTO);
		
		return "Y";
	}
	
	public String commentUpdate(CommentDTO commentDTO) {
		
		commentMapper.commentUpdate(commentDTO);
				
		return "Y";
	}
	
	public String commentDelete(int commentNum) {
		
		commentMapper.commentDelete(commentNum);
		
		return "Y";
	}
	
	public String commentTrueDelete(int commentNum) {
			
		commentMapper.commentTrueDelete(commentNum);
		
		return "Y";
	}
	
	public List<CommentDTO> commentSelect(int boardNum) {
		
		List<CommentDTO> list = commentMapper.commentSelect(boardNum);		
		
		return list;
	}
}
