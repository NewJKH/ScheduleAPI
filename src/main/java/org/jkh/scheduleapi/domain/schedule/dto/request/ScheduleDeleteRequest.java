package org.jkh.scheduleapi.domain.schedule.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public record ScheduleDeleteRequest (
        @Length(min = 1, max = 4)
        @JsonProperty("member_name")
        String memberName,
        @Length(min = 1, max = 10)
        String title){
}
