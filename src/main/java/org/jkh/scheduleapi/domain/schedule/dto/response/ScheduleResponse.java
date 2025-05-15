package org.jkh.scheduleapi.domain.schedule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponse {
    private String memberName;
    private String title;
    private String content;

}
