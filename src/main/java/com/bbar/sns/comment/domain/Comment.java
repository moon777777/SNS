package com.bbar.sns.comment.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Comment {
	
	private int id;
	private int loginId;
	private int postId;
	private String contents;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	

}
