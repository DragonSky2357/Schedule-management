package com.dragonsky.ScheduleManagement.entity.member;

import com.dragonsky.ScheduleManagement.dto.member.request.CreateMemberDto;
import com.dragonsky.ScheduleManagement.dto.member.request.UpdateMemberDto;
import com.dragonsky.ScheduleManagement.entity.Timestamped;
import com.dragonsky.ScheduleManagement.entity.schedule.Schedule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 담당자 엔터티 클래스
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member") // 매핑할 테이블의 이름을 지정
public class Member extends Timestamped {
    /** 담당자 식별자 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 담당자명 **/
    @Column(name="username",nullable = false,unique = true)
    private String username;

    /** 담당자 비밀번호 (JSON 직렬화에서 무시 => password 응답 무시) */
    @Column(name="password",nullable = false)
    @JsonIgnore
    private String password;

    /** 담당자 일정 목록 (JsonIgnore 참조 순환 문제) */
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Schedule> schedules = new ArrayList<>();

    /**
     * CreateMemberDto 생성자
     * @param createMemberDto 작성자 생성 정보를 담은 DTO
     */
    public Member(CreateMemberDto createMemberDto) {
        this.username = createMemberDto.getUsername();
        this.password = createMemberDto.getPassword();
    }

    /**
     * UpdateMemberDto 담당자 정보 업데이트 메서드
     * @param updateMemberDto 업데이트할 담당자 정보를 담은 DTO
     */
    public void update(UpdateMemberDto updateMemberDto){
        this.username = updateMemberDto.getUsername();
        this.password = updateMemberDto.getPassword();
    }
}
