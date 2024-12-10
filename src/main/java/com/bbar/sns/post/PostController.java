package com.bbar.sns.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbar.sns.post.dto.CardDTO;
import com.bbar.sns.post.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;

	}
	
	@GetMapping("/timeline-view")
	public String timeLine(Model model) {
		List<CardDTO> cardList = postService.getPostList();
		
		model.addAttribute("cardList", cardList);
		
		return "post/timeline";
	}
	
	@GetMapping("/create-view")
	public String input() {
		return "post/input";
	}
}
