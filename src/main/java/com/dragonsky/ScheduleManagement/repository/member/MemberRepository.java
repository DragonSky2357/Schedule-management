package com.dragonsky.ScheduleManagement.repository.member;

import com.dragonsky.ScheduleManagement.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 담당자 JpaRepository 인터페이스
 */

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    /**
     * 담당자명에 해당하는 회원을 조회하는 메서드
     * @param username 조회할 담당자명
     * @return 담당자 엔터티(Optional)
     */
    Optional<Member> findByUsername(String username);
}
