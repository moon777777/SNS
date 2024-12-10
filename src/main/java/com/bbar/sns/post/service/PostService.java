package com.bbar.sns.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bbar.sns.common.FileManager;
import com.bbar.sns.post.domain.Post;
import com.bbar.sns.post.repository.PostRepository;

@Service
public class PostService {
	
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public boolean addPost(int userId, String contents, MultipartFile file) {   

        try {
        	String imagePath = FileManager.savaFile(userId, file);
        	int count = postRepository.insertPost(userId, contents, imagePath);
			return count == 1;
        } catch(Exception e) {
			return false;
		}
    }
	
	public List<Post> getPostList() {
		
		List<Post> postList = postRepository.selectPost();
		return postList;
	}
	
//	public Post getPost(int id) {
//		
//		Optional<Post> optionalPost = postRepository.selectPost(id);
//		
//		return optionalPost.orElse(null);
//	}
}


