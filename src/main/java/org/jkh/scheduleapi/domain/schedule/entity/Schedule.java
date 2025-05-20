package org.jkh.scheduleapi.domain.schedule.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jkh.scheduleapi.common.entity.BaseDate;

@Table(name = "schedules")
@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String member_name;

    @Column(length = 20, nullable = false)
    private String title;
    @Column(columnDefinition = "longtext", nullable = false)
    private String content;

    public Schedule(String member_name, String title, String content) {
        this.member_name = member_name;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
