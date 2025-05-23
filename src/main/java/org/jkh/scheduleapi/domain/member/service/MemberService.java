package org.jkh.scheduleapi.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.common.config.PasswordEncoder;
import org.jkh.scheduleapi.common.exception.login.NotMatchedPasswordException;
import org.jkh.scheduleapi.domain.member.dto.MemberResponse;
import org.jkh.scheduleapi.domain.member.entity.Member;
import org.jkh.scheduleapi.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    public MemberResponse signUp(String memberName, String email, String password){
        Member member = new Member(memberName, email, encoder.encode(password));
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
    public MemberResponse updateMemberName(Long id, String memberName){
        Member member = memberRepository.findByIdOrThrow(id);
        member.setMemberName(memberName);
        return MemberResponse.toDto(member);
    }

    public void deleteMember(Long id, String password){
        Member member = memberRepository.findByIdOrThrow(id);
        if (!encoder.matches(password,member.getPassword())){
            throw new NotMatchedPasswordException();
        }
        memberRepository.delete(member);
    }
}
