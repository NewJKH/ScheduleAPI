package org.jkh.scheduleapi.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.domain.comment.dto.CommentResponse;
import org.jkh.scheduleapi.domain.comment.entity.Comment;
import org.jkh.scheduleapi.domain.comment.repository.CommentRepository;
import org.jkh.scheduleapi.domain.member.entity.Member;
import org.jkh.scheduleapi.domain.member.repository.MemberRepository;
import org.jkh.scheduleapi.domain.schedule.entity.Schedule;
import org.jkh.scheduleapi.domain.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

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
        return memberRepository.findByIdOrThrow(member_id).getComments().stream()
                .map(CommentResponse::toDto)
                .toList();
    }

    public List<CommentResponse> getAllCommentsByScheduleId(Long schedule_id){
        return scheduleRepository.findByIdOrThrow(schedule_id).getComments().stream()
                .map(CommentResponse::toDto)
                .toList();
    }
}
