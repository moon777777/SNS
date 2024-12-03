package com.bbar.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbar.sns.user.domain.User;
import com.bbar.sns.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	private UserService userService;
	
	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("userId") String userId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("nickname") String nickname 
			) {
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(userService.addUser(userId, password, name, nickname)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@GetMapping("/duplicate-id")
	@ResponseBody
	public Map<String, Boolean> isDuplicateUserId(@RequestParam("userId") String userId) {
		
		boolean isDuplicate = userService.isDuplicateUserId(userId);
		
		Map<String, Boolean> resultMap = new HashMap<>();

		if(isDuplicate) {
			resultMap.put("isDuplicate", true);
		} else {
			resultMap.put("isDuplicate", false);
		}
				
		return resultMap;
	}
	
	
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam("userId") String userId
			, @RequestParam("password") String password) {
		
		User user = userService.getUser(userId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(user != null) {
			resultMap.put("result", "success"); 
		} else {
			resultMap.put("result", "fail");			
		}
		
		return resultMap;
	}

}
