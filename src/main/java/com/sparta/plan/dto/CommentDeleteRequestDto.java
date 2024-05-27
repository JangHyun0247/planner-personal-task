package com.sparta.plan.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentDeleteRequestDto {

    @NotBlank(message = "삭제를 위해 댓글 작성자를 입력해주세요.")
    private String getUserName;

    @NotBlank(message = "삭제하려는 댓글의 일정 ID 를 입력해주세요.")
    private Long planId;

    @NotBlank(message = "삭제하려는 댓글 ID 를 입력해주세요.")
    private Long commentId;
}
