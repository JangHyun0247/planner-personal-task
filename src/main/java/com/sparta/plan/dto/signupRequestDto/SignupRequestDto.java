package com.sparta.plan.dto.signupRequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank(message = "username 을 필수로 입력해주세요.")
    @Size(min = 4, max = 10, message = "username 은 최소 4자 이상, 최대 10자 이하 입니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "username 은 알파벳 소문자 a~z, 숫자 0~9 로 구성 되어야 합니다.")
    private String username;

    @NotBlank(message = "password 를 필수로 입력해주세요.")
    @Size(min = 8, max = 15, message = "password 는 최소 8자 이상, 최대 15자 이하 입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "password 는 알파벳 대소문자 a~z, A~Z, 숫자 0~9 로 구성 되어야 합니다.")
    private String password;

    private boolean admin = false;

    private String adminToken = "";
}