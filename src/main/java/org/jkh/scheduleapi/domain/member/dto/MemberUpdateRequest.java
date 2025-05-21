package org.jkh.scheduleapi.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public record MemberUpdateRequest(
        @Length(min = 1, max = 4)
        @JsonProperty("member_name")
        String memberName) { }
