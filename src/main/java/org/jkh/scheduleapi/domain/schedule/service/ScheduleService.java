package org.jkh.scheduleapi.domain.schedule.service;

import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.common.exception.schedule.ScheduleNotMatchedException;
import org.jkh.scheduleapi.domain.schedule.dto.response.ScheduleResponse;
import org.jkh.scheduleapi.domain.schedule.entity.Schedule;
import org.jkh.scheduleapi.domain.schedule.repository.ScheduleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponse save(String member_name, String title, String content) {
        Schedule schedule = new Schedule(member_name,title,content);
        scheduleRepository.save(schedule);

        return ScheduleResponse.toDTO(schedule);
    }

    public ScheduleResponse findByMemberName(String memberName) {
        Schedule schedule = scheduleRepository.findByMemberNameOrThrow(memberName);
        return ScheduleResponse.toDTO(schedule);
    }

    public List<ScheduleResponse> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createAt"));
        return scheduleRepository.findAll(pageable).stream()
                .map(ScheduleResponse::toDTO)
                .toList();
    }

    @Transactional
    public ScheduleResponse updateSchedule(String member_name, String title, String content) {
        Schedule schedule = scheduleRepository.findByMemberNameOrThrow(member_name);
        schedule.update(title,content);

        return ScheduleResponse.toDTO(schedule);
    }

    @Transactional
    public void delete(String member_name, String title) {
        Schedule schedule = scheduleRepository.findByMemberNameOrThrow(member_name);

        if ( !schedule.getTitle().equals(title)){
            throw new ScheduleNotMatchedException();
        }
        scheduleRepository.delete(schedule);
    }
}
