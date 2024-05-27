package com.sparta.plan.controller;

import com.sparta.plan.dto.CommentRequestDto;
import com.sparta.plan.dto.CommentResponseDto;
import com.sparta.plan.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.createComment(commentRequestDto);
    }

    @PutMapping("/comments")
    public CommentResponseDto updateComment(@RequestBody CommentRequestDto commentRequestDto){
        return commentService.updateComment(commentRequestDto);
    }
    @DeleteMapping("/comments")
    public ResponseEntity<String> deleteComment(@RequestBody CommentRequestDto commentRequestDto) {
        commentService.deleteComment(commentRequestDto);
        return new ResponseEntity<>("댓글이 성공적으로 삭제되었습니다.", HttpStatus.OK);
    }
}
