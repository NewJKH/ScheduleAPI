package org.jkh.scheduleapi.domain.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.domain.login.dto.request.LoginRequest;
import org.jkh.scheduleapi.domain.login.session.SessionService;
import org.jkh.scheduleapi.domain.member.dto.MemberResponse;
import org.jkh.scheduleapi.domain.member.service.MemberService;
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
    private final MemberService memberService;
    private final SessionService sessionService;


    @PostMapping
    public ResponseEntity<MemberResponse> login(@Validated @RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        MemberResponse memberResponse = memberService.login(request.email(), request.password());
        sessionService.save(httpRequest, memberResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberResponse);
    }
}
