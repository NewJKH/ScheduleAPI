package org.jkh.scheduleapi.domain.member.dto;

import org.hibernate.validator.constraints.Length;

public record MemberUpdateRequest(
        Long id,
        @Length(min = 1, max = 4)
        String memberName) { }
