package com.zerobase.minicommerce.common.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {

    USER_EMAIL_EXIST("해당 이메일을 가진 사용자가 존재합니다");

    private final String description;
}
