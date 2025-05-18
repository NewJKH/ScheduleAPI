package org.jkh.scheduleapi.domain.member.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.domain.member.dto.MemberResponse;
import org.jkh.scheduleapi.domain.member.entity.Member;
import org.jkh.scheduleapi.domain.member.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse signUp(String member_name, String email, String password){
        Member member = new Member(member_name, email, password);
        memberRepository.save(member);
        return MemberResponse.toDto(member);
    }

    public MemberResponse findById(Long id){
        return MemberResponse.toDto(memberRepository.findByIdOrThrow(id));
    }

    public List<MemberResponse> findAll(){
        return memberRepository.findAll().stream()
                .map(MemberResponse::toDto)
                .toList();
    }

    @Transactional
    public MemberResponse updateMemberName(Long id, String member_name){
        Member member = memberRepository.findByIdOrThrow(id);
        member.setMemberName(member_name);
        return MemberResponse.toDto(member);
    }

    public void deleteMember(Long id){
        Member member = memberRepository.findByIdOrThrow(id);
        memberRepository.delete(member);
    }

    public MemberResponse login(@Email String email, String password) {
        Member member = memberRepository.findByEmailOrThrow(email);
        if ( !member.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED," 비밀번호가 일치하지 않습니다. ");
        }
        return MemberResponse.toDto(member);
    }
}
