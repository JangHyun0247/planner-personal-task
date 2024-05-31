package com.sparta.plan.entity;

import com.sparta.plan.dto.commentRequestDto.CommentCreateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped {

    //아이디(고유번호) - Long
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    //댓글 내용 - String
    @Column(nullable = false)
    private String contents;


    //일정 아이디
    @ManyToOne //자식이 부모를 볼 때
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    public Comment(CommentCreateRequestDto commentCreateRequestDto, Plan plan, User user){
        this.contents = commentCreateRequestDto.getContents();
        this.user = user;
        this.plan = plan;
    }

    public void updateContent(String contents) {
        this.contents = contents;
    }
}
