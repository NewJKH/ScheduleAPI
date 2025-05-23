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

    public LoginResponse login(@Email String email, String password) {
        Member member = memberRepository.findByEmailOrThrow(email);
        if (!encoder.matches(password,member.getPassword())){
            throw new NotMatchedPasswordException();
        }
        return LoginResponse.toDto(member.getMemberName());
    }
}
