package org.jkh.scheduleapi.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommentRequest(
        @JsonProperty("schedule_id")
        Long scheduleId,
        @JsonProperty("member_id")
        Long memberId,
        String message) { }
