package org.jkh.scheduleapi.domain.schedule.dto.response;

import org.jkh.scheduleapi.domain.schedule.entity.Schedule;

public record ScheduleResponse(String member_name, String title, String content) {
    public static ScheduleResponse toDTO(Schedule schedule){
        return new ScheduleResponse(schedule.getMember_name(), schedule.getTitle(), schedule.getContent());
    }
}
