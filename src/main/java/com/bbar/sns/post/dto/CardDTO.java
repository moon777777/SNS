package com.bbar.sns.post.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardDTO {
	
	private int postId;
	private int userId;
	
	private String contents;
	private String imagePath;
	
	private String nickname;
	
	private int likeCount;
	private boolean userLike;
	
}
