package org.jkh.scheduleapi.domain.member.dto;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record MemberSignUpRequest(
        @Length(min = 1, max = 4)
        String member_name,
        @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
        String email,
        @Length(min = 1, max = 16)
        String password) { }
