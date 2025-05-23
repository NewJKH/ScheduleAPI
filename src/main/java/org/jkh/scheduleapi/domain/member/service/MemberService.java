package org.jkh.scheduleapi.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.common.config.PasswordEncoder;
import org.jkh.scheduleapi.common.exception.login.NotMatchedPasswordException;
import org.jkh.scheduleapi.common.exception.member.MemberNotFoundException;
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

    /**
     * 회원 가입을 처리합니다.
     *
     * @param memberName 회원 이름
     * @param email 회원 이메일 (중복 검증은 따로 필요)
     * @param password 평문 비밀번호 (저장 시 암호화)
     * @return 생성된 회원의 응답 DTO
     */
    public MemberResponse signUp(String memberName, String email, String password) {
        Member member = new Member(memberName, email, encoder.encode(password));
        memberRepository.save(member);
        return MemberResponse.toDto(member);
    }

    /**
     * 회원 ID를 통해 회원 정보를 조회합니다.
     *
     * @param id 회원 ID
     * @return 해당 회원의 응답 DTO
     * @throws MemberNotFoundException 존재하지 않는 회원일 경우
     */
    public MemberResponse findById(Long id) {
        return MemberResponse.toDto(memberRepository.findByIdOrThrow(id));
    }

    /**
     * 전체 회원 목록을 조회합니다.
     *
     * @return 모든 회원의 응답 DTO 리스트
     */
    public List<MemberResponse> findAll() {
        return memberRepository.findAll().stream()
                .map(MemberResponse::toDto)
                .toList();
    }

    /**
     * 회원 이름을 수정합니다.
     *
     * @param id 회원 ID
     * @param memberName 변경할 회원 이름
     * @return 수정된 회원의 응답 DTO
     * @throws MemberNotFoundException 존재하지 않는 회원일 경우
     */
    @Transactional
    public MemberResponse updateMemberName(Long id, String memberName) {
        Member member = memberRepository.findByIdOrThrow(id);
        member.setMemberName(memberName);
        return MemberResponse.toDto(member);
    }

    /**
     * 비밀번호를 확인한 뒤 회원을 삭제합니다.
     *
     * @param id 회원 ID
     * @param password 평문 비밀번호
     * @throws MemberNotFoundException 존재하지 않는 회원일 경우
     * @throws NotMatchedPasswordException 비밀번호가 일치하지 않을 경우
     */
    public void deleteMember(Long id, String password) {
        Member member = memberRepository.findByIdOrThrow(id);
        if (!encoder.matches(password, member.getPassword())) {
            throw new NotMatchedPasswordException();
        }
        memberRepository.delete(member);
    }
}
