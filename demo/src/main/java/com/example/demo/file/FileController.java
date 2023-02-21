package com.example.demo.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

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
//	public void downloadFile(HttpServletResponse res, @PathVariable String fileId) throws Exception{	
//		//파일 조회
//		FileDTO fileDto = fileService.selectFile(fileId);
//		
//		//파일 경로
//		Path saveFilePath = Paths.get(fileDto.getLogiPath() + fileDto.getLogiNm());
//		
//		System.out.println("saveFilePath: "+ saveFilePath);
//		
//		//해당경로에 파일이 없으면
//		if(!saveFilePath.toFile().exists()) {
//			throw new RuntimeException("file not found");
//		}
//		
//		res.setHeader("Content-Disposition", "attachment; filename=\""+ URLEncoder.encode((String)fileDto.getOrigNm(),"UTF-8")+"\";");
//		res.setHeader("Content-Transfer-Encoding", "binary");
//		res.setHeader("Content-Type", "application/download; utf-8");			
//		res.setHeader("Pragma", "no-cahe;");
//		res.setHeader("Expires", "-1");
//		FileInputStream fis = null;
//		
//		try {
//			fis= new FileInputStream(saveFilePath.toFile());
//			FileCopyUtils.copy(fis,res.getOutputStream());
//			res.getOutputStream().flush();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		} finally {
//			try {
//				fis.close();
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return ResponseEntity.ok().headers(headers).body(resource);
		

		//파일 조회
		FileDTO fileDto = fileService.selectFile(fileId);
		
		//파일 경로
		Path saveFilePath = Paths.get(fileDto.getLogiPath() + fileDto.getLogiNm());

		//System.out.println("saveFilePath:"+ saveFilePath);
		
        UrlResource resource = new UrlResource("file:" + saveFilePath);

        String encodedFileName = UriUtils.encode(fileDto.getOrigNm(), StandardCharsets.UTF_8);

        String contentType = "application/download; utf-8";
        
        // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
        // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
        //String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        HttpHeaders headers = new HttpHeaders();
                
        headers.add("Content-Disposition", "attachment; filename=\""+ URLEncoder.encode(encodedFileName,"UTF-8")+"\";");
        headers.add("Content-Transfer-Encoding", "binary");
        headers.add("Content-Type", contentType);			
        headers.add("Pragma", "no-cahe;");
        headers.add("Expires", "-1");
        headers.add("Access-Control-Expose-Headers", "Content-Disposition");
        
        return ResponseEntity.ok().headers(headers).body(resource);
		
	}
	@GetMapping("/imageview/{fileId}")
	public ResponseEntity<byte[]> userSearch(@PathVariable("fileId") String fileId) throws IOException {
		
		FileDTO fileDTO = fileService.selectFile(fileId);
		
		InputStream imageStream = new FileInputStream(fileDTO.getLogiPath() + fileDTO.getLogiNm());
		
		byte[] imageByteArray = IOUtils.toByteArray(imageStream);
		imageStream.close();	
		
		return new ResponseEntity<byte[]>(imageByteArray,HttpStatus.OK);
	}
}
