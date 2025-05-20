package org.jkh.scheduleapi.domain.comment.controller;

import lombok.RequiredArgsConstructor;
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
                .body(commentService.createComment(request.schedule_id(),request.member_id(),request.message()));
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

    @PutMapping
    public ResponseEntity<CommentResponse> update(@RequestBody CommentUpdateRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.updateComment(request.id(), request.schedule_id(),request.member_id(),request.message()));
    }

}
