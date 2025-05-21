package org.jkh.scheduleapi.common.exception.schedule;

public class EmptyContentException extends RuntimeException {
    public EmptyContentException() {
        super("내용이 비었습니다.");
    }
}
