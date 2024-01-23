package com.dragonsky.ScheduleManagement.service.schedule;

import com.dragonsky.ScheduleManagement.dto.schedule.request.CreateScheduleDto;
import com.dragonsky.ScheduleManagement.dto.schedule.request.DeleteScheduleDto;
import com.dragonsky.ScheduleManagement.dto.schedule.request.UpdateScheduleDto;
import com.dragonsky.ScheduleManagement.dto.schedule.response.FindScheduleResponseDto;
import com.dragonsky.ScheduleManagement.dto.schedule.response.UpdateScheduleResponseDto;
import com.dragonsky.ScheduleManagement.entity.member.Member;
import com.dragonsky.ScheduleManagement.entity.schedule.Schedule;
import com.dragonsky.ScheduleManagement.repository.member.MemberRepository;
import com.dragonsky.ScheduleManagement.repository.schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


/**
 * 일정 비즈니스 로직을 처리하는 서비스 클래스
 */

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    /**
     * 모든 일정을 조회하는 메서드
     * @return 일정 정보를 담은 DTO 리스트
     */
    public List<FindScheduleResponseDto> findSchedules() {
        return scheduleRepository.findAll(Sort.by(Sort.Direction.DESC,"createAt"))
                .stream()
                .map(FindScheduleResponseDto::new)
                .toList();
    }

    /**
     * 특정 일정을 조회하는 메서드
     * @param scheduleId 조회할 일정의 식별자
     * @return 조회된 일정 정보를 담은 DTO
     */
    public FindScheduleResponseDto findSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(()-> new IllegalStateException("존재 하지 않은 일정입니다."));

        return new FindScheduleResponseDto(schedule);
    }

    /**
     * 새로운 일정을 생성하는 메서드
     * @param createScheduleDto 생성할 일정 정보를 담은 DTO
     */
    public void createSchedule(CreateScheduleDto createScheduleDto) {
        Member member = memberRepository.findByUsername(createScheduleDto.getUsername())
                .orElseThrow(() -> new IllegalStateException("존재하지 않은 담당자입니다."));

        Schedule schedule = new Schedule(createScheduleDto, member);

        scheduleRepository.save(schedule);
    }

    /**
     * 특정 일정을 업데이트하는 메서드
     * @param scheduleId 업데이트할 일정의 식별자
     * @param updateScheduleDto 업데이트할 일정 정보를 담은 DTO
     * @return 업데이트된 일정 정보를 담은 DTO
     */
    @Transactional
    public UpdateScheduleResponseDto updateSchedule(Long scheduleId, UpdateScheduleDto updateScheduleDto){
        Member member = memberRepository.findByUsername(updateScheduleDto.getUsername())
                .orElseThrow(() -> new IllegalStateException("존재하지 않은 일정입니다."));


        Schedule schedule = scheduleRepository.findById(scheduleId)
                .filter(s -> s.getPassword().equals(updateScheduleDto.getPassword()))
                .orElseThrow(() -> new IllegalStateException("존재하지 않은 일정이거나 일정 비밀번호가 다릅니다."));

        schedule.update(updateScheduleDto, member);

        return new UpdateScheduleResponseDto(schedule);
    }

    /**
     * 특정 일정을 삭제하는 메서드
     * @param scheduleId 삭제할 일정의 식별자
     */
    public void deleteSchedule(Long scheduleId, DeleteScheduleDto deleteScheduleDto){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                        .filter(s -> s.getPassword().equals(deleteScheduleDto.getPassword()))
                                .orElseThrow(()-> new IllegalStateException("존재하지 않은 일정이거나 일정 비밀번호가 다릅니다."));

        scheduleRepository.delete(schedule);
    }
}
