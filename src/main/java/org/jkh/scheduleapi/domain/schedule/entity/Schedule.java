package org.jkh.scheduleapi.domain.schedule.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jkh.scheduleapi.common.entity.BaseDate;

@Table(name = "schedules")
@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseDate {

    @Id
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
}
