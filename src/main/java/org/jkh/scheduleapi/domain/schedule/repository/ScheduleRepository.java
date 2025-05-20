package org.jkh.scheduleapi.domain.schedule.repository;

import org.jkh.scheduleapi.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
    Optional<Schedule> findByMemberName(String memberName);

    default Schedule findByMemberNameOrThrow(String member_name){
        return this.findByMemberName(member_name)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND," 유저를 찾을 수 없습니다"));
    }

    default Schedule findByIdOrThrow(Long id){
        return this.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND," 일정을 찾을 수 없습니다."));
    }
}
