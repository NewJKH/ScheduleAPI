package org.jkh.scheduleapi.domain.comment.repository;

import org.jkh.scheduleapi.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment findByIdOrThrow(Long id){
        return this.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
}
