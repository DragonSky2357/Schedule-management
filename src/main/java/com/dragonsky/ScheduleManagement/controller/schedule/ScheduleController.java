package com.dragonsky.ScheduleManagement.controller.schedule;

import com.dragonsky.ScheduleManagement.dto.common.ResponseDto;
import com.dragonsky.ScheduleManagement.dto.schedule.request.DeleteScheduleDto;
import com.dragonsky.ScheduleManagement.dto.schedule.request.UpdateScheduleDto;
import com.dragonsky.ScheduleManagement.dto.schedule.request.CreateScheduleDto;
import com.dragonsky.ScheduleManagement.dto.schedule.response.FindScheduleResponseDto;
import com.dragonsky.ScheduleManagement.dto.schedule.response.UpdateScheduleResponseDto;
import com.dragonsky.ScheduleManagement.service.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 일정 HTTP 요청을 처리하는 컨트롤러 클래스입니다.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 모든 일정 조회
    @GetMapping("/schedules")
    public ResponseEntity findSchedules() {
        try {
            List<FindScheduleResponseDto> result = scheduleService.findSchedules();
            return ResponseEntity.ok(ResponseDto.success(HttpStatus.OK.value(), result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value())
                    .body(ResponseDto.fail(HttpStatus.FORBIDDEN.value(), e.getMessage()));
        }
    }

    // 특정 일정 조회
    @GetMapping("/schedules/{id}")
    public ResponseEntity findSchedule(@PathVariable Long id) {
        try {
            FindScheduleResponseDto result = scheduleService.findSchedule(id);
            return ResponseEntity.ok(ResponseDto.success(HttpStatus.OK.value(), result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value())
                    .body(ResponseDto.fail(HttpStatus.FORBIDDEN.value(), e.getMessage()));
        }
    }

    // 일정 생성
    @PostMapping("/schedules")
    public ResponseEntity createSchedule(@RequestBody CreateScheduleDto createScheduleDto) {
        try {
            scheduleService.createSchedule(createScheduleDto);
            return ResponseEntity.ok(ResponseDto.success(HttpStatus.CREATED.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value())
                    .body(ResponseDto.fail(HttpStatus.FORBIDDEN.value(), e.getMessage()));
        }
    }

    // 특정 일정 수정
    @PatchMapping("/schedules/{id}")
    public ResponseEntity updateSchedule(@PathVariable Long id, @RequestBody UpdateScheduleDto updateScheduleDto) {
        try {
            UpdateScheduleResponseDto result = scheduleService.updateSchedule(id, updateScheduleDto);
            return ResponseEntity.ok(ResponseDto.success(HttpStatus.OK.value(),result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value())
                    .body(ResponseDto.fail(HttpStatus.FORBIDDEN.value(), e.getMessage()));
        }
    }

    // 특정 일정 삭제
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity deleteSchedule(@PathVariable Long id, @RequestBody DeleteScheduleDto deleteScheduleDto){
        try {
            scheduleService.deleteSchedule(id,deleteScheduleDto);
            return ResponseEntity.ok(ResponseDto.success(HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value())
                    .body(ResponseDto.fail(HttpStatus.FORBIDDEN.value(), e.getMessage()));
        }
    }
}
