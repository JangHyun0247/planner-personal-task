package com.sparta.plan.dto.responseDto;

import com.sparta.plan.entity.Plan;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
//return, 응답, 빈환
public class PlanResponseDto {
    private Long planid;
    private String title;
    private String contents;
    private LocalDateTime createdAt;

    public PlanResponseDto(Plan plan) {
        this.planid = plan.getPlanId();
        this.title = plan.getTitle();
        this.contents = plan.getContents();
        this.createdAt = plan.getCreatedAt();
    }
}
