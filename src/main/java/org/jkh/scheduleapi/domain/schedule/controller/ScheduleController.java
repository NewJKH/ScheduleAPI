package org.jkh.scheduleapi.domain.schedule.controller;

import lombok.RequiredArgsConstructor;
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

    @PostMapping("/schedule")
    public ResponseEntity<ScheduleResponse> save(@Validated @RequestBody ScheduleRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.save(request.getMemberName(),request.getTitle(),request.getContent()));
    }

    @GetMapping("/schedule")
    public ResponseEntity<ScheduleResponse> find(@Validated @RequestParam long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.findById(id));
    }

    @GetMapping("/schedule/all")
    public ResponseEntity<List<ScheduleResponse>> find(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.findAll(page,size));
    }

    @PutMapping("/schedule/update/{id}")
    public ResponseEntity<ScheduleResponse> update(@PathVariable long id, @Validated @RequestBody ScheduleUpdateRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.updateSchedule(id,request.getTitle(),request.getContent()));
    }

    @DeleteMapping("/schedule/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id, @Validated @RequestBody ScheduleDeleteRequest request){
        scheduleService.delete(id,request.getTitle());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
