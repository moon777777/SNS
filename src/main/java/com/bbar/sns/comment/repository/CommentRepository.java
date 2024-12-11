package com.bbar.sns.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbar.sns.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
