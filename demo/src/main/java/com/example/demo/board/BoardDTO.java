package com.example.demo.board;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class BoardDTO {
	private int boardNum;				//게시글 번호
	private String boardTitle;			//게시글 제목
	private String boardTxt;			//게시글 내용
	private String boardTag;			//게시글 태그
	private int boardViewcnt;			//게시글 조회수
	private String userId;				//게시글 작성자
	private String udateUserId;			//게시글 수정자
	private String fileIdxs;			//파일
    private List<Integer> delete_files;		//삭제할 파일리스트
    private String board_file_cnt;		//파일리스트
	private LocalDateTime boardDate; 	//게시글 작성일자 
	private LocalDateTime boardUdt;		//게시글 수정일자
	
}
