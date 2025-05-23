package org.jkh.scheduleapi.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.common.exception.login.NotMatchedPasswordException;
import org.jkh.scheduleapi.common.exception.member.MemberNotFoundException;
import org.jkh.scheduleapi.domain.member.dto.MemberDeleteRequest;
import org.jkh.scheduleapi.domain.member.dto.MemberResponse;
import org.jkh.scheduleapi.domain.member.dto.MemberSignUpRequest;
import org.jkh.scheduleapi.domain.member.dto.MemberUpdateRequest;
import org.jkh.scheduleapi.domain.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원 가입 요청을 처리합니다.
     *
     * @param request 회원가입 요청 정보 (이름, 이메일, 비밀번호)
     * @return 생성된 회원의 응답 DTO
     */
    @PostMapping("/member/register")
    public ResponseEntity<MemberResponse> create(@Validated @RequestBody MemberSignUpRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.signUp(request.memberName(), request.email(), request.password()));
    }

    /**
     * 회원 ID를 통해 단일 회원 정보를 조회합니다.
     *
     * @param id 회원 ID
     * @return 해당 회원의 응답 DTO
     * @throws MemberNotFoundException 존재하지 않는 회원일 경우
     */
    @GetMapping("/member")
    public ResponseEntity<MemberResponse> find(@Validated @RequestParam Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.findById(id));
    }

    /**
     * 모든 회원 정보를 조회합니다.
     *
     * @return 전체 회원의 응답 DTO 리스트
     */
    @GetMapping("/member/all")
    public ResponseEntity<List<MemberResponse>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.findAll());
    }

    /**
     * 회원 이름을 수정합니다.
     *
     * @param id 회원 ID
     * @param request 회원 이름 수정 요청 정보
     * @return 수정된 회원의 응답 DTO
     * @throws MemberNotFoundException 존재하지 않는 회원일 경우
     */
    @PutMapping("/member/update/{id}")
    public ResponseEntity<MemberResponse> update(@PathVariable long id, @Validated @RequestBody MemberUpdateRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.updateMemberName(id, request.memberName()));
    }

    /**
     * 비밀번호 검증 후 회원을 삭제합니다.
     *
     * @param id 회원 ID
     * @param request 회원 삭제 요청 정보 (비밀번호)
     * @return HTTP 200 OK 응답 (본문 없음)
     * @throws MemberNotFoundException 존재하지 않는 회원일 경우
     * @throws NotMatchedPasswordException 비밀번호가 일치하지 않을 경우
     */
    @DeleteMapping("/member/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id, @Validated @RequestBody MemberDeleteRequest request) {
        memberService.deleteMember(id, request.password());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
