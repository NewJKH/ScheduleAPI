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

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody CommentRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.createComment(request.scheduleId(),request.memberId(),request.message()));
    }

    @GetMapping("/member")
    public ResponseEntity<List<CommentResponse>> findByMemberId(@RequestParam Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getAllCommentsByMemberId(id));
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<CommentResponse>> findByScheduleId(@RequestParam Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getAllCommentsByScheduleId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable long id, @RequestBody CommentUpdateRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.updateComment(id, request.scheduleId(),request.memberId(),request.message()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id, @RequestBody CommentDeleteRequest request){
        commentService.deleteComment(id, request.memberId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
