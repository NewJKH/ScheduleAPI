package org.jkh.scheduleapi.domain.login.dto.response;

public record LoginResponse(String message) {
    public static LoginResponse toDto(String memberName) {
        return new LoginResponse(memberName+" 님 로그인을 환영합니다!");
    }
}
