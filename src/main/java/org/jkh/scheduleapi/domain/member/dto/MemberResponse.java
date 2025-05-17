package org.jkh.scheduleapi.domain.member.dto;

import org.jkh.scheduleapi.domain.member.entity.Member;

import java.util.Date;

public record MemberResponse(Long id, String user_name, String email, Date createAt, Date modifiedAt) {
    public static MemberResponse toDto(Member member) {
        return new MemberResponse(member.getId(), member.getMember_name(), member.getEmail(), member.getCreateAt(), member.getModifiedAt());
    }
}
