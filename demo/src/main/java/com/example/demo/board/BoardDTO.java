package com.example.demo.board;

import lombok.Data;

@Data
public class BoardDTO {
	private int boardNum;
	private String boardTitle;
	private String boardTxt;
	private String boardTag;
	private int boardViewcnt;
	private String userId;
	private String udateUserId;
	
}