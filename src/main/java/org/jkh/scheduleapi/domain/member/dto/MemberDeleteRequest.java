package org.jkh.scheduleapi.domain.member.dto;

import org.hibernate.validator.constraints.Length;

public record MemberDeleteRequest(
        Long id,
        @Length(min = 1, max = 16)
        String password) {
}
