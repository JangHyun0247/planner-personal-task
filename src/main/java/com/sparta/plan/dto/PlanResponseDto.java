package com.sparta.plan.dto;

import com.sparta.plan.entity.Plan;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
//return, 응답, 빈환
public class PlanResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String user;
    private LocalDateTime createdAt;

    public PlanResponseDto(Plan plan) {
        this.id = plan.getId();
        this.title = plan.getTitle();
        this.contents = plan.getContents();
        this.user = plan.getUser();
        this.createdAt = plan.getCreatedAt();
    }
}
