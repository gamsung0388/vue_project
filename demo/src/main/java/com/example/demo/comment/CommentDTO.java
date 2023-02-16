package com.example.demo.comment;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentDTO {
	private int commentNum;				//댓글넘버
	private int boardNum;				//게시글넘버
	private String commentTxt; 			//댓글 내용
	private String userId;				//댓글 작성자
	private String delYn;				//삭제 여부
	private LocalDateTime commentReg;	//등록일
	private LocalDateTime commentUdt;	//수정일

}
