package org.jkh.scheduleapi.common.exception.schedule;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(String message) {
        super(message);
    }
    public ScheduleNotFoundException() {
     super(" 일정을 찾지 못했습니다. ");
   }
}
