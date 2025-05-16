package org.jkh.scheduleapi.domain.schedule.service;

import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.domain.schedule.dto.response.ScheduleResponse;
import org.jkh.scheduleapi.domain.schedule.entity.Schedule;
import org.jkh.scheduleapi.domain.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponse save(String member_name, String title, String content) {
        Schedule schedule = new Schedule(member_name,title,content);
        scheduleRepository.save(schedule);

        return new ScheduleResponse(schedule.getMember_name(),schedule.getTitle(), schedule.getContent());
    }

    public ScheduleResponse findByMemberName(String memberName) {
        Schedule schedule = scheduleRepository.findByMemberName(memberName);
        return ScheduleResponse.toDTO(schedule);
    }

    public List<ScheduleResponse> findAll() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponse::toDTO)
                .toList();
    }
}
