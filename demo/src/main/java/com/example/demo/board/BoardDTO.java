package com.example.demo.board;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardDTO {
	private int boardNum;		//게시글 번호
	private String boardTitle;	//게시글 제목
	private String boardTxt;	//게시글 내용
	private String boardTag;	//게시글 태그
	private int boardViewcnt;	//게시글 조회수
	private String userId;		//게시글 작성자
	private String udateUserId;	//게시글 수정자
	private LocalDateTime boardDate; 	//게시글 작성일자 
	private LocalDateTime board_udt;		//게시글 수정일자
	
}
