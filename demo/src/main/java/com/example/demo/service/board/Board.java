package com.example.demo.service.board;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.demo.service.auditor.Auditor;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
public class Board extends Auditor<String> {
	
	
	@Id
	@GeneratedValue
	private String userId;
	
	private String boardTitle;
	private String boardTxt;
	private String boardTag;
	
}