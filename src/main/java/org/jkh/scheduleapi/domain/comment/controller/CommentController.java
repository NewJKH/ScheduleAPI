package org.jkh.scheduleapi.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.domain.comment.dto.CommentDeleteRequest;
import org.jkh.scheduleapi.domain.comment.dto.CommentRequest;
import org.jkh.scheduleapi.domain.comment.dto.CommentResponse;
import org.jkh.scheduleapi.domain.comment.dto.CommentUpdateRequest;
import org.jkh.scheduleapi.domain.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    /**
     * 댓글을 생성합니다.
     *
     * @param request 댓글 생성 요청 정보 (일정 ID, 회원 ID, 메시지)
     * @return 생성된 댓글의 응답 DTO
     */
    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody CommentRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.createComment(request.scheduleId(), request.memberId(), request.message()));
    }

    /**
     * 특정 댓글을 확인 합니다.
     *
     * @param id 댓글 ID
     * @return 댓글 응답 DTO 리스트
     */
    @GetMapping
    public ResponseEntity<CommentResponse> findById(@RequestParam Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getCommentById(id));
    }


    /**
     * 특정 회원이 작성한 댓글 목록을 조회합니다.
     *
     * @param id 회원 ID
     * @return 댓글 응답 DTO 리스트
     */
    @GetMapping("/member")
    public ResponseEntity<List<CommentResponse>> findByMemberId(@RequestParam Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getAllCommentsByMemberId(id));
    }

    /**
     * 특정 일정에 등록된 댓글 목록을 조회합니다.
     *
     * @param id 일정 ID
     * @return 댓글 응답 DTO 리스트
     */
    @GetMapping("/schedule")
    public ResponseEntity<List<CommentResponse>> findByScheduleId(@RequestParam Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getAllCommentsByScheduleId(id));
    }

    /**
     * 댓글 내용을 수정합니다.
     *
     * @param id 수정할 댓글 ID (경로 변수)
     * @param request 댓글 수정 요청 정보 (일정 ID, 회원 ID, 메시지)
     * @return 수정된 댓글의 응답 DTO
     */
    @PutMapping("{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable long id, @RequestBody CommentUpdateRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.updateComment(id, request.scheduleId(), request.memberId(), request.message()));
    }

    /**
     * 댓글을 삭제합니다.
     * 요청자의 회원 ID와 댓글 작성자가 일치하는지 검증합니다.
     *
     * @param id 삭제할 댓글 ID (경로 변수)
     * @param request 댓글 삭제 요청 정보 (회원 ID)
     * @return HTTP 200 OK (본문 없음)
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id, @RequestBody CommentDeleteRequest request) {
        commentService.deleteComment(id, request.memberId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
