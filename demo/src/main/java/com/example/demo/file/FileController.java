package com.example.demo.file;

import java.io.FileInputStream;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class FileController {

	FileService fileService;
	
	@PostMapping("/file-upload")
	public Map<String, Object> FileUpload(
			@RequestParam("article_file") List<MultipartFile> multipartFile,
			HttpServletRequest request) {
		System.out.println("multipartFile:" + multipartFile);
		return fileService.uploadFile(request, multipartFile);
		
	}
	
	@GetMapping("/file-download/{fileId}")
	public void downloadFile(HttpServletResponse res, @PathVariable String fileId) throws Exception{
		
		//파일 조회
		FileDTO fileDto = fileService.selectFile(fileId);
		
		//파일 경로
		Path saveFilePath = Paths.get(fileDto.getLogiPath() + fileDto.getLogiNm());
				
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
		} finally {
			try {
				fis.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
}
