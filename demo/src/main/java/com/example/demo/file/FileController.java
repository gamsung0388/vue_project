package com.example.demo.file;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
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
		System.out.println("request:" + request);
		return fileService.uploadFile(request, multipartFile);
		
	}
	
	@GetMapping("/file-download/{fileId}")
	public ResponseEntity<Resource> downloadFile(HttpServletResponse res, @PathVariable String fileId) throws Exception{
		
		return fileService.filedown(res,fileId);
		
				
	}
}
