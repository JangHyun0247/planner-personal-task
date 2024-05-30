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
    private Long id;

    //댓글 내용 - String
    @Column(nullable = false)
    private String contents;

    //사용자 이름 - String
    @Column(nullable = false)
    private String userName;

    //일정 아이디
    @ManyToOne //자식이 부모를 볼 때
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    public Comment(CommentCreateRequestDto commentCreateRequestDto, Plan plan){
        this.contents = commentCreateRequestDto.getContents();
        this.userName = commentCreateRequestDto.getUserName();
        this.plan = plan;
    }

    public void updateContent(String contents) {
        this.contents = contents;
    }
}
