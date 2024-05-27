package com.sparta.plan.service;

import com.sparta.plan.dto.CommentCreateRequestDto;
import com.sparta.plan.dto.CommentDeleteRequestDto;
import com.sparta.plan.dto.CommentResponseDto;
import com.sparta.plan.dto.CommentUpdateRequestDto;
import com.sparta.plan.entity.Comment;
import com.sparta.plan.entity.Plan;
import com.sparta.plan.repository.CommentRepository;
import com.sparta.plan.repository.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final PlanRepository planRepository;

    public CommentService(CommentRepository commentRepository, PlanRepository planRepository) {
        this.commentRepository = commentRepository;
        this.planRepository = planRepository;
    }

    @Transactional
    public CommentResponseDto createComment(CommentCreateRequestDto commentCreateRequestDto) {

        if (commentCreateRequestDto.getPlanId() == null) {
            throw new IllegalArgumentException("댓글을 넣으려는 일정 ID 를 입력해주세요.");
        }

        Plan plan = planRepository.findById(commentCreateRequestDto.getPlanId()).orElseThrow(
                () -> new IllegalArgumentException("DB 에 입력한 ID 의 일정을 찾아볼 수 없습니다.")
        );

        if (commentCreateRequestDto.getContents() == null || commentCreateRequestDto.getContents().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용을 입력해주세요.");
        }

        Comment comment = new Comment(commentCreateRequestDto, plan);

        Comment saveComment = commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);

        return commentResponseDto;
    }

    @Transactional
    public CommentResponseDto updateComment(CommentUpdateRequestDto requestDto) {

        Comment comment = findById(requestDto.getPlanId(), requestDto.getCommentId());


        if (!comment.getUserName().equals(requestDto.getGetUserName())) {
            throw new IllegalArgumentException("현재 사용자가 댓글 작성자가 아닙니다.");
        }

        comment.updateContent(requestDto.getContents());
        return new CommentResponseDto(comment);
    }

    public void deleteComment(CommentDeleteRequestDto requestDto) {

        Comment comment = findById(requestDto.getPlanId(), requestDto.getCommentId());

        if (!comment.getUserName().equals(requestDto.getGetUserName())) {
            throw new IllegalArgumentException("현재 사용자가 댓글 작성자가 아닙니다.");
        }

        commentRepository.delete(comment);
    }

    private Comment findById(Long planId, Long commentId){
        return commentRepository.findByPlanIdAndId(planId, commentId).orElseThrow(() ->
                new IllegalArgumentException("DB 에서 해당 ID 의 댓글을 찾아볼 수가 없습니다.")
        );
    }
}
