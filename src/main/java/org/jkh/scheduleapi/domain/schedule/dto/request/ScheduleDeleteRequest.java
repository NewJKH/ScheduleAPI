package org.jkh.scheduleapi.domain.schedule.dto.request;

import org.hibernate.validator.constraints.Length;

public record ScheduleDeleteRequest (
        @Length(min = 1, max = 4)
        String memberName,
        @Length(min = 1, max = 10)
        String title){
}
