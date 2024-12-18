package com.bbar.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
public static final String FILE_UPLOAD_PATH = "C:\\Moon777\\SpringProject\\upload\\sns";
	
	public static String savaFile(int userId, MultipartFile file) {
		
		// 파일 이름 유지
		// 같은 이름의 파일이 전달 될 경우를 대비해서 디렉토리를 마늘어서 파일 저장
		// 디렉토리 이름에 사용자 정보 포함
		// 시간을 디렉토리 이름에 포함
		// UNIX TIME : 1970년 1월 1일 부터 흐른시간을 mili second(1/1000초) 로 표현한 값 - 개발에서 많이 쓰임
		// ex ) 2_89723598273
		
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		// 디렉토리 만들기
		
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(directoryPath);
		 //여러가지 에러상황이 많이 만들어짐
		if(!directory.mkdir()) {
			// 디렉토리 생성 실패
			return null;
		}
		
		// 파일 저장
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath);
			
			Files.write(path, bytes);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			// 파일 저장 실패
			return null;
		}
		
		// 클라이언트가 접근할 url path경로
		// 너무 복잡하니 images/파일이름.png 이걸로 매칭시켜 경로로 설정
		
		return "/images" + directoryName + "/" + file.getOriginalFilename();
	}
	
		public static boolean removeFile(String filePath) { // /image/2_234545309/test.png // static 객체생성없이 쓰려고 붙임
		// /C:\\Moon777\\SpringProject\\upload\\memo/2_234545309/test.png 요렇게 바꾸자
		
		if(filePath == null) {
			return false;
		}
		
		String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
		
		Path path = Paths.get(fullFilePath);
		
		// 디렉토리 경로 /C:\\Moon777\\SpringProject\\upload\\memo/2_234545309
		Path directoryPath = path.getParent();
		
		try {
			Files.delete(path);
			Files.delete(directoryPath);
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
}
