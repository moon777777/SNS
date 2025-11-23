package com.bbar.sns.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bbar.sns.post.domain.Post;

@Mapper
public interface PostRepository {
	
	public int insertPost(
			@Param("userId") int userId,
            @Param("contents") String contents,
            @Param("imagePath") String imagePath
			);
	
	List<Post> findAllPosts();
	
	public Post findById(@Param("id") int id);
	 
	public int deletePost(
			@Param("id") int id
			);

}
