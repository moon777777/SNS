package com.bbar.sns.like.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bbar.sns.like.domain.Like;
import com.bbar.sns.like.repository.LikeRepository;

@Service
public class LikeService {
	
	private LikeRepository likeRepository;
	
	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
	
	public boolean addLike(int postId, int userId) {
		
		Like like = Like.builder()
		.postId(postId)
		.userId(userId)
		.build();
		
		try {
			likeRepository.save(like);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public int getLikeCount(int postId) {
		return likeRepository.countByPostId(postId);
	}
	
	public boolean userLike(int userId, int postId) {
		int count = likeRepository.countByUserIdAndPostId(userId, postId);
		
		return count >= 1;
	}
	
	public Like getLike(int userId, int postId) {
		Optional<Like> optionalLike = likeRepository.findByUserIdAndPostId(userId, postId);
		
		return optionalLike.orElse(null);
	}

}
