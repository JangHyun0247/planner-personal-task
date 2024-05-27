package com.sparta.plan.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentCreateRequestDto {

    @NotBlank(message = "댓글 작성자를 입력해 주세요.")
    private String getUserName;

    @NotBlank(message = "댓글 내용을 입력해 주세요.")
    private String contents;

    @NotBlank(message = "댓글을 달고싶은 일정의 ID 를 입력해주세요.")
    private Long planId;
}
