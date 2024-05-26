package com.sparta.plan.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String contents;
    private String author;
    private Long planId;
}
