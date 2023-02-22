package com.example.demo.file;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FileDTO {
	private int fileId;
	private String origNm;
	private String logiNm;
	private String logiPath;
	private String thumbnailNm;
	private String ext;
	private int size;
	private LocalDateTime regDt;
}
