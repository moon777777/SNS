package com.bbar.sns.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bbar.sns.post.domain.Post;

@Mapper
public interface PostRepository {
	
	public int insertPost(
			@Param("userId") int userId
			, @Param("contents") String contents
			, @Param("imagePath") String imagePath
			);
	
//	public int selecttPost(
//			@Param("userId") int userId
//			, @Param("contents") String contents
//			, @Param("imagePath") String imagePath
//			);
	
	public List<Post> selectPost();

	
	
	

}
