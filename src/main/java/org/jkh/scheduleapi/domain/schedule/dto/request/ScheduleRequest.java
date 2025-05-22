package org.jkh.scheduleapi.domain.schedule.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ScheduleRequest{

        @Length(min = 1, max = 4)
        @JsonProperty("member_name")
        private String memberName;

        @Length(min = 1, max = 10)
        private String title;
        private String content;
}
