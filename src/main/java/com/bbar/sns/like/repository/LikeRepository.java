package com.bbar.sns.like.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbar.sns.like.domain.Like;

import jakarta.transaction.Transactional;

public interface LikeRepository extends JpaRepository<Like, Integer> {
	
	public int countByPostId(int postId);
	
	public int countByUserIdAndPostId(int userId, int postId);
	
	public Optional<Like> findByPostIdAndUserId(int postId, int userId);
	
	// transaction 두개의 쿼리가 하나의 묶음으로 사용될수 있도록
	// SELECT * FROM `like` WHERE `postId` = #{}
	// DELETE FROM `like` WHERE `postId` = #{}
	@Transactional
	public void deleteByPostId(int postId);
	
}
