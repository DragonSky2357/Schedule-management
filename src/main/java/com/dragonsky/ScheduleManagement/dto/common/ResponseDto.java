package com.dragonsky.ScheduleManagement.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * HTTP 응답을 표현하는 DTO 클래스
 * @param <T> 응답 데이터의 타입을 나타냅니다.
 */
@Getter
@AllArgsConstructor
public class ResponseDto<T>{
    /** HTTP 응답 상태 코드 */
    private int status;
    /** 응답 메시지 */
    private String message;
    /** 응답 데이터 */
    private T data;

    /**
     * 성공 응답을 생성 함수
     * @param <T> 응답 데이터의 타입
     * @param status 응답 상태 코드
     * @return ResponseDto
     */
    public static <T> ResponseDto<T> success(int status){
        return new ResponseDto<>(status,null,null);
    }

    /**
     * 성공 응답을 생성
     * @param <T> 응답 데이터의 타입
     * @param status 응답 상태 코드
     * @param data 응답 데이터
     * @return ResponseDto
     */
    public static <T> ResponseDto<T> success(int status, T data ){
        return new ResponseDto<>(status,null,data);
    }

    /**
     * 실패 응답을 생성
     * @param <T> 응답 데이터의 타입
     * @param status 응답 상태 코드
     * @param message 실패 메시지
     * @return ResponseDto
     */
    public static <T> ResponseDto<T> fail(int status, String message){
        return new ResponseDto<>(status,message,null);
    }
}
