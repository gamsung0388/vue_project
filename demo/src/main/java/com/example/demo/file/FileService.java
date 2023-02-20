package com.example.demo.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.board.BoardDTO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class FileService {
	
	@Autowired
	Environment env;
	
	@Autowired
	FileMapper fileMapper;

	private final String UPLOAD_FILE_PATH = "C:/dev/files";
	
	public Map<String, Object> uploadFile(HttpServletRequest request,List<MultipartFile> multipartFile){
		
		Map<String, Object> result = new HashMap<>();
		
		//파일 시퀀스 리스트
		List<String> fileIds = new ArrayList<String>();
		
		result.put("result", "FAIL");
		
		String _filePath = String.valueOf(request.getParameter("filePath")).equals("null")? UPLOAD_FILE_PATH : UPLOAD_FILE_PATH+String.valueOf(request.getParameter("filePath")+"/");
		//String _filePath = String.valueOf(request.getParam eter("filePath")).equals("null")? env.getProperty(UPLOAD_FILE_PATH): env.getProperty(UPLOAD_FILE_PATH)+String.valueOf(request.getParameter("filePath")+"/");
		
		System.out.println("_filePath= "+_filePath);
		
		System.out.println("_filePath= "+ UPLOAD_FILE_PATH);
		
		try {
			if(multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
				
				for (MultipartFile file : multipartFile) {
					
					String originalFileName = file.getOriginalFilename();		//오리지널 파일명
					String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); 	//파일 확장자
					String saveFileName = UUID.randomUUID() + extension; 		//지정할 파일명
					
					File targetFile = new File(_filePath + saveFileName);
					
					//파일저장후 db insert
					
					result.put("pyscPath", _filePath);
					result.put("pyscNm", saveFileName);
					result.put("origNm", originalFileName);
					result.put("fileExt", extension);
					result.put("contentType", file.getContentType());
					result.put("fileSize", file.getSize());
					
					System.out.println("result: "+result);
					
					//파일 insert
					insertFile(result);
					
					System.out.println("fileId = "+result.get("fileId"));
					
					//배열에 담기
					fileIds.add(String.valueOf(result.get("fileId")));
					
					try {
						InputStream fileStream = file.getInputStream();
						FileUtils.copyInputStreamToFile(fileStream, targetFile); //파일 저장
												
					}catch(Exception e){
						FileUtils.deleteQuietly(targetFile);	//저장된 현재파일 삭제
						e.printStackTrace();
						result.put("result", "FAIL");
					}
				}
				
				result.put("fileIdxs", fileIds.toString());
				result.put("result", "OK");
				
			}else {
				result.put("result", "OK");
			}
		}catch(Exception e) {
			e.printStackTrace();
			result.put("result", "FAIL");
		}
		
		return result;
	}
	
	//파일저장 db
	public int insertFile(Map<String, Object> params) {
		
		return fileMapper.insertFile(params);		
	}
	
	//
	public FileDTO selectFile(String fileId) {
		
		FileDTO fileDTO = fileMapper.getFileInfo(fileId);
				
		return fileDTO;
	}
	
	public void deleteBoardFile(String fileIds) {
		log.info("fileIds: "+fileIds);
		
		String[] fileIdArray = fileIds.split(",");
		
		System.out.println(fileIdArray);
		
		for(int i=0;i<fileIdArray.length;i++) {
			String fileId =fileIdArray[i];
			
			fileId = fileId.replace("[", "");
			fileId = fileId.replace("]", "");
			
			log.info("fileId: "+fileId);
			int cnt =fileMapper.deleteFile(fileId);
			log.info("fileDelteCnt: "+cnt);
		}
	}	
	
	//게시물 파일 등록
	public String insertBoardFile(BoardDTO boardDTO){
				
		System.out.println("boardDTO.getFileIdxs(): " + boardDTO.getFileIdxs());
		
		if(boardDTO.getFileIdxs() != null) {
			//파일 등록
			String fileIdxs = boardDTO.getFileIdxs().replace("[", "").replace("]", "");
			
			System.out.println("fileIdxs: "+fileIdxs);
			
			log.info("fileIdxs:{}",fileIdxs);
			log.info("board_num:{}",boardDTO.getBoardNum());
			String[] fileIdxArray = fileIdxs.split(",");
			
			Map<String, Object> map = new HashMap<>();
			
			for(int i=0; i<fileIdxArray.length; i++) {
				
				map.put("bnum",boardDTO.getBoardNum());
				map.put("fileId",fileIdxArray[i]);
				
				fileMapper.insertBoardFile(map);
			}
			return "Y";
		}else {
			System.out.println("boardDTO.getFileIdxs(): " + boardDTO.getFileIdxs());
			return "N";
		}
	}
	
	//게시판 파일 select
	public List<BoardFileTotal> selectBoardFile(int bnum){
		List<BoardFileTotal> list = fileMapper.selectBoardFile(bnum);		
		System.out.println("list: "+list);
		return list;
	}
	
	public void filedown(HttpServletResponse res,String fileId) throws Exception{
		
		//파일 조회
		FileDTO fileDto = selectFile(fileId);
		
		//파일 경로
		Path saveFilePath = Paths.get(fileDto.getLogiPath() + fileDto.getLogiNm());
		
		System.out.println("saveFilePath: "+ saveFilePath);
		
		//해당경로에 파일이 없으면
		if(!saveFilePath.toFile().exists()) {
			throw new RuntimeException("file not found");
		}
		
		res.setHeader("Content-Disposition", "attachment; filename=\""+ URLEncoder.encode((String)fileDto.getOrigNm(),"UTF-8")+"\";");
		res.setHeader("Content-Transfer-Encoding", "binary");
		res.setHeader("Content-Type", "application/download; utf-8");			
		res.setHeader("Pragma", "no-cahe;");
		res.setHeader("Expires", "-1");
		FileInputStream fis = null;
		
		try {
			fis= new FileInputStream(saveFilePath.toFile());
			FileCopyUtils.copy(fis,res.getOutputStream());
			res.getOutputStream().flush();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally {
				try {
					fis.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
		}		
	
	}	
}