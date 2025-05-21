package org.jkh.scheduleapi.common.exception.member;

public class MemberNotMatchedException extends RuntimeException {
    public MemberNotMatchedException(String message) {
        super(message);
    }

    public MemberNotMatchedException() {
        super(" 사용자가 일치하지 않습니다. ");
    }
}
