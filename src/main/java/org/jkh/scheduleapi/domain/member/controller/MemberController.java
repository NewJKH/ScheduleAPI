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

    @PostMapping("/members/register")
    public ResponseEntity<MemberResponse> create(@Validated @RequestBody MemberSignUpRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.signUp(request.memberName(),request.email(),request.password()));
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

    @PutMapping("/members/update/{id}")
    public ResponseEntity<MemberResponse> update(@Validated @PathVariable long id, @Validated @RequestBody MemberUpdateRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.updateMemberName(id, request.memberName()));
    }

    @DeleteMapping("/members/delete/{id}")
    public ResponseEntity<Void> delete(@Validated @PathVariable long id,@Validated @RequestBody MemberDeleteRequest request){
        memberService.deleteMember(id,request.password());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
