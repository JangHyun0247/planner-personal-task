package com.sparta.plan.dto.commentRequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentCreateRequestDto {

    @NotNull(message = "댓글을 달고싶은 일정의 ID 를 입력해주세요.")
    private Long planId;

    @NotBlank(message = "댓글 내용을 입력해 주세요.")
    private String contents;
}
