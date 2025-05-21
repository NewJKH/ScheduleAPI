package org.jkh.scheduleapi.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommentDeleteRequest(
        Long id,
        @JsonProperty("member_id")
        Long memberId) { }