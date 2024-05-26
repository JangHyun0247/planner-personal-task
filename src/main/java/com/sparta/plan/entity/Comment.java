package com.sparta.plan.entity;

import com.sparta.plan.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped {

    //아이디(고유번호) - Long
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //댓글 내용 - String
    @Column(nullable = false)
    private String content;

    //사용자 이름 - String
    @Column(nullable = false)
    private String author;

    //일정 아이디 - Long
    @Column(nullable = false)
    private Long planId;

    //작성일자 - Timestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Comment(CommentRequestDto commentRequestDto){
        this.content = commentRequestDto.getComment();
        this.author = commentRequestDto.getAuthor();
        this.planId = commentRequestDto.getPlanId();
    }
}
