package org.jkh.scheduleapi.common.exception.schedule;

public class ScheduleNotMatchedException extends RuntimeException {
    public ScheduleNotMatchedException(String message) {
        super(message);
    }

  public ScheduleNotMatchedException() {
      super(" 일정이 일치하지 않습니다. ");
  }
}
