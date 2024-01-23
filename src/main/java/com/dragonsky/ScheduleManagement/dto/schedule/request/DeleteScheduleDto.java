package com.dragonsky.ScheduleManagement.dto.schedule.request;

import lombok.Getter;

/**
 * 일정을 삭제하기 위한 DTO 클래스
 */
@Getter
public class DeleteScheduleDto {
    /** 일정 비밀번호 */
    private String password;
}
