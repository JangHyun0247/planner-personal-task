package com.sparta.plan.controller;

import com.sparta.plan.dto.commentRequestDto.CommentCreateRequestDto;
import com.sparta.plan.dto.commentRequestDto.CommentDeleteRequestDto;
import com.sparta.plan.dto.responseDto.CommentResponseDto;
import com.sparta.plan.dto.commentRequestDto.CommentUpdateRequestDto;
import com.sparta.plan.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public CommentResponseDto createComment(@Valid @RequestBody CommentCreateRequestDto requestDto) {
        return commentService.createComment(requestDto);
    }

    @PutMapping("/comments")
    public CommentResponseDto updateComment(@Valid @RequestBody CommentUpdateRequestDto requestDto){
        return commentService.updateComment(requestDto);
    }
    @DeleteMapping("/comments")
    public ResponseEntity<String> deleteComment(@Valid @RequestBody CommentDeleteRequestDto requestDto) {
        commentService.deleteComment(requestDto);
        return new ResponseEntity<>("댓글이 성공적으로 삭제되었습니다.", HttpStatus.OK);
    }
}
