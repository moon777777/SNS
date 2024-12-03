package com.bbar.sns.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bbar.sns.user.domain.User;

@Mapper
public interface UserRepository {
	
	public int insertUser(
			@Param("userId") String userId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("nickname") String nickname 
			);
	
	public int selectCountUserId(@Param("userId") String userId);
	
	public User selectUser(
			@Param("userId") String userId
			, @Param("password") String password
			);


}
