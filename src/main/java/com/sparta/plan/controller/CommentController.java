package com.sparta.plan.controller;

import com.sparta.plan.dto.commentRequestDto.CommentCreateRequestDto;
import com.sparta.plan.dto.commentRequestDto.CommentDeleteRequestDto;
import com.sparta.plan.dto.responseDto.CommentResponseDto;
import com.sparta.plan.dto.commentRequestDto.CommentUpdateRequestDto;
import com.sparta.plan.security.UserDetailsImpl;
import com.sparta.plan.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public CommentResponseDto createComment(@Valid @RequestBody CommentCreateRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(requestDto, userDetails.getUser());
    }

    @PutMapping("/comments")
    public CommentResponseDto updateComment(@Valid @RequestBody CommentUpdateRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(requestDto, userDetails.getUser());
    }
    @DeleteMapping("/comments")
    public ResponseEntity<String> deleteComment(@Valid @RequestBody CommentDeleteRequestDto requestDto,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(requestDto, userDetails.getUser());
        return new ResponseEntity<>("댓글이 성공적으로 삭제되었습니다.", HttpStatus.OK);
    }
}
