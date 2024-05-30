package com.sparta.plan.dto.responseDto;

import com.sparta.plan.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private String userName;
    private String contents;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.contents = comment.getContents();
        this.userName = comment.getUserName();
        this.createdAt = comment.getCreatedAt();
    }
}
