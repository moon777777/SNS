package com.bbar.sns.like.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbar.sns.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Integer> {
	
	public int countByPostId(int postId);
	
	public int countByUserIdAndPostId(int userId, int postId);
	
	public Optional<Like> findByUserIdAndPostId(int userId, int postId);
	
}
