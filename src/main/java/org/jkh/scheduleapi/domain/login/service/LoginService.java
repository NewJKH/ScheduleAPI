package org.jkh.scheduleapi.domain.login.service;

import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.common.config.PasswordEncoder;
import org.jkh.scheduleapi.common.exception.login.NotMatchedPasswordException;
import org.jkh.scheduleapi.domain.login.dto.response.LoginResponse;
import org.jkh.scheduleapi.domain.member.entity.Member;
import org.jkh.scheduleapi.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    /**
     * 이메일과 비밀번호를 기반으로 사용자를 인증합니다.
     *
     * @param email 사용자의 이메일 주소 (유효한 이메일 형식이어야 함)
     * @param password 사용자의 평문 비밀번호
     * @return 로그인 성공 시 사용자 이름을 포함한 응답 DTO
     * @throws NotMatchedPasswordException 비밀번호가 일치하지 않을 경우 예외 발생
     */
    public LoginResponse login(@Email String email, String password) {
        Member member = memberRepository.findByEmailOrThrow(email);
        if (!encoder.matches(password,member.getPassword())){
            throw new NotMatchedPasswordException();
        }
        return LoginResponse.toDto(member.getMemberName());
    }
}
