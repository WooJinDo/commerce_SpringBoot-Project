package com.zerobase.minicommerce.common.exception.model;

import com.zerobase.minicommerce.common.exception.type.MemberErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 *  회원 서비스 에러 공통 응답 객체
 * */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberErrorResponse {

    private MemberErrorCode memberErrorCode;
    private String errorMessage;
}
