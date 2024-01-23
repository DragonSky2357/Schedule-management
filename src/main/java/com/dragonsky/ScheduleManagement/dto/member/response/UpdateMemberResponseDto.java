package com.dragonsky.ScheduleManagement.dto.member.response;

import com.dragonsky.ScheduleManagement.entity.member.Member;
import lombok.Getter;

/**
 * 담당자 업데이트 DTO 클래스
 */
@Getter
public class UpdateMemberResponseDto {
    /** 담당자명 */
    private String username;
    public UpdateMemberResponseDto(Member member) {
        this.username = member.getUsername();
    }
}
