package com.example.demo.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
	public void commentInsert(CommentDTO commentDTO);
	public void commentUpdate(CommentDTO commentDTO);
	public void commentDelete(int commentNum);
	public void boardCommentDel(int boardNum);
	public List<CommentDTO> commentSelect(int boardNum);
}
