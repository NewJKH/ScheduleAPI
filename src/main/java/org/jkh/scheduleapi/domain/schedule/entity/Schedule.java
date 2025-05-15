package org.jkh.scheduleapi.domain.schedule.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {
    private String memberName;
    private String title;
    private String content;

    private Date createdAt;
    private Date modifiedAt;
}
