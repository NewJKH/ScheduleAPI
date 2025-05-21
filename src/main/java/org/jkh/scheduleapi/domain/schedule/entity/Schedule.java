package org.jkh.scheduleapi.domain.schedule.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jkh.scheduleapi.common.entity.BaseDate;
import org.jkh.scheduleapi.domain.comment.entity.Comment;

import java.util.ArrayList;
import java.util.List;

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
    private String memberName;

    @Column(length = 20, nullable = false)
    private String title;
    @Column(columnDefinition = "longtext", nullable = false)
    private String content;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    public Schedule(String memberName, String title, String content) {
        this.memberName = memberName;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
