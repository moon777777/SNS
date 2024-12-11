package com.bbar.sns.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbar.sns.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Integer> {
	
}
