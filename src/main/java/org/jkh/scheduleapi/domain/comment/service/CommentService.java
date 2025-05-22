package org.jkh.scheduleapi.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.common.exception.member.MemberNotMatchedException;
import org.jkh.scheduleapi.common.exception.schedule.ScheduleNotMatchedException;
import org.jkh.scheduleapi.domain.comment.dto.CommentResponse;
import org.jkh.scheduleapi.domain.comment.entity.Comment;
import org.jkh.scheduleapi.domain.comment.repository.CommentRepository;
import org.jkh.scheduleapi.domain.member.entity.Member;
import org.jkh.scheduleapi.domain.member.repository.MemberRepository;
import org.jkh.scheduleapi.domain.schedule.entity.Schedule;
import org.jkh.scheduleapi.domain.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    public CommentResponse createComment(Long schedule_id, Long member_id, String message) {
        Schedule schedule = scheduleRepository.findByIdOrThrow(schedule_id);
        Member member = memberRepository.findByIdOrThrow(member_id);

        Comment comment = new Comment(schedule,member,message);
        commentRepository.save(comment);
        return CommentResponse.toDto(commentRepository.findByIdOrThrow((comment.getId())));
    }

    public List<CommentResponse> getAllCommentsByMemberId(Long member_id){
        return commentRepository.findByMemberId(member_id).stream()
                .map(CommentResponse::toDto)
                .toList();
    }

    public List<CommentResponse> getAllCommentsByScheduleId(Long schedule_id){
        return commentRepository.findByScheduleId(schedule_id).stream()
                .map(CommentResponse::toDto)
                .toList();
    }

    @Transactional
    public CommentResponse updateComment(Long id, Long schedule_id, Long member_id, String message){
        Comment comment = commentRepository.findByIdOrThrow(id);
        if ( comment.getSchedule().getId() != schedule_id.longValue() ){
            throw new ScheduleNotMatchedException();
        }
        if ( comment.getMember().getId() != member_id.longValue()){
            throw new MemberNotMatchedException();
        }
        comment.setMessage(message);
        return CommentResponse.toDto(comment);
    }

    public void deleteComment(Long id, Long member_id){
        Comment comment = commentRepository.findByIdOrThrow(id);
        if ( comment.getMember().getId() != member_id.longValue()){
            throw new MemberNotMatchedException();
        }
        commentRepository.delete(comment);
    }
}
