package com.dragonsky.ScheduleManagement.service.member;

import com.dragonsky.ScheduleManagement.dto.member.request.CreateMemberDto;
import com.dragonsky.ScheduleManagement.dto.member.request.UpdateMemberDto;
import com.dragonsky.ScheduleManagement.dto.member.response.FindMemberResponseDto;
import com.dragonsky.ScheduleManagement.dto.member.response.UpdateMemberResponseDto;
import com.dragonsky.ScheduleManagement.entity.member.Member;
import com.dragonsky.ScheduleManagement.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 담당자 서비스 로직 클래스입니다.
 */
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 모든 담당자를 조회 및 FindMemberResponseDto 형태로 반환하는 메서드
     * @return 모든 담당자 목록 (FindMemberResponseDto)
     */
    public List<FindMemberResponseDto>findMembers(){
        List<Member> findMembers = memberRepository.findAll();

        return findMembers.stream()
                .map(FindMemberResponseDto::new)
                .toList();
    }

    /**
     * 특정 담당자를 조회하여 FindMemberResponseDto로 반환하는 메서드
     * @param memberId 조회할 담당자 식별자
     * @return 특정 회원 (FindMemberResponseDto)
     * @throws IllegalStateException 해당 식별자의 담당자가 존재하지 않을 때 예외 발생
     */
    public FindMemberResponseDto findMember(Long memberId){
        return memberRepository.findById(memberId)
                .map(FindMemberResponseDto::new)
                .orElseThrow(()-> new IllegalStateException("존재 하지 않은 담당자입니다."));
    }


    /**
     * 담당자 생성하는 메서드
     * @param createMemberDto 생성할 담당자 정보를 담은 DTO
     * @throws IllegalStateException 이미 존재하는 담당자일 경우 예외 발생
     */
    public void createMember(CreateMemberDto createMemberDto) {
        memberRepository.findByUsername(createMemberDto.getUsername())
                .ifPresent(existingMember -> {
                    throw new IllegalStateException("이미 존재하는 담당자입니다.");
                });

        Member member = new Member(createMemberDto);
        memberRepository.save(member);
    }

    /**
     * 특정 담당자 정보를 업데이트하고, 업데이트된 정보를 UpdateMemberResponseDto로 반환하는 메서드
     * @param memberId 업데이트할 담당자 식별자
     * @param updateMemberDto 업데이트할 담당자 정보를 담은 DTO
     * @return 업데이트된 담당자 정보
     * @throws IllegalStateException 해당 식별자의 담당자가 존재하지 않을 때 예외 발생
     */
    @Transactional
    public UpdateMemberResponseDto updateMember(Long memberId, UpdateMemberDto updateMemberDto){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalStateException("존재하지 않은 담당자입니다."));

        member.update(updateMemberDto);
        return new UpdateMemberResponseDto(member);
    }

    /**
     * 특정 담당자를 삭제하는 메서드
     * @param memberId 삭제할 담당자 식별자
     * @throws IllegalStateException 해당 식별자의 담당자가 존재하지 않을 때 예외 발생
     */
    public void deleteMember(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalStateException("존재하지 않은 담당자입니다."));

        memberRepository.delete(member);
    }
}
