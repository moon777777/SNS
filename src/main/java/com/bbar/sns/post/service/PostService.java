package com.bbar.sns.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bbar.sns.comment.domain.Comment;
import com.bbar.sns.comment.service.CommentService;
import com.bbar.sns.common.FileManager;
import com.bbar.sns.like.service.LikeService;
import com.bbar.sns.post.domain.Post;
import com.bbar.sns.post.dto.CardDTO;
import com.bbar.sns.post.repository.PostRepository;
import com.bbar.sns.user.domain.User;
import com.bbar.sns.user.service.UserService;

@Service
public class PostService {
	
	private PostRepository postRepository;
	private UserService userService;
	private LikeService likeService;
	private CommentService commentService;
	
	
	public PostService(PostRepository postRepository, UserService userService, LikeService likeService, CommentService commentService) {
		this.postRepository = postRepository;
		this.userService = userService;
		this.likeService = likeService;
		this.commentService = commentService;
	}
	
	public boolean addPost(int userId, String contents, MultipartFile file) {   

        try {
        	String imagePath = FileManager.savaFile(userId, file);
        	int count = postRepository.insertPost(userId, contents, imagePath);
			return count == 1;
        } catch(Exception e) {
			return false;
		}
    }
	
	public List<CardDTO> getPostList(int loginUserId) {
		
		// 조회된 게시글마다 작성자의 로그인 ID 얻어오기
		
		List<Post> postList =  postRepository.selectPost();
		
		List<CardDTO> cardList = new ArrayList<>();
		for(Post post:postList) {
			
			int userId = post.getUserId(); //DTO
			int likeCount = likeService.getLikeCount(post.getId());
			boolean isLike = likeService.isLike(loginUserId, post.getId());
			User user = userService.getUserById(userId);
			
			List<Comment> commentList = commentService.getCommentList(post.getId());
			
			CardDTO card = CardDTO.builder()
			.postId(post.getId())
			.userId(userId)
			.likeCount(likeCount)
			.isLike(isLike)
			.contents(post.getContents())
			.imagePath(post.getImagePath())
			.nickname(user.getNickname())
			.commentList(commentList)
			.build();
			
			cardList.add(card);
			
		}

		return cardList;
	}
	
}


