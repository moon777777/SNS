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
			String loginId
			, String password
			, String name
			, String nickname) {
		
		String endcodingPassword = MD5HashingEncoder.encode(password);
		
		int count = userRepository.insertUser(loginId, endcodingPassword, name, nickname);
		
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isDuplicateLoginId(String loginId) {
		int count = userRepository.selectCountLoginId(loginId);
		
		return count >= 1;
	}
	
	public User getUser(String loginId
			, String password) {
		
		String endcodingPassword = MD5HashingEncoder.encode(password);
		
		return userRepository.selectUser(loginId, endcodingPassword);
	
	}
	
	public User getUserById(int id) {
		return userRepository.selectUserById(id);
	}
	
	
	
	

}
