package com.zerobase.minicommerce.common.exception;

import com.zerobase.minicommerce.common.exception.customexception.CartException;
import com.zerobase.minicommerce.common.exception.customexception.MemberException;
import com.zerobase.minicommerce.common.exception.model.CartErrorResponse;
import com.zerobase.minicommerce.common.exception.model.FileErrorResponse;
import com.zerobase.minicommerce.common.exception.model.MemberErrorResponse;
import com.zerobase.minicommerce.common.exception.model.ValidErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /*
     * 회원 에러 핸들러 - error 반환
     * */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MemberException.class)
    public MemberErrorResponse handleMemberException(MemberException e) {
        log.error("{} is occurred.", e.getMemberErrorCode());
        return new MemberErrorResponse(e.getMemberErrorCode(), e.getErrorMessage());
    }

    /*
     * 장바구니 에러 핸들러 - error 반환
     * */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CartException.class)
    public CartErrorResponse handleMemberException(CartException e) {
        log.error("{} is occurred.", e.getCartErrorCode());
        return new CartErrorResponse(e.getCartErrorCode(), e.getErrorMessage());
    }

    /**
     * DTO @Valid 유효성 검사 실패 시
     * */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidErrorResponse methodValidException(MethodArgumentNotValidException e, HttpServletRequest request) {

        log.error("methodValidException is occurred. url: {}", request.getRequestURI());

        BindingResult bindingResult = e.getBindingResult();
        // DTO 에 설정한 message 값을 가져온다
        String errorMessage = bindingResult.hasErrors() ? bindingResult.getFieldError().getDefaultMessage() : "";
        String errorCode = "Parameter Validate Fail";

        return new ValidErrorResponse(errorCode, errorMessage);
    }

    /**
     * 파일 업로드 - 선택하지 않고 등록을 시도할 때 발생하는 오류 처리
     * */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestPartException.class)
    public FileErrorResponse missingServletRequestPartException(MissingServletRequestPartException e, HttpServletRequest request) {

        log.error("missingServletRequestPartException is occurred. url: {}", request.getRequestURI());

        String errorMessage = "파일을 선택해 주세요.";
        String errorCode = "Please select a file to upload";

        return new FileErrorResponse(errorCode, errorMessage);
    }

}
