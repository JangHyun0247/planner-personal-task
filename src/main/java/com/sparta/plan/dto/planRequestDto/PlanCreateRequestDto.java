package com.sparta.plan.dto.planRequestDto;

import lombok.Getter;

//Jackson 라이브러리가 Json requestDto 로 파싱하기 위해서는 @Getter 필요
//requestDto 는 클라이언트의 요청 데이터 전달
@Getter
public class PlanCreateRequestDto {
    private String user;
    private String password;
    private String title;
    private String contents;
}
