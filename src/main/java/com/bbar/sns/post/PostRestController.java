package com.bbar.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bbar.sns.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	private PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	
		@PostMapping("/create")
		public Map<String, String> createTimeline(
				@RequestParam("contents") String contents
				, @RequestParam("imageFile") MultipartFile file
				, HttpSession session
				) {
			
			int userId = (Integer) session.getAttribute("userId");
			
			Map<String, String> resultMap = new HashMap<>();
			
			
			if(postService.addPost(userId, contents, file)) {
				resultMap.put("result", "success");
			} else {
				resultMap.put("result", "fail");
			}
			return resultMap;
		}
		
		
		
		
}
