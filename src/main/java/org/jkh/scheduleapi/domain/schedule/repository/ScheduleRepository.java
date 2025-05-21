package org.jkh.scheduleapi.domain.schedule.repository;

import org.jkh.scheduleapi.common.exception.member.MemberNotFoundException;
import org.jkh.scheduleapi.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
    Optional<Schedule> findByMemberName(String memberName);

    default Schedule findByMemberNameOrThrow(String member_name){
        return this.findByMemberName(member_name)
                .orElseThrow(MemberNotFoundException::new);
    }

    default Schedule findByIdOrThrow(Long id){
        return this.findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }
}
