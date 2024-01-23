package com.dragonsky.ScheduleManagement.dto.member.request;

import lombok.Getter;

/**
 * 담당자 정보 업데이트에 사용되는 DTO
 */
@Getter
public class UpdateMemberDto {
    private String username;
    private String password;

    /**
     * UpdateMemberDto 생성자
     * @param username 업데이트할 담당자의 사용자명
     * @param password 업데이트할 담당자의 비밀번호
     */
    public UpdateMemberDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
