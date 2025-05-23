package org.jkh.scheduleapi.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.common.exception.comment.CommentNotFoundException;
import org.jkh.scheduleapi.common.exception.member.MemberNotFoundException;
import org.jkh.scheduleapi.common.exception.member.MemberNotMatchedException;
import org.jkh.scheduleapi.common.exception.schedule.ScheduleNotFoundException;
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

    /**
     * 새로운 댓글을 작성합니다.
     *
     * @param schedule_id 댓글이 속한 일정의 ID
     * @param member_id 댓글 작성자의 ID
     * @param message 댓글 내용
     * @return 저장된 댓글의 응답 DTO
     * @throws ScheduleNotFoundException 해당 일정이 존재하지 않을 경우
     * @throws MemberNotFoundException 해당 회원이 존재하지 않을 경우
     */
    public CommentResponse createComment(Long schedule_id, Long member_id, String message) {
        Schedule schedule = scheduleRepository.findByIdOrThrow(schedule_id);
        Member member = memberRepository.findByIdOrThrow(member_id);

        Comment comment = new Comment(schedule, member, message);
        commentRepository.save(comment);
        return CommentResponse.toDto(commentRepository.findByIdOrThrow(comment.getId()));
    }

    /**
     * 댓글 ID를 통해 단일 댓글을 조회합니다.
     *
     * @param id 댓글 ID
     * @return 조회된 댓글의 응답 DTO
     * @throws CommentNotFoundException 해당 댓글이 존재하지 않을 경우
     */
    public CommentResponse getCommentById(Long id) {
        return CommentResponse.toDto(commentRepository.findByIdOrThrow(id));
    }
    /**
     * 특정 회원이 작성한 모든 댓글을 조회합니다.
     *
     * @param member_id 회원 ID
     * @return 댓글 응답 DTO 리스트
     */
    public List<CommentResponse> getAllCommentsByMemberId(Long member_id) {
        return commentRepository.findByMemberId(member_id).stream()
                .map(CommentResponse::toDto)
                .toList();
    }

    /**
     * 특정 일정에 등록된 모든 댓글을 조회합니다.
     *
     * @param schedule_id 일정 ID
     * @return 댓글 응답 DTO 리스트
     */
    public List<CommentResponse> getAllCommentsByScheduleId(Long schedule_id) {
        return commentRepository.findByScheduleId(schedule_id).stream()
                .map(CommentResponse::toDto)
                .toList();
    }

    /**
     * 댓글 내용을 수정합니다.
     * 일정 ID와 회원 ID가 일치하는지 확인 후 수정합니다.
     *
     * @param id 댓글 ID
     * @param schedule_id 댓글이 속한 일정 ID
     * @param member_id 댓글 작성자 ID
     * @param message 수정할 메시지 내용
     * @return 수정된 댓글의 응답 DTO
     * @throws ScheduleNotMatchedException 일정 불일치 시 예외 발생
     * @throws MemberNotMatchedException 회원 불일치 시 예외 발생
     */
    @Transactional
    public CommentResponse updateComment(Long id, Long schedule_id, Long member_id, String message) {
        Comment comment = commentRepository.findByIdOrThrow(id);
        if (comment.getSchedule().getId() != schedule_id.longValue()) {
            throw new ScheduleNotMatchedException();
        }
        if (comment.getMember().getId() != member_id.longValue()) {
            throw new MemberNotMatchedException();
        }
        comment.setMessage(message);
        return CommentResponse.toDto(comment);
    }

    /**
     * 댓글을 삭제합니다.
     * 작성자와 일치하지 않으면 삭제할 수 없습니다.
     *
     * @param id 댓글 ID
     * @param member_id 삭제 요청한 회원 ID
     * @throws MemberNotMatchedException 작성자와 일치하지 않을 경우 예외 발생
     */
    public void deleteComment(Long id, Long member_id) {
        Comment comment = commentRepository.findByIdOrThrow(id);
        if (comment.getMember().getId() != member_id.longValue()) {
            throw new MemberNotMatchedException();
        }
        commentRepository.delete(comment);
    }

}
