package com.dragonsky.ScheduleManagement.repository.schedule;

import com.dragonsky.ScheduleManagement.entity.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 일정 JpaRepository 인터페이스
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
