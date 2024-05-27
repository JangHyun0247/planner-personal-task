package com.sparta.plan.service;

import com.sparta.plan.dto.CommentRequestDto;
import com.sparta.plan.dto.CommentResponseDto;
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
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {

        if (commentRequestDto.getPlanId() == null) {
            throw new IllegalArgumentException("댓글을 넣으려는 일정 ID 를 입력해주세요.");
        }

        Plan plan = planRepository.findById(commentRequestDto.getPlanId()).orElseThrow(
                () -> new IllegalArgumentException("DB 에 입력한 ID 의 일정을 찾아볼 수 없습니다.")
        );

        if (commentRequestDto.getContents() == null || commentRequestDto.getContents().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용을 입력해주세요.");
        }

        Comment comment = new Comment(commentRequestDto, plan);

        Comment saveComment = commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);

        return commentResponseDto;
    }

    @Transactional
    public CommentResponseDto updateComment(CommentRequestDto commentRequestDto) {

        if (commentRequestDto.getCommentId() == null) {
            throw new IllegalArgumentException("댓글 ID 를 입력해주세요.");
        }

        if (commentRequestDto.getPlanId() == null) {
            throw new IllegalArgumentException("일정 ID 를 입력해주세요.");
        }

        Plan findPlan = planRepository.findById(commentRequestDto.getPlanId()).orElseThrow(
                () -> new IllegalArgumentException("DB 에서 일정을 찾아볼 수 없습니다.")
        );

        Comment comment = commentRepository.findByPlanIdAndId (findPlan.getId(),commentRequestDto.getCommentId()).orElseThrow(
                () -> new IllegalArgumentException("DB 에서 댓글을 찾을 수 없습니다.")
        );

        if (!comment.getAuthor().equals(commentRequestDto.getAuthor())) {
            throw new IllegalArgumentException("현재 사용자가 댓글 작성자가 아닙니다.");
        }

        if (commentRequestDto.getContents() == null || commentRequestDto.getContents().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용을 입력해주세요.");
        }

        comment.updateContent(commentRequestDto.getContents());
        return new CommentResponseDto(comment);
    }

    public void deleteComment(CommentRequestDto commentRequestDto) {

        if (commentRequestDto.getCommentId() == null) {
            throw new IllegalArgumentException("댓글 ID 를 입력해주세요.");
        }

        if (commentRequestDto.getPlanId() == null) {
            throw new IllegalArgumentException("일정 ID 를 입력해주세요.");
        }

        Plan findPlan = planRepository.findById(commentRequestDto.getPlanId()).orElseThrow(
                () -> new IllegalArgumentException("DB 에 입력한 ID 의 일정을 찾아볼 수 없습니다.")
        );

        Comment comment = commentRepository.findByPlanIdAndId (findPlan.getId(),commentRequestDto.getCommentId()).orElseThrow(
                () -> new IllegalArgumentException("DB 에서 입력한 ID 의 댓글을 찾을 수 없습니다.")
        );

        if (!comment.getAuthor().equals(commentRequestDto.getAuthor())) {
            throw new IllegalArgumentException("현재 사용자가 댓글 작성자가 아닙니다.");
        }
        commentRepository.delete(comment);
    }
}
