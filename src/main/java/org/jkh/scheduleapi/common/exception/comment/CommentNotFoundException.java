package org.jkh.scheduleapi.common.exception.comment;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(String message) {
        super(message);
    }
    public CommentNotFoundException() {
        super(" 댓글을 찾지 못했습니다. ");
    }
}
