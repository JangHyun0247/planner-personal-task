package com.sparta.plan.dto;

import com.sparta.plan.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private String comment;
    private String author;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.comment = comment.getContent();
        this.author = comment.getAuthor();
        this.createdAt = comment.getCreatedAt();
    }
}
