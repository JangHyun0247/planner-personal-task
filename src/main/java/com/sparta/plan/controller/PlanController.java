package com.sparta.plan.controller;

import com.sparta.plan.dto.planRequestDto.PlanCreateRequestDto;
import com.sparta.plan.dto.planRequestDto.PlanDeleteRequestDto;
import com.sparta.plan.dto.planRequestDto.PlanUpdateRequestDto;
import com.sparta.plan.dto.responseDto.PlanResponseDto;
import com.sparta.plan.security.UserDetailsImpl;
import com.sparta.plan.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public PlanResponseDto createPlan(@RequestBody PlanCreateRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return planService.createPlan(requestDto, userDetails.getUser());
    }

    @PutMapping("/plans")
    public PlanResponseDto updatePlan(@RequestBody PlanUpdateRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return planService.updatePlan(requestDto, userDetails.getUser());
    }

    @DeleteMapping("/plans")
    public ResponseEntity<String> deletePlan(@RequestBody PlanDeleteRequestDto requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails){
        planService.deletePlan(requestDto, userDetails.getUser());
        return new ResponseEntity<>("일정이 성공적으로 삭제되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/plans/{id}")
    public PlanResponseDto findPlan(@PathVariable Long id){
        return planService.findPlan(id);
    }

    @GetMapping("/plans")
    public List<PlanResponseDto> getPlans(){
        return planService.getPlans();
    }


}
