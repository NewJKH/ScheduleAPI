package org.jkh.scheduleapi.domain.schedule.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ScheduleDeleteRequest{
        @Length(min = 1, max = 10)
        private String title;
}
