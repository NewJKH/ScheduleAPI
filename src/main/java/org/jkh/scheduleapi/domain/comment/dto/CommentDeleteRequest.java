package org.jkh.scheduleapi.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommentDeleteRequest(
        @JsonProperty("member_id")
        Long memberId) { }