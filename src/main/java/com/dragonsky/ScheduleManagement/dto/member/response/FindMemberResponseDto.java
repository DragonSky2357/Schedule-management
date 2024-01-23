package com.dragonsky.ScheduleManagement.dto.member.response;

import com.dragonsky.ScheduleManagement.entity.member.Member;
import com.dragonsky.ScheduleManagement.entity.schedule.Schedule;
import lombok.Getter;

import java.util.List;

/**
 * 담당자 조회 응답에 사용되는 DTO 클래스입니다.
 */
@Getter
public class FindMemberResponseDto {
    /** 담당자명 */
    private String username;
    /** 담당자 일정 목록 */
    private List<Schedule> schedules;
    public FindMemberResponseDto(Member member) {
        this.username = member.getUsername();
        this.schedules = member.getSchedules();
    }
}
