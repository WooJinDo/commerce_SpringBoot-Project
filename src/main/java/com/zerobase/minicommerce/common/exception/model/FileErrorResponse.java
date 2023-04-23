package com.zerobase.minicommerce.common.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *   File error 공통 응답 객체
 * */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileErrorResponse {

    private String errorCode;
    private String errorMessage;

}
