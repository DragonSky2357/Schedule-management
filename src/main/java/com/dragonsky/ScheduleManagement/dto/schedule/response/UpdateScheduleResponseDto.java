package com.dragonsky.ScheduleManagement.dto.schedule.response;

import com.dragonsky.ScheduleManagement.entity.member.Member;
import com.dragonsky.ScheduleManagement.entity.schedule.Schedule;
import lombok.Getter;

/**
 * 일정 업데이트 응답을 위한 DTO 클래스
 */
@Getter
public class UpdateScheduleResponseDto {

    /** 일정의 제목 */
    private String title;

    /** 일정의 내용 */
    private String content;

    /** 일정을 작성한 담당자 */
    private Member member;

    /**
     * UpdateScheduleResponseDto의 생성자
     * @param schedule 일정 엔터티
     */
    public UpdateScheduleResponseDto(Schedule schedule) {
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.member = schedule.getMember();
    }
}