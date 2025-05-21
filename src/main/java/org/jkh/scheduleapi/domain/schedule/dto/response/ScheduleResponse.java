package org.jkh.scheduleapi.domain.schedule.dto.response;

import org.jkh.scheduleapi.domain.schedule.entity.Schedule;

import java.util.Date;

public record ScheduleResponse(String memberName, String title, String content, Date createAt, Date modifiedAt) {
    public static ScheduleResponse toDTO(Schedule schedule){
        return new ScheduleResponse(schedule.getMemberName(), schedule.getTitle(), schedule.getContent(),schedule.getCreateAt(), schedule.getModifiedAt());
    }
}
