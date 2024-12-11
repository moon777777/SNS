package com.bbar.sns.comment.service;

import org.springframework.stereotype.Service;

import com.bbar.sns.comment.domain.Comment;
import com.bbar.sns.comment.repository.CommentRepository;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	public boolean addComment(int postId, String contents) {
		
		Comment comment = Comment.builder()
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
}
