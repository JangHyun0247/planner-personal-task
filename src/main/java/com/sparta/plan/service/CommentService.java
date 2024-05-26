package com.sparta.plan.service;

import com.sparta.plan.dto.CommentRequestDto;
import com.sparta.plan.dto.CommentResponseDto;
import com.sparta.plan.entity.Comment;
import com.sparta.plan.entity.Plan;
import com.sparta.plan.repository.CommentRepository;
import com.sparta.plan.repository.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

        Optional<Plan> findPlan = planRepository.findById(commentRequestDto.getPlanId());

        Comment comment = new Comment(commentRequestDto, findPlan.get());

        Comment saveComment = commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);

        return commentResponseDto;
    }
}
