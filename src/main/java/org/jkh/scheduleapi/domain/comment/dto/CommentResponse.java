package org.jkh.scheduleapi.domain.comment.dto;

import org.jkh.scheduleapi.domain.comment.entity.Comment;

import java.util.Date;

public record CommentResponse(Long id, Long schedule_id, Long member_id, String message, Date createAt, Date modifiedAt) {
    public static CommentResponse toDto(Comment comment){
        return new CommentResponse(comment.getId(),comment.getSchedule().getId(),comment.getMember().getId(),comment.getMessage(),comment.getCreateAt(),comment.getModifiedAt());
    }
}
