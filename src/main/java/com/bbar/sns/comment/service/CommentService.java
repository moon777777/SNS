package com.bbar.sns.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bbar.sns.comment.domain.Comment;
import com.bbar.sns.comment.dto.CommentDTO;
import com.bbar.sns.comment.repository.CommentRepository;
import com.bbar.sns.user.domain.User;
import com.bbar.sns.user.service.UserService;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	private UserService userService;
	
	public CommentService(CommentRepository commentRepository, UserService userService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
	}
	
	public boolean addComment(int userId, int postId, String contents) {
		
		Comment comment = Comment.builder()
		.userId(userId)
		.postId(postId)
		.contents(contents)
		.build();
		
		// try catch랑 if else 랑 뭐가 더 나은 환경인지 검색
		try {
			 commentRepository.save(comment);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public List<CommentDTO> getCommentList(int postId) {
		
		List<Comment> commentList = commentRepository.findByPostId(postId);
		
		List<CommentDTO> commentDTOList = new ArrayList<>();
		
		for(Comment comment: commentList) {
			
			int userId = comment.getUserId();
			User user = userService.getUserById(userId);
			
			CommentDTO commentDTO = CommentDTO.builder()
			.commentId(comment.getId())
			.userId(userId)
			.nickname(user.getNickname())
			.contents(comment.getContents())
			.build();
			
			commentDTOList.add(commentDTO);
			
		}
		return  commentDTOList;
	
	}
	// 삭제 개수가 모호해서 있을수도 없을수도
	public void deleteCommentByPostId(int postId) {
		commentRepository.deleteByPostId(postId);
	}
}
