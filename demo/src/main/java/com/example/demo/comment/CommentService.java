package com.example.demo.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.board.BoardDTO;
import com.example.demo.paging.Pagination;
import com.example.demo.paging.SearchDTO;

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
	
	public String commentTrueDelete(List<String> checkList) {
			
		for(int i=0;i<checkList.size();i++) {
			
			String sdata =checkList.get(i);
			
			sdata = sdata.replace("[","");
			sdata = sdata.replace("]","");
			
			int checkdata = Integer.parseInt(sdata); 
			
			System.out.println(checkdata);
			
			commentMapper.commentTrueDelete(checkdata);
			
		}
		
		return "Y";
		
	}
	
	public List<CommentDTO> commentSelectOne(int boardNum) {
		
		List<CommentDTO> list = commentMapper.commentSelectOne(boardNum);		
		
		return list;
	}
	
	public Map<String, Object> commentSelect(SearchDTO searchDTO){
		
		Map<String, Object> map = new HashMap<>();
		
		int cnt = commentMapper.commentCnt();
		
		Pagination pagination = new Pagination(cnt, searchDTO);
		
		searchDTO.setPagination(pagination);
		
		List<CommentDTO> list = commentMapper.commentSelect(searchDTO);				
		
		map.put("pagination", pagination);
		map.put("commentList", list);
		
		return map;
	}
	
}
