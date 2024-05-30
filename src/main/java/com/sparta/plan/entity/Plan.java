package com.sparta.plan.entity;

import com.sparta.plan.dto.planRequestDto.PlanCreateRequestDto;
import com.sparta.plan.dto.planRequestDto.PlanUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "plan")
@NoArgsConstructor
public class Plan extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
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


    public Plan(PlanCreateRequestDto planCreateRequestDto) {
        this.title = planCreateRequestDto.getTitle();
        this.contents = planCreateRequestDto.getContents();
        this.user = planCreateRequestDto.getUser();
        this.password = planCreateRequestDto.getPassword();
    }


    public void update(PlanUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.user = requestDto.getUser();
    }
}
