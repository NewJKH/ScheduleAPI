package org.jkh.scheduleapi.common.exception;

import org.jkh.scheduleapi.common.exception.comment.CommentNotFoundException;
import org.jkh.scheduleapi.common.exception.login.LoginRequiredException;
import org.jkh.scheduleapi.common.exception.login.NotMatchedPasswordException;
import org.jkh.scheduleapi.common.exception.member.MemberNotFoundException;
import org.jkh.scheduleapi.common.exception.member.MemberNotMatchedException;
import org.jkh.scheduleapi.common.exception.schedule.EmptyContentException;
import org.jkh.scheduleapi.common.exception.schedule.ScheduleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 요청 본문의 내용이 비어 있을 경우 발생하는 사용자 정의 예외를 처리합니다.
     *
     * HTTP 400 (Bad Request) 응답을 반환하여,
     * 클라이언트가 유효하지 않은 데이터를 전송했음을 명확히 알립니다.
     *
     * @param e EmptyContentException 예외 인스턴스
     * @return 400 Bad Request + 예외 메시지
     */
    @ExceptionHandler(EmptyContentException.class)
    public ResponseEntity<String> handleEmptyContent(EmptyContentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    /**
     * 요청한 일정을 찾을 수 없을 때 발생하는 예외를 처리합니다.
     *
     * 존재하지 않는 리소스에 대한 요청이므로,
     * HTTP 404 (Not Found)를 반환하여 RESTful 원칙을 따릅니다.
     *
     * @param e ScheduleNotFoundException 예외 인스턴스
     * @return 404 Not Found + 예외 메시지
     */
    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<String> handleScheduleNotFound(ScheduleNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * 요청한 사용자를 찾을 수 없을 경우 발생하는 예외를 처리합니다.
     *
     * 클라이언트가 존재하지 않는 사용자 정보를 요청했기 때문에,
     * HTTP 404 응답으로 리소스 부재를 명확히 전달합니다.
     *
     * @param e MemberNotFoundException 예외 인스턴스
     * @return 404 Not Found + 예외 메시지
     */
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<String> handleMemberNotFound(MemberNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * 인증된 사용자와 요청을 수행하려는 사용자가 일치하지 않을 경우 발생하는 예외입니다.
     *
     * 사용자의 권한 범위를 벗어난 요청이므로, HTTP 400 (Bad Request)를 사용하여
     * 잘못된 요청임을 클라이언트에 알립니다.
     *
     * @param e MemberNotMatchedException 예외 인스턴스
     * @return 400 Bad Request + 예외 메시지
     */
    @ExceptionHandler(MemberNotMatchedException.class)
    public ResponseEntity<String> handleMemberNotMatched(MemberNotMatchedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * 로그인하지 않은 사용자가 인증이 필요한 요청을 시도할 때 발생하는 예외입니다.
     *
     * 인증 정보가 없으므로, HTTP 401 (Unauthorized)를 통해
     * 로그인이 필요함을 클라이언트에 알립니다.
     *
     * @param e LoginRequiredException 예외 인스턴스
     * @return 401 Unauthorized + 예외 메시지
     */
    @ExceptionHandler(LoginRequiredException.class)
    public ResponseEntity<String> handleLoginRequired(LoginRequiredException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /**
     * 사용자가 입력한 비밀번호가 저장된 비밀번호와 일치하지 않을 때 발생하는 예외입니다.
     *
     * 인증 실패에 해당하므로, HTTP 401 (Unauthorized)를 반환하여
     * 클라이언트에 인증 실패를 알립니다.
     *
     * @param e NotMatchedPasswordException 예외 인스턴스
     * @return 401 Unauthorized + 예외 메시지
     */
    @ExceptionHandler(NotMatchedPasswordException.class)
    public ResponseEntity<String> handleNotMatchedPassword(NotMatchedPasswordException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /**
     * 요청한 댓글이 존재하지 않을 때 발생하는 예외입니다.
     *
     * 클라이언트가 존재하지 않는 리소스를 요청한 경우이므로,
     * HTTP 404 (Not Found)를 반환하여 댓글을 찾을 수 없음을 알립니다.
     *
     * @param e CommentNotFoundException 예외 인스턴스
     * @return 404 Not Found + 예외 메시지
     */
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<String> handleCommentNotFound(CommentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
