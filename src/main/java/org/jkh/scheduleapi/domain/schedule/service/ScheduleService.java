package org.jkh.scheduleapi.domain.schedule.service;

import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.common.exception.schedule.ScheduleNotFoundException;
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

    /**
     * 새로운 일정을 등록합니다.
     *
     * @param memberName 작성자 이름
     * @param title 일정 제목
     * @param content 일정 내용
     * @return 저장된 일정의 응답 DTO
     */
    public ScheduleResponse save(String memberName, String title, String content) {
        Schedule schedule = new Schedule(memberName, title, content);
        scheduleRepository.save(schedule);
        return ScheduleResponse.toDTO(schedule);
    }

    /**
     * 작성자 이름을 기준으로 일정을 조회합니다.
     *
     * @param memberName 작성자 이름
     * @return 일정 응답 DTO
     * @throws ScheduleNotFoundException 해당 작성자의 일정이 없을 경우
     *
     * @deprecated 이제 findById()를 사용하세요.
     */
    @Deprecated
    public ScheduleResponse findByMemberName(String memberName) {
        Schedule schedule = scheduleRepository.findByMemberNameOrThrow(memberName);
        return ScheduleResponse.toDTO(schedule);
    }

    /**
     * 일정 ID로 일정을 조회합니다.
     *
     * @param id 일정 ID
     * @return 일정 응답 DTO
     * @throws ScheduleNotFoundException 해당 ID의 일정이 없을 경우
     */
    public ScheduleResponse findById(long id) {
        Schedule schedule = scheduleRepository.findByIdOrThrow(id);
        return ScheduleResponse.toDTO(schedule);
    }

    /**
     * 모든 일정을 페이징하여 조회합니다.
     *
     * @param page 페이지 번호 (0부터 시작)
     * @param size 페이지 크기
     * @return 일정 응답 DTO 리스트 (최신순 정렬)
     */
    public List<ScheduleResponse> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createAt"));
        return scheduleRepository.findAll(pageable).stream()
                .map(ScheduleResponse::toDTO)
                .toList();
    }

    /**
     * 일정의 제목과 내용을 수정합니다.
     *
     * @param id 일정 ID
     * @param title 수정할 제목
     * @param content 수정할 내용
     * @return 수정된 일정의 응답 DTO
     * @throws ScheduleNotFoundException 해당 일정이 없을 경우
     */
    @Transactional
    public ScheduleResponse updateSchedule(long id, String title, String content) {
        Schedule schedule = scheduleRepository.findByIdOrThrow(id);
        schedule.update(title, content);
        return ScheduleResponse.toDTO(schedule);
    }

    /**
     * 일정 제목을 검증한 후 일정을 삭제합니다.
     *
     * @param id 일정 ID
     * @param title 검증할 제목
     * @throws ScheduleNotMatchedException 제목이 일치하지 않을 경우
     * @throws ScheduleNotFoundException 해당 일정이 없을 경우
     */
    public void delete(long id, String title) {
        Schedule schedule = scheduleRepository.findByIdOrThrow(id);
        if (!schedule.getTitle().equals(title)) {
            throw new ScheduleNotMatchedException();
        }
        scheduleRepository.delete(schedule);
    }
}
