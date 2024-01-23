package com.dragonsky.ScheduleManagement.entity.schedule;

import com.dragonsky.ScheduleManagement.dto.schedule.request.CreateScheduleDto;
import com.dragonsky.ScheduleManagement.dto.schedule.request.UpdateScheduleDto;
import com.dragonsky.ScheduleManagement.entity.Timestamped;
import com.dragonsky.ScheduleManagement.entity.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 일정 정보를 나타내는 엔터티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "schedule")
public class Schedule extends Timestamped {

    /** 일정 식별자 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 일정 제목 */
    @Column(name="title", nullable = false)
    private String title;

    /** 일정 내용 */
    @Column(name="content", nullable = false)
    private String content;

    /** 일정을 업데이트하기 위한 비밀번호 */
    @Column(name="password", nullable = false)

    private String password;

    /** 일정을 소유한 담당자 정보  fetch = FetchType.LAZY -> N+1 해결*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    /**
     * Schedule 엔터티의 생성자입니다.
     * @param createScheduleDto 새로운 일정을 생성하기 위한 DTO
     * @param member 일정을 소유한 담당자 정보
     */
    public Schedule(CreateScheduleDto createScheduleDto, Member member) {
        this.title = createScheduleDto.getTitle();
        this.content = createScheduleDto.getContent();
        this.password = createScheduleDto.getPassword();
        this.member = member;
        member.getSchedules().add(this);
    }

    /**
     * 일정 정보를 업데이트하는 메서드입니다.
     * @param updateScheduleDto 업데이트할 일정 정보를 담은 DTO
     * @param member 업데이트를 수행하는 담당자 정보
     */
    public void update(UpdateScheduleDto updateScheduleDto, Member member) {
        this.title = updateScheduleDto.getTitle().isEmpty() ? this.title : updateScheduleDto.getTitle();
        this.content = updateScheduleDto.getContent().isEmpty() ? this.getContent() : updateScheduleDto.getContent();

        // 담당자 정보가 주어진 경우에만 회원 정보 업데이트
        if (member != null) {
            // 현재 일정을 이전 소유 담당자 일정 목록에서 제거
            this.member.getSchedules().remove(this);
            // 새로운 회원의 일정 목록에 추가
            member.getSchedules().add(this);
            // 현재 일정 담당자 변경
            this.member = member;
        }
    }
}