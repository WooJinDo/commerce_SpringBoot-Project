package com.zerobase.minicommerce.common.exception.customexception;

import com.zerobase.minicommerce.common.exception.type.MemberErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 Exception
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberException extends RuntimeException {

    private MemberErrorCode memberErrorCode;
    private String errorMessage;

    public MemberException(MemberErrorCode memberErrorCode) {
        this.memberErrorCode = memberErrorCode;
        this.errorMessage = memberErrorCode.getDescription();
    }

}
