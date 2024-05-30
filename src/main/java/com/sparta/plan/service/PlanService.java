package com.sparta.plan.service;

import com.sparta.plan.dto.planRequestDto.PlanCreateRequestDto;
import com.sparta.plan.dto.planRequestDto.PlanDeleteRequestDto;
import com.sparta.plan.dto.planRequestDto.PlanUpdateRequestDto;
import com.sparta.plan.dto.responseDto.PlanResponseDto;
import com.sparta.plan.entity.Plan;
import com.sparta.plan.repository.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Transactional
    public PlanResponseDto createPlan(PlanCreateRequestDto requestDto) {

        Plan plan = new Plan(requestDto);

        Plan savePlan = planRepository.save(plan);

        return new PlanResponseDto(savePlan);
    }

    public PlanResponseDto findPlan(Long id) {
        Plan plan = findById(id);
        return new PlanResponseDto(plan);
    }

    public List<PlanResponseDto> getPlans() {
        return planRepository.findAllByOrderByModifiedAtDesc().stream().map(PlanResponseDto::new).toList();
    }

    @Transactional
    public PlanResponseDto updatePlan(PlanUpdateRequestDto requestDto) {
        Plan plan = findById(requestDto.getId());
        //입력 받은 패스워드와 저장 되어 있는 패스워드가 동일한지 검사
        if (requestDto.getPassword().equals(plan.getPassword())) {
            plan.update(requestDto);
        }
        return new PlanResponseDto(plan);
    }

    public void deletePlan(PlanDeleteRequestDto requestDto) {
        Plan plan = findById(requestDto.getId());
        if(requestDto.getPassword().equals(plan.getPassword())) {
            planRepository.delete(plan);
        }

        planRepository.delete(plan);
    }

    private Plan findById(Long id) {
        return planRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
