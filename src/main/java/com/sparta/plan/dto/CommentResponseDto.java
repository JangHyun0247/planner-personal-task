package com.sparta.plan.dto;

import com.sparta.plan.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private String contents;
    private String author;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.contents = comment.getContents();
        this.author = comment.getAuthor();
        this.createdAt = comment.getCreatedAt();
    }
}
