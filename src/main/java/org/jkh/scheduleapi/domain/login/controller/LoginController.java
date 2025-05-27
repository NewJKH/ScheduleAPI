package org.jkh.scheduleapi.domain.login.controller;

import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.common.exception.login.NotMatchedPasswordException;
import org.jkh.scheduleapi.common.exception.member.MemberNotFoundException;
import org.jkh.scheduleapi.domain.login.dto.request.LoginRequest;
import org.jkh.scheduleapi.domain.login.dto.response.LoginResponse;
import org.jkh.scheduleapi.domain.login.jwt.JwtProvider;
import org.jkh.scheduleapi.domain.login.service.LoginService;
import org.jkh.scheduleapi.domain.login.session.LoginSessionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final LoginSessionManager sessionService;
    private final JwtProvider jwtProvider;

    /**
     * 로그인 요청을 처리합니다.
     *
     * 이메일과 비밀번호를 검증하여 세션을 저장하고 로그인 응답 정보를 반환합니다.
     *
     * @param request 로그인 요청 정보 (이메일, 비밀번호)
     * @return 로그인 성공 시 로그인 응답 DTO (예: 사용자 이름 등)
     * @throws MemberNotFoundException 이메일에 해당하는 회원이 존재하지 않을 경우
     * @throws NotMatchedPasswordException 비밀번호가 일치하지 않을 경우
     */
    @PostMapping
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest request) {
        LoginResponse memberResponse = loginService.login(request.email(), request.password());
        jwtProvider.createToken(request.email());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberResponse);
    }

    @GetMapping("/check")
    public ResponseEntity<String> valid(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer ", "");
        try {
            jwtProvider.validToken(token);
            String username = jwtProvider.getUsername(token);
            return ResponseEntity.ok("인증된 사용자: " + username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 유효하지 않음");
        }
    }
}
