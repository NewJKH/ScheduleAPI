package org.jkh.scheduleapi.domain.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.common.exception.schedule.ScheduleNotFoundException;
import org.jkh.scheduleapi.common.exception.schedule.ScheduleNotMatchedException;
import org.jkh.scheduleapi.domain.schedule.dto.request.ScheduleDeleteRequest;
import org.jkh.scheduleapi.domain.schedule.dto.request.ScheduleRequest;
import org.jkh.scheduleapi.domain.schedule.dto.request.ScheduleUpdateRequest;
import org.jkh.scheduleapi.domain.schedule.dto.response.ScheduleResponse;
import org.jkh.scheduleapi.domain.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    /**
     * 새로운 일정을 등록합니다.
     *
     * @param request 일정 등록 요청 정보 (작성자 이름, 제목, 내용)
     * @return 등록된 일정의 응답 DTO
     */
    @PostMapping("/schedule")
    public ResponseEntity<ScheduleResponse> save(@Validated @RequestBody ScheduleRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.save(request.getMemberName(), request.getTitle(), request.getContent()));
    }

    /**
     * 일정 ID로 단일 일정을 조회합니다.
     *
     * @param id 일정 ID
     * @return 일정 응답 DTO
     * @throws ScheduleNotFoundException 해당 일정이 없을 경우
     */
    @GetMapping("/schedule")
    public ResponseEntity<ScheduleResponse> find(@Validated @RequestParam long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.findById(id));
    }

    /**
     * 페이징 처리된 전체 일정 목록을 조회합니다.
     *
     * @param page 페이지 번호 (기본값: 0)
     * @param size 페이지 크기 (기본값: 10)
     * @return 일정 응답 DTO 리스트
     */
    @GetMapping("/schedule/all")
    public ResponseEntity<List<ScheduleResponse>> find(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.findAll(page, size));
    }

    /**
     * 일정 내용을 수정합니다.
     *
     * @param id 수정할 일정 ID
     * @param request 일정 수정 요청 정보 (제목, 내용)
     * @return 수정된 일정의 응답 DTO
     * @throws ScheduleNotFoundException 해당 일정이 없을 경우
     */
    @PutMapping("/schedule/update/{id}")
    public ResponseEntity<ScheduleResponse> update(@PathVariable long id, @Validated @RequestBody ScheduleUpdateRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.updateSchedule(id, request.getTitle(), request.getContent()));
    }

    /**
     * 일정 제목을 검증한 뒤 일정을 삭제합니다.
     *
     * @param id 삭제할 일정 ID
     * @param request 일정 삭제 요청 정보 (제목)
     * @return HTTP 200 OK (본문 없음)
     * @throws ScheduleNotMatchedException 제목이 일치하지 않을 경우
     * @throws ScheduleNotFoundException 해당 일정이 없을 경우
     */
    @DeleteMapping("/schedule/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id, @Validated @RequestBody ScheduleDeleteRequest request) {
        scheduleService.delete(id, request.getTitle());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
