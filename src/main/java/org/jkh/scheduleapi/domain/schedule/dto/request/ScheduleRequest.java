package org.jkh.scheduleapi.domain.schedule.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleRequest {
    private String memberName;
    private String title;
    private String content;

}
