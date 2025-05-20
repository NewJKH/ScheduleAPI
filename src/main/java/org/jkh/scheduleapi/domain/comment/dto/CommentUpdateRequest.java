package org.jkh.scheduleapi.domain.comment.dto;

public record CommentUpdateRequest(Long id, Long schedule_id, Long member_id, String message) {
}
