package org.jkh.scheduleapi.domain.comment.repository;

import org.jkh.scheduleapi.common.exception.comment.CommentNotFoundException;
import org.jkh.scheduleapi.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment findByIdOrThrow(Long id){
        return this.findById(id)
                .orElseThrow(CommentNotFoundException::new);
    }
}
