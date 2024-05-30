package com.sparta.plan.dto.commentRequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentDeleteRequestDto {

    @NotNull(message = "삭제하려는 댓글의 일정 ID 를 입력해주세요.")
    private Long planId;

    @NotBlank(message = "삭제를 위해 댓글 작성자를 입력해주세요.")
    private String userName;

    @NotNull(message = "삭제하려는 댓글 ID 를 입력해주세요.")
    private Long commentId;
}
