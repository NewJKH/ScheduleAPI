package org.jkh.scheduleapi.domain.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.domain.schedule.dto.request.ScheduleRequest;
import org.jkh.scheduleapi.domain.schedule.dto.response.ScheduleResponse;
import org.jkh.scheduleapi.domain.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public ResponseEntity<ScheduleResponse> save(@RequestBody ScheduleRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.save(request.member_name(),request.title(),request.content()));
    }

    @GetMapping("/schedule")
    public ResponseEntity<ScheduleResponse> find(@RequestParam String username){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.findByMemberName(username));
    }
    @GetMapping("/schedule/all")
    public ResponseEntity<List<ScheduleResponse>> find(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.findAll());
    }

    @PutMapping("/schedule")
    public ResponseEntity<ScheduleResponse> update(@RequestBody ScheduleRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.updateSchedule(request.member_name(),request.title(),request.content()));
    }
}
