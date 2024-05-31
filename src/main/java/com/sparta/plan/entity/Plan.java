package com.sparta.plan.entity;

import com.sparta.plan.dto.planRequestDto.PlanCreateRequestDto;
import com.sparta.plan.dto.planRequestDto.PlanUpdateRequestDto;
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
    @Column(name = "plan_id")
    private Long planId;

    //할일 제목
    @Column(name = "title", nullable = false)
    private String title;

    //할일 내용
    @Column(name = "contents")
    private String contents;

    @OneToMany(mappedBy = "plan")
    private final List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Plan(PlanCreateRequestDto planCreateRequestDto, User user) {
        this.title = planCreateRequestDto.getTitle();
        this.contents = planCreateRequestDto.getContents();
        this.user = user;
    }


    public void update(PlanUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
