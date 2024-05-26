package com.sparta.plan.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String comment;
    private String author;
    private Long planId;
}
