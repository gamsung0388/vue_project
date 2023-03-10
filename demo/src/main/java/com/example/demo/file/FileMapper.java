package com.example.demo.file;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
	public int insertFile(Map<String, Object> map);
	public FileDTO getFileInfo(String fileId);
	public int deleteFile(String fileId);
	public void insertBoardFile(Map<String, Object> map);
	public List<BoardFileTotal> selectBoardFile(int bnum);
}
