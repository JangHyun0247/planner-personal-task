package com.sparta.plan.dto.commentRequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentUpdateRequestDto {

    @NotNull(message = "댓글을 수정하고 싶은 일정의 ID 를 입력해주세요.")
    private Long planId;

    @NotNull(message = "수정하려는 댓글의 ID 를 입력해주세요.")
    private Long commentId;

    @NotBlank(message = "수정할 내용을 입력해주세요.")
    private String contents;
}
