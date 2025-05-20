package org.jkh.scheduleapi.domain.comment.dto;

public record CommentRequest(Long schedule_id, Long member_id, String message) { }
