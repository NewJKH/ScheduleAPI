package org.jkh.scheduleapi.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.domain.comment.dto.CommentRequest;
import org.jkh.scheduleapi.domain.comment.dto.CommentResponse;
import org.jkh.scheduleapi.domain.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
