package com.sparta.plan.repository;

import com.sparta.plan.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByPlanPlanIdAndCommentId(Long planId, Long commentId);
}
