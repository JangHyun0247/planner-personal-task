package com.sparta.plan.service;

import com.sparta.plan.dto.PlanRequestDto;
import com.sparta.plan.dto.PlanResponseDto;
import com.sparta.plan.entity.Plan;
import com.sparta.plan.repository.PlanRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public PlanResponseDto createPlan(PlanRequestDto requestDto) {

        Plan plan = new Plan(requestDto);

        Plan savePlan = planRepository.save(plan);

        PlanResponseDto responseDto = new PlanResponseDto(savePlan);

        return responseDto;
    }
}
