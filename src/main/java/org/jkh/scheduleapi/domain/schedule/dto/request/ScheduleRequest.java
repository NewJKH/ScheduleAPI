package org.jkh.scheduleapi.domain.schedule.dto.request;

import org.hibernate.validator.constraints.Length;

public record ScheduleRequest(
        @Length(min = 1, max = 4)
        String member_name,
        @Length(min = 1, max = 10)
        String title,
        String content ) { }
