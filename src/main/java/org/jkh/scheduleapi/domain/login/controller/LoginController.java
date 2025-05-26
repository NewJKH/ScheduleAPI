package org.jkh.scheduleapi.domain.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.common.exception.login.NotMatchedPasswordException;
import org.jkh.scheduleapi.common.exception.member.MemberNotFoundException;
import org.jkh.scheduleapi.domain.login.dto.request.LoginRequest;
import org.jkh.scheduleapi.domain.login.dto.response.LoginResponse;
import org.jkh.scheduleapi.domain.login.service.LoginService;
import org.jkh.scheduleapi.domain.login.session.LoginSessionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final LoginSessionManager sessionService;

    /**
     * 로그인 요청을 처리합니다.
     *
     * 이메일과 비밀번호를 검증하여 세션을 저장하고 로그인 응답 정보를 반환합니다.
     *
     * @param request 로그인 요청 정보 (이메일, 비밀번호)
     * @param httpRequest 현재 HTTP 요청 객체 (세션 저장에 사용)
     * @return 로그인 성공 시 로그인 응답 DTO (예: 사용자 이름 등)
     * @throws MemberNotFoundException 이메일에 해당하는 회원이 존재하지 않을 경우
     * @throws NotMatchedPasswordException 비밀번호가 일치하지 않을 경우
     */
    @PostMapping
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        LoginResponse memberResponse = loginService.login(request.email(), request.password());
        sessionService.save(httpRequest, memberResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberResponse);
    }
}
