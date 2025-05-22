package org.jkh.scheduleapi.domain.login.dto.request;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.jkh.scheduleapi.common.entity.Email;

public record LoginRequest(
        @Pattern(regexp = Email.emailPattern, message = "이메일 형식이 올바르지 않습니다.")
        String email,
        @Length(min = 2, max = 16)
        String password) { }
