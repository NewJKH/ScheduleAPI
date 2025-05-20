package org.jkh.scheduleapi.domain.comment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jkh.scheduleapi.common.entity.BaseDate;
import org.jkh.scheduleapi.domain.member.entity.Member;
import org.jkh.scheduleapi.domain.schedule.entity.Schedule;

@Table(name = "comments")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Setter
    @Column(columnDefinition = "text", nullable = false)
    private String message;

    public Comment(Schedule schedule, Member member, String message) {
        this.schedule = schedule;
        this.member = member;
        this.message = message;
    }
}
