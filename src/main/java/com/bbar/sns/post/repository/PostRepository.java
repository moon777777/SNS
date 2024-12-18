package com.bbar.sns.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bbar.sns.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	public List<Post> findAllByOrderByIdDesc();

}
