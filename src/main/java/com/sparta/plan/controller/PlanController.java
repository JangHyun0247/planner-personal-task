package com.sparta.plan.controller;

import com.sparta.plan.dto.PlanRequestDto;
import com.sparta.plan.dto.PlanResponseDto;
import com.sparta.plan.entity.Plan;
import com.sparta.plan.service.PlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping("/plans")
    //@RequestBody 요청 데이터(JSON 이든 뭐든)를 PlanRequestDto 객체로 바꿔주는 역할, 내부적으로 Jackson 라이브러리 쓴다
    public PlanResponseDto createPlan(@RequestBody PlanRequestDto requestDto){
        return planService.createPlan(requestDto);
    }

    @GetMapping("/plans/{id}")
    public PlanResponseDto findPlan(@PathVariable Long id){
        return planService.findPlan(id);
    }

    @GetMapping("/plans")
    public List<PlanResponseDto> getPlans(){
        return planService.getPlans();
    }

    @PutMapping("/plans/{id}")
    public Long updatePlan(@PathVariable Long id, @RequestBody PlanRequestDto requestDto){
        return planService.updatePlan(id, requestDto);
    }

    @DeleteMapping("/plans/{id}")
    public Long deletePlan(@PathVariable Long id, @RequestBody PlanRequestDto requestDto){
        return planService.deletePlan(id, requestDto);
    }


}
