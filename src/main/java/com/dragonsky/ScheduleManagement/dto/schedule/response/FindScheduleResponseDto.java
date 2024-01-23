package com.dragonsky.ScheduleManagement.dto.schedule.response;

import com.dragonsky.ScheduleManagement.entity.schedule.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;


/**
 * 일정 조회 응답을 위한 DTO 클래스
 */
@Getter
public class FindScheduleResponseDto {

    /** 일정 제목 */
    private String title;

    /** 일정 내용 */
    private String content;

    /** 일정 생성 시간 */
    private LocalDateTime createAt;

    /**
     * FindScheduleResponseDto 생성자
     * @param schedule 일정 엔터티
     */
    public FindScheduleResponseDto(Schedule schedule) {
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createAt = schedule.getCreateAt();
    }
}
