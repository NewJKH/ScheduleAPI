package org.jkh.scheduleapi.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.domain.member.dto.MemberResponse;
import org.jkh.scheduleapi.domain.member.entity.Member;
import org.jkh.scheduleapi.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse signUp(String member_name, String email){
        Member member = new Member(member_name, email);
        memberRepository.save(member);
        return MemberResponse.toDto(member);
    }
}
