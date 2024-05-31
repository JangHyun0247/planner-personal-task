package com.sparta.plan.service;

import com.sparta.plan.dto.commentRequestDto.CommentCreateRequestDto;
import com.sparta.plan.dto.commentRequestDto.CommentDeleteRequestDto;
import com.sparta.plan.dto.responseDto.CommentResponseDto;
import com.sparta.plan.dto.commentRequestDto.CommentUpdateRequestDto;
import com.sparta.plan.entity.Comment;
import com.sparta.plan.entity.Plan;
import com.sparta.plan.entity.User;
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
    public CommentResponseDto createComment(CommentCreateRequestDto requestDto, User user) {

        if (requestDto.getPlanId() == null) {
            throw new IllegalArgumentException("댓글을 넣으려는 일정 ID 를 입력해주세요.");
        }

        Plan plan = planRepository.findById(requestDto.getPlanId()).orElseThrow(
                () -> new IllegalArgumentException("DB 에 입력한 ID 의 일정을 찾아볼 수 없습니다.")
        );

        if (requestDto.getContents() == null || requestDto.getContents().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용을 입력해주세요.");
        }

        Comment comment = new Comment(requestDto, plan, user);

        Comment saveComment = commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);

        return commentResponseDto;
    }

    @Transactional
    public CommentResponseDto updateComment(CommentUpdateRequestDto requestDto, User user) {

        Comment comment = findById(requestDto.getPlanId(), requestDto.getCommentId());

        if (!comment.getUser().getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException("현재 사용자가 댓글 작성자가 아닙니다.");
        }

        comment.updateContent(requestDto.getContents());
        return new CommentResponseDto(comment);
    }

    public void deleteComment(CommentDeleteRequestDto requestDto, User user) {

        Comment comment = findById(requestDto.getPlanId(), requestDto.getCommentId());

        if (!comment.getUser().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("현재 사용자가 댓글 작성자가 아닙니다.");
        }

        commentRepository.delete(comment);
    }

    private Comment findById(Long planId, Long commentId){
        return commentRepository.findByPlanPlanIdAndCommentId(planId, commentId).orElseThrow(() ->
                new IllegalArgumentException("DB 에서 해당 ID 의 댓글을 찾아볼 수가 없습니다.")
        );
    }
}
