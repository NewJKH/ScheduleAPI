package org.jkh.scheduleapi.domain.schedule.dto.response;

import org.jkh.scheduleapi.domain.schedule.entity.Schedule;

import java.util.Date;

public record ScheduleResponse(long id, String memberName, String title, String content, Date createAt, Date modifiedAt) {
    public static ScheduleResponse toDTO(Schedule schedule){
        return new ScheduleResponse(schedule.getId(), schedule.getMemberName(), schedule.getTitle(), schedule.getContent(),schedule.getCreateAt(), schedule.getModifiedAt());
    }
}
