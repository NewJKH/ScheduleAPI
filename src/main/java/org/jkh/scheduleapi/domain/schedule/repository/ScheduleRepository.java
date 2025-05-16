package org.jkh.scheduleapi.domain.schedule.repository;

import org.jkh.scheduleapi.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, String>{

}
