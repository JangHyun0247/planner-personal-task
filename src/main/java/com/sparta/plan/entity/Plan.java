package com.sparta.plan.entity;

import com.sparta.plan.dto.PlanRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "plan")
@NoArgsConstructor
public class Plan extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //할일 제목
    @Column(name = "title", nullable = false)
    private String title;

    //할일 내용
    @Column(name = "contents")
    private String contents;

    //담당자
    @Column(name = "user" , nullable = false)
    private String user;

    //비밀번호
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany
    @JoinColumn(name = "comment_id", nullable = false)
    private List<Comment> commentList = new ArrayList<>();

    public Plan(PlanRequestDto planRequestDto) {
        this.title = planRequestDto.getTitle();
        this.contents = planRequestDto.getContents();
        this.user = planRequestDto.getUser();
        this.password = planRequestDto.getPassword();
    }


    public void update(PlanRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.user = requestDto.getUser();
    }
}
