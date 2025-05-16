package org.jkh.scheduleapi.domain.schedule.repository;

import org.jkh.scheduleapi.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule, String>{

    default Schedule findByMemberName(String member_name){
        return findById(member_name)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND," 유저를 찾을 수 없습니다"));
    }
}
