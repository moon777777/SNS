package com.bbar.sns.comment.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentRepository {
	
	public int insertComment(@Param("comment") String comment);

}
