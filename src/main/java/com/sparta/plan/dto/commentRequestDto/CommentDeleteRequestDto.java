package com.sparta.plan.dto.commentRequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentDeleteRequestDto {

    @NotNull(message = "삭제하려는 댓글의 일정 ID 를 입력해주세요.")
    private Long planId;

    @NotNull(message = "삭제하려는 댓글 ID 를 입력해주세요.")
    private Long commentId;
}
