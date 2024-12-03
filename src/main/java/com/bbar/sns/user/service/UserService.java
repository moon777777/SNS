package com.bbar.sns.user.service;

import org.springframework.stereotype.Service;

import com.bbar.sns.common.MD5HashingEncoder;
import com.bbar.sns.user.domain.User;
import com.bbar.sns.user.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean addUser(
			String userId
			, String password
			, String name
			, String nickname) {
		
		String endcodingPassword = MD5HashingEncoder.encode(password);
		
		int count = userRepository.insertUser(userId, endcodingPassword, name, nickname);
		
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isDuplicateUserId(String userId) {
		int count = userRepository.selectCountUserId(userId);
		
		return count >= 1;
	}
	
	public User getUser(String userId
			, String password) {
		
		
		return userRepository.selectUser(userId, password);
		
		
	}
	
	

}
