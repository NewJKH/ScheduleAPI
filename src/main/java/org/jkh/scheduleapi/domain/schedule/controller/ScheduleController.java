package org.jkh.scheduleapi.domain.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.domain.schedule.dto.request.SchedulerCreateRequest;
import org.jkh.scheduleapi.domain.schedule.dto.response.ScheduleResponse;
import org.jkh.scheduleapi.domain.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public ResponseEntity<ScheduleResponse> save(@RequestBody SchedulerCreateRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.save(request.member_name(),request.title(),request.content()));
    }
}
