package org.jkh.scheduleapi.domain.member.controller;

import lombok.RequiredArgsConstructor;
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

    @PostMapping("/members")
    public ResponseEntity<MemberResponse> create(@Validated @RequestBody MemberSignUpRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.signUp(request.member_name(),request.email(),request.password()));
    }

    @GetMapping("/members")
    public ResponseEntity<MemberResponse> find(@Validated @RequestParam Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.findById(id));
    }

    @GetMapping("members/all")
    public ResponseEntity<List<MemberResponse>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.findAll());
    }

    @PutMapping("/members")
    public ResponseEntity<MemberResponse> update(@Validated @RequestBody MemberUpdateRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.updateMemberName(request.id(),request.member_name()));
    }

    @DeleteMapping("/members")
    public ResponseEntity<Void> delete(@Validated @RequestBody MemberDeleteRequest request){
        memberService.deleteMember(request.id());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
