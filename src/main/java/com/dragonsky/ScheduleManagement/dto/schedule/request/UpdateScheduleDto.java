package com.dragonsky.ScheduleManagement.dto.schedule.request;

import lombok.Getter;

/**
 * 일정을 업데이트하기 위한 DTO 클래스
 */
@Getter
public class UpdateScheduleDto {

    /** 업데이트할 일정의 제목 */
    private String title;

    /** 업데이트할 일정의 내용 */
    private String content;

    /** 일정을 소유한 담당자명 */
    private String username;

    /** 일정 비밀번호 */
    private String password;
}