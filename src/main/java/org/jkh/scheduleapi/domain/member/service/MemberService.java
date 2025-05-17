package org.jkh.scheduleapi.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.domain.member.dto.MemberResponse;
import org.jkh.scheduleapi.domain.member.entity.Member;
import org.jkh.scheduleapi.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse signUp(String member_name, String email){
        Member member = new Member(member_name, email);
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
}
