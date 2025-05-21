package org.jkh.scheduleapi.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.jkh.scheduleapi.common.entity.Email;

public record MemberSignUpRequest(
        @Length(min = 1, max = 4)
        @JsonProperty("member_name")
        String memberName,
        @Pattern(regexp = Email.emailPattern, message = "이메일 형식이 올바르지 않습니다.")
        String email,
        @Length(min = 1, max = 16)
        String password) { }
