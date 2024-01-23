package com.dragonsky.ScheduleManagement.dto.member.request;

import com.dragonsky.ScheduleManagement.entity.member.Member;
import lombok.Getter;

/**
 * 담당자 생성에 사용되는 DTO
 */
@Getter
public class CreateMemberDto {
    /** 담당자 사용자명 */
    private String username;
    /** 담당자 비밀번호 */
    private String password;

    /**
     * CreateMemberDto 생성자
     * Controller
     * @param username 담당자 사용자명
     * @param password 담당자 비밀번호
     */
    public CreateMemberDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
