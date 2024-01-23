package com.dragonsky.ScheduleManagement.controller.member;

import com.dragonsky.ScheduleManagement.dto.common.ResponseDto;
import com.dragonsky.ScheduleManagement.dto.member.request.CreateMemberDto;
import com.dragonsky.ScheduleManagement.dto.member.request.UpdateMemberDto;
import com.dragonsky.ScheduleManagement.dto.member.response.FindMemberResponseDto;
import com.dragonsky.ScheduleManagement.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 작성자 HTTP 요청을 처리하는 컨트롤러 클래스입니다.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;
    // 모든 작성자 조회
    @GetMapping("/members")
    public ResponseEntity findMembers(){
        try{
            List<FindMemberResponseDto> result = memberService.findMembers();
            return ResponseEntity.ok(ResponseDto.success(HttpStatus.OK.value(),result));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value())
                    .body(ResponseDto.fail(HttpStatus.FORBIDDEN.value(),e.getMessage()));
        }
    }
    // 특정 작성자 조회
    @GetMapping("/members/{id}")
    public ResponseEntity findMember(@PathVariable Long id){
        try{
            FindMemberResponseDto result = memberService.findMember(id);
            return ResponseEntity.ok(ResponseDto.success(HttpStatus.OK.value(),result));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value())
                    .body(ResponseDto.fail(HttpStatus.FORBIDDEN.value(),e.getMessage()));
        }

    }

    // 작성자 생성
    @PostMapping("/members")
    public ResponseEntity createMember(@RequestBody CreateMemberDto createMemberDto){
        try{
            memberService.createMember(createMemberDto);
            return ResponseEntity.ok(ResponseDto.success(HttpStatus.CREATED.value()));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value())
                    .body(ResponseDto.fail(HttpStatus.FORBIDDEN.value(),e.getMessage()));
        }
    }

    // 특정 작성자 수정
    @PatchMapping("/members/{id}")
    public ResponseEntity updateMember(@PathVariable Long id, @RequestBody UpdateMemberDto updateMemberDto){
        try{
            memberService.updateMember(id,updateMemberDto);
            return ResponseEntity.ok(ResponseDto.success(HttpStatus.OK.value()));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value())
                    .body(ResponseDto.fail(HttpStatus.FORBIDDEN.value(),e.getMessage()));
        }
    }

    // 특정 작성자 삭제
    @DeleteMapping("/members/{id}")
    public ResponseEntity deleteMember(@PathVariable Long id){
        try{
            memberService.deleteMember(id);
            return ResponseEntity.ok(ResponseDto.success(HttpStatus.OK.value()));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value())
                    .body(ResponseDto.fail(HttpStatus.FORBIDDEN.value(),e.getMessage()));
        }
    }
}
